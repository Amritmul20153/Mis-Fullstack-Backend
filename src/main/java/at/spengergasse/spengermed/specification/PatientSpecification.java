package at.spengergasse.spengermed.specification;

import at.spengergasse.spengermed.model.*;

import at.spengergasse.spengermed.model.Patient;
import at.spengergasse.spengermed.model.Address;
import at.spengergasse.spengermed.model.ContactPoint;
import at.spengergasse.spengermed.model.HumanName;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class PatientSpecification {

    public static Specification<Patient> buildSpecification(Map<String, String> params) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            for (var entry : params.entrySet()) {
                String key = entry.getKey();
                String rawValue = entry.getValue();

                switch (key) {

                    // ────────────── BOOLEAN ──────────────
                    case "active" ->
                            predicates.add(cb.equal(root.get("active"), Boolean.valueOf(rawValue)));

                    case "deceased" ->
                            predicates.add(cb.equal(root.get("deceasedBoolean"), Boolean.valueOf(rawValue)));


                    // ────────────── TOKEN ──────────────
                    case "gender" ->
                            predicates.add(cb.equal(root.get("gender"), Patient.GenderCode.valueOf(rawValue)));


                    // ────────────── DATE ──────────────
                    case "birthdate" ->
                            predicates.add(buildDatePredicate(cb, root.get("birthDate"), rawValue));

                    case "deceased-date" ->
                            predicates.add(buildDateTimePredicate(cb, root.get("deceasedDateTime"), rawValue));


                    // ────────────── NAME SEARCH ──────────────
                    case "family" ->
                            predicates.add(buildNamePredicate(root, cb, "family", rawValue));

                    case "given" ->
                            predicates.add(buildNamePredicate(root, cb, "given", rawValue));

                    case "name" ->
                            predicates.add(buildNameFullText(root, cb, rawValue));


                    // ────────────── ADDRESS SEARCH ──────────────
                    case "address" ->
                            predicates.add(buildAddressFullText(root, cb, rawValue));

                    case "address-city" ->
                            predicates.add(buildAddressPredicate(root, cb, "city", rawValue));

                    case "address-state" ->
                            predicates.add(buildAddressPredicate(root, cb, "state", rawValue));

                    case "address-postalcode" ->
                            predicates.add(buildAddressPredicate(root, cb, "postalCode", rawValue));

                    case "address-country" ->
                            predicates.add(buildAddressPredicate(root, cb, "country", rawValue));


                    // ────────────── TELECOM ──────────────
                    case "phone" ->
                            predicates.add(buildTelecomPredicate(root, cb, "phone", rawValue));

                    case "email" ->
                            predicates.add(buildTelecomPredicate(root, cb, "email", rawValue));

                    case "telecom" ->
                            predicates.add(buildTelecomAnyPredicate(root, cb, rawValue));


                    default -> { /* ignore unknown parameters */ }
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    // ─────────────────────────────────────────────────────────────
    // NAME
    // ─────────────────────────────────────────────────────────────
    private static Predicate buildNamePredicate(Root<Patient> root, CriteriaBuilder cb,
                                                String field, String value) {
        Join<Patient, HumanName> join = root.join("name", JoinType.LEFT);
        String[] parts = value.split(",");

        List<Predicate> orPred = new ArrayList<>();
        for (String p : parts) {
            orPred.add(cb.like(cb.lower(join.get(field)), "%" + p.toLowerCase() + "%"));
        }
        return cb.or(orPred.toArray(new Predicate[0]));
    }

    private static Predicate buildNameFullText(Root<Patient> root, CriteriaBuilder cb,
                                               String value) {
        Join<Patient, HumanName> join = root.join("name", JoinType.LEFT);
        String v = "%" + value.toLowerCase() + "%";

        return cb.or(
                cb.like(cb.lower(join.get("family")), v),
                cb.like(cb.lower(join.get("given")), v),
                cb.like(cb.lower(join.get("text")), v)
        );
    }

    // ─────────────────────────────────────────────────────────────
    // ADDRESS
    // ─────────────────────────────────────────────────────────────
    private static Predicate buildAddressPredicate(Root<Patient> root, CriteriaBuilder cb,
                                                   String field, String value) {
        Join<Patient, Address> join = root.join("address", JoinType.LEFT);
        String[] parts = value.split(",");

        List<Predicate> orPred = new ArrayList<>();
        for (String p : parts) {
            orPred.add(cb.like(cb.lower(join.get(field)), "%" + p.toLowerCase() + "%"));
        }
        return cb.or(orPred.toArray(new Predicate[0]));
    }

    private static Predicate buildAddressFullText(Root<Patient> root, CriteriaBuilder cb,
                                                  String value) {
        Join<Patient, Address> join = root.join("address", JoinType.LEFT);
        String v = "%" + value.toLowerCase() + "%";

        return cb.or(
                cb.like(cb.lower(join.get("city")), v),
                cb.like(cb.lower(join.get("state")), v),
                cb.like(cb.lower(join.get("country")), v),
                cb.like(cb.lower(join.get("district")), v),
                cb.like(cb.lower(join.get("line")), v),
                cb.like(cb.lower(join.get("postalCode")), v)
        );
    }

    // ─────────────────────────────────────────────────────────────
    // TELECOM
    // ─────────────────────────────────────────────────────────────
    private static Predicate buildTelecomPredicate(Root<Patient> root, CriteriaBuilder cb,
                                                   String system, String value) {
        Join<Patient, ContactPoint> join = root.join("telecom", JoinType.LEFT);
        return cb.and(
                cb.equal(join.get("system"), system),
                cb.like(cb.lower(join.get("value")), "%" + value.toLowerCase() + "%")
        );
    }

    private static Predicate buildTelecomAnyPredicate(Root<Patient> root, CriteriaBuilder cb,
                                                      String value) {
        Join<Patient, ContactPoint> join = root.join("telecom", JoinType.LEFT);
        return cb.like(cb.lower(join.get("value")), "%" + value.toLowerCase() + "%");
    }

    // ─────────────────────────────────────────────────────────────
    // DATE / DATETIME
    // ─────────────────────────────────────────────────────────────
    private static Predicate buildDatePredicate(
            CriteriaBuilder cb, Path<LocalDate> field, String value) {

        if (value.startsWith("gt"))
            return cb.greaterThan(field, LocalDate.parse(value.substring(2)));

        if (value.startsWith("lt"))
            return cb.lessThan(field, LocalDate.parse(value.substring(2)));

        return cb.equal(field, LocalDate.parse(value));
    }

    private static Predicate buildDateTimePredicate(
            CriteriaBuilder cb, Path<LocalDateTime> field, String value) {

        if (value.startsWith("gt"))
            return cb.greaterThan(field, LocalDateTime.parse(value.substring(2)));

        if (value.startsWith("lt"))
            return cb.lessThan(field, LocalDateTime.parse(value.substring(2)));

        return cb.equal(field, LocalDateTime.parse(value));
    }
}