package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Bundle;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public abstract class AbstractSearchController<T> {

    protected Bundle searchInternal(
            Map<String, String> params,
            HttpServletRequest request,
            Specification<T> spec,
            Page<T> page
    ) {

        int count = parse(params.remove("_count"), 10);
        int pageNr = parse(params.remove("_page"), 1);

        Bundle bundle = new Bundle();
        bundle.setTotal((int) page.getTotalElements());

        String baseUrl = request.getRequestURL().toString();

        bundle.getLink().add(new Bundle.Link(
                "self", buildUrl(baseUrl, params, pageNr, count)));

        if (page.hasNext()) {
            bundle.getLink().add(new Bundle.Link(
                    "next", buildUrl(baseUrl, params, pageNr + 1, count)));
        }

        if (page.hasPrevious()) {
            bundle.getLink().add(new Bundle.Link(
                    "previous", buildUrl(baseUrl, params, pageNr - 1, count)));
        }

        page.getContent().forEach(e ->
                bundle.getEntry().add(new Bundle.Entry(baseUrl + "/" + getId(e), e)));

        return bundle;
    }

    protected abstract String getId(T entity);

    private int parse(String raw, int fallback) {
        try {
            return raw == null ? fallback : Integer.parseInt(raw);
        } catch (Exception e) {
            return fallback;
        }
    }

    private String buildUrl(String base, Map<String, String> params, int page, int count) {
        StringBuilder sb = new StringBuilder(base).append("?");
        params.forEach((k, v) -> sb.append(k).append("=").append(v).append("&"));
        sb.append("_page=").append(page).append("&_count=").append(count);
        return sb.toString();
    }
}
