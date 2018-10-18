package com.ruoyi.project.system.order.service.impl;

import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.order.domain.PayOrder;
import com.ruoyi.project.system.order.domain.RefundOrder;
import com.ruoyi.project.system.order.mapper.PayOrderMapper;
import com.ruoyi.project.system.order.mapper.RefundOrderMapper;
import com.ruoyi.project.system.order.service.RefundOrderService;
import org.apache.poi.ss.formula.functions.T;
import org.omg.CORBA.StringHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service.impl
 * @ClassName: RefundOrderServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/14 19:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 19:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class RefundOrderServiceImpl implements RefundOrderService {
    @Resource
    private RefundOrderMapper refundOrderMapper;
    @Resource
    private PayOrderMapper payOrderMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public RefundOrder selectByPrimaryKey(String refundorderid) {
        return refundOrderMapper.selectByPrimaryKey(refundorderid);
    }

    @Override
    public List<RefundOrder> list(RefundOrder refundOrder) {
        return refundOrderMapper.list(refundOrder);
    }

    @Override
    public List<RefundOrder> listRefundAmount() {
        List<RefundOrder> list = refundOrderMapper.listRefundAmount();
        int temp =0;
        String remounts = list.get(0).getTimeList();
        for (int n = 0; n < 11; n++){
            if (!remounts.equals(StringUtils.formatted(n))){
                ++temp;
            }
            else {
                break;
            }
        }
        RefundOrder refundOrder = new RefundOrder();
        Collections.reverse(list);
        for (int i = 0 ; i < temp ; i++){
            refundOrder.setRefundAmount("0.00");
            list.add(refundOrder);

        }
        Collections.reverse(list);

        redisUtil.set("refund",list,380);
        return list;
    }

}
