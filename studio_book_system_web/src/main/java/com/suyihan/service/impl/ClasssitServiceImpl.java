package com.suyihan.service.impl;

import com.suyihan.entity.Classsit;
import com.suyihan.entity.QueryClasssitCondition;
import com.suyihan.mapper.ClasssitMapper;
import com.suyihan.service.ClasssitService;
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
public class ClasssitServiceImpl extends ServiceImpl<ClasssitMapper, Classsit> implements ClasssitService {
    @Autowired
    private ClasssitMapper classsitMapper;
    /**
     * web端 根据教室id查询工位
     * @param ClassroomId
     * @return
     */
    @Override
    public List<Classsit> querySitByClassRoomId(Long ClassroomId) {
        return classsitMapper.querySitByClassRoomId(ClassroomId);
    }

    @Override
    public List<Classsit> querySitByCondition(QueryClasssitCondition qcc) {
        return classsitMapper.querySitByCondition(qcc);
    }
}
