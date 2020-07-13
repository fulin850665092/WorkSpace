<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>翡翠玉石_a货缅甸翡翠_玉器翡翠价格大全</title>
		<%-- jsp头部的引入
		包含 CSS样式
		Jquery的引入
		Base标签
		 --%>
	<%@ include file="/pages/common/header.jsp" %>
	<script type="text/javascript">
	
		$(function(){
		       $("#select_id").change(function(){
		    	   var type =  $("#select_id").val();
		    	   var tex=$("#select_id").find("option:selected").text(); 
		    	   location.href = "http://localhost:8001/commodity/queryByType.html?type=" + type
		    	  
		       })
		   
		    })
	
		   
		
	</script>
	<style type="text/css">
	.cartButton{
		background: #cc0000;
		border: none; 
		outline: none;
		color: #FFF;
		text-transform: uppercase;
		font-size: 0.2em;
	    padding: 0.7em 1.5em;
	    border-radius: 0.3em;
	    cursor: pointer;
	}
		
	</style>
	</head>
	<body>
		
		<!-- /sub-header -->
		<!-- content -->
		<div class="collections">
				<div class="collections-head">
					<div class="container">
						<div class="collections-head-left">

						</div>
						<div class="collections-head-right">
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
				<!-- categories -->
				<div class="categories">
					<div class="container">
						<div class="categories-left">
							<ul>
								<li><a href="/index/index.html">主页</a></li>
								<li><a href="/commodity/newCommodity.html">店铺新品</a></li>
								<li><a href="/commodity/page.html">全部商品</a></li>
								<li><a href="/commodity/queryByType.html?type=1">吊坠</a></li>
								<li><a href="/commodity/queryByType.html?type=2" >手镯</a></li>
								<li><a href="/commodity/queryByType.html?type=3">戒指</a></li>
								<li><a href="/commodity/queryByType.html?type=4">项链</a></li>
							</ul>
						</div>
						<div class="categories-right">
							<ul>
								
								
								<li class="view"><a href="/commodity/page.html">全部</a></li>
								<li class="options">
									<select id="select_id">
										<option value="0">分类</option>
										<option value="1">吊坠</option>
										<option value="2">手镯</option>
										<option value="3">戒指</option>
										<option value="4">项链</option>
									</select>
								</li>
								<div class="clearfix"> </div>
							</ul>
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
				<!-- categories -->
				<!-- iteam-grids -->
				<div class="iteam-grids">
					<div class="container">
						<c:forEach items="${pathList}" var="map">
						  <div class="col-md-3 cartBox">
							<div  class="iteam-grid text-center">
							<div onclick="location.href='/commodity/getCommodityDetails.html?labelId=${map.labelId}';">
								<img src="${map.pathSrc}" title="name"  width="228"  height="228"/>
								</div>
								<div style="line-height: 25px;">
									<div style="margin-left: 0px;font-size: 15px;">${map.name}</div>
									<div style="margin-left: -80px;font-size: 16px;" id="labelId">【货  号】${map.labelId}</div>							
									<div style="margin-left: -65px;font-size: 16px;"><span style="display: inline-block;;">【结缘价】 </span><span style="color: red;display: inline-block;">￥${map.price}元</span></div>
								</div>
								<ul>
								
									<li>
										<div style="padding-left: 10px;">
											<input class="cartButton"  type="button"  id="addCart" value="加入购物车"/>		
														
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
									</li>
								
									<li>
										<input class="cartButton" type="button" value="商品详情"  onclick="location.href='/commodity/getCommodityDetails.html?labelId=${map.labelId}'" />																										
									</li>
									<div class="clearfix"> </div>
								</ul>
							</div>
						 </div>
						 
						</c:forEach>
						<div class="clearfix"> </div>
					</div>
				</div>
			
				<!-- iteam-grids -->
				<%@ include file="/pages/common/page.jsp" %>
		</div>
		
		<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
		<script type="text/javascript">
			$('.cartBox').on('click',"#addCart",function () { 
					var labelId=$(this).parents('.cartBox').find('#labelId').html();
					if (labelId!=null&&labelId!="") {
						labelId=labelId.substring(6,15);		
							
						var username=$("#username").html();
						var referer = document.referrer;										
						if (username==null||username=="") {
							window.location.href="http://localhost:8001/user/login.html"
								
						}else{
							$.ajax({
							             type: "get",
							             url: "<c:url value='/cart/addItem.html'/>"+"?labelId="+labelId+"&username="+username,
							             contentType: 'application/x-www-form-urlencoded;charset=utf-8',				         
							             dataType: "json",
							           //  async: false,
							             success: function(data){ 		
											alert(data);
											           
							             }
								});		
						}
					}													
					
										
				});  
		</script>			
</body>
</html>

