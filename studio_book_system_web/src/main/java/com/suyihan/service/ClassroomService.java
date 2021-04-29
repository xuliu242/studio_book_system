package com.suyihan.service;

import com.suyihan.entity.Classroom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-18
 */
public interface ClassroomService extends IService<Classroom> {
    /**
     * 更新教室座位数量
     * @return
     */
    public boolean updateClassroomSitNum();

    /**
     * 查询教室可用座位数量
     * @return
     */
    public List<Classroom> queryClassroomAbleSits();

    /**
     * 刷新座位和预订信息状态
     */
    public Boolean flushStatus();
}
