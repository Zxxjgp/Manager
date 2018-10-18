package com.ruoyi.project.system.user.controller;

import java.util.List;

import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.notice.domain.Notice;
import com.ruoyi.project.system.notice.service.INoticeService;
import com.ruoyi.project.system.order.domain.PayOrder;
import com.ruoyi.project.system.order.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.user.domain.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private RuoYiConfig ruoYiConfig;

    @Autowired
    private INoticeService iNoticeService;

    @Resource
    private PayOrderService payOrderService;

    @Resource
    private RedisUtil redisUtil;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getUser();
        List<Notice> list = iNoticeService.count();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("counts",list.size());
        mmap.put("rows",list);
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", ruoYiConfig.getVersion());
        Object receiptAmount = redisUtil.get("receiptAmount");
        Object totalOrderCount = redisUtil.get("totalOrderCount");

        if (StringUtils.isNull(receiptAmount)){
            if (StringUtils.isNull(totalOrderCount)) {
                PayOrder payOrder = payOrderService.orderTotal();
                receiptAmount = payOrder.getAmount();
                totalOrderCount = payOrder.getOrderCount();
            }
        }

        mmap.put("receiptAmount",receiptAmount);
        mmap.put("totalOrderCount",totalOrderCount);
        mmap.put("todayMoney",payOrderService.todayOrderTotal());


        Object payAbnormalOrder = redisUtil.get("payAbnormalOrder");
        if (StringUtils.isNull(payAbnormalOrder)){
            payAbnormalOrder = payOrderService.payAbnormalOrder();
        }
        mmap.put("payAbnormalOrder",payAbnormalOrder);
        return "main";
    }

}
