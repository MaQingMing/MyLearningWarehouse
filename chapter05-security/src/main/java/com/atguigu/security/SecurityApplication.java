package com.atguigu.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/4 22:00
 */

/**
 * Spring Security 默认行为： 所有请求都需要登录才能访问
 * 1、SecurityAutoConfiguration：  以前
 *          导入 SecurityFilterChain 组件： 默认所有请求都需要登录才可以访问、默认登录页
 *
 * 2、SecurityFilterAutoConfiguration：
 * 3、ReactiveSecurityAutoConfiguration：
 *          导入 ServerHttpSecurityConfiguration 配置：注解导入 ServerHttpSecurityConfiguration
 * 4、MethodSecurityAspectJAutoProxyRegistrar：
 */
@SpringBootApplication
@ComponentScan("com.atguigu.controller")
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class,args);
    }
}
