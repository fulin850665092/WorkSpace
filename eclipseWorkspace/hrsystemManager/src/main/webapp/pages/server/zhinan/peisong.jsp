<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
	<style type="text/css">
		.help_left {
    width: 200px;
    color: #333;
    float: left;
    background: #fbfbfb;
    height: 100%;
    border: 1px solid #d3d3d3;
    padding-bottom: 25px;
}
	
	</style>
<%-- jsp头部的引入
		包含 CSS样式
		Jquery的引入
		Base标签
	 --%>
	 <link href="/static/css/header.css" rel='stylesheet' type='text/css' />
	  <link href="/static/css/article_c.css" rel='stylesheet' type='text/css' />
	<%@ include file="/pages/common/header.jsp" %>
<%-- 	<%@ include file="/pages/common/adminManagerCom.jsp" %> --%>
	</head>
	<body style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体; color: rgb(102, 102, 102);">
	

	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

