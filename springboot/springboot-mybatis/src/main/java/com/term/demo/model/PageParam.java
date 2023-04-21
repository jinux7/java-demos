package com.term.demo.model;

public class PageParam {
    private int pageNum;

    private int pageSize;

    private  int start;

    private  int end;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
