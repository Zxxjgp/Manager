package com.ruoyi.project.system.order.mapper;


import com.ruoyi.project.system.order.domain.MchInfo;

import java.util.List;

/**
 * @author jgp
 */
public interface MchInfoMapper {

    /**
     * 更新状态商户
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

    /**\
     * 检查商户名字是否唯一
     * @param name
     * @return
     */
    int checkMchInfoNameUnique(String name);
    /**
     * 得到最新的id
     */
    String findId();

    /**
     * 得到标签
     * @return
     */
    List<MchInfo> selectMchInfo();


    /**
     * 删除商户信息
     */
    int deleteMchInfo(Integer[] mchId);
}