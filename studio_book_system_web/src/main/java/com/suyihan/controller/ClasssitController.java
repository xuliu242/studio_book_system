package com.suyihan.controller;


import com.suyihan.entity.Classsit;
import com.suyihan.response.Result;
import com.suyihan.service.ClassroomService;
import com.suyihan.service.ClasssitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-18
 */
@Api(tags = "座位管理模块")
@RestController
@RequestMapping("/classsit")
public class ClasssitController {
    @Autowired
    private ClasssitService classsitService;

    @ApiOperation(value = "saveOrUpdateClasssit")
    @RequestMapping(value = "/saveOrUpdateClasssit",method = RequestMethod.POST)
    public Result saveOrUpdateClasssit(Classsit classsit){
        boolean b = classsitService.saveOrUpdate(classsit);
        if (b){
            return Result.ok();
        }
        return Result.error();
    }

    @ApiOperation(value = "deleteClasssit")
    @RequestMapping(value = "/deleteClasssit",method = RequestMethod.GET)
    public Result deleteClasssit(Long classsitId){
        boolean b = classsitService.removeById(classsitId);
        if (b){
            return Result.ok();
        }
        return Result.error();
    }

//    @ApiOperation(value = "initClasssit")
//    @RequestMapping(value = "/initClasssit",method = RequestMethod.GET)
//    public Result initClasssit(){
//        for (int i = 1; i <= 50; i++) {
//            Classsit classsit=new Classsit();
//            if (i<10){
//                classsit.setSyhSitName("01012700"+i);
//            }else
//                classsit.setSyhSitName("0101270"+i);
//
//            classsit.setSyhClassroomId(2L);
//            classsit.setSyhSitStatus(1);
//            classsit.setSyhSitUpdateTime(new Date());
//            boolean save = classsitService.save(classsit);
//            if (!save){
//                return Result.error();
//            }
//
//        }
//        return Result.ok();
//    }

}

