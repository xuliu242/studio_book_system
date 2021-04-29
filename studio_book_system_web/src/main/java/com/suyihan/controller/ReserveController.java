package com.suyihan.controller;


import com.suyihan.entity.Reserve;
import com.suyihan.response.Result;
import com.suyihan.service.ReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@Api(tags = "预订管理模块")
@RestController
@RequestMapping("/reserve")
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

    /**
     * 新增预订信息信息
     * @param map
     * @return
     * @throws ParseException
     */
    @ApiOperation(value = "新增预订信息信息")
    @RequestMapping(value = "/addReserve",method = RequestMethod.POST)
    public Result addReserve(@RequestBody Map<String,Object> map) throws ParseException {
        Integer valueId = (Integer) map.get("syhUserId");
        long syhUserId = valueId.longValue();
        // 手机传递的格式为 "yyyy-MM-ddTHH:mm:ss.SSSZ" "2021-04-29T21:36:36.555Z"
        String endTime = (String) map.get("syhReserveEndTime");
        int t = endTime.indexOf("T");
        String substring1 = endTime.substring(0, t);
        String substring2 = endTime.substring(t + 1, endTime.indexOf("."));
        endTime=substring1+" "+substring2;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
        Date syhReserveEndTime=format.parse(endTime);
        Date startTime = new Date();
        if (startTime.getTime()>syhReserveEndTime.getTime()){
            return Result.error().message("时间选择错误");
        }
        List<Integer> sitIdList = (List<Integer>) map.get("syhSitIds");
        for (int i = 0; i < sitIdList.size(); i++) {
            //判断当前座位id是否被占用
            Reserve reserve=new Reserve();
            reserve.setSyhReserveStartTime(startTime);
            reserve.setSyhUserId(syhUserId);
            reserve.setSyhSitId(sitIdList.get(i).longValue());
            reserve.setSyhReserveEndTime(syhReserveEndTime);
            reserve.setSyhReserveStatus(1);
            int addReserve = reserveService.addReserve(reserve);
            if (addReserve<=0){
                return Result.error();
            }
        }
        return Result.ok();
    }
    @ApiOperation(value = "根据用户id查询预订信息信息")
    @RequestMapping(value = "/queryReserveCondition",method = RequestMethod.POST)
    public Result queryReserveCondition(@RequestBody Map<String,Object> map){

        return Result.error();
    }


    @ApiOperation(value = "根据用户id查询预订信息信息")
    @RequestMapping(value = "/queryReserveByUserId",method = RequestMethod.GET)
    public Result queryReserve(Long syhUserId){
        List<Reserve> reserveList = reserveService.queryReserveByUserId(syhUserId);
        return Result.ok().data("result",reserveList);
    }


}

