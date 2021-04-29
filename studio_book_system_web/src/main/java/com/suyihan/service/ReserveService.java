package com.suyihan.service;

import com.suyihan.entity.Reserve;
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
public interface ReserveService extends IService<Reserve> {
    int addReserve(Reserve reserve);

    /**
     * 根据用户id查询预订信息信息
     * @param syhUserId
     * @return
     */
    List<Reserve> queryReserveByUserId(Long syhUserId);
}
