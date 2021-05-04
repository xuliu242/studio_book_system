package com.suyihan.mapper;

import com.suyihan.entity.QueryReserveCondition;
import com.suyihan.entity.Reserve;
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
public interface ReserveMapper extends BaseMapper<Reserve> {
    /**
     * 查询已过期的预订信息
     * @return
     */
    @Select("select * from syh_reserve where syh_reserve_status=1 and syh_reserve_end_time < now()")
    List<Reserve> queryExpireSitIds();

    /**
     * 更新预订状态
     * @param syhReserveId
     * @return
     */
    @Update("update syh_reserve set syh_reserve_status=#{syhReserveStatus} where syh_reserve_id=#{syhReserveId}")
    int updateReserveStatusById(@Param("syhReserveId") Long syhReserveId,@Param("syhReserveStatus") Integer syhReserveStatus);

    List<Reserve> queryReserveCondition(QueryReserveCondition qrc);

    /**
     * 根据用户id查询预定信息
     * @param syhUserId
     * @return
     */
    @Select("select rs.syh_reserve_id,rs.syh_user_id,rs.syh_sit_id,rs.syh_reserve_start_time,\n" +
            "rs.syh_reserve_end_time,rs.syh_reserve_status,u.syh_user_name,room.syh_classroom_number,\n" +
            "sit.syh_sit_name from syh_reserve rs\n" +
            "left join syh_user u on u.syh_user_id=rs.syh_user_id\n" +
            "left join syh_classsit sit on sit.syh_sit_id=rs.syh_sit_id\n" +
            "left join syh_classroom room on room.syh_classroom_id=sit.syh_classroom_id\n" +
            "where rs.syh_user_id=#{syhUserId} ORDER BY rs.syh_reserve_start_time desc;\n")
    List<Reserve> queryReserveByUserId(Long syhUserId);




}
