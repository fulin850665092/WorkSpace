package com.hengyangshiyuan.hrsystem.controlle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hengyangshiyuan.hrsystem.bean.Address;
import com.hengyangshiyuan.hrsystem.bean.Order;
import com.hengyangshiyuan.hrsystem.bean.User;
import com.hengyangshiyuan.hrsystem.dao.AddressDao;
import com.hengyangshiyuan.hrsystem.service.AddressService;
import com.hengyangshiyuan.hrsystem.service.CartService;
import com.hengyangshiyuan.hrsystem.service.CommodityService;
import com.hengyangshiyuan.hrsystem.service.OrderService;
import com.hengyangshiyuan.hrsystem.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {
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
	private AddressService addressService;
	
	/**
	 * 订单生成页面
	 */
	@RequestMapping("/creatOrder.html")
	private String CreatOrder(){
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "/user/login";
		}
		int userId = user.getId();
		// 2.调用orderService.myOrders()处理业务
		List<Map<String, Object>> orders = cartService.geMyOrders(userId);
		List<Map<String, String>> pathList=new ArrayList<>();
		Double marketPriceCount=0.00;
		Double priceCount=0.00;		
		for (int i = 0; i < orders.size(); i++) {	
			String cartId = (String) orders.get(i).get("cartId");
			String typeId = (String) orders.get(i).get("typeId");						
			int countInt = (int) orders.get(i).get("count");
			String count = String.valueOf(countInt);
			String name=(String) orders.get(i).get("name");
			BigDecimal priceBigDecimal=(BigDecimal) orders.get(i).get("price");
			//小计价格
			Double ltPriceCount=priceBigDecimal.doubleValue()*countInt;
			String ltPriceCountStr=String.valueOf(ltPriceCount);
			String price=String.valueOf(priceBigDecimal);	
			BigDecimal marketPriceDouble=(BigDecimal) orders.get(i).get("marketPrice");			
			//网页小计价格
			Double wyPriceCount=marketPriceDouble.doubleValue()*countInt;	
			String wyPriceCountStr=String.valueOf(wyPriceCount);
			//计算网页价格总数	
			marketPriceCount=marketPriceCount+wyPriceCount;
			//计算价格总数
			priceCount=priceCount+ltPriceCount;
			String marketPrice=String.valueOf(marketPriceDouble);	
			String specification = (String) orders.get(i).get("specification");		
			String labelId = (String) orders.get(i).get("commodityId");
			Map<String, String> map=new HashMap<>();
			map.put("cartId", cartId);	
			map.put("name", name);
			map.put("price", price);
			map.put("labelId", labelId);
			map.put("marketPrice", marketPrice);			
			map.put("count", count);
			map.put("specification", specification);
			map.put("ltPriceCountStr", ltPriceCountStr);
			map.put("wyPriceCountStr", wyPriceCountStr);
			pathList.add(map);
		}
		//差价
		Double chaPrice=priceCount-marketPriceCount;		
		String chaPriceStr = String.valueOf(chaPrice);	
		//百分比
		Double baifenBi=marketPriceCount/priceCount*100;
		String baifenBiStr = String.valueOf(baifenBi)+"%";	
		
		String marketPriceCountStr = String.valueOf(marketPriceCount);
		String priceCountStr = String.valueOf(priceCount);
		//查询默认地址
		Address address=addressService.queryAddress(userId);
		// 3.保存数据在request域对象中
		request.setAttribute("pathList", pathList);
		request.setAttribute("marketPriceCountStr", marketPriceCountStr);	
		request.setAttribute("priceCountStr", priceCountStr);
		request.setAttribute("chaPriceStr", chaPriceStr);
		request.setAttribute("baifenBiStr", baifenBiStr);
		request.setAttribute("address", address);
		return "/commodity/pay";
	}
	
	/**
	 * 订单页面更新默认地址
	 */
	@RequestMapping("/updateAdress.html")
	private String updateAdress(){
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "/user/login";
		}
		int userId = user.getId();
		String recname=request.getParameter("recname");
		String phone=request.getParameter("phone");
		String addressStr=request.getParameter("addressStr");
		String email=request.getParameter("email");
		Address address= new Address();
		address.setAddressStr(addressStr);
		address.setAddressId(userId);
		address.setEmail(email);
		address.setPhone(phone);
		address.setRecname(recname);
		addressService.updateAdress(address);
		return CreatOrder();
	}
	/**
	 * 生成订单支付
	 */
	@RequestMapping("/payForInto.html")
	private String payFor(){
		String recName =request.getParameter("recname");
		String mobile =  request.getParameter("mobile");
		String address =  request.getParameter("address");
		String email =  request.getParameter("email");
		
		User user = (User) request.getSession().getAttribute("user");
		int userId=user.getId();
		//生成订单号
		String orderId = System.currentTimeMillis() + "" +userId;
		//修改订单状态为1（支付）
		String status="1";
		List<Map<String, Object>> orders = cartService.geMyOrders(userId);
		//订单总价
		Double marketPriceCount=0.00;
		String priceStr=null;
		try {
			//有问题 要做判断
			for (int i = 0; i < orders.size(); i++) {	
				String cartId = (String) orders.get(i).get("cartId");
				//订单的数量
				int countInt = (int) orders.get(i).get("count");					
				String labelId = (String) orders.get(i).get("commodityId");
				BigDecimal marketPriceDouble=(BigDecimal) orders.get(i).get("marketPrice");			
				//网页小计价格
				Double wyPriceCount=marketPriceDouble.doubleValue()*countInt;			
				//计算网页价格总数	
				marketPriceCount=marketPriceCount+wyPriceCount;
				cartService.updataOrderIdAndStatusBycartId(cartId,orderId,status);
				//库存
				int countCommodity =commodityService.queryCountByLabelId(labelId);
				int countUpd=countCommodity-countInt;
				commodityService.updataCountByLabelId(labelId,countUpd);
			}
			//钱都用BigDecimal类型
			BigDecimal price = BigDecimal.valueOf(marketPriceCount);			
			orderService.creatOrder(email,mobile,address,price,userId,orderId,recName);
			priceStr = price.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if (null!=user) {
			int num=cartService.queryCountByUserId(user.getId());
			System.out.println(num);
			request.getSession().setAttribute("num", num);
		}
			request.setAttribute("priceStr", priceStr);
//			System.out.println(recName);
			return "/commodity/paySuccess";
	}
	/**
	 * 扫描支付页面
	 * @return
	 */
	@RequestMapping("/payfor.html")
	public String get(){
		User user = (User) request.getSession().getAttribute("user");
		if (null!=user) {
			int num=cartService.queryCountByUserId(user.getId());
			System.out.println(num);
			request.getSession().setAttribute("num", num);
		}
		return "/commodity/paySuccess";
	}
}
