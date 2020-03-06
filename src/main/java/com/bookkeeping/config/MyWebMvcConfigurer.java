package com.bookkeeping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bookkeeping.contrller.interceptor.TestInterceptor;
import com.bookkeeping.contrller.interceptor.UserOperInterceptor;


@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	
	/**
	 *  将自定义拦截器作为Bean写入配置
	 *  (拦截器中操作Redis缓存
	 *  按照 controller，service层配置发现无法注入
	 *  一直报空指针异常。)
	 * @return 解决注入依赖失败的办法
	 */
	@Bean
	public UserOperInterceptor userOperInterceptor() {
		return new UserOperInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		/*
		 * // 测试 registry.addInterceptor(new
		 * TestInterceptor()).addPathPatterns("/interceptor/**");
		 */
		
		// 登录以及登录后操作的拦截器
		registry.addInterceptor(userOperInterceptor()).addPathPatterns("/userOperation/**");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	
	
}
