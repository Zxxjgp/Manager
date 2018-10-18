package com.ruoyi.project.system.order.service;

import com.ruoyi.project.system.order.domain.RefundOrder;

import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service
 * @ClassName: RefundOrderService
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/14 19:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 19:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface RefundOrderService {
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
