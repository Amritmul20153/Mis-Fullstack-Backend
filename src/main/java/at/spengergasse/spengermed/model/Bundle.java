package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bundle {

    private String resourceType = "Bundle";
    private BundleType type = BundleType.searchset;
    private Integer total;
    private List<Link> link = new ArrayList<>();
    private List<Entry> entry = new ArrayList<>();

    public enum BundleType {
        searchset
    }

    @Data
    public static class Link {
        private String relation;
        private String url;

        public Link(String relation, String url) {
            this.relation = relation;
            this.url = url;
        }
    }

    @Data
    public static class Entry {
        private String fullUrl;
        private Object resource;

        public Entry(String fullUrl, Object resource) {
            this.fullUrl = fullUrl;
            this.resource = resource;
        }
    }
}
