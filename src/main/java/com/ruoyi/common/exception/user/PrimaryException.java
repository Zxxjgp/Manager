package com.ruoyi.common.exception.user;


/**
 * 用户信息异常类
 * 
 * @author ruoyi
 */
public class PrimaryException extends UserException
{

    private static final long serialVersionUID = 1L;

    public PrimaryException()
    {
        super("主键存在异常，ChannelId不允许有重复", null);
    }

}
