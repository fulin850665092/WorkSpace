package com.hengyangshiyuan.hrsystem.controlle;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.hengyangshiyuan.hrsystem.bean.Commodity;
import com.hengyangshiyuan.hrsystem.bean.Page;
import com.hengyangshiyuan.hrsystem.bean.User;
import com.hengyangshiyuan.hrsystem.service.CartService;
import com.hengyangshiyuan.hrsystem.service.CommodityService;
import com.hengyangshiyuan.hrsystem.utils.Utils;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping("/commodity")
public class CommodityController {
	@Autowired
	private CommodityService commodityService;
	@Autowired 
	private HttpServletRequest request;
	@Autowired
	private CartService cartService;

	
	/**
	 * 全部商品（分页功能）
	 */
	@RequestMapping("/page.html")
	protected String page(){
		// 1、先获取请求的参数pageNo和pageSize
		int pageNo = Utils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = Utils.parseInt(request.getParameter("pageSize"),
				Page.PAGE_SIZE);
		// 2、调用bookService.page方法处理分页业务
		Page<Commodity> page = commodityService.page(pageNo, pageSize);
		List<Commodity> commodityList = page.getItems();
		List<Map<String, String>> pathList=new ArrayList<>();
		for (int i = 0; i < commodityList.size(); i++) {	
			
			String pathSrc="/image/commodities/"+commodityList.get(i).getTypeId()+"/"+commodityList.get(i).getImgPath()+"/1.jpg";		
			String name=commodityList.get(i).getName();
			Double priceDouble=commodityList.get(i).getPrice();
			String labelId = commodityList.get(i).getLabelId();			
			String price=String.valueOf(priceDouble);
			Map<String, String> map=new HashMap<>();
			map.put("pathSrc", pathSrc);
			map.put("name", name);
			map.put("price", price);
			map.put("labelId", labelId);
			pathList.add(map);
		}
		User user=(User) request.getSession().getAttribute("user");
		if (null!=user) {
			int num=cartService.queryCountByUserId(user.getId());
			System.out.println(num);
			request.getSession().setAttribute("num", num);
		}
		request.setAttribute("pathList", pathList);
		// 保存当前模块的分页的请求地址
		page.setUrl("/commodity/page.html");
		// 3、把分页的信息，保存到request域对象中
		request.setAttribute("page", page);
		// 4、转发到pages/manager/book_manager.jsp页面
		return "/commodity/collections";
	}
	/**
	 * 店铺新品（按照录入时间排序取前二十条为新品）

	 */
	@RequestMapping("/newCommodity.html")
	protected String getNewCommodity(){
		// 1、先获取请求的参数pageNo和pageSize	
		int pageNo = 0;
		int pageSize =20;
		// 2、调用bookService.page方法处理分页业务
		Page<Commodity> page = commodityService.getNewCommodity(pageNo, pageSize);
		List<Commodity> commodityList = page.getItems();
		List<Map<String, String>> pathList=new ArrayList<>();
		for (int i = 0; i < commodityList.size(); i++) {	
			String pathSrc="/image/commodities/"+commodityList.get(i).getTypeId()+"/"+commodityList.get(i).getImgPath()+"/1.jpg";		
			String name=commodityList.get(i).getName();
			Double priceDouble=commodityList.get(i).getPrice();
			String labelId = commodityList.get(i).getLabelId();			
			String price=String.valueOf(priceDouble);
			Map<String, String> map=new HashMap<>();
			map.put("pathSrc", pathSrc);
			map.put("name", name);
			map.put("price", price);
			map.put("labelId", labelId);
			pathList.add(map);
		}
		User user=(User) request.getSession().getAttribute("user");
		if (null!=user) {
			int num=cartService.queryCountByUserId(user.getId());
			System.out.println(num);
			request.getSession().setAttribute("num", num);
		}
		request.setAttribute("pathList", pathList);
		// 保存当前模块的分页的请求地址
		page.setUrl("/commodity/page.html");
		// 3、把分页的信息，保存到request域对象中
		request.setAttribute("page", page);
		// 4、转发到pages/manager/book_manager.jsp页面
		return "/commodity/collections";
	}

	/**
	 * 按照商品类型查询并分页（功能）
	 * @param
	 */
	@RequestMapping("/queryByType.html")
	private String queryByType(){
		// 1、先获取请求的参数pageNo和pageSize
		int pageNo = Utils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = Utils.parseInt(request.getParameter("pageSize"),
				Page.PAGE_SIZE);
		String type = request.getParameter("type");
		if (type.equals(0)) {
			return page();
		}
		// 2、调用bookService.page方法处理分页业务
		Page<Commodity> page = commodityService.queryByType(pageNo, pageSize,type);
		List<Commodity> commodityList = page.getItems();
		List<Map<String, String>> pathList=new ArrayList<>();
		for (int i = 0; i < commodityList.size(); i++) {	
			String pathSrc="/image/commodities/"+commodityList.get(i).getTypeId()+"/"+commodityList.get(i).getImgPath()+"/1.jpg";		
			String name=commodityList.get(i).getName();
			Double priceDouble=commodityList.get(i).getPrice();
			String price=String.valueOf(priceDouble);
			String labelId = commodityList.get(i).getLabelId();
			Map<String, String> map=new HashMap<>();
			map.put("pathSrc", pathSrc);
			map.put("name", name);
			map.put("price", price);
			map.put("labelId", labelId);
			pathList.add(map);
		}
		request.setAttribute("pathList", pathList);
		User user=(User) request.getSession().getAttribute("user");
		if (null!=user) {
			int num=cartService.queryCountByUserId(user.getId());
			System.out.println(num);
			request.getSession().setAttribute("num", num);
		}
		// 保存当前模块的分页的请求地址
		page.setUrl("/commodity/page.html");
		// 3、把分页的信息，保存到request域对象中
		request.setAttribute("page", page);
		// 4、转发到pages/manager/book_manager.jsp页面
		return "/commodity/collections";
	}
	/**
	 * 查看商品详情
	 * @return
	 */
	
	@RequestMapping("/getCommodityDetails.html")
	private String getCommodityDetails(){
		String labelId = request.getParameter("labelId");
		Map<String, Object> map=commodityService.getCommodityDetails(labelId);
		String imgPath = (String) map.get("imgPath");
		String typeId= (String) map.get("typeId");
		for (int i = 1; i < 7; i++) {
			String pathSrc="pathSrc"+i;
			pathSrc="/image/commodities/"+typeId+"/"+imgPath+"/"+i+".jpg";
			map.put("pathSrc"+i, pathSrc);
		}
		User user=(User) request.getSession().getAttribute("user");
		if (null!=user) {
			int num=cartService.queryCountByUserId(user.getId());
			System.out.println(num);
			request.getSession().setAttribute("num", num);
		}
		String referer = request.getHeader("referer");
		System.out.println(referer);	
		request.getSession().setAttribute("referer", referer);
		request.setAttribute("map", map);
		return "/commodity/details";
	}
	/**
	 * 搜索商品
	 * @return
	 */
	
	@RequestMapping("/getCommodityBySearch.html")
	private String getCommodityBySearch(){		
		// 1、先获取请求的参数pageNo和pageSize
		int pageNo = Utils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = Utils.parseInt(request.getParameter("pageSize"),
				Page.PAGE_SIZE);
		String serach = request.getParameter("serach");
		//模糊查询拼接
		String name="%"+serach+"%";
		if (name.equals("%%")) {
			return page();
		}
		// 2、调用bookService.page方法处理分页业务
		Page<Commodity> page = commodityService.queryBySerach(pageNo, pageSize,name);
		List<Commodity> commodityList = page.getItems();
		List<Map<String, String>> pathList=new ArrayList<>();
		for (int i = 0; i < commodityList.size(); i++) {	
			String pathSrc="/image/commodities/"+commodityList.get(i).getTypeId()+"/"+commodityList.get(i).getImgPath()+"/1.jpg";		
			String name1=commodityList.get(i).getName();
			Double priceDouble=commodityList.get(i).getPrice();
			String price=String.valueOf(priceDouble);
			String labelId = commodityList.get(i).getLabelId();
			Map<String, String> map=new HashMap<>();
			map.put("pathSrc", pathSrc);
			map.put("name", name1);
			map.put("price", price);
			map.put("labelId", labelId);
			pathList.add(map);
		}
		User user=(User) request.getSession().getAttribute("user");
		if (null!=user) {
			int num=cartService.queryCountByUserId(user.getId());
			System.out.println(num);
			request.getSession().setAttribute("num", num);
		}
		request.setAttribute("pathList", pathList);
		// 保存当前模块的分页的请求地址
		page.setUrl("/commodity/page.html");
		// 3、把分页的信息，保存到request域对象中
		request.setAttribute("page", page);
		
		return "/commodity/collections";
	}
	
}
	
