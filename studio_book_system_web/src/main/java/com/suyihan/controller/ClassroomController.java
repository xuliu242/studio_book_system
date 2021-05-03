package com.suyihan.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suyihan.entity.Classroom;
import com.suyihan.entity.QueryClassroomCondition;
import com.suyihan.response.Result;
import com.suyihan.service.ClassroomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-18
 */
@Api(tags = "教室管理模块")
@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    /**
     * 添加/修改教室
     * @param classroom
     * @return
     */
    @ApiOperation(value = "saveOrUpdateClassroom")
    @RequestMapping(value = "/saveOrUpdateClassroom",method = RequestMethod.POST)
    public Result addClassroom(@RequestBody Classroom classroom){
        boolean b = classroomService.saveOrUpdate(classroom);
        if (b){
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 删除教室
     * @param classroomId
     * @return
     */
    @ApiOperation(value = "deleteClassroom")
    @RequestMapping(value = "/deleteClassroom",method = RequestMethod.GET)
    public Result deleteClassroom(Long classroomId){
        boolean b = classroomService.removeById(classroomId);
        if (b){
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 条件查询classroom
     * @param
     * @return
     */
    @ApiOperation(value = "条件查询classroom")
    @RequestMapping(value = "/queryClassroomCondition",method = RequestMethod.POST)
    public Result queryClassroomCondition(@RequestBody QueryClassroomCondition qcc){
        String syhClassroomName = qcc.getSyhClassroomName();
        Integer syhClassroomType = qcc.getSyhClassroomType();
        Integer pageNum = qcc.getPageNum() ==null?1:qcc.getPageNum();
        Integer pageSize = qcc.getPageSize()==null?8:qcc.getPageSize();
        QueryWrapper<Classroom> wrapper=new QueryWrapper();
        if (syhClassroomName!=null){
            wrapper.like("syh_classroom_name",syhClassroomName);
        }
        if (syhClassroomType!=null)
            wrapper.eq("syh_classroom_type",syhClassroomType);
        Page<Classroom> page=new Page<>(pageNum,pageSize);
        Page<Classroom> classroomPage = classroomService.page(page,wrapper);
        List<Classroom> classroomList = classroomPage.getRecords();
        long total = classroomPage.getTotal();
        return Result.ok().data("result",classroomList).data("total",total);
    }

    /**
     * 查询教室可用座位数量
     * @return
     */
    @ApiOperation(value = "查询教室可用座位数量")
    @RequestMapping(value = "/queryClassroomAbleSits",method = RequestMethod.POST)
    public Result queryClassroomAbleSits(){

        List<Classroom> classroomList = classroomService.queryClassroomAbleSits();
        if (classroomList==null){
            return Result.error();
        }

        return Result.ok().data("result",classroomList);
    }


}

