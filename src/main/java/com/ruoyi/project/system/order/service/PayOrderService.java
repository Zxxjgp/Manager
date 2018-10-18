package com.ruoyi.project.system.order.service;

import com.ruoyi.project.system.order.domain.PayOrder;

import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service
 * @ClassName: PayOrderService
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/12 19:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/12 19:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface PayOrderService {
    /**
     * 分页查询支付订单
     * @param payOrder
     * @return
     */
    List<PayOrder> findByPayOrder(PayOrder payOrder);

    /**\
     * 查看支付单号的详情
     * @param payOrderId
     * @return
     */
    PayOrder findDetailPayOrder(String payOrderId);


    /**
     * echarts图标所需要的数据
     * @return
     */
    List<PayOrder> getListEChartsDate();

    /**
     *累计
     */
    PayOrder orderTotal();

    /**
     *日订单数和金额
     * @return
     */
    PayOrder todayOrderTotal();
    /**
     * 支付异常订单
     */
    int payAbnormalOrder();
}
