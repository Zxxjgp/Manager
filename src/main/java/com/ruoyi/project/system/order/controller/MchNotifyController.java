package com.ruoyi.project.system.order.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.order.domain.MchInfo;
import com.ruoyi.project.system.order.domain.MchNotify;
import com.ruoyi.project.system.order.service.MchNotifyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.controller
 * @ClassName: MchNotifyController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/14 10:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 10:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("/system/notify")
public class MchNotifyController extends BaseController {
    private String prefix = "system/notify";

    @Resource
    private MchNotifyService mchNotifyService;

    /**
     * 跳转到列表页
     * @return
     */
    @RequiresPermissions("system:notify:view")
    @GetMapping()
    public String notifyPage()
    {
        return prefix + "/notify";
    }

    /**
     * 分页查询
     * @param mchNotify
     * @return
     */
    @RequiresPermissions("system:notify:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo notifyList(MchNotify mchNotify){
        startPage();
        List<MchNotify> list =mchNotifyService.getMchNotifyList(mchNotify);
        return getDataTable(list);
    }
    /**
     * 查看支付订单详情
     */
    @GetMapping("/view/{orderId}")
    public String view(@PathVariable("orderId") String orderId, ModelMap mmap)
    {
        mmap.put("mchNo", mchNotifyService.selectByPrimaryKey(orderId));
        return prefix + "/view";
    }
}
