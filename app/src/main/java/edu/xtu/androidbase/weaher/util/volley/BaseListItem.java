package edu.xtu.androidbase.weaher.util.volley;

/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class BaseListItem extends BaseItem{

    // 当前数据分页索引，从1开始
    private int pageIndex;

    // 每页数据条数
    private int pageSize;

    // 总的条数
    private int total;

    // 本次返回数据条数
    private int size;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isLastPage() {
        return size < pageSize || size + (pageIndex -1) * pageSize >= total;
    }
}
