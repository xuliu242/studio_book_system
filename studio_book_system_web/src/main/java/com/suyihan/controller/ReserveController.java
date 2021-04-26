package com.suyihan.controller;


import com.suyihan.response.Result;
import com.suyihan.service.ReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-18
 */
@Api(tags = "预订管理模块")
@RestController
@RequestMapping("/reserve")
public class ReserveController {
    @Autowired
    private ReserveService reserveService;
    @ApiOperation(value = "新增预订信息信息")
    @RequestMapping(value = "/addReserve",method = RequestMethod.POST)
    public Result addReserve(){
        return Result.error();
    }
    @ApiOperation(value = "查询预订信息信息")
    @RequestMapping(value = "/queryReserve",method = RequestMethod.GET)
    public Result queryReserve(){
        return Result.error();
    }


}

