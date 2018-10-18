package com.ruoyi.project.system.order.service;

import com.ruoyi.project.system.order.domain.PayChannel;

import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service
 * @ClassName: PayChannelService
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/13 14:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/13 14:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface PayChannelService {
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
     * @param ids
     * @return
     */
    int deleteChannel(String ids) throws Exception;
}
