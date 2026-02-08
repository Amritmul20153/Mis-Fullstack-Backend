package at.spengergasse.spengermed.specification;

import at.spengergasse.spengermed.model.Observation;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObservationSpecification {

    public static Specification<Observation> buildSpecification(Map<String, String> params) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            for (var entry : params.entrySet()) {
                String key = entry.getKey();
                String rawValue = entry.getValue();

                switch (key) {

                    // ────────────── STATUS ──────────────
                    case "status" ->
                            predicates.add(cb.equal(root.get("status"), Observation.Code.valueOf(rawValue)));

                    // ────────────── CODE ──────────────
                    case "code" ->
                            predicates.add(cb.like(cb.lower(root.get("code").get("text")), "%" + rawValue.toLowerCase() + "%"));

                    // ────────────── SUBJECT ──────────────
                    case "subject" ->
                            predicates.add(cb.like(cb.lower(root.get("subject").get("reference")), "%" + rawValue.toLowerCase() + "%"));

                    // ────────────── EFFECTIVE DATE ──────────────
                    case "effectiveDateTime" ->
                            predicates.add(buildDatePredicate(cb, root.get("effectiveDateTime"), rawValue));

                    // ────────────── ISSUED DATE ──────────────
                    case "issued" ->
                            predicates.add(buildDatePredicate(cb, root.get("issued"), rawValue));

                    // ────────────── CATEGORY ──────────────
                    case "category" ->
                            predicates.add(cb.like(cb.lower(root.join("category").get("text")), "%" + rawValue.toLowerCase() + "%"));

                    default -> { /* Unbekannte Parameter ignorieren */ }
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    // ─────────────────────────────────────────────────────────────
    // DATE / DATETIME
    // ─────────────────────────────────────────────────────────────
    private static Predicate buildDatePredicate(
            CriteriaBuilder cb, Path<LocalDateTime> field, String value) {

        if (value.startsWith("gt"))
            return cb.greaterThan(field, LocalDateTime.parse(value.substring(2)));

        if (value.startsWith("lt"))
            return cb.lessThan(field, LocalDateTime.parse(value.substring(2)));

        return cb.equal(field, LocalDateTime.parse(value));
    }
}