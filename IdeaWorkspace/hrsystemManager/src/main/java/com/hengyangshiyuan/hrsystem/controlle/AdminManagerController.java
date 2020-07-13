package com.hengyangshiyuan.hrsystem.controlle;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileSystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import scala.annotation.meta.param;
import scala.runtime.Statics;

import com.alibaba.fastjson.JSONObject;
import com.hengyangshiyuan.hrsystem.bean.Commodity;
import com.hengyangshiyuan.hrsystem.bean.Order;
import com.hengyangshiyuan.hrsystem.bean.Page;
import com.hengyangshiyuan.hrsystem.bean.User;
import com.hengyangshiyuan.hrsystem.service.CartService;
import com.hengyangshiyuan.hrsystem.service.CommodityService;
import com.hengyangshiyuan.hrsystem.service.ManagerService;
import com.hengyangshiyuan.hrsystem.service.OrderService;
import com.hengyangshiyuan.hrsystem.service.UserService;
import com.hengyangshiyuan.hrsystem.utils.UUIDUtil;
import com.hengyangshiyuan.hrsystem.utils.Utils;

@Controller
@RequestMapping("/adminManager")
public class AdminManagerController {
	private static final String String = null;
	@Autowired 
	private HttpServletRequest request;
	@Autowired 
	private HttpServletResponse response;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private CartService cartService; 
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ManagerService managerService;
	private static int prcName=1;
	
	/**
	 * 用户信息管理
	 * @return
	 */
	@RequestMapping("/user.html")
	public String getUser(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			//已登录	
			// 1、先获取请求的参数pageNo和pageSize
			int pageNo = Utils.parseInt(request.getParameter("pageNo"), 1);
			int pageSize =5;
			// 2、调用bookService.page方法处理分页业务
			Page<User> page = managerService.userpage(pageNo, pageSize);
			List<User> userList = page.getItems();
			for (int i = 0; i < userList.size(); i++) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date brithday = userList.get(i).getBrithday();
				if (null!=brithday) {
					String brithdayStr = dateFormat.format(brithday);
					//使用remark字段封装格式装换后的生日
					userList.get(i).setRemark(brithdayStr);
				}
			}
			request.setAttribute("userList", userList);
			request.setAttribute("page", page);
			page.setUrl("/adminManager/user.html");	
			return "/manager/userManager";
		}
	}
	/**
	 * 用户信息管理---更新用户信息
	 * @return
	 */
	@RequestMapping("/updateUser.html")
	public String updateUser(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			String id = request.getParameter("id");
			String sex = request.getParameter("sex");
			String name = request.getParameter("name");			
			String phone = request.getParameter("phone");
			String userType = request.getParameter("userType");
			String brithdayStr = request.getParameter("brithday");		
			Date brithday=null;
			if (null!=brithdayStr&&""!=brithdayStr) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					brithday= sdf.parse(brithdayStr);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				
			}
			int  paramId= Integer.valueOf(id);
			User paramUser=new User();
			paramUser.setId(paramId);	
			paramUser.setPhone(phone);
			paramUser.setBrithday(brithday);
			paramUser.setSex(sex);
			paramUser.setUsertype(userType);
			paramUser.setUsername(name);			
			userService.managerUpdateUser(paramUser);
			return  getUser();
		}
	}
	/**
	 * 用户信息管理---删除用户信息
	 * @return
	 */
	@RequestMapping("/deleteUser.html")
	public String deleteUser(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			String id = request.getParameter("id");		
			int  paramId= Integer.valueOf(id);				
			try {
				userService.deleteUser(paramId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  getUser();
		}
	}
	
	
	/*
	 * 订单管理
	 */
	@RequestMapping("/order.html")
	public String getOrder(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			//已登录	
			// 1、先获取请求的参数pageNo和pageSize
			int pageNo = Utils.parseInt(request.getParameter("pageNo"), 1);
			int pageSize = 4;
			// 2、调用bookService.page方法处理分页业务
			Page<Order> page = managerService.page(pageNo, pageSize);
			List<Order> orderList = page.getItems();
			//查询所有订单			
			for (int i = 0; i < orderList.size(); i++) {
				String orderId = orderList.get(i).getOrderId();
				int count = cartService.queryCountByOrderId(orderId);
				//临时将商品数量存为remark字段
				orderList.get(i).setRemark(String.valueOf(count));
			}
			request.setAttribute("orderList", orderList);
			request.setAttribute("page", page);
			page.setUrl("/adminManager/order.html");
			//管理员订单界面
			return "/manager/orderManager";
		}		
	}
	/**
	 * 订单管理--更新订单
	 * @return
	 */
	@RequestMapping("/updateOrder.html")
	public String updateOrder() {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
//			// 重定向和转发之后。不要再执行任何的业务代码，或者功能代码。
//			response.sendRedirect(request.getContextPath()
//					+ "/pages/user/login.jsp");
			return "/user/login";
		}
	
		String orderId = request.getParameter("orderId");
		String recName = request.getParameter("recName");
		String priceStr = request.getParameter("price");
		String phone = request.getParameter("phone");
		String statusStr = request.getParameter("status");
		Double priceDouble=Double.valueOf(priceStr);
		int status=Integer.valueOf(statusStr);
		String address = request.getParameter("address");		
		BigDecimal price=new BigDecimal(priceDouble);
		Order order= new Order();
		order.setOrderId(orderId);
		order.setPrice(price);
		order.setRecName(recName);
		order.setAddress(address);
		order.setStatus(status);
		order.setPhone(phone);
		try {	
			
			if (order!=null) {	
				
				orderService.updateOrder(order);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return getOrder();
	}
	
	/**
	 * 商品管理
	 * @return
	 */
	@RequestMapping("/commodity.html")
	public String commodity() {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
//			// 重定向和转发之后。不要再执行任何的业务代码，或者功能代码。
//			response.sendRedirect(request.getContextPath()
//					+ "/pages/user/login.jsp");
			return "/user/login";
		}
		// 1、先获取请求的参数pageNo和pageSize
		int pageNo = Utils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = 5;	
		Page<Commodity> page = commodityService.pageManager(pageNo, pageSize);
		List<Commodity> commodityList = page.getItems();		
		for (int i = 0; i < commodityList.size(); i++) {	
			String specificationStr = commodityList.get(i).getSpecification();
			java.lang.String specification = specificationStr.trim();
			
			String typeId = (String) commodityList.get(i).getTypeId();
			String imgPath = (String) commodityList.get(i).getImgPath();
			String pathSrc="/image/commodities/"+typeId+"/"+imgPath+"/1.jpg";		
			//使用remark保存图片路径
			commodityList.get(i).setRemark(pathSrc);
			commodityList.get(i).setSpecification(specification);
		}
		// 3.保存数据在request域对象中
		request.setAttribute("page", page);
		request.setAttribute("commodityList", commodityList);
		page.setUrl("/adminManager/commodity.html");
		//管理员订单界面
		return "/manager/commodityManager";
	}
	/**
	 * 商品管理--更新商品
	 * @return
	 */
	@RequestMapping("/updateCommodity.html")
	public String updateCommodity() {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
//			// 重定向和转发之后。不要再执行任何的业务代码，或者功能代码。
//			response.sendRedirect(request.getContextPath()
//					+ "/pages/user/login.jsp");
			return "/user/login";
		}
		String labelId = request.getParameter("labelId");
		String water = request.getParameter("water");
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		String marketPriceStr = request.getParameter("marketPrice");
		String countStr = request.getParameter("count");
		String display = request.getParameter("display");
		Double price=Double.valueOf(priceStr);
		Double marketPrice=Double.valueOf(marketPriceStr);
		int count=Integer.valueOf(countStr);
		Commodity commodity= new Commodity();
		commodity.setCount(count);
		commodity.setDisplay(display);
		commodity.setLabelId(labelId);
		commodity.setMarketPrice(marketPrice);
		commodity.setPrice(price);
		commodity.setWater(water);
		commodity.setName(name);	
		try {	
			
			if (commodity!=null) {	
				
				commodityService.updateCommodity(commodity);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		return commodity();
	}
	
	/**
	 * 商品管理--添加商品页面
	 * @return
	 */
	@RequestMapping("/insertCommodity.html")
	public String insertCommodity() {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
//			// 重定向和转发之后。不要再执行任何的业务代码，或者功能代码。
//			response.sendRedirect(request.getContextPath()
//					+ "/pages/user/login.jsp");
			return "/user/login";
		}
		
		 
		return "/manager/commodityAdmin";
	}
	/**
	 * 文件上传/录入商品
	 * @param file
	 * @param areaName
	 * @throws Exception
	 */
	@RequestMapping("/uploadImg.html")
	@ResponseBody
	public void uploadImg(MultipartFile file[], String areaName) throws Exception {	
		String name = request.getParameter("name");
		System.out.println("得到的name:"+name); 
		String priceStr = request.getParameter("price");
		String marketpriceStr = request.getParameter("marketprice");		
		String accessory = request.getParameter("accessory");
		String countStr = request.getParameter("count");
		String water = request.getParameter("water");
		String specification = request.getParameter("specification");
		String depiction = request.getParameter("depiction");
		String typeid = request.getParameter("typeid");
		String display = request.getParameter("display");
		Double price=Double.valueOf(priceStr);
		Double marketPrice=Double.valueOf(marketpriceStr);
		int count=Integer.valueOf(countStr);		
		//生成文件夹名
		String imgPath = UUIDUtil.getZZID();
		//截取imgPath前三位
		String labelIdStart=imgPath.substring(0, 3);
		//截取imgPath后六位
		String labelIdEnd=imgPath.substring( imgPath.length()-6, imgPath.length());
		//拼接成唯一的labelId
		String labelId=labelIdStart.trim()+labelIdEnd.trim();
		Commodity commodity=new Commodity();
		commodity.setAccessory(accessory);
		commodity.setDepiction(depiction);
		commodity.setDisplay(display);
		commodity.setName(name);
		commodity.setWater(water);
		commodity.setTypeId(typeid);
		commodity.setImgPath(imgPath);
		commodity.setSpecification(specification);
		commodity.setPrice(price);
		commodity.setMarketPrice(marketPrice);
		commodity.setCount(count);
		commodity.setLabelId(labelId);
		int queryCountByLabelId;
		if (file.length!=0) {
			try {
				commodityService.insertCommodity(commodity);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			queryCountByLabelId = commodityService.queryCountByLabelId(labelId);
			if (queryCountByLabelId!=0) {
				// 设置上传的路径是D盘下的picture
				String imgSrcPath = "F:/imgService/commodities/"+typeid+"/"+imgPath+"/";
//				String imgSrcPath="D:/picture/";
				for (MultipartFile f : file) {
					// 图片的名字用毫秒数+图片原来的名字拼接
					System.out.println(f.getSize());
					System.out.println(f.getBytes());
					String imgName = prcName + ".jpg";						
					if (prcName<=5) {
						prcName++;
					}else {
						prcName=1;
					} 
					//上传文件
					uploadFileUtil(f.getBytes(), imgSrcPath, imgName);
					
				}
			}
			String msg="添加购物车成功！";
			String jsonString = "";
			response.setContentType("text/html;charset=utf-8");
			try {
				jsonString =JSONObject.toJSONString(msg);
				PrintWriter pw= response.getWriter();
				pw.write(jsonString);
			} catch (IOException e1) {	
				e1.printStackTrace();
			}			

		}else {
			String msg="商品图片不能为空！";
			String jsonString = "";
			response.setContentType("text/html;charset=utf-8");
			try {
				jsonString =JSONObject.toJSONString(msg);
				PrintWriter pw= response.getWriter();
				pw.write(jsonString);
			} catch (IOException e1) {	
				e1.printStackTrace();
			}			
		}
		
	
	}

	/**
	 * 上传文件的方法
	 * @param file：文件的字节
	 * @param imgPath：文件的路径
	 * @param imgName：文件的名字
	 * @throws Exception
	 */
	public void uploadFileUtil(byte[] file, String imgPath, String imgName) throws Exception {
		File targetFile = new File(imgPath);
		System.out.println(imgPath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(imgPath + imgName);
		BufferedOutputStream bout=new BufferedOutputStream(out);
		bout.write(file);
		bout.flush();
		bout.close();
		out.close();
	}
	
	/**
	 * 公告管理
	 * @return
	 */
	@RequestMapping("/savaGongGao.html")
	public String savaGongGao() {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
//			// 重定向和转发之后。不要再执行任何的业务代码，或者功能代码。
//			response.sendRedirect(request.getContextPath()
//					+ "/pages/user/login.jsp");
			return "/user/login";
		}
		String gonggao = request.getParameter("gonggao");
		String usertype = managerService.getType(user.getId());
		userService.updateGongGao(gonggao,usertype);		
		String lastDate = (String) request.getSession().getAttribute("lastDate");
		request.setAttribute("lastDate", lastDate);
		if (usertype.equals("S")) {
			
			request.setAttribute("user", user);
			String gonggaoStr=managerService.queryGongGao(usertype).trim();
			int adminCount= userService.quseryByTypeAdmin();
			int huiyuanCount=userService.quseryByTypeHuiYuan();
			int allOrderCount = orderService.queryForPageTotalCount();
			request.setAttribute("adminCount",adminCount);
			request.setAttribute("huiyuanCount",huiyuanCount);
			request.setAttribute("allOrderCount",allOrderCount);
			request.setAttribute("gonggaoStr",gonggaoStr);
			//管理员界面
			return "/manager/managerAdmin";
		}else {
			//订单总数
			int count=orderService.queryCountOrderByUserId(user.getId());	
			//模拟管理员账户查询
			String userTypeString="S";
			String gonggaoStr=managerService.queryGongGao(userTypeString).trim();
			int jifen=count*10000;
			request.setAttribute("jifen", jifen);
			request.setAttribute("gonggaoStr",gonggaoStr);
			request.setAttribute("count", count);
			request.setAttribute("user", user);
			//会员界面
			return "/manager/manager";
		}
	
	}
}