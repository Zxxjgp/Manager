package com.ruoyi.project.system.order.service.impl;

import com.ruoyi.project.system.order.domain.MchNotify;
import com.ruoyi.project.system.order.mapper.MchNotifyMapper;
import com.ruoyi.project.system.order.service.MchNotifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service.impl
 * @ClassName: MchNotifyServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/14 10:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 10:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class MchNotifyServiceImpl implements MchNotifyService {
    @Resource
    private MchNotifyMapper mchNotifyMapper;

    @Override
    public MchNotify selectByPrimaryKey(String orderid) {
        return mchNotifyMapper.selectByPrimaryKey(orderid);
    }

    @Override
    public List<MchNotify> getMchNotifyList(MchNotify mchNotify) {
        return mchNotifyMapper.getMchNotifyList(mchNotify);
    }
}
