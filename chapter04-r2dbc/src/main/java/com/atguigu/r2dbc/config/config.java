package com.atguigu.r2dbc.config;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/4 17:14
 */

import com.atguigu.r2dbc.config.converter.BookConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.MySqlDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories //开启 R2dbc 仓库功能；jpa
@Configuration
public class config {
    @Bean //替换容器中原来的
    @ConditionalOnMissingBean
    public R2dbcCustomConversions conversions(){

        //把我们的转换器加入进去； 效果新增了我们的 Converter
        return R2dbcCustomConversions.of(MySqlDialect.INSTANCE,new BookConverter());
    }
}
