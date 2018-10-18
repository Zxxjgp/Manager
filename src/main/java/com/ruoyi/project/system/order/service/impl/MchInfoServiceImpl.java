package com.ruoyi.project.system.order.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.project.system.order.domain.MchInfo;
import com.ruoyi.project.system.order.mapper.MchInfoMapper;
import com.ruoyi.project.system.order.service.MchInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: Manager
 * @Package: com.ruoyi.project.system.order.service
 * @ClassName: MchInfoServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/11 10:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/11 10:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class MchInfoServiceImpl implements MchInfoService {

    @Resource
    private MchInfoMapper mchInfoMapper;
    /**
     * 删除商户
     * @param mchInfo
     * @return
     */
    @Override
    public int deleteByPrimaryKey(MchInfo mchInfo) {
        return mchInfoMapper.deleteByPrimaryKey(mchInfo);
    }

    /**
     * 新增商户
     * @param mchInfo
     * @return
     */
    @Override
    public int insertSelective(MchInfo mchInfo) {
        int temp = Integer.parseInt(mchInfoMapper.findId());
        ++temp;
        mchInfo.setMchid(Integer.toString(temp));
        mchInfo.setCreateTime(new Date());
        mchInfo.setUpdateTime(new Date());
        return mchInfoMapper.insertSelective(mchInfo);
    }

    /**
     * 查看商户的详情
     * @param mchid
     * @return
     */
    @Override
    public MchInfo selectByPrimaryKey(String mchid) {
        return mchInfoMapper.selectByPrimaryKey(mchid);
    }

    /**
     * 更新商户
     * @param mchInfo
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(MchInfo mchInfo) {
        mchInfo.setUpdateTime(new Date());
        return mchInfoMapper.updateByPrimaryKeySelective(mchInfo);
    }

    /**
     * 分页查询
     * @return
     */
    @Override
    public List<MchInfo> mchInfoList(MchInfo mchinfo) {
        return mchInfoMapper.mchInfoList(mchinfo);
    }

    @Override
    public String checkMchInfoNameUnique(String name) {
        int count = mchInfoMapper.checkMchInfoNameUnique(name);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    @Override
    public List<MchInfo> selectMchInfo() {
        return mchInfoMapper.selectMchInfo();
    }

    @Override
    public int deleteMchInfo(String ids) {
        Integer[] userIds = Convert.toIntArray(ids);
        return mchInfoMapper.deleteMchInfo(userIds);
    }
}
