package at.spengergasse.spengermed.util;

public class Pagination {
    private int page;   // aktuelle Seite
    private int count;  // Anzahl Elemente pro Seite

    public Pagination() {
        this.page = 0;    // default 0
        this.count = 10;  // default 10
    }

    public Pagination(int page, int count) {
        this.page = page;
        this.count = count;
    }

    // Getter & Setter
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // Convenience: berechne Offset für DB
    public int getOffset() {
        return page * count;
    }
}
