<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>404</title>
	<%-- jsp头部的引入
			包含 CSS样式
			Jquery的引入
			Base标签
		 --%>
		<%@ include file="/pages/common/header.jsp" %>
	</head>
	<body>
		
		<!-- /sub-header -->
		<!-- content -->
		<!-- error-page -->
		<div class="error-page text-center">
			<div class="container">
				<h1>404</h1>
				<p>${Msg}</p>
				<a class="b-home" href="${referer}">返回</a>
			</div>
		</div>
			<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

