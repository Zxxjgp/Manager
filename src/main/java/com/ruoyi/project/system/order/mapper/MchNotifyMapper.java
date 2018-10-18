package com.ruoyi.project.system.order.mapper;

import com.ruoyi.project.system.order.domain.MchNotify;

import java.util.List;

/**
 * @author jgp
 */
public interface MchNotifyMapper {

    /**
     * 查看详情
     * @param orderid
     * @return
     */
    MchNotify selectByPrimaryKey(String orderid);

    /**
     * 分页查询数据
     * @param mchNotify
     * @return
     */
    List<MchNotify> getMchNotifyList(MchNotify mchNotify);
}