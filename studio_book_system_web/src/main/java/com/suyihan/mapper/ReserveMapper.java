package com.suyihan.mapper;

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



}
