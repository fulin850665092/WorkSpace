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
	</head>
	<body>
		
	
		<!-- /sub-header -->
		<!-- content -->
		<!-- details -->
		<div class="details">
			<div class="details-head">
				
			</div>
			<div class="details-cate">
				<div class="container">
					<div class="categories-left">
							<ul>
								<li><a href="/index/index.html">主页</a></li>
								<li><a href="#">店铺新品</a></li>
								<li><a href="/commodity/page.html">全部商品</a></li>
								<li><a href="/commodity/queryByType.html?type=1">吊坠</a></li>
								<li><a href="/commodity/queryByType.html?type=2" >手镯</a></li>
								<li><a href="/commodity/queryByType.html?type=3">戒指</a></li>
								<li><a href="/commodity/queryByType.html?type=4">项链</a></li>
							</ul>
						</div>
				</div>
			</div>
			<!-- product-single-details -->
			<div class="product-single-details">
				<div class="container">
					<div class="product-single-details-left">
						<!----details-product-slider--->
				<!-- Include the Etalage files -->
					<link rel="stylesheet" href="/static/css/etalage.css">
					<script src="/static/js/jquery.etalage.min.js"></script>
				<!-- Include the Etalage files -->
				<script>
						jQuery(document).ready(function($){
			
							$('#etalage').etalage({
								thumb_image_width: 300,
								thumb_image_height: 400,
								source_image_width: 600,
								source_image_height: 700,
								show_hint: true,
								click_callback: function(image_anchor, instance_id){
									alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
								}
							});
							var src=$('.etalage_small_thumb').eq(3).attr('src');
							$('.etalage_small_thumb').eq(0).attr('src',src)
							// This is for the dropdown list example:
							$('.dropdownlist').change(function(){
								etalage_show( $(this).find('option:selected').attr('class') );
							});

					});
				</script>
				<!----//details-product-slider--->
				<div class="details-left">
					<div class="details-left-slider">
						<ul id="etalage" class="etalage" style="display: block; width: 314px; height: 552px;">
							<li class="etalage_thumb thumb_1" style="display: none; opacity: 0; background-image: none;">
								<a href="optionallink.html">
									<img class="etalage_thumb_image" src="${map.pathSrc1 }" style="display: inline; width: 300px; height: 400px; opacity: 1;">
									<img class="etalage_source_image" src="${map.pathSrc1 }">
								</a>
							</li>
							<li class="etalage_thumb thumb_2" style="display: none; opacity: 0; background-image: none;">
								<img class="etalage_thumb_image" src="${map.pathSrc2 }" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image" src="${map.pathSrc2 }">
							</li>
							<li class="etalage_thumb thumb_3 etalage_thumb_active" style="display: list-item; opacity: 1; background-image: none;">
								<img class="etalage_thumb_image" src="${map.pathSrc3 }" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image" src="${map.pathSrc3 }">
							</li>
							<li class="etalage_thumb thumb_4" style="display: none; opacity: 0; background-image: none;">
								<img class="etalage_thumb_image" src="${map.pathSrc4 }" style="display: inline; width: 300px; height:400px; opacity: 1;">
								<img class="etalage_source_image" src="${map.pathSrc4 }">
							</li>
							<li class="etalage_thumb thumb_5" style="display: none; opacity: 0; background-image: none;">
								<img class="etalage_thumb_image" src="${map.pathSrc5 }" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image" src="${map.pathSrc5 }">
							</li>
							<li class="etalage_thumb thumb_6" style="display: none; opacity: 0; background-image: none;">
								<img class="etalage_thumb_image" src="${map.pathSrc6 }" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image" src="${map.pathSrc6 }">
							</li>

						<li class="etalage_magnifier" style="margin: 0px; padding: 0px; left: 6px; top: 6px; display: none; opacity: 0.05449673905968666;"><div style="margin: 0px; padding: 0px; width: 195px; height: 215px;">
					        
						</div>
						</li>
						<li class="etalage_icon" style="display: list-item; top: 380px; left: 20px; opacity: 1;">
						</li>
						<li class="etalage_hint" style="display: none; margin: 0px; top: -15px; right: -15px;">
						</li>
						<li class="etalage_zoom_area" style="margin: 0px; opacity: 0; left: 324px; display: none; background-image: none;">
						<div class="etalage_description" style="width: 546px; bottom: 6px; left: 6px; opacity: 0.7; display: none;"></div>
						<div style="width: 586px; height: 538px;">
						<img class="etalage_zoom_img" src="${map.pathSrc1 }" style="width: 900px; height: 1000px; left: -157.34166420232418px; top: 0px;"></div>
							</li>
								<li class="etalage_small_thumbs" style="width: 314px; top: 424px;">
								  <ul style="width: 1072px;"> 
								   <li class="etalage_smallthumb_navtoend" style="opacity: 0.4; margin: 0px 10px 0px 0px; left: -216px;"> 
										<img class="etalage_small_thumb" src="${map.pathSrc1 }" width="90" style="width: 90px; height: 120px;"></li>
									<li class="etalage_smallthumb_active" style="opacity: 1; margin: 0px 10px 0px 0px; left: -216px;"> 
										<img class="etalage_small_thumb" src="${map.pathSrc2 }" width="90" style="width: 90px; height: 120px;"></li>
									<li class="etalage_smallthumb_last" style="opacity: 0.4; margin: 0px 10px 0px 0px; left: -216px;"> 
										<img class="etalage_small_thumb" src="${map.pathSrc3 }" width="90" style="width: 90px; height: 120px;"></li>
									<li class="" style="opacity: 0.4; margin: 0px 10px 0px 0px; left: -216px;"> 
										<img class="etalage_small_thumb" src="${map.pathSrc4 }" width="90" style="width: 90px; height: 120px;"></li>
									<li class="" style="opacity: 0.4; margin: 0px 10px 0px 0px; left: -216px;"> 
									<img class="etalage_small_thumb" src="${map.pathSrc5 }" width="90" style="width: 90px; height: 120px;"></li>
								<li class="" style="opacity: 0.4; margin: 0px 10px 0px 0px; left: -216px;"> 
									<img class="etalage_small_thumb" src="${map.pathSrc6 }" width="90" style="width: 90px; height: 120px;"></li>
						      </ul>
							</li>
								
						</ul>
					</div>
					</div>
				</div>
					<div class="product-single-details-right">
						<h2>${map.name }</h2>
						<p>尺寸规格:<span style="color: #00744A;"> ${map.specification }</span></p>
						<ul class="r-list">
							<li class="rating"><span> </span>340评级</li>
							<li class="heart"><span> </span>加入观看列表</li>
						</ul>
						<!-- price-details -->
						<div class="price-details">
							<div class="price-details-left">

								<div style="margin-top: -10px;">
									<span style="font-size: 1.3em;">产品代码 	: </span><span id="labelId" style="font-size:1.3em;">${map.labelId }</span>	
								</div>
								<br/>
								<span style="font-size: 1.3em;">最优惠价格:</span><span style="color: #cc0000;font-size: 2.0em;">￥${map.marketPrice }</span>
							</div>
							<div class="price-details-right">
<!-- 									展示商品有可能已经售出，要提示客户不能购买 -->
								<c:if test="${map.count ne 0}">
									<input type="button" value="加入购物车" id="addCart"/>
								</c:if>
								<c:if test="${map.count eq 0}">
									<input type="button" value="该商品已出售" id="addCart" disabled="disabled" style="background:gray;"/>
								</c:if>
								<script type="text/javascript">
									$("#addCart").click(function () { 
										//var id=${map.id};
										var labelId=$("#labelId").html();
										var username=$("#username").html();
										var referer = document.referrer;
										
// 										alert(referer);
										if (username==null||username=="") {
											window.location.href="http://localhost:8001/user/login.html"
												
										}else{
											$.ajax({
											             type: "get",
											             url: "<c:url value='/cart/addItem.html'/>"+"?labelId="+labelId+"&username="+username,
											             contentType: 'application/x-www-form-urlencoded;charset=utf-8',				         
											             dataType: "json",
//		 									             async: false,
											             success: function(data){ 										           
															alert(data)                   
											             }
												});		
										}
															
									});  
									
								</script>
							</div>
							<div class="clearfix"> </div>
						</div>
						<!-- price-details -->
						<!-- product-description -->
						<div class="product-description">
							<h3>更多描述:</h3>
							<!-- des-tabs -->
							<div class="des-tabs">
								<div class="tab1 tab">
										<span style="font-size: 18px;">【翡翠种水】/</span>&nbsp;&nbsp;&nbsp;
										<span style="font-size: 16px;color: #00744A;">${map.water }</span> 
								
<%-- 									<h4>【翡翠种水】/</span> <span>  ${map.water }</h4> --%>
<!-- 									<p></p> -->
								</div>
								<div class="tab2 tab">
									<span style="font-size: 18px;">【宝贝描述】/</span>&nbsp;&nbsp;&nbsp;
									<span style="font-size: 16px;color: #00744A;">${map.depiction }</span> 
<!-- 									<p></p> -->
								</div>
								<div class="tab3 tab">
									<span style="font-size: 18px;">【随单配件】/</span>&nbsp;&nbsp;&nbsp;
									<span style="font-size: 16px;color: #00744A;">${map.accessory }</span> 
<%-- 									<h4>【随单配件】/  ${map.accessory }</h4> --%>
<!-- 									<p></p> -->
								</div>
								<h3>友情提示:</h3>
									<div >
										<img style="vertical-align: initial;" src="/static/images/css/imgchange.png">
										<span style="font-size: 16px;padding-left:1em;">此宝贝为实体与网络同步销售，因天然珠宝特性，如翡翠等商品库存仅一件，下单前请先添加客服微信了解具体情况，谢谢！</span>
									</div>
									<div >
									<img style="vertical-align: initial;" src="/static/images/css/imgphone.jpg">
										<span style="font-size: 16px;padding-left:1em;">宝贝照片为手机拍摄，实物更漂亮</span>
									</div><br/><br/>
						
										<div style="line-height: 21px;">
											<img src="/static/images/css/new_icon1.jpg">
											<img src="/static/images/css/new_icon2.jpg">
											<img src="/static/images/css/new_icon3.jpg">
											<img src="/static/images/css/new_icon4.jpg">
										</div>
									<div style="border:3px solid #00744A;width: 450px;background: 	#C0C0C0">
										<div style="margin-top: 10px;margin-bottom: 20px;">
											<div class="kefuBox2_inner">
												<img class="kefuBox2_img" src="/static/images/css/kefu.jpg">
												<div class="kefuBox2_tbox">
													<h4>淘翠微信号</h4><p class="kefuBox2_wx">ICE747711</p>
													<p class="kefuBox2_msg">加微信领取专属优惠<br>一对一帮您解决问题</p>
												</div>
											</div>
										</div>
									</div>
							</div>
							<!-- des-tabs -->
							<!-- script-for-tabs -->
							<script>
								$(document).ready(function(){
									$(".tab2 p").hide();
									$(".tab3 p").hide();
									$(".tab2 h4").click(function(){
										$(".tab2 p").slideToggle(300);
										$(".tab1 p").hide();
										$(".tab3 p").hide();
									});
									$(".tab1 h4").click(function(){
										$(".tab1 p").slideToggle(300);
										$(".tab2 p").hide();
										$(".tab3 p").hide();
									});
									$(".tab3 h4").click(function(){
										$(".tab3 p").slideToggle(300);
										$(".tab2 p").hide();
										$(".tab1 p").hide();
									});
								});
							</script>
							<!-- script-for-tabs -->
						</div>
						<!-- product-description -->
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
			<!-- product-single-details -->
		</div>
					
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

