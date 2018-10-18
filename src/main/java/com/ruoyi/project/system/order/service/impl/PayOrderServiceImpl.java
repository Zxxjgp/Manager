package com.ruoyi.project.system.order.service.impl;

import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.project.system.order.domain.PayOrder;
import com.ruoyi.project.system.order.mapper.PayOrderMapper;
import com.ruoyi.project.system.order.mapper.RefundOrderMapper;
import com.ruoyi.project.system.order.service.PayOrderService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service
 * @ClassName: PayOrderServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/12 19:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/12 19:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {
    @Resource
    private PayOrderMapper payOrderMapper;


    @Resource
    private RedisUtil redisUtil;
    /**
     * 分页查询支付订单
     * @param payOrder
     * @return
     */
    @Override
    public List<PayOrder> findByPayOrder(PayOrder payOrder) {
        return payOrderMapper.findByPayOrder(payOrder);
    }

    /**
     * 查询支付订单的详情
     * @param payOrderId
     * @return
     */
    @Override
    public PayOrder findDetailPayOrder(String payOrderId) {
        return payOrderMapper.findDetailPayOrder(payOrderId);
    }

    /**
     * echarts图标所需要的数据
     * @return
     */
    @Override
    public List<PayOrder> getListEChartsDate() {
        List<PayOrder> list = payOrderMapper.getListEChartsDate();
        Collections.reverse(list);
        redisUtil.set("twelveOrder",list,380);
        return payOrderMapper.getListEChartsDate();
    }

    @Override
    public PayOrder orderTotal() {
        PayOrder payOrder = payOrderMapper.orderTotal();
        redisUtil.set("totalOrderCount",payOrder.getOrderCount(),24);
        redisUtil.set("receiptAmount",payOrder.getAmount(),24);
        return payOrder;
    }

    @Override
    public PayOrder todayOrderTotal() {
        return payOrderMapper.todayOrderTotal();
    }

    @Override
    public int payAbnormalOrder() {
        int counts = payOrderMapper.payAbnormalOrder().getCounts();
        redisUtil.set("payAbnormalOrder",counts,480);
        return counts;
    }

}
