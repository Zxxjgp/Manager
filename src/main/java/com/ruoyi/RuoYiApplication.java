package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.ruoyi.project.*.*.mapper")
@EnableScheduling
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ 启动成功   ლ(´ڡ`ლ)ﾞ\n" +
                " |    |                  |    |  \n" +
                " |    |                  |    |  \n" +
                " |    |                  |    |  \n" +
                " |----|     江苏华生基因   |----|  \n" +
                " |    |                  |    |  \n" +
                " |    |                  |    |  \n" +
                " |    |                  |    |  \n" +
                " |    |                  |    |  \n" +
                "                                          ");
    }
}