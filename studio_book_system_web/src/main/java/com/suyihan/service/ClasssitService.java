package com.suyihan.service;

import com.suyihan.entity.Classsit;
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
public interface ClasssitService extends IService<Classsit> {
    /**
     * web端 根据教室id查询工位
     * @param ClassroomId
     * @return
     */
    List<Classsit> querySitByClassRoomId(Long ClassroomId);

}
