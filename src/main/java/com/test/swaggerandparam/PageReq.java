package com.test.swaggerandparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *@ClassName PageReq
 *@Description 页面请求封装类
 *@Author wangyongtao
 *@Date 2020/7/1 16:59
 *@Version 1.0
 */
@ApiModel
public class PageReq {

    @NotNull(message = "pageNo不能为空")
    @Min(value = 1,message = "pageNo 不能小于1")
    @ApiModelProperty(value = "页码")
    public int pageNo;

    @NotNull(message = "pageSize不能为空")
    @Min(value = 1,message = "pageSize 不能小于1")
    @ApiModelProperty(value = "每页的条数")
    public int pageSize;

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
}
