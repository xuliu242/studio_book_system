package com.suyihan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.suyihan.entity.Classroom;
import com.suyihan.entity.Classsit;
import com.suyihan.entity.Reserve;
import com.suyihan.mapper.ClassroomMapper;
import com.suyihan.mapper.ClasssitMapper;
import com.suyihan.mapper.ReserveMapper;
import com.suyihan.service.ClassroomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-18
 */
@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements ClassroomService {
    @Autowired
    private ClassroomMapper classroomMapper;
    @Autowired
    private ClasssitMapper classsitMapper;
    @Autowired
    private ReserveMapper reserveMapper;
    @Override
    public boolean updateClassroomSitNum() {

//        QueryWrapper<Classroom> classroomQueryWrapper=new QueryWrapper<>();
        List<Classroom> classroomList = classroomMapper.selectList(null);
        for (Classroom classroom:classroomList) {
            Long syhClassroomId = classroom.getSyhClassroomId();
            QueryWrapper<Classsit> classsitQueryWrapper=new QueryWrapper<>();
            classsitQueryWrapper.eq("syh_classroom_id",syhClassroomId);
//            获取教室下所有座位数量
            Integer sits = classsitMapper.selectCount(classsitQueryWrapper);
            classsitQueryWrapper.eq("syh_sit_status",1);
            //获取教室下 可用座位数量
            Integer ableSits = classsitMapper.selectCount(classsitQueryWrapper);
            classroom.setSyhClassroomSitNumber(sits);
            classroom.setReserveAbleSitNum(ableSits);
            int updateById = classroomMapper.updateById(classroom);
            if (updateById<=0){
                return false;
            }

        }

        return true;
    }


    /**
     * 查询教室可用座位数量
     * 1.查询预订失效的记录
     * 2.查询失效预订的座位id
     * 3.更新预订失效的结果和座位状态
     * @return 
     */
    @Override
    public List<Classroom> queryClassroomAbleSits() {
        // 3.查询更新后的教室信息
        List<Classroom> classroomList = classroomMapper.selectList(null);
        for (Classroom classroom:classroomList) {
            Long syhClassroomId = classroom.getSyhClassroomId();
            Integer ableSits = classsitMapper.queryClasssitAbleSits(syhClassroomId);
            classroom.setReserveAbleSitNum(ableSits);
        }
        return classroomList;
    }

    @Override
    public Boolean flushStatus() {
        // 1.查询预订失效的记录 并更新预订状态为2 过期
        List<Reserve> reserveList = reserveMapper.queryExpireSitIds();
        List<Long> expireSitIds=new ArrayList<>();
        for (int i = 0; i < reserveList.size(); i++) {
            expireSitIds.add(reserveList.get(i).getSyhSitId());
            int updateReserveStatusById = reserveMapper.updateReserveStatusById(reserveList.get(i).getSyhReserveId(), 2);
            if (updateReserveStatusById<=0){
                return false;
            }
        }
        // 2.查询失效预订的座位id 更新座位状态 为0 可用
        List<Classsit> classsitList = classsitMapper.selectBatchIds(expireSitIds);
        for (Classsit classsit:classsitList) {
            Integer updateSitStatusById = classsitMapper.updateSitStatusById(classsit.getSyhSitId(), 0);
            if (updateSitStatusById<=0){
                return false;
            }
        }
        return true;
    }
}
