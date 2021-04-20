package com.suyihan.mapper;

import com.suyihan.entity.Classroom;
import com.suyihan.entity.Classsit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-18
 */
@Mapper
public interface ClasssitMapper extends BaseMapper<Classsit> {
    /**
     * 根据教室id获取可预订座位数量
     * @return
     */
    @Select("select count(*) from syh_classsit sit where sit.syh_sit_status =1 and syh_classroom_id=#{syhClassroomId}")
    Integer queryClasssitAbleSits(Long syhClassroomId);
}
