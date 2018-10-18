package com.ruoyi.project.system.order.mapper;

import com.ruoyi.project.system.order.domain.PayChannel;

import java.util.List;

/**
 * @author 焦关平
 * @/// TODO: 2018/8/13
 */
public interface PayChannelMapper {

    /**
     * 增加支付渠道
     * @param record
     * @return
     */
    int insertSelective(PayChannel record);

    /**
     * 查看支付渠道
     * @param id
     * @return
     */
    PayChannel selectByPrimaryKey(Integer id);

    /**
     * 编辑更新支付渠道
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PayChannel record);
    /**
     * 分页查询支付渠道
     */
    List<PayChannel> findPayChannelList(PayChannel record);

    /**
     * 更新状态
     */
    int updatePayChannelState(PayChannel record);
    /**
     * 删除商户信息
     */
    int deleteChannel(Integer[] ids);
}