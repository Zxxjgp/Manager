package com.ruoyi.project.system.order.controller;

import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.constant.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.order.domain.PayOrder;
import com.ruoyi.project.system.order.domain.RefundOrder;
import com.ruoyi.project.system.order.domain.TransOrder;
import com.ruoyi.project.system.order.service.PayOrderService;
import com.ruoyi.project.system.order.service.RefundOrderService;
import com.ruoyi.project.system.order.service.TransOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.controller
 * @ClassName: PayOrderController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/12 19:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/12 19:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("/system/payOrder")
public class PayOrderController extends BaseController {
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    String prefix = "system/payOrder";

    @Resource
    private PayOrderService payOrderService;

    @Resource
    private RefundOrderService  refundOrderService;

    @Resource
    private TransOrderService transOrderService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 页面的跳转支付订单列表页面
     * @return
     */
    @RequiresPermissions("system:payOrder:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/payOrder";
    }

    /**
     * 支付单号表
     * @param payOrder
     * @return
     */
    @RequiresPermissions("system:payOrder:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo mchInfoList(PayOrder payOrder){
        startPage();
        List<PayOrder> list = payOrderService.findByPayOrder(payOrder);
        return getDataTable(list);
    }

    /**
     * 查看支付订单详情
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("payOrder", payOrderService.findDetailPayOrder(id));
        return prefix + "/view";
    }


    @Log(title = "订单表导出", action = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayOrder payOrder) throws Exception
    {
        try
        {
            List<PayOrder> list = payOrderService.findByPayOrder(payOrder);
            ExcelUtil<PayOrder> util = new ExcelUtil<PayOrder>(PayOrder.class);
            return util.exportExcel(list, "order");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("/echarts")
    @ResponseBody
    public List<PayOrder> getListEcharts(){
        return orderList();
    }

    @GetMapping("/info")
    @ResponseBody
    public Map<String,Object> getListEchartsInfo(RefundOrder refundOrder){

        Map<String,Object> map=  new HashMap<>(16);
        map.put("month", orderList());
        List<RefundOrder> list = (List<RefundOrder>) redisUtil.get("refund");
        if (StringUtils.isEmpty(list)){
            list=refundOrderService.listRefundAmount();
        }
        map.put("refund",list);

        List<TransOrder> transOrders = (List<TransOrder>) redisUtil.get("transOrder");
        if (StringUtils.isEmpty(transOrders)){
            transOrders = transOrderService.listTransOrder();
        }
        map.put("transOrder",transOrders);
        return map;
    }

    public List<PayOrder> orderList(){
        List<PayOrder> list= (List<PayOrder>) redisUtil.get("twelveOrder");
        if (StringUtils.isEmpty(list)){
            list = payOrderService.getListEChartsDate();
        }
        return list;
    }
}
