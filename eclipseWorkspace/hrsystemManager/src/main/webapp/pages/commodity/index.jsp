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
		<!-- /sub-header -->
		<!-- banner -->
		<div class="banner">
			<!--- img-slider --->
				<div class="img-slider">
						<!----start-slider-script---->
					<script src="/static/js/responsiveslides.min.js"></script>
					 <script>
					    // You can also use "$(window).load(function() {"
					    $(function () {
					      // Slideshow 4
					      $("#slider4").responsiveSlides({
					        auto: true,
					        pager: true,
					        nav: true,
					        speed: 500,
					        namespace: "callbacks",
					        before: function () {
					          $('.events').append("<li>before event fired.</li>");
					        },
					        after: function () {
					          $('.events').append("<li>after event fired.</li>");
					        }
					      });
					
					    });
					  </script>
					<!----//End-slider-script---->
					<!-- Slideshow 4 -->
					    <div  id="top" class="callbacks_container">
					      <ul class="rslides" id="slider4">
					        <li>
					          <img class="img-responsive" src="/static/images/carousel/carousel1.jpg" alt="">
					        </li>
					        <li>
					          <img src="/static/images/carousel/carousel2.jpg" alt="">
					        </li>
					        <li>
					          <img src="/static/images/carousel/carousel3.jpg" alt="">
					        </li>
					      </ul>
					    </div>
					    <div class="clearfix"> </div>
					</div>
						<!-- slider -->
		</div>
		<!-- banner -->
		<!-- Welcome-note -->
		<div class="Welcome-note">
			<div class="Welcome-note-left">
				<div class="Welcome-note-left-pic">
<!-- 					<img src="/static/images/pic1.png" title="name" /> -->
					<img src="/static/images/banner/banner2.jpg" title="name" />
					
				</div>
				<div class="Welcome-note-left-pic-info">
					<p>探索我们新到的 <span>纯金</span>半宝石首饰令人兴奋的新颜色..</p>
				</div>
			</div>
			<div class="Welcome-note-right">
				<p>什么是 <span>潮流</span></p>
			</div>
			<div class="clearfix"> </div>
		</div>
		<!-- Welcome-note -->
		<!-- content -->
		<div class="content">
			<!-- top-grids -->
			<div class="top-grids">
			<div class="container">
				<div class="product-grids">
							<link href="/static/css/owl.carousel.css" rel="stylesheet">
							    <script src="/static/js/owl.carousel.js"></script>
							        <script>
							    $(document).ready(function() {
							      $("#owl-demo").owlCarousel({
							        items : 5,
							        lazyLoad : true,
							        autoPlay : true,
							        navigation : true,
							        navigationText :  false,
							        pagination : false,
							      });
							    });
							    </script>
													
						       <div id="owl-demo" class="owl-carousel text-center">
						        
								     <c:forEach items="${pathList}" var="map">
										
							                <div onclick="location.href='/commodity/getCommodityDetails.html?labelId=${map.labelId}';" class="item">
							                	<div class="product-grid">
													<div class="product-pic">
														<a href="/commodity/getCommodityDetails.html?labelId=${map.labelId}"><img id="pic" src="${map.pathSrc}" title="name" /></a> 
													</div>
													<div class="product-pic-info">
														<p>${map.name}</p>
													</div>
												
												</div>
							                </div>													
									 	</c:forEach>
				              </div>
			</div>
		</div>
		</div>
			
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

