package com.suyihan.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suyihan.entity.Classroom;
import com.suyihan.entity.Classsit;
import com.suyihan.entity.QueryClasssitCondition;
import com.suyihan.response.Result;
import com.suyihan.service.ClassroomService;
import com.suyihan.service.ClasssitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
@Api(tags = "座位管理模块")
@RestController
@RequestMapping("/classsit")
public class ClasssitController {
    @Autowired
    private ClasssitService classsitService;
    @Autowired
    private ClassroomService classroomService;

    @ApiOperation(value = "saveOrUpdateClasssit")
    @RequestMapping(value = "/saveOrUpdateClasssit",method = RequestMethod.POST)
    public Result saveOrUpdateClasssit(@RequestBody Classsit classsit){
        classsit.setSyhSitUpdateTime(new Date());
        boolean b = classsitService.saveOrUpdate(classsit);
        boolean updateClassroomSitNum = classroomService.updateClassroomSitNum();
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

    /**
     * 根据教室id查询座位信息 移动端
     * @param classroomId
     * @return
     */
    @ApiOperation(value = "根据教室id查询座位信息 移动端")
    @RequestMapping(value = "/queryByClassroomId",method = RequestMethod.GET)
    public Result queryByClassroomId(Long classroomId){
        classroomService.queryClassroomAbleSits();
        QueryWrapper<Classsit> wrapper=new QueryWrapper();
        wrapper.eq("syh_classroom_id",classroomId);
        List<Classsit> classsitList = classsitService.list(wrapper);
        //处理座位位置数据
        for (Classsit classsit:classsitList) {
            String syhSitName = classsit.getSyhSitName();
            //行
            int rowNum = Integer.parseInt(syhSitName.substring(syhSitName.length()-2,syhSitName.length() -1)) + 1;
            //列
            int columnNum = Integer.parseInt(syhSitName.substring(syhSitName.length()-1, syhSitName.length()));
            if (columnNum==0){
                columnNum=10;
            }
            classsit.setRowNum(rowNum);
            classsit.setColumnNum(columnNum);
            classsit.setXCoord(20-columnNum-1);
            classsit.setYCoord(rowNum+2);
        }


        return Result.ok().data("result",classsitList);
    }

    /**
     * web端 根据条件查询工位
     * @param
     * @return
     */
    @ApiOperation(value = "web端 根据条件查询工位")
    @RequestMapping(value = "/querySitByClasssitByCondition",method = RequestMethod.POST)
    public Result querySitByClassRoomId(@RequestBody QueryClasssitCondition qcc){
        Integer pageNum = qcc.getPageNum() ==null?1:qcc.getPageNum();
        Integer pageSize = qcc.getPageSize()==null?10:qcc.getPageSize();
        Long syhClassroomId = qcc.getSyhClassroomId();
        String syhClasssitName = qcc.getSyhClasssitName();
        List<Classsit> classsits=new ArrayList<>();
        Long total=null;
        if ("".equals(syhClasssitName)){
            qcc.setSyhClasssitName(null);
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(classsitService.querySitByCondition(qcc));
        classsits=pageInfo.getList();
        total=pageInfo.getTotal();
        return Result.ok().data("result",classsits).data("total",total);
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

