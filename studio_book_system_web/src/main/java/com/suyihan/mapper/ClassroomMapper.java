package com.suyihan.mapper;

import com.suyihan.entity.Classroom;
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
public interface ClassroomMapper extends BaseMapper<Classroom> {

}
