package com.ruoyi.project.system.order.service;

import com.ruoyi.project.system.order.domain.MchNotify;

import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service
 * @ClassName: MchNotifyService
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/14 10:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 10:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface MchNotifyService {
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
