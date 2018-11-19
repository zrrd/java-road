package cn.learn.mybatis_plus.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybaitsPlus配置
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Configuration
@MapperScan(basePackages = {"cn.learn.mybaits_plus.application.steamParty.domain.mapper", "cn.learn.mybaits_plus.query.steamParty.mapper"})
public class MybatisPlusConfig {

}
