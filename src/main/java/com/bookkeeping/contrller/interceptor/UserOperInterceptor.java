package com.bookkeeping.contrller.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bookkeeping.model.WXSessionModel;
import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.utils.JsonUtils;
import com.bookkeeping.utils.RedisOperator;


public class UserOperInterceptor implements HandlerInterceptor{

	@Autowired
	private RedisOperator redisOper;
	
	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		/*
		 * System.out.println(request.getSession().getId());
		 * System.out.println(request.getHeader("content-type"));
		 * System.out.println("account:" + request.getParameter("account") +
		 * "\npassword:" + request.getParameter("password"));
		 */
		// 判断是否已经登录 获取传过来的sessionId
		String resSessionId = request.getHeader("sessionId");
		System.out.println("获取的sessionId:" + resSessionId);
		
		//从redis缓存中读出数据
		String userAccount = request.getParameter("account");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("user-redis-session:" + userAccount), WXSessionModel.class);
		
		if (model != null) {
			System.out.println("redis的sessionId:" + model.getSessionId());
			
			// sessionId相等，则放行  不相等则返回错误信息
			if (resSessionId.equals(model.getSessionId())) {
				return true;
			}else {
				returnErrorResponse(response, JSONResult.errorMsg("sessionId错误，请重新登录"));
				return false;
			}
		}else {
			returnErrorResponse(response, JSONResult.errorMsg("请重新登录"));
			return false;
		}
	}

	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
	 * （主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	// 被拦截后的响应
	public void returnErrorResponse(HttpServletResponse response, JSONResult result) 
			throws IOException, UnsupportedEncodingException {
		OutputStream out=null;
		try{
		    response.setCharacterEncoding("utf-8");
		    response.setContentType("text/json");
		    out = response.getOutputStream();
		    out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
		    out.flush();
		} finally{
		    if(out!=null){
		        out.close();
		    }
		}
	}

}
