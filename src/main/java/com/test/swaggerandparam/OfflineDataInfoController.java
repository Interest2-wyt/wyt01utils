package com.test.swaggerandparam;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *@ClassName OfflineDataInfoController
 *@Description 离线数据对外接口类
 *@Author wangyongtao
 *@Date 2020/7/1 16:56
 *@Version 1.0
 */
@RestController
@Api(tags = "离线报警OfflineDataInfoController相关的api")
public class OfflineDataInfoController {
    private static Logger logger = LoggerFactory.getLogger(OfflineDataInfoController.class);

    /**
     * 1、根据条件分页查询离线数据
     */
    @ApiOperation(value = "根据分页条件查询离线数据", notes = "查询数据库中的分页数据")
    @PostMapping("/findListOfflineDataByPage")
    public OfflineDataPageResp findListOfflineDataByPage(@RequestBody @Valid PageReq pageReq, BindingResult bindingResult){
        // 1、如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        for (ObjectError error : bindingResult.getAllErrors()) {
            logger.error("报错信息：",error.getDefaultMessage());
            return new OfflineDataPageResp();
        }
        //2、查询最近一条数据时间为空，或者最近一条数据跟当前时间差一小时的数据

        //3、返回
        return new OfflineDataPageResp();
    }

}
