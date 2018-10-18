package com.ruoyi.project.system.order.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.order.domain.PayOrder;
import com.ruoyi.project.system.order.domain.TransOrder;
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
@RequestMapping("/system/transOrder")
public class TransOrderController extends BaseController {
    private String prefix = "system/transOrder";

    @Resource
    private TransOrderService transOrderService;
    /**
     * 页面的跳转支付订单列表页面
     * @return
     */
    @RequiresPermissions("system:transOrder:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/transOrder";
    }

    /**
     * 支付单号表
     * @param transOrder
     * @return
     */
    @RequiresPermissions("system:transOrder:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo mchInfoList(TransOrder transOrder){
        startPage();
        List<TransOrder> list = transOrderService.list(transOrder);
        return getDataTable(list);
    }

    /**
     * 查看支付订单详情
     */
    @GetMapping("/view/{transOrderId}")
    public String view(@PathVariable("transOrderId") String transOrderId, ModelMap mmap)
    {
        mmap.put("transOrder", transOrderService.selectByPrimaryKey(transOrderId));
        return prefix + "/view";
    }
}
