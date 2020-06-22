 package com.hengyangshiyuan.hrsystem.controlle;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;




















import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hengyangshiyuan.hrsystem.bean.Commodity;
//import com.hengyangshiyuan.hrsystem.bean.Book;
import com.hengyangshiyuan.hrsystem.bean.Page;
import com.hengyangshiyuan.hrsystem.bean.User;
import com.hengyangshiyuan.hrsystem.service.CartService;
import com.hengyangshiyuan.hrsystem.service.CommodityService;
//import com.hengyangshiyuan.hrsystem.service.BookService;
import com.hengyangshiyuan.hrsystem.service.UserService;
import com.hengyangshiyuan.hrsystem.utils.Utils;
import com.mysql.jdbc.StringUtils;

/**
 * 登录、注册、主页
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserControlle  {
	
	@Autowired
	private UserService userService;
//	@Autowired
//	private BookService bookSrevice;
	@Autowired
    private DefaultKaptcha defaultKaptcha;
	
	@Autowired
	private CommodityService commodityService;
	@Autowired 
	private HttpServletRequest request;
	@Autowired
	private CartService cartService;

	
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/regist.html")
	public void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	 	ModelAndView andView = new ModelAndView();
	 	//服务器生成的的验证码
        String sessionToken = (String) request.getSession().getAttribute("code");
        request.getSession().removeAttribute("code");
        //用户输入生成的的验证码
        String code = request.getParameter("code");
        User user =new User();
        String username = request.getParameter("registusername");
		String passwordStr = request.getParameter("registpassword");
		user.setPassword(passwordStr);
		user.setUsername(username);
		System.out.println("服务器的验证码是：" + sessionToken);
		System.out.println("客户端的验证码是：" + code);
		
		// 2.先验证，这个验证是否有效。
		if (sessionToken != null && sessionToken.equalsIgnoreCase(code)&&code!=null) {
			// 验证码正常
			if (userService.existUsername(user.getUsername())) {
				// 说明用户名已存在
				System.out.println("用户名已【" + user.getUsername() + "】存在！");

				// 把需要回显的信息，保存到request域对象中
				request.setAttribute("msg", "用户名已【" + user.getUsername() + "】存在！");
				request.setAttribute("username", user.getUsername());
				request.setAttribute("email", user.getEmail());
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(
						request, response);
			} else {
				// 说明用户名可用
				// 保存到数据库
				String str = user.getPassword();
				//MD5加密密码
				String password = DigestUtils.md5Hex(str);
				System.out.println(password);
				user.setPassword(password);
				userService.regist(user);
				System.out.println("注册成功！【" + user.getUsername() + "】");
				request.getRequestDispatcher("/pages/user/regist_success.jsp")
						.forward(request, response);
			}
		} else {
			if(code==null){
				request.setAttribute("msg", "");
				request.setAttribute("username", user.getUsername());
				request.setAttribute("email", user.getEmail());
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(
						request, response);
			}else{
				// 验证码错误
				System.out.println("验证码错误！");
				// 把需要回显的信息，保存到request域对象中
				request.setAttribute("msg", "验证码错误！");
				request.setAttribute("username", user.getUsername());
				request.setAttribute("email", user.getEmail());
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(
						request, response);
			}
			
		}
	}
	/**
	 * 登录页面
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/login.html")
	public String login(){
		String referer = request.getHeader("referer");
		System.out.println(referer);
		if(null==referer){
			referer="http://localhost:8001/index/index.html";
		}
		request.getSession().setAttribute("referer", referer);
		return "user/login";
	}
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/ifLogin.html")
	public void ifLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取请求的参数 用户名和密码
		String username = request.getParameter("loginusername");
		String passwordStr = request.getParameter("loginpassword");
		User user=new User();
		user.setPassword(passwordStr);
		user.setUsername(username);
//		User user = Utils.copyParam2Bean(request.getParameterMap(), new User());
		String str = user.getPassword();
		if (str!=null&&str!="") {
			//MD5加密密码
			String password = DigestUtils.md5Hex(str);
			System.out.println(password);
			user.setPassword(password);
		}		
		// 2.调用UserService.login();去执行登录的业务
		User loginUser = userService.login(user);
		if (loginUser == null) {
			// 2.2 如果没有值，说明用户名和密码错误---- 跳转回pages/user/login.jsp页面
			System.out.println("用户名或密码错误！登录失败！");
			// 把需要回显的信息，保存到域对象中
			request.setAttribute("msg", "用户名或密码错误，登录失败！");
			request.setAttribute("username", user.getUsername());
			response.sendRedirect("/user/login");
//			return "/user/login";
		} else {
			System.out.println("登录成功！");
			
			//用户登录成功之后。我们应该把用户登录的信息。保存到Session域对象。
			//方便我们在工程的其他地方，获取用户登录的信息。
			request.getSession().setAttribute("user", loginUser);
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
			// 2\保存到域对象中request
			request.setAttribute("pathList", pathList);
			if (loginUser.getId()!=0) {
				int num = cartService.queryCountByUserId(loginUser.getId());
				request.getSession().setAttribute("num", num);
				System.err.println(num);
			}			
			String referer=(String) request.getSession().getAttribute("referer");
			System.out.println(referer);
			String lastDate = userService.queryLastDate(loginUser.getId());
			if (null!=lastDate||""!=lastDate) {
				request.getSession().setAttribute("lastDate", lastDate);
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String date=df.format(new Date());
			userService.UpdateLastDate(date,loginUser.getId());
	        System.out.println(date);// new Date()为获取当前系统时间
			if (null==referer||""==referer) {
//				response.sendRedirect("/user/index");
//				if(null==referer){
					referer="http://localhost:8001/index/index.html";
//				}
			}
			//返回前一个页面
			response.sendRedirect(referer);
		}
	}
	/**
	 * 动态验证码验证
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/check.html")
	public String check(HttpServletRequest request,HttpServletResponse response){
		String username =request.getParameter("username");
		String passwordStr =  request.getParameter("password");
		//MD5加密密码
		String password = DigestUtils.md5Hex(passwordStr);
		System.out.println(password);
		User user = userService.queryUserByUsernameAndPassword(username, password);
		String jsonString = "";
		response.setContentType("text/html;charset=utf-8");
		try {
			jsonString =JSONObject.toJSONString(user);
			PrintWriter pw= response.getWriter();
			pw.write(jsonString);
		} catch (IOException e1) {	
			e1.printStackTrace();
		}
		return null;
	}
	/**
	 * 用户登出
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/logout.html")
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//删除Session域对象中，用户登录的信息。
		request.getSession().removeAttribute("user");
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
		// 2\保存到域对象中request
		request.setAttribute("pathList", pathList);
		request.setAttribute("pathList", pathList);
		String referer=(String) request.getSession().getAttribute("referer");
		System.out.println(referer);

		if (null==referer||""==referer) {
				referer="http://localhost:8001/index/index.html";
			}
		//返回前一个页面
		response.sendRedirect(referer);

	}
	
	/**
	 * 服务器验证码生成
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws Exception
	 */
	@RequestMapping("/kaptchaImage")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("code", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
 
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
	
	@RequestMapping("/dd.html")
	public  String dd(){
		
		return "/user/regist_success";
	}

}
