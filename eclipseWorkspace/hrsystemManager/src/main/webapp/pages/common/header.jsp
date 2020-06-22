<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	/**
	*	getScheme() 得到http
	*	getServerName() 获取服务器的ip
	*	getServerPort() 获取服务器的端口号
	*/
	String path = request.getScheme() + "://" 
		+ request.getServerName() + ":"
		+ request.getServerPort() 
		+ request.getContextPath() + "/";
	
	request.setAttribute("path", path);
%>
<base href="<%=path %>">
<link href="/static/css/bootstrap.css" rel='stylesheet' type='text/css' />
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="/static/js/jquery.min.js"></script>
		 <!-- Custom Theme files -->
		<link href="/static/css/style.css" rel='stylesheet' type='text/css' />
   		 <!-- Custom Theme files -->
   		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		
		<body>
	<!-- container -->
			<!-- header -->
			<div class="header">
				<div class="container">
				<!-- logo -->
				<div class="logo">
					<a href="/index/index.html" style="text-decoration:none;">
<!-- 						<img src="/static/images/logo.png" title="baku5" /> -->
						<span style="color: #006545;font-size: 30px;margin-left:-70px;">翡翠阁珠宝</span>
					</a>
				</div>
				<!-- logo -->
				<!-- cart-info -->
				<div class="cart-info">
					<ul>
					<c:if test="${user.username eq null}">
						<li><a href="user/login.html"><span style="color: #006545;display: none;" id="username" >${user.username }</span>登录</a></li>
					</c:if>	
					<c:if test="${user.username ne null }">
						<li><span style="color: #006545;" id="username">${user.username }</span>,您好！<a href="user/logout.html" style="color: red">注销</a></li>
					</c:if>
					<c:if test="${user.usertype eq 'S'}">
						<li><a href="/manager/manager.html">后台管理中心</a></li>
					</c:if>				
					<c:if test="${user.usertype ne 'S'}">
						<li><a href="/manager/manager.html">个人中心</a></li>
					</c:if>
						<li><a href="/commodity/page.html">店铺</a></li>
					<c:if test="${user.username eq null}">
						<li class="cartinfo"><a href="/cart/myCart.html"><span> </span>我的购物车 (0)</a></li>
					</c:if>	
					<c:if test="${user.username ne null }">
						<li class="cartinfo"><a href="/cart/myCart.html"><span> </span>我的购物车 (${num })</a></li>
					</c:if>
						
						<div class="clearfix"> </div>
					</ul>
				</div>
				<!-- /cart-info -->
			</div>
			<!-- header -->
		</div>
		<!-- sub-header -->
		<div class="sub-header" style="margin-left: -175px;">
			<div class="container">
				<!-- top-nav -->
				<div class="top-nav">
					<span class="menu"> </span>
					<ul>
						<li class="active" style="color: #007e56;"><a href="/index/index.html" style="color: #007e56;">主页</a></li>
<!-- 						<li><a href="404.html">About</a></li> -->
						<li><a href="/commodity/newCommodity.html">店铺新品</a></li>
						<li><a href="/commodity/page.html">全部商品</a></li>
						<li><a href="/commodity/queryByType.html?type=1">吊坠</a></li>
						<li><a href="/commodity/queryByType.html?type=2" >手镯</a></li>
						<li><a href="/commodity/queryByType.html?type=3">戒指</a></li>
						<li><a href="/commodity/queryByType.html?type=4">项链</a></li>
<!-- 						<li><a href="404.html">Baku Club</a></li> -->
<!-- 						<li><a href="404.html">店铺地址</a></li> -->
<!-- 						<li><a href="contact.html">联系我们</a></li> -->
						<div class="clearfix"> </div>
					</ul>
				</div>
				<!-- top-nav -->
				<!-- script-for-nav -->
				<script>
					$(document).ready(function(){
						$("span.menu").click(function(){
							$(".top-nav ul").slideToggle(300);
						});
					});
				</script>
				<!-- script-for-nav -->
				<!-- search-form -->
				<div class="search-form" style="margin-right: -80px">
					<form action="/commodity/getCommodityBySearch.html">
						<input type="text" class="text" name="serach" value="关键字或产品名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Keyword or product code';}">
						<input type="submit" value="" />
					
					</form>
<!-- 					<button type="submit" class="srh-btn">搜索</button> -->
				</div>
				<div class="clearfix"> </div>
				<!-- /search-form -->
			</div>
		</div>
</body>	