package com.bookkeeping.test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.bookkeeping.mapper.UserInfoMapperCustom;
import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.pojo.UserInfo;
import com.bookkeeping.service.UserService;

@RequestMapping("test")
@RestController
public class HelloController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private Sid sid;

	@Autowired
	private UserService userService;

	@Autowired
	private UserInfoMapperCustom mapper;

	/*
	 * @RequestMapping("/hello") public Object hello(HttpServletRequest request) {
	 * 
	 * //String sql = "select Wname from workers WHERE Wno = ?";
	 * 
	 * 
	 * for (int i = 0; i < 10; i++) { System.out.println(sid.nextShort()); }
	 * 
	 * //String mobile = (String)jdbcTemplate.queryForObject( //sql, new Object[] {
	 * 001 }, String.class); Cookie cookie = new Cookie("sessionId",
	 * sid.nextShort()); //response.addCookie(cookie);
	 * 
	 * HttpSession session = request.getSession(); String sessionId =
	 * session.getId();
	 * 
	 * UserInfo user = mapper.queryUserByAccount("11");
	 * 
	 * return "sessionId: " + sessionId;
	 * 
	 * }
	 */

	@Autowired
	private StringRedisTemplate redis;

	@RequestMapping("/redis")
	public JSONResult test() {

		redis.opsForValue().set("my-redis", "hello redis ~~~~");

		String str = redis.opsForValue().get("my-redis");

		return JSONResult.ok(str);
	}

	/*
	 * @RequestMapping("/saveUser") public JSONResult saveUser() throws Exception{
	 * 
	 * UserInfo user = new UserInfo(); user.setAccount("123456");
	 * user.setName("测试"); user.setNetname("昵称"); user.setPassword("123456");
	 * user.setTelephone("14735225864");
	 * 
	 * userService.saveUser(user);
	 * 
	 * return JSONResult.ok(); }
	 * 
	 * @RequestMapping("/queryByAccount") public JSONResult
	 * queryUserByAccount(String account) { System.out.println(account); return
	 * JSONResult.ok(userService.queryUserByAccount(account));
	 * 
	 * }
	 */
}
