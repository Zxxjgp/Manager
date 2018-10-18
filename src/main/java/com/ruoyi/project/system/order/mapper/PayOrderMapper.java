package com.ruoyi.project.system.order.mapper;

import com.ruoyi.project.system.order.domain.PayOrder;

import java.util.List;

/**
 * @author jgp
 * @date 2018.08.12
 */
public interface PayOrderMapper {
    /**
     * 查看详情
     * @param payOrderId
     * @return
     */
    PayOrder findDetailPayOrder(String payOrderId);


    /**
     * 分页查询数据
     * @param payOrder
     * @return
     */
    List<PayOrder> findByPayOrder(PayOrder payOrder);

    /**
     * echarts图标所需要的数据
     * @return
     */
    List<PayOrder> getListEChartsDate();

    /**
     *今日订单数和金额
     */
    PayOrder orderTotal();

    /**
     *昨日订单数
     * @return
     */
    PayOrder todayOrderTotal();
    /**
     * 支付异常订单
     */
    PayOrder payAbnormalOrder();
}