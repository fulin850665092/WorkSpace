<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>主页</title>
		<%-- jsp头部的引入
	包含 CSS样式
	Jquery的引入
	Base标签
 --%>
<%@ include file="/pages/common/header.jsp" %>
	</head>
	<body>
		<div style="  width: 100%; height: 202px;text-align: center;">
			<div style="margin-top: 100px;color: red;">
				<span style="font-size: 25px;">恭喜你注册成功！</span>
			</div>
			
			<a style="font-size: 20px;color: blue;" href="/user/login.html">登录</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a  style="font-size: 20px;color: blue;" href="/index/index.html">首页</a>
		</div>
				
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

