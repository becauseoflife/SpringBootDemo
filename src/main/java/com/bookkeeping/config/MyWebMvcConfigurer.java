package com.bookkeeping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bookkeeping.contrller.interceptor.TestInterceptor;


@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		
		registry.addInterceptor(new TestInterceptor()).addPathPatterns("/interceptor/**");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	
	
}
