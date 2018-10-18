package com.ruoyi.project.system.order.service.impl;

import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.order.domain.RefundOrder;
import com.ruoyi.project.system.order.domain.TransOrder;
import com.ruoyi.project.system.order.mapper.TransOrderMapper;
import com.ruoyi.project.system.order.service.TransOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service.impl
 * @ClassName: TransOrderServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/14 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 14:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class TransOrderServiceImpl implements TransOrderService {

    @Resource
    private TransOrderMapper transOrderMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public TransOrder selectByPrimaryKey(String transorderid) {
        return transOrderMapper.selectByPrimaryKey(transorderid);
    }

    @Override
    public List<TransOrder> list(TransOrder transOrder) {
        return transOrderMapper.list(transOrder);
    }

    @Override
    public List<TransOrder> listTransOrder() {
        List<TransOrder> list = transOrderMapper.listTransOrder();
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
        TransOrder transOrder = new TransOrder();
        Collections.reverse(list);
        for (int i = 0 ; i < temp ; i++){
            transOrder.setAmount("0.00");
            list.add(transOrder);

        }
        Collections.reverse(list);
        redisUtil.set("transOrder",list,380);
        return list;
    }

}
