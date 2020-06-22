package com.hengyangshiyuan.hrsystem.controlle;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;








import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hengyangshiyuan.hrsystem.bean.Cart;
import com.hengyangshiyuan.hrsystem.bean.CartItem;
import com.hengyangshiyuan.hrsystem.bean.Commodity;
import com.hengyangshiyuan.hrsystem.bean.User;
import com.hengyangshiyuan.hrsystem.service.CartService;
import com.hengyangshiyuan.hrsystem.service.CommodityService;
import com.hengyangshiyuan.hrsystem.service.UserService;
import com.hengyangshiyuan.hrsystem.utils.Utils;

@Controller
@RequestMapping("/cart")
public class CartController {
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
	
	/**
	 * 查看我的购物车
	 * @return
	 */
	@RequestMapping("/myCart.html")
	public String myCart() {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
//			// 重定向和转发之后。不要再执行任何的业务代码，或者功能代码。
//			response.sendRedirect(request.getContextPath()
//					+ "/pages/user/login.jsp");
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
			String imgPath = (String) orders.get(i).get("imgPath");
			String pathSrc="/image/commodities/"+typeId+"/"+imgPath+"/1.jpg";	;		
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
			map.put("pathSrc", pathSrc);
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
	
		if (null!=user) {
			int num=cartService.queryCountByUserId(user.getId());
			System.out.println(num);
			request.getSession().setAttribute("num", num);
		}
		// 3.保存数据在request域对象中
		request.setAttribute("pathList", pathList);
		request.setAttribute("marketPriceCountStr", marketPriceCountStr);	
		request.setAttribute("priceCountStr", priceCountStr);
		request.setAttribute("chaPriceStr", chaPriceStr);
		request.setAttribute("baifenBiStr", baifenBiStr);
		return "/commodity/cart";
	}
	/**
	 * 跟据购物id删除购物车的商品
	 * @return
	 */
	@RequestMapping("/deleteCart.html")
	private String deleteItem(){
		// 1.获取请求的参数
		String cartId = request.getParameter("cartId");
		try {
			if (cartId!=null&&cartId!="") {
				
				cartService.deleteCartByCartId(cartId);
				User user = (User) request.getSession().getAttribute("user");
				int userId=user.getId();
				int num=cartService.queryCountByUserId(userId);
				System.out.println(num);
				request.getSession().setAttribute("num", num);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return myCart();
	}
	
	/**
	 * 清空购物车
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/clear.html")
	private String clear(){
		try {
			
			User user = (User) request.getSession().getAttribute("user");
			int userId=user.getId();
			cartService.clearCart(userId);
			int num=cartService.queryCountByUserId(userId);
			System.out.println(num);
			request.getSession().setAttribute("num", num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myCart();
	}
	/**
	 * 更新购物车商品数量
	 */
	@RequestMapping("/updateCount.html")
	private String updateCount()  {
		// 1.获取请求的参数
		String cartId = request.getParameter("cartId");
		System.err.println(cartId);
		String countStr=request.getParameter("count");		
		System.err.println(countStr);
		try {
			if (countStr!=null&&countStr!="") {
				int count= Integer.valueOf(countStr);			
				cartService.updateCountByCartId(cartId,count);
				User user = (User) request.getSession().getAttribute("user");
				int userId=user.getId();
				int num=cartService.queryCountByUserId(userId);
				System.out.println(num);
				request.getSession().setAttribute("num", num);
				} 
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  myCart();
	}	

	/**
	 * 添加商品到购物车
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("addItem.html")
	@ResponseBody
	private void  addItem() {
		// 1.获取请求的参数。
		String labelId = request.getParameter("labelId");
		String username = request.getParameter("username");
		User user = userService.queryUserIdByUsername(username);
		int userId=user.getId();
		//根据userId,labelId查询c相对应cart车信息
		Cart cart=cartService.queryCartByUserId(userId,labelId);
		//查询购物车表该商品在此用户中是否已经存在，存在数量加1,使用原先的	cartId
		if (cart!=null) {
			int countNum = cart.getCount();
			//查询商品数量判断是否还能再添加购物车
			int commodityCount= commodityService.queryCountByLabelId(labelId);
			if (commodityCount>countNum) {
				int count=countNum+1;
				//更新数量
				cartService.updateCount(cart.getCartId(),count);
			}else{
				
				int num=cartService.queryCountByUserId(userId);
				System.out.println(num);
				request.getSession().setAttribute("num", num);
				String jsonString = "";
				response.setContentType("text/html;charset=utf-8");
					String msg="改商品在购物车中数量已达上限，不能再添加！";
					jsonString ="";
				
					try {
						jsonString =JSONObject.toJSONString(msg);
						PrintWriter pw1 = response.getWriter();
						pw1.write(jsonString);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				return;
				
			}
			
		}else {
			//没有则创建cartId (我们使用当前时间+用户id)
			String cartId = System.currentTimeMillis() + "" + userId;
			//插入cart操作
			cartService.addCart(cartId,labelId,userId);
		}
		int num=cartService.queryCountByUserId(userId);
		System.out.println(num);
		request.getSession().setAttribute("num", num);
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
	}
}
