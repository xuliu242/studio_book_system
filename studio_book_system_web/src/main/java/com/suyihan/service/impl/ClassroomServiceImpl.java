package com.suyihan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suyihan.entity.Classroom;
import com.suyihan.entity.Classsit;
import com.suyihan.mapper.ClassroomMapper;
import com.suyihan.mapper.ClasssitMapper;
import com.suyihan.service.ClassroomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
