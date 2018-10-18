package com.ruoyi.project.system.order.mapper;

import com.ruoyi.project.system.order.domain.RefundOrder;

import java.util.List;

/**
 * @author HSHY-394
 */
public interface RefundOrderMapper {

    /**
     * 查看详情
     * @param refundorderid
     * @return
     */

    RefundOrder selectByPrimaryKey(String refundorderid);

    /**
     * 分页查询
     * @param refundOrder
     * @return
     */
    List<RefundOrder> list(RefundOrder refundOrder);

    /**
     * 退款eacherts12个月
     * @return
     */
    List<RefundOrder> listRefundAmount();

}