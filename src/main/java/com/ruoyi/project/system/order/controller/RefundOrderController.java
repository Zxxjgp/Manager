package com.ruoyi.project.system.order.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.order.domain.RefundOrder;
import com.ruoyi.project.system.order.domain.TransOrder;
import com.ruoyi.project.system.order.service.RefundOrderService;
import com.ruoyi.project.system.order.service.TransOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.controller
 * @ClassName: TransOrderController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/14 14:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 14:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("/system/refundOrder")
public class RefundOrderController extends BaseController {

    private String prefix = "system/refundOrder";

    @Resource
    private RefundOrderService refundOrderService;
    /**
     * 页面的跳转支付订单列表页面
     * @return
     */
    @RequiresPermissions("system:refundOrder:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/refundOrder";
    }

    /**
     * 支付单号表
     * @param refundOrder
     * @return
     */
    @RequiresPermissions("system:refundOrder:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo mchInfoList(RefundOrder refundOrder){
        startPage();
        List<RefundOrder> list = refundOrderService.list(refundOrder);
        return getDataTable(list);
    }

    /**
     * 查看支付订单详情
     */
    @GetMapping("/view/{transOrderId}")
    public String view(@PathVariable("transOrderId") String transOrderId, ModelMap mmap)
    {
        mmap.put("refundOrder", refundOrderService.selectByPrimaryKey(transOrderId));
        return prefix + "/view";
    }
}
