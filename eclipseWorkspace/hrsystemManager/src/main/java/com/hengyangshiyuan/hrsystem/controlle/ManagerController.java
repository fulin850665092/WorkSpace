package com.hengyangshiyuan.hrsystem.controlle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hengyangshiyuan.hrsystem.bean.Address;
import com.hengyangshiyuan.hrsystem.bean.Order;
import com.hengyangshiyuan.hrsystem.bean.User;
import com.hengyangshiyuan.hrsystem.service.AddressService;
import com.hengyangshiyuan.hrsystem.service.CartService;
import com.hengyangshiyuan.hrsystem.service.CommodityService;
import com.hengyangshiyuan.hrsystem.service.ManagerService;
import com.hengyangshiyuan.hrsystem.service.OrderService;
import com.hengyangshiyuan.hrsystem.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
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
	@Autowired
	private AddressService addressService;
	/**
	 * 进入用户欢迎页界面
	 * @return
	 */
	@RequestMapping("/manager.html")
	public String getManagerPage(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			//已登录
			int userId=user.getId();
			String usertype = managerService.getType(userId);
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
				int count=orderService.queryCountOrderByUserId(userId);
				//模拟管理员账户查询
				String userTypeString="S";
				String gonggaoStr=managerService.queryGongGao(userTypeString);
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
	/**
	 * 进入用户信息页面
	 * @return
	 */
	@RequestMapping("/user.html")
	public String getUser(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			String username = user.getUsername();
			User userInf = userService.queryUserIdByUsername(username);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date brithday = userInf.getBrithday();
			if (null!=brithday) {
				String brithdayStr = dateFormat.format(brithday);
				request.setAttribute("brithdayStr", brithdayStr);
			}
			
			request.setAttribute("userInf", userInf);
			return "/manager/user";
		}
	}
	/**
	 * 更新个人用户信息
	 * @return
	 */
	@RequestMapping("/userUpde.html")
	public String userUpde(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
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
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			
			userService.updateUser(user.getId(),brithday,sex,email,phone);
			return  getUser();
		}
	}
	/**
	 * 更新个人账户密码
	 * @return
	 */
	@RequestMapping("/updePassword.html")
	public String updePassword(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			if (""!=oldPassword&&null!=oldPassword) {
				String password = DigestUtils.md5Hex(oldPassword);
				User usernameAndPassword = userService.queryUserByUsernameAndPassword(user.getUsername(), password);
				if (usernameAndPassword!=null) {
					if (""!=newPassword&&null!=newPassword) {
						String newpasswod = DigestUtils.md5Hex(newPassword);
						userService.updePassword(user.getId(),newpasswod);
					}				
				}else {
					String Msg="更改密码失败！（原密码错误，请重新修改！）";
					String referer = request.getHeader("referer");
					System.out.println(referer);
					if(null==referer){
						referer="http://localhost:8001/index/index.html";
					}
					request.getSession().setAttribute("referer", referer);		
					request.setAttribute("Msg", Msg);
					return "/commodity/404.jsp";
				}
			}
			return  getUser();
		}
	}
	
	/**
	 * 进入我的地址页面
	 * @return
	 */
	@RequestMapping("/addrees.html")
	public String getAddrees(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			//查出默认地址回显
			Address queryAddress = addressService.queryAddress(user.getId());
			List<Address> addressList=addressService.queryAddressList(user.getId());
			List<Map<String, String>> list=new ArrayList<Map<String,String>>();		
			for (int i = 0; i < addressList.size(); i++) {
				Map<String, String> map=new HashMap<String, String>();
				String addressString = "地址："+addressList.get(i).getAddressStr()+"，收件人："+addressList.get(i).getRecname()
									+"，收件人电话："+addressList.get(i).getPhone();
				String typeString=addressList.get(i).getType();
				int idInt = addressList.get(i).getId();
				String id=String.valueOf(idInt);
				String index=String.valueOf(i);
				map.put("addressString", addressString);
				map.put("typeString", typeString);
				map.put("id", id);
				map.put("index", index);
				list.add(map);
			}
			request.setAttribute("list", list);
			request.setAttribute("queryAddress", queryAddress);
			return "/manager/address";
		}
		
	}
	/**
	 * 新增地址
	 * @return
	 */
	@RequestMapping("/addAddress.html")
	public String addAddress(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			String recname=request.getParameter("recname");
			String phone=request.getParameter("phone");
			String addressStr=request.getParameter("addressStr");
			String email=request.getParameter("email");
			String zipcode=request.getParameter("zipcode");
			Address address= new Address();
			address.setAddressStr(addressStr);
			address.setAddressId(user.getId());
			address.setEmail(email);
			address.setPhone(phone);
			address.setRecname(recname);
			address.setZipcode(zipcode);
			addressService.addAdress(address);
			return  getAddrees();
		}
		
	}
	
	/**
	 * 修改默认地址
	 * @return
	 */
	@RequestMapping("/updateType.html")
	public String updateType(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			String type=request.getParameter("type");
			String idStr=request.getParameter("id");
			int id=Integer.valueOf(idStr);
			Address queryAddress = addressService.queryAddress(user.getId());
			if (queryAddress!=null) {
				String updateType="A";
				addressService.updateTypeByupdateType(updateType,user.getId());
				addressService.updateType(type,id);
			}else {
				addressService.updateType(type,id);
			}
			
			return getAddrees();
		}
		
	}
	/**
	 * 修改默认地址
	 * @return
	 */
	@RequestMapping("/deleteAddress.html")
	public String deleteAddress(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			String type=request.getParameter("type");
			String idStr=request.getParameter("id");
			int id=Integer.valueOf(idStr);			
			try {
				addressService.deleteAddress(type,id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return getAddrees();
		}
		
	}
	
	/**
	 * 进入我的订单页面
	 * @return
	 */
	@RequestMapping("/order.html")
	public String getOrder(){
		User user = (User) request.getSession().getAttribute("user");
		//判断是否登录
		if (null==user) {
			return "/user/login";
		}else {
			//已登录
			int userId=user.getId();
			String usertype = managerService.getType(userId);
			if (usertype.equals("S")) {
				//查询所有订单
				List<Order> orderList=orderService.queryOrderListByUserId(userId);
				
				request.setAttribute("orderList", orderList);
				//管理员订单界面
				return "/manager/adminOrder";
			}else {
				//我的订单
				List<Order> orderList=orderService.queryOrderListByUserId(userId);
				for (int i = 0; i < orderList.size(); i++) {
					String orderId = orderList.get(i).getOrderId();
					int count = cartService.queryCountByOrderId(orderId);
					//临时将商品数量存为phone字段
					orderList.get(i).setPhone(String.valueOf(count));
				}
				request.setAttribute("orderList", orderList);
				//会员订单展示界面
				return "/manager/order";
			}
		
		}	
		
	}
}
