package com.bookkeeping.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.pojo.UserInfo;
import com.bookkeeping.service.UserService;

@RestController
@RequestMapping("/mybatis")
public class HelloController {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	public Object hello() {
		
		String sql = "select Wname from workers WHERE Wno = ?";
		
		String mobile = (String)jdbcTemplate.queryForObject(
                sql, new Object[] { 001 }, String.class);
		
		return "Hello " + mobile;
	}
	
	@RequestMapping("/saveUser")
	public JSONResult saveUser() throws Exception{
		
		UserInfo user = new UserInfo();
		user.setAccount("123456");
		user.setName("测试");
		user.setNetname("昵称");
		user.setPassword("123456");
		user.setTelephone("14735225864");
		
		userService.saveUser(user);
		
		return JSONResult.ok();
	}
	
	@RequestMapping("/queryByAccount")
	public JSONResult queryUserByAccount(String account) {
		System.out.println(account);
		return JSONResult.ok(userService.queryUserByAccount(account));
		
	}
}
