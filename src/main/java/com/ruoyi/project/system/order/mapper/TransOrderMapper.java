package com.ruoyi.project.system.order.mapper;

import com.ruoyi.project.system.order.domain.TransOrder;

import java.util.List;

/**
 * @author 焦关平
 * @date 2018.08.13
 */
public interface TransOrderMapper {

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