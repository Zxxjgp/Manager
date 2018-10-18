package com.ruoyi.project.system.order.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.constant.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.dict.service.IDictDataService;
import com.ruoyi.project.system.order.domain.MchInfo;
import com.ruoyi.project.system.order.service.MchInfoService;
import com.ruoyi.project.system.post.service.IPostService;
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
 * @ClassName: MchInfoController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/11 10:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/11 10:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("/system/mch")
public class MchInfoController extends BaseController {

    private String prefix = "system/mch";
    @Resource
    private MchInfoService mchInfoService;

    @Resource
    private IPostService postService;

    @Resource
    private IDictDataService dictDataService;

    @RequiresPermissions("system:mch:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/mch";
    }



    @RequiresPermissions("system:mch:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo mchInfoList(MchInfo mchinfo){
        startPage();
        List<MchInfo> list = mchInfoService.mchInfoList(mchinfo);
        return getDataTable(list);
    }


    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("posts", dictDataService.selectDictDataByType("sys_mch_add"));
        return prefix + "/add";
    }
    /**
     * 保存商户信息
     * @param mchInfo
     * @return
     */
    @RequiresPermissions("system:mch:add")
    @Log(title = "用户管理", action = BusinessType.INSERT)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addMchInfo")
    @ResponseBody
    public AjaxResult addMchInfo(MchInfo mchInfo){
        if (StringUtils.isNull(mchInfo)){
            return error("用户为空，请核对信息");
        }
        return toAjax(mchInfoService.insertSelective(mchInfo));
    }


    /**
     * 修改用户
     */
    @GetMapping("/edit/{mchid}")
    public String edit(@PathVariable("mchid") String mchid, ModelMap mmap)
    {
        mmap.put("mch", mchInfoService.selectByPrimaryKey(mchid));
        return prefix + "/edit";
    }

    /**
     * 编辑保存商户信息
     * @param mchInfo
     * @return
     */
    @Log(title = "用户管理", action = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/editMchInfo")
    @ResponseBody
    public AjaxResult editMchInfo(MchInfo mchInfo){
        if (StringUtils.isNull(mchInfo)){
            return error("商户为空，请核对信息");
        }
        return toAjax(mchInfoService.updateByPrimaryKeySelective(mchInfo));
    }

    /**
     * 停用商户
     */
    @Log(title = "商户管理", action = BusinessType.UPDATE)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleMchInfo")
    @ResponseBody
    public AjaxResult deleMchInfo(String ids){
        MchInfo mchInfo = new MchInfo();
        if (StringUtils.isNull(ids)){
            return error("商户id为空，请核对信息");
        }
        if (ids.endsWith("a")){
            mchInfo.setMchid(ids.substring(0,ids.length()-1));
            mchInfo.setState("1");
            return toAjax(mchInfoService.deleteByPrimaryKey(mchInfo));
        }
        mchInfo.setMchid(ids);
        mchInfo.setState("0");
        return toAjax(mchInfoService.deleteByPrimaryKey(mchInfo));
    }

    /**
     * 查看用户
     */
    @GetMapping("/view/{mchid}")
    public String view(@PathVariable("mchid") String mchid, ModelMap mmap)
    {
        mmap.put("mch", mchInfoService.selectByPrimaryKey(mchid));
        return prefix + "/view";
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkMchInfoNameUnique")
    @ResponseBody
    public String checkMchInfoNameUnique(MchInfo mchInfo)
    {
        String uniqueFlag = "0";
        if (StringUtils.isNotNull(mchInfo))
        {
            uniqueFlag = mchInfoService.checkMchInfoNameUnique(mchInfo.getName());
        }
        return uniqueFlag;
    }

    @Log(title = "商户导出", action = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MchInfo mchInfo) throws Exception
    {
        try
        {
            List<MchInfo> list = mchInfoService.mchInfoList(mchInfo);
            ExcelUtil<MchInfo> util = new ExcelUtil<MchInfo>(MchInfo.class);
            return util.exportExcel(list, "mchInfo");
        }
        catch (Exception e)
        {
            return error("导出Excel失败，请联系网站管理员！");
        }
    }

    /**
     * 删除商户信息
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult deleteChannel(String ids){
        try {
            return toAjax(mchInfoService.deleteMchInfo(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
