package com.alejandro.storewebpage.web.response;

import java.util.List;

public class ListResponse<T> extends PaginationResponse{

    private List<T> data;
    private boolean success;

    public ListResponse(List<T> posts,
                        Integer numberPage,
                        Integer totalPage,
                        boolean next,
                        boolean previous) {
        super(numberPage, totalPage, next, previous);

        this.setData(posts);
        this.setSuccess();
    }

    public static <T> ListResponse<T> createResponse(List<T> pages,
                                                  Integer numberPage,
                                                  Integer totalPage,
                                                  boolean next,
                                                  boolean previous) {
        return new ListResponse<T>(pages,numberPage,totalPage,next,previous);
    }

    public List<T> getData() {
        return data;
    }

    private void setData(List<T> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess() {
        this.success = true;
    }
}
