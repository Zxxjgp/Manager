package com.ruoyi.project.system.order.service;

import com.ruoyi.project.system.order.domain.MchInfo;

import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service
 * @ClassName: MchInfoService
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/11 10:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/11 10:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface MchInfoService {
    /**
     * 删除商户
     * @param mchid
     * @return
     */
    int deleteByPrimaryKey(MchInfo mchInfo);


    /**
     * 新增商户信息
     * @param mchInfo
     * @return
     */

    int insertSelective(MchInfo mchInfo);

    /**
     * 通过id查看详情
     * @param mchid
     * @return
     */
    MchInfo selectByPrimaryKey(String mchid);

    /**
     * 通过id更新
     * @param mchInfo
     * @return
     */
    int updateByPrimaryKeySelective(MchInfo mchInfo);

    /**
     * 全部的商户
     */
    List<MchInfo> mchInfoList(MchInfo mchinfo);

    /**
     * 校验商户的唯一性
     * @param name
     * @return
     */
    String checkMchInfoNameUnique(String name);


    /**
     * 得到标签
     * @return
     */
    List<MchInfo> selectMchInfo();

    /**
     * 删除商户信息
     */
    int deleteMchInfo(String ids);
}
