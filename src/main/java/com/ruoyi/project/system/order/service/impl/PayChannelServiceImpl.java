package com.ruoyi.project.system.order.service.impl;

import com.ruoyi.common.exception.user.PrimaryException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.order.domain.PayChannel;
import com.ruoyi.project.system.order.mapper.PayChannelMapper;
import com.ruoyi.project.system.order.service.PayChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service
 * @ClassName: PayChannelServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/13 14:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/13 14:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class PayChannelServiceImpl implements PayChannelService {
    @Resource
    private PayChannelMapper payChannelMapper;

    @Override
    public int insertSelective(PayChannel record) {
        record.setCreateTime(new Date());
        return payChannelMapper.insertSelective(record);
    }

    @Override
    public PayChannel selectByPrimaryKey(Integer id) {
        return payChannelMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PayChannel record) {
         record.setUpdateTime(new Date());
         return payChannelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<PayChannel> findPayChannelList(PayChannel record) {
        return payChannelMapper.findPayChannelList(record);
    }

    @Override
    public int updatePayChannelState(PayChannel record) {
        return payChannelMapper.updatePayChannelState(record);
    }

    @Override
    public int deleteChannel(String ids) throws Exception {
        Integer[] userIds = Convert.toIntArray(ids);
        return payChannelMapper.deleteChannel(userIds);
    }

}
