package com.test.swaggerandparam;


import java.util.List;

/**
 *@ClassName OfflineDataPageResp
 *@Description 分页查询离线数据的返回封装类
 *@Author wangyongtao
 *@Date 2020/7/1 18:03
 *@Version 1.0
 */
public class OfflineDataPageResp {
    public long total;
    public int pageNo;
    public int pageSize;
    public List<?> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
