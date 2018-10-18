package com.ruoyi.project.system.order.job;

import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.project.system.order.service.PayOrderService;
import com.ruoyi.project.system.order.service.RefundOrderService;
import com.ruoyi.project.system.order.service.TransOrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.job
 * @ClassName: JobService
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/23 16:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/23 16:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Component
public class JobService {
    @Resource
    private PayOrderService payOrderService;

    @Resource
    private RefundOrderService refundOrderService;

    @Resource
    private TransOrderService transOrderService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 更新累计订单数和金额
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAddupAmountOrder(){
        try {
            if (redisUtil.hasKey("totalOrderCount") & redisUtil.hasKey("receiptAmount")){
                    redisUtil.del("totalOrderCount", "receiptAmount");

            }
            payOrderService.orderTotal();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * 每月1号执行一次 获得支付异常订单数
     */
   @Scheduled(cron = "0 0 0 1 * ?")
    public void payAbnormalOrder(){
        if (redisUtil.hasKey("payAbnormalOrder")){
            redisUtil.del("payAbnormalOrder");
        }
        payOrderService.payAbnormalOrder();
    }

    /**
     * 每15天执行一次 获得最近12个月的订单情况
     */
   @Scheduled(cron = "0 0 0 15 * ?")
    public  void twelveOrder(){
        if (redisUtil.hasKey("twelveOrder")){
            redisUtil.del("twelveOrder");
        }
        payOrderService.getListEChartsDate();

        if (redisUtil.hasKey("refund")){
            redisUtil.del("refund");
        }
        refundOrderService.listRefundAmount();

        if (redisUtil.hasKey("transOrder")){
            redisUtil.del("transOrder");
        }
        transOrderService.listTransOrder();
    }
}
