<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
	.order_table th {
	    height: 40px;
		}
		body, th, td, input, select, textarea, button {
    font-size: 14px;
    font-family: "微软雅黑", "黑体","宋体";
    color: #666;
}
table {
    border-spacing: 2px;
}
</style>
<%-- jsp头部的引入
		包含 CSS样式
		Jquery的引入
		Base标签
	 --%>
	<%@ include file="/pages/common/header.jsp" %>
	<%@ include file="/pages/common/managerCom.jsp" %>
	</head>
	<body style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体; color: rgb(102, 102, 102);">
		<div class="AreaR3" style="width:1015px !important;">
<!-- 			<div class="box_1"> -->
				<div class="userCenterBox boxCenterList clearfix" style="_height:1%; line-height:26px;margin-top: -334px; margin-bottom: 212px;margin-left: -65px;">
					<h5><span>我的订单</span></h5>
					<div class="blank"></div>
						<table class="order_table" style="width: 100%;    margin-left: -64px;margin-bottom: -100px;">
							<tbody>
								<tr align="center">
									<th style="    text-align: center;">订单id</th>									
									<th style="    text-align: center;">商品数量</th>
									<th style="    text-align: center;">收货人</th>
									<th style="    text-align: center;">订单总金额</th>
									<th style="    text-align: center;">订单状态</th>
									<th style="    text-align: center;">操作</th>
								</tr>
								<c:forEach items="${orderList}" var="order">
									<tr align="center">
										<td>${order.orderId }</td>								
										<td>${order.phone }</td>
										<td>${order.recName}</td>
										<td>${order.price}</td>
										<c:if test="${order.status eq 0}">
											<td>未支付</td>
										</c:if>
										<c:if test="${order.status eq 1}">
											<td>待发货</td>
										</c:if>
										<c:if test="${order.status eq 3}">
											<td>已发货</td>
										</c:if>
										<c:if test="${order.status eq 4}">
											<td>已签收</td>
										</c:if>
										<c:if test="${order.status eq 0}">
											<td><a href="#">支付</a></td>
										</c:if>
										<c:if test="${order.status eq 1}">
											<td><a href="#">提醒卖价发货</a></td>
										</c:if>
										<c:if test="${order.status eq 2}">
											<td>确认收货</td>
										</c:if>	
										<c:if test="${order.status eq 3}">
											<td>确认收货</td>
										</c:if>																								
									</tr>	
								</c:forEach>
							</tbody>
						</table>
					<div class="blank5">
				</div>
				<link type="text/css" rel="stylesheet" href="//www.aitaocui.cn/themes/mmb_taocui/store_css/pages.css">
				<div style="width:100%; text-align:center" id="dandu">
					<form name="selectPageForm" action="/user.php" method="get" style="100%">
						<div id="pager1" class="pagebar1"></div>
						<div style="clear: both;"></div>
					</form>
				</div>
				<script type="Text/Javascript" language="JavaScript">
						<!--
						
								
						function selectPage(sel)
						{
						  sel.form.submit();
						}
				
						//-->
				</script>
				<script language="javascript">
					<!--
					function keyUp(e) {
					if(navigator.appName == "Microsoft Internet Explorer")
					{
					var keycode = event.keyCode;
					var realkey = String.fromCharCode(event.keyCode);
					}else{
					var keycode = e.which;
					var realkey = String.fromCharCode(e.which);
					}
					if(keycode==39){
					next=document.getElementById("next").href;
					window.location.href=next;
					}
					if(keycode==37){
					prev=document.getElementById("prev").href;
					window.location.href=prev;
					}
					}
					document.onkeydown = keyUp;
					//-->
				</script>
           </div>
           </div>
<!--            </div> -->
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

