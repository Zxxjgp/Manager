package com.ruoyi.project.system.order.controller;

import com.ruoyi.common.exception.user.PrimaryException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.constant.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.order.domain.MchInfo;
import com.ruoyi.project.system.order.domain.PayChannel;
import com.ruoyi.project.system.order.domain.PayOrder;
import com.ruoyi.project.system.order.service.PayChannelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.controller
 * @ClassName: PayChannelController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/13 15:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/13 15:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("/system/channel")
public class PayChannelController extends BaseController {
    String prefix = "system/channel";

    @Resource
    private PayChannelService payChannelService;
    /**
     * 页面的跳转支付订单列表页面
     * @return
     */
    @RequiresPermissions("system:channel:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/channel";
    }

    /**
     * 支付单号表
     * @param payChannel
     * @return
     */
    @RequiresPermissions("system:payOrder:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo mchInfoList(PayChannel payChannel){
        startPage();
        List<PayChannel> list = payChannelService.findPayChannelList(payChannel);
        return getDataTable(list);
    }


    /**
     * 查看支付渠道详情
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Integer id, ModelMap mmap)
    {
        mmap.put("payChannel", payChannelService.selectByPrimaryKey(id));
        return prefix + "/view";
    }

    /**
     * 编辑支付渠道详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        mmap.put("payChannel", payChannelService.selectByPrimaryKey(id));
        return prefix + "/edit";
    }

    /**
     * 编辑保存商户信息
     * @param payChannel
     * @return
     */
    @Log(title = "用户管理", action = BusinessType.UPDATE)
    @Transactional(rollbackFor = PrimaryException.class)
    @PostMapping("/editPayChannel")
    @ResponseBody
    public AjaxResult editMchInfo(PayChannel payChannel) throws Exception{
        if (StringUtils.isNull(payChannel)){
            return error("商户为空，请核对信息");
        }
        int t = 0;
        try {
            t = payChannelService.updateByPrimaryKeySelective(payChannel);
        } catch ( Exception e ) {
           return error("主键存在异常，同一商户ID下面不可以选择重复的渠道ID");
        }
        return toAjax(t);
    }
    /**
     * 新增支付渠道
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }

    /**
     * 保存商户信息
     * @param payChannel
     * @return
     */
    @Log(title = "支付渠道管理", action = BusinessType.INSERT)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addPayChannel")
    @ResponseBody
    public AjaxResult addPayChannel(PayChannel payChannel){
        if (StringUtils.isNull(payChannel)){
            return error("用户为空，请核对信息");
        }
        int t = 0;
        try {
            t = payChannelService.insertSelective(payChannel);
        } catch ( Exception e ) {
            return error(e.toString());
        }
        return toAjax(t);
    }

    /**
     * 停用商户
     */
    @Log(title = "商户管理", action = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleMchInfo")
    @ResponseBody
    public AjaxResult deleMchInfo(String ids){
        PayChannel mchInfo = new PayChannel();
        if (StringUtils.isNull(ids)){
            return error("商户id为空，请核对信息");
        }
        if (ids.endsWith("a")){
            mchInfo.setId(Integer.valueOf(ids.substring(0,ids.length()-1)));
            mchInfo.setState("1");
            return toAjax(payChannelService.updatePayChannelState(mchInfo));
        }
        mchInfo.setId(Integer.valueOf(ids));
        mchInfo.setState("0");
        return toAjax(payChannelService.updatePayChannelState(mchInfo));
    }
    @Log(title = "支付渠道导出", action = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayChannel payChannel) throws Exception
    {
        try
        {
            List<PayChannel> list = payChannelService.findPayChannelList(payChannel);
            ExcelUtil<PayChannel> util = new ExcelUtil<PayChannel>(PayChannel.class);
            return util.exportExcel(list, "支付渠道");
        }
        catch (Exception e)
        {
            return error("导出Excel失败，请联系网站管理员！");
        }
    }


    /**
     * 删除渠道信息
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult deleteChannel(String ids){
        try {
            return toAjax(payChannelService.deleteChannel(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
