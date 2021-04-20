package com.suyihan.service;

import com.suyihan.entity.Classroom;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
