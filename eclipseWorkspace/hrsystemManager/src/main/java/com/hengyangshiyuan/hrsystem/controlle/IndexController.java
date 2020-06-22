package com.hengyangshiyuan.hrsystem.controlle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.bcel.generic.LSTORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hengyangshiyuan.hrsystem.bean.Commodity;
import com.hengyangshiyuan.hrsystem.bean.User;
import com.hengyangshiyuan.hrsystem.service.CartService;
import com.hengyangshiyuan.hrsystem.service.CommodityService;

@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private CommodityService commodityService;
	@Autowired 
	private HttpServletRequest request;
	@Autowired
	private CartService cartService;
	/*
	 * 首页，并查询需要展示的商品
	 */
	@RequestMapping("/index.html")
	public  String index(){
		//获取需要展示的商品
		String display="Y";
		// 1\获取请求的id，然后查询到你需要的图书信息。
		List<Commodity> commodityList=commodityService.queryAllByDisplay(display);		
		List<Map<String, String>> pathList=new ArrayList<>();
		for (int i = 0; i < commodityList.size(); i++) {	
			String pathSrc="/image/commodities/"+commodityList.get(i).getTypeId()+"/"+commodityList.get(i).getImgPath()+"/1.jpg";		
			String name=commodityList.get(i).getName();
			String labelId = commodityList.get(i).getLabelId();	
			Map<String, String> map=new HashMap<>();
			map.put("pathSrc", pathSrc);
			map.put("labelId", labelId);
			map.put("name", name);
			pathList.add(map);
		}
		User user = (User) request.getSession().getAttribute("user");
		if (null!=user) {
			int num=cartService.queryCountByUserId(user.getId());
			System.out.println(num);
			request.getSession().setAttribute("num", num);
		}
		
		// 2\保存到域对象中request
		request.setAttribute("pathList", pathList);
		return "commodity/index";
	}
}
