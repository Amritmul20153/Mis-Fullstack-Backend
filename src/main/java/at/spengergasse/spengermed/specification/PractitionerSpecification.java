package at.spengergasse.spengermed.specification;

import at.spengergasse.spengermed.model.Practitioner;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PractitionerSpecification {

    public static Specification<Practitioner> build(Map<String, String> params) {
        return (root, query, cb) -> {

            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

            params.forEach((k, v) -> {
                switch (k) {
                    case "active" -> predicates.add(cb.equal(root.get("active"), Boolean.valueOf(v)));
                    case "gender" -> predicates.add(cb.equal(root.get("gender"), v));
                    case "family" -> predicates.add(cb.like(cb.lower(root.get("family")), "%" + v.toLowerCase() + "%"));
                    case "given" -> predicates.add(cb.like(cb.lower(root.get("given")), "%" + v.toLowerCase() + "%"));
                    case "name" -> predicates.add(cb.or(
                            cb.like(cb.lower(root.get("family")), "%" + v.toLowerCase() + "%"),
                            cb.like(cb.lower(root.get("given")), "%" + v.toLowerCase() + "%")
                    ));
                    case "email" -> predicates.add(cb.like(cb.lower(root.get("email")), "%" + v.toLowerCase() + "%"));
                    case "phone" -> predicates.add(cb.like(cb.lower(root.get("phone")), "%" + v.toLowerCase() + "%"));
                    case "identifier" -> predicates.add(cb.like(root.get("identifier"), "%" + v + "%"));
                }
            });

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}