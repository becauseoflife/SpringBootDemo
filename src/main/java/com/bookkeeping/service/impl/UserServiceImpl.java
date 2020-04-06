package com.bookkeeping.service.impl;

import java.util.Date;
import java.util.List;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookkeeping.appdata.entity.HomePageData;
import com.bookkeeping.appdata.entity.LimitCostData;
import com.bookkeeping.appdata.entity.PieChartSeries;
import com.bookkeeping.appdata.entity.RecordData;
import com.bookkeeping.appdata.entity.WeekMonthCost;
import com.bookkeeping.mapper.UserInfoMapper;
import com.bookkeeping.mapper.UserInfoMapperCustom;
import com.bookkeeping.mapper.UserRecordMapper;
import com.bookkeeping.model.WXSessionModel;
import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.pojo.UserInfo;
import com.bookkeeping.pojo.UserRecord;
import com.bookkeeping.service.UserService;
import com.bookkeeping.utils.JsonUtils;
import com.bookkeeping.utils.RedisOperator;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserInfoMapper userInfoMapper;		// 数据库对表user_info的操作
	
	@Autowired
	private UserRecordMapper userRecordMapper;	// 保存用户记录
	
	@Autowired
	private Sid sid;		// id生成器
	
	@Autowired
	private UserInfoMapperCustom userInfoCustomMapper;	// 通过某个字段查询用户信息（UserInfo.java）
	
	@Autowired
	private RedisOperator redisOper;		// 对Redis的操作
	



	// 用户注册
	@Override
	@Transactional(propagation = Propagation.REQUIRED)		// 事务的回滚
	public JSONResult userRedister(UserInfo user) {
		
		UserInfo getUser = userInfoCustomMapper.queryUserByAccount(user.getAccount());
		
		// 检查用户是否存在
		try {
			if (getUser != null) {
				// 判断表是否存在
				if(userRecordMapper.existTable(getUser.getId()) <= 0) {
					userRecordMapper.createTable(getUser.getId());
				}
				return JSONResult.errorMsg("账号已经存在");
			}else {
				// 创建id
				String id = sid.nextShort();
				user.setId(id);
				// 创建对应表储存信息
				String tableName = id;
				// 判断表是否存在
				userRecordMapper.createTable(tableName);
				
				/*
				 * if(userRecordMapper.existTable(tableName) > 0) { System.out.println("创建成功");
				 * }else { System.out.println("创建失败"); }
				 */
				
				userInfoMapper.insert(user);
				
				return JSONResult.ok("注册成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JSONResult.errorMsg(e.getMessage());
		}
	}

	// 用户登陆
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public JSONResult userLogin(HttpServletRequest request, String userAccount, String userPassword) {
		// TODO Auto-generated method stub
		// 检车账号是否存在
		UserInfo user = new UserInfo();
		user = userInfoCustomMapper.queryUserByAccount(userAccount);
		if(user == null) {
			return JSONResult.errorMsg("账号不存在，请先注册");
		}else {
			// 检查密码是否正确
			//System.out.println("user password: " + user.getPassword());
			if(!user.getPassword().equals(userPassword)) {
				return JSONResult.errorMsg("密码错误");
			}else {
				// 登录成功 保存session并返回sessionId	
				//System.out.println("SeesionId:" + request.getSession().getId());
				String sessionId = request.getSession().getId();
				
				WXSessionModel model = new WXSessionModel();
				model.setSessionId(sessionId);
				model.setExpiredTime(1 * 60 * 60 * 1000);
				model.setUserNetName(user.getNetname());
				model.setUserId(user.getId());
				model.setUserAccount(user.getAccount());
				model.setUserPassword(user.getPassword());
				
				// 保存session到redis
				redisOper.set("wxlogin-user-session:" + sessionId, JsonUtils.objectToJson(model), model.getExpiredTime());
				
				return JSONResult.ok("登录成功", model);
			}
		}

	}

	// 保存记账
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public JSONResult saveBookkeeping(HttpServletRequest request, String date, String time, String cost, String type, String imgPath) {

		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		
		String dateTimeStr = date + " " + time;
		//System.out.println("dataTimeStr:" + dateTimeStr);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateTime = new Date();
		try {
			dateTime = format.parse(dateTimeStr);
			//System.out.println("dateTime: " + dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// 创建一条记录
		UserRecord record = new UserRecord();
		record.setId(model.getUserId());
		record.setDate(dateTime);
		record.setCost(cost);
		record.setType(type);
		record.setImgPath(imgPath);
		
		// 插入数据库
		userRecordMapper.insertRecord(model.getUserId(), record);
		
		return JSONResult.ok("保存成功");
	}

	
	// 获取首页数据
	@Override
	public JSONResult getHomePageData(HttpServletRequest request) {
		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		String tableName = model.getUserId();
		
		// 获取花费的记录
		String yearCostStr = userRecordMapper.getYearSumCost(tableName);
		String monthCostStr = userRecordMapper.getMonthSumCost(tableName);
		String todayCostStr = userRecordMapper.getDaySumCost(tableName);
		
		// 获取收入的记录
		String yearIncomeStr = userRecordMapper.getYearSumIncome(tableName);
		String monthIncomeStr = userRecordMapper.getMonthSumIncome(tableName);
		String todayIncomeStr = userRecordMapper.getDaySumIncome(tableName);
		
		// 保留小数点后两位数
		DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
		
		//  总支出默认值为0
		Double yearCost = 0.00;
		Double monthCost = 0.00;
		Double todayCost = 0.00;
		
		// 总收入默认值为0
		Double yearIncome = 0.00;
		Double monthIncome = 0.00;
		Double todayIncome = 0.00;
		
		// 支出不为空则设置
		if (yearCostStr != null) 
			yearCost = Double.parseDouble(yearCostStr);
		if (monthCostStr != null) 
			monthCost = Double.parseDouble(monthCostStr);
		if (todayCostStr != null) 
			todayCost = Double.parseDouble(todayCostStr);
		
		// 收入不为空则设置
		if (yearIncomeStr != null) 
			yearIncome = Double.parseDouble(yearIncomeStr);
		if (monthIncomeStr != null) 
			monthIncome = Double.parseDouble(monthIncomeStr);
		if (todayIncomeStr != null) 
			todayIncome = Double.parseDouble(todayIncomeStr);
		
		
		// 封装数据
		HomePageData data = new HomePageData();
		data.setYearCost(df.format( Math.abs(yearCost) ));
		data.setMonthCost(df.format( Math.abs(monthCost) ));
		data.setTodayCost(df.format( Math.abs(todayCost) ));
		
		data.setYearIncome(df.format( Math.abs(yearIncome) ));
		data.setMonthIncome(df.format( Math.abs(monthIncome) ));
		data.setTodayIncome(df.format( Math.abs(todayIncome) ));
		
		return JSONResult.ok("获取首页数据成功", data);
	}

	// 获取记账记录
	@Override
	public JSONResult getRecords(HttpServletRequest request) {

		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		String tableName = model.getUserId();
		
		List<RecordData> recordList = userRecordMapper.queryRecords(tableName);
		
		/*
		 * for(RecordData r:recordList) { System.out.println(r.getDate() + " " +
		 * r.getCost() + " " + r.getType()); }
		 */
		
		return JSONResult.ok("获取记账历史记录成功", recordList);
	}

	// 获取饼状图数据
	@Override
	public JSONResult getPieChartData(HttpServletRequest request, String[] typeArray) {
		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		String tableName = model.getUserId();
		
		List<PieChartSeries> seriesList = userRecordMapper.queryTypeSum(tableName, typeArray);
		
		
		for(PieChartSeries pcs:seriesList) {
			System.out.println(pcs.getName() + "  " + pcs.getData());
		}
		
		return JSONResult.ok("获取饼状图数据成功", seriesList);
	}

	// 保存心愿存钱
	@Override
	public JSONResult saveWishMoney(HttpServletRequest request, String wishMoney) {
		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		String userId = model.getUserId();
		String userAccount = model.getUserAccount();
		
		userInfoCustomMapper.updateByUserId(userId, wishMoney);
		
		return JSONResult.ok("保存成功");
	}

	// 获取心愿存钱数
	@Override
	public JSONResult getWishMoney(HttpServletRequest request) {
		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		String userId = model.getUserId();
		
		double wishMoney = userInfoCustomMapper.queryWishMoney(userId);
		
		return JSONResult.ok("获取心愿存钱成功", wishMoney);
		
	}
	
	// 修改密码
	@Override
	public JSONResult changePwd(HttpServletRequest request, String oldPwd, String newPwd) {
		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		String userId = model.getUserId();
		String userPwd = model.getUserPassword();
		
		if(!oldPwd.equals(userPwd)) {
			return JSONResult.errorMsg("原密码输入错误");
		}else {
			userInfoCustomMapper.updatePassword(userId, newPwd);
			
			// 修改缓存中的session信息
			model.setUserPassword(newPwd);
			redisOper.set("wxlogin-user-session:" + sessionId, JsonUtils.objectToJson(model), model.getExpiredTime());
			
			return JSONResult.ok("修改成功");
		}

	}

	// 保存本周和本月最大花费额度
	@Override
	public JSONResult saveLimitCost(HttpServletRequest request, String weekMaxCost, String monthMaxCost) {
		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		String userId = model.getUserId();
		
		if (!weekMaxCost.equals("")) {
			userInfoCustomMapper.updateWeekMaxCost(userId, weekMaxCost);
		}
		if (!monthMaxCost.equals("")) {
			userInfoCustomMapper.updateMonthMaxCost(userId, monthMaxCost);
		}
		
		LimitCostData data = new LimitCostData();
		data.setWeekMaxCost(Double.parseDouble(weekMaxCost));
		data.setMonthMaxCost(Double.parseDouble(monthMaxCost));
		
		return JSONResult.ok("设置成功", data);
	}

	// 获取本周和本月的花费
	@Override
	public JSONResult getWeekMonthCost(HttpServletRequest request) {
		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		String tableName = model.getUserId();
		
		// 获取数据
		String weekCostStr = userRecordMapper.getWeekCost(tableName);
		String monthCostStr = userRecordMapper.getMonthSumCost(tableName);
		
		// 保留小数点后两位数
		DecimalFormat df = new DecimalFormat("#########0.00");
		Double weekCost = 0.00;
		Double monthCost= 0.00;
		
		if (weekCostStr != null) {
			weekCost = Double.parseDouble(weekCostStr);
		}
		if (monthCostStr != null) {
			monthCost = Double.parseDouble(monthCostStr);
		}
		
		// 封装数据
		WeekMonthCost cost = new WeekMonthCost();
		cost.setWeekCost(df.format( Math.abs(weekCost) ));
		cost.setMonthCost(df.format( Math.abs(monthCost) ));
		
		
		return JSONResult.ok("获取本周和本月的总消费成功", cost);
	}

	// 获取本周或本月限制的消费
	@Override
	public JSONResult getLimitCost(HttpServletRequest request) {
		
		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		String userId = model.getUserId();
		
		// 获取本周和本月最大消费额
		LimitCostData data = userInfoCustomMapper.queryMaxCost(userId);

		//System.out.println(data.getWeekMaxCost() + " " + data.getMonthMaxCost());
		
		return JSONResult.ok("获取本周和本月限制消费额度成功", data);
	}
	
	
	// 修改个人信息（网名和手机号）
	@Override
	public JSONResult resetUserInfo(HttpServletRequest request, String netName, String telephone) {
		// 获取session中的信息
		String sessionId = request.getHeader("sessionId");
		WXSessionModel model = JsonUtils.jsonToPojo(redisOper.get("wxlogin-user-session:" + sessionId), WXSessionModel.class);
		String userId = model.getUserId();
		
		if (!netName.equals("")) {
			userInfoCustomMapper.updateNetName(userId, netName);
		}
		if(!telephone.equals(""))
			userInfoCustomMapper.updateTelephone(userId, telephone);

		return JSONResult.ok("修改成功");
	}
	
	

}
