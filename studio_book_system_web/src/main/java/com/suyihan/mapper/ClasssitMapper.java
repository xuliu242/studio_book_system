package com.suyihan.mapper;

import com.suyihan.entity.Classroom;
import com.suyihan.entity.Classsit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("select count(*) from syh_classsit sit where sit.syh_sit_status =0 and syh_classroom_id=#{syhClassroomId}")
    Integer queryClasssitAbleSits(Long syhClassroomId);

    /**
     * 更新座位状态
     * @param syhSitId
     * @param syhSitStatus
     * @return
     */
    @Update("update syh_classsit set syh_sit_status=#{syhSitStatus} where syh_sit_id=#{syhSitId}")
    Integer updateSitStatusById(@Param("syhSitId") Long syhSitId,@Param("syhSitStatus") Integer syhSitStatus);


}
