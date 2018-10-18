package com.ruoyi.project.system.order.service;

import com.ruoyi.project.system.order.domain.TransOrder;

import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service
 * @ClassName: TransOrderService
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/14 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 14:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface TransOrderService {
    /**
     * 查看详情
     * @param transorderid
     * @return
     */
    TransOrder selectByPrimaryKey(String transorderid);

    /**
     * 分页查询
     * @param transOrder
     * @return
     */
    List<TransOrder> list(TransOrder transOrder);

    /**
     * 退款eacherts
     * @return
     */
    List<TransOrder> listTransOrder();
}
