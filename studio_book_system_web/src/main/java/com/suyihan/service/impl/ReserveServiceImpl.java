package com.suyihan.service.impl;

import com.suyihan.entity.Reserve;
import com.suyihan.mapper.ClasssitMapper;
import com.suyihan.mapper.ReserveMapper;
import com.suyihan.service.ReserveService;
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
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve> implements ReserveService {
    @Autowired
    private ReserveMapper reserveMapper;
    @Autowired
    private ClasssitMapper classsitMapper;
    @Override
    public int addReserve(Reserve reserve) {
        Long syhSitId = reserve.getSyhSitId();
        int syhSitStatus=1;
        Integer updateSitStatusById = classsitMapper.updateSitStatusById(syhSitId, syhSitStatus);
        if (updateSitStatusById<=0){
            return 0;
        }
        return reserveMapper.insert(reserve);
    }

    /**
     * 根据用户id查询预订信息信息
     * @param syhUserId
     * @return
     */
    @Override
    public List<Reserve> queryReserveByUserId(Long syhUserId) {
        return reserveMapper.queryReserveByUserId(syhUserId);
    }
}
