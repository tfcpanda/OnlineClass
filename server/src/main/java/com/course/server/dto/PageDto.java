package com.course.server.dto;

import java.util.List;

/**
 * @author 田付成
 * @date 2021/3/16 1:35
 */
public class PageDto<T> {
    /**
     * 当前页码，前端给的。
     */
    protected int page;

    /**
     * 每页条数，前端给的
     */
    protected int size;

    /**
     * 总条数，后端查询到的
     */
    protected long total;

    /**
     * 查询到的记录放在了这个list里面，大章，小节，管理员。
     * 后端查询到的。
     */
    protected List<T> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "page=" + page +
                ", size=" + size +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
