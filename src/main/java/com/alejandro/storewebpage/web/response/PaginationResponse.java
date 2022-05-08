package com.alejandro.storewebpage.web.response;

import java.io.Serializable;

public class PaginationResponse implements Serializable {

    private final Integer numberPage;
    private final Integer totalPages;
    private final boolean next;
    private final boolean previous;

    protected PaginationResponse(Integer numberPage, Integer totalPages, boolean next, boolean previous) {
        this.numberPage = numberPage;
        this.totalPages = totalPages;
        this.next = next;
        this.previous = previous;
    }

    public Integer getNumberPage() {
        return numberPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public boolean isNext() {
        return next;
    }

    public boolean isPrevious() {
        return previous;
    }
}
