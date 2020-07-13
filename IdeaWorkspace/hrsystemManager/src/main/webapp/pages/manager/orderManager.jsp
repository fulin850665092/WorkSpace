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
    border: 1px solid #cccccc;
}
td{
    border: 1px solid #cccccc;
    }
</style>
<%-- jsp头部的引入
		包含 CSS样式
		Jquery的引入
		Base标签
	 --%>
	<%@ include file="/pages/common/header.jsp" %>
	<%@ include file="/pages/common/adminManagerCom.jsp" %>
	</head>
	<script type="text/javascript">
			function updateOrder(Obj){
				var orderId=null;	
				var address=null;	
				var recName=null;				
				var price=null;			
				var status=null;
				var phone=null;
				$('table tr').each(function(){
					orderId=$(Obj).closest("tr").find("input[class='orderId']").val();
					address=$(Obj).closest("tr").find("input[class='address']").val();
					recName=$(Obj).closest("tr").find("input[class='recName']").val();
					phone=$(Obj).closest("tr").find("input[class='phone']").val();
					price=$(Obj).closest("tr").find("input[class='price']").val();					
					status=$(Obj).closest("tr").find("select[class='status']").val();	
					console.log(status)
				})		
				
				if (confirm("您确实要修改订单Id为"+orderId+"的订单吗？")){
					if (check(orderId,recName,price,status,address,phone)) {
						location.href="http://localhost:8001/adminManager/updateOrder.html?orderId="+orderId+"&price="+price+"&phone="+phone+"&address="+address+"&recName="+recName+"&status="+status;
					}
				}else{
					return;
				}
			}
			function check(orderId,recName,price,status,address,phone){
			
					if(recName){
						if(price){
							if(status){		
								if(address){
									if (phone) {
										return true;
									}else{
										alert("收件人电话不能为空！");
										return false;
									}	
									
								}else{
									alert("收件人地址不能为空！");
									return false;
								}							
							}else{
								alert("状态不能为空！");
								return false;
							}
						}else{
							alert("价格不能为空！");
							return false;
						}
					}else{
						alert("收件人不能为空！");
						return false;
					}
				}
		
				
			
	</script>
	<body style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体; color: rgb(102, 102, 102);">
		<div class="AreaR3" style="width:1015px !important;">
<!-- 			<div class="box_1"> -->
				<div class="userCenterBox boxCenterList clearfix" style="_height:1%; line-height:26px;margin-top: -394px; margin-bottom: 0px;margin-left: -65px;">
					<h5><span>我的订单</span></h5>
					<div class="blank">
				</div>
					<table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#cccccc">
						<tbody>							 
							<tr>								
								<th bgcolor="#ffffff" style="text-align: center;">订单id</th>
								<th bgcolor="#ffffff" style="text-align: center;">用户id</th>
								<th bgcolor="#ffffff" style="text-align: center;">商品总数量：</th>
								<th bgcolor="#ffffff" style="text-align: center;">收货人</th>
								<th bgcolor="#ffffff" style="text-align: center;">收货人电话</th>
								<th bgcolor="#ffffff" style="text-align: center;">收货人地址</th>
								<th bgcolor="#ffffff" style="text-align: center;">订单总金额</th>
								<th bgcolor="#ffffff" style="text-align: center;">订单状态</th>
								<th bgcolor="#ffffff" style="text-align: center;">操作</th>
							</tr>
							<c:forEach items="${orderList}" var="order">
							<tr>
								<td bgcolor="#ffffff" align="center">
									<div class="blank5"></div>${order.orderId }
								</td>
								<td bgcolor="#ffffff">${order.userId }<br></td>
								<td align="center" bgcolor="#ffffff">${order.remark }
<!-- 									<input type="text" class="count" name="count" id="count" value="" size="4" class="inputBg" style="text-align:center;height:30px;width: 109px; ">									 -->
								</td>
								<td align="center" bgcolor="#ffffff">
									<input type="text" class="recName" name="recName" id="recName" value="${order.recName}" size="4" class="inputBg" style="text-align:center;height:30px;width: 109px; " >
								</td>
								<td align="center" bgcolor="#ffffff">
									<input type="text" class="phone" name="phone" id="phone" value="${order.phone}" size="4" class="inputBg" style="text-align:center;height:30px;width: 109px; " >
								</td>
								<td align="center" bgcolor="#ffffff">
									<input type="text" class="address" name="address" id="address" value="${order.address}" size="4" class="inputBg" style="text-align:center;height:30px;width: 109px; " >
								</td>
								<td align="center" bgcolor="#ffffff">
									<input type="text" class="price" name="price" id="price" value="${order.price}" size="4" class="inputBg" style="text-align:center;height:30px;width: 109px; " >
								</td>
								  								
								<td align="center" bgcolor="#ffffff">
									<select class="status">
										 <c:if test="${order.status eq 0}">
											<option id="sex" value="0">未支付</option>
											<option id="sex" value="1">待发货</option>
											<option id="sex" value="2">已发货</option>
											<option id="sex" value="3">已签收</option>
										</c:if>
									   <c:if test="${order.status eq 1}">
									   		<option id="sex" value="1">待发货</option>											
											<option id="sex" value="0">未支付</option>										
											<option id="sex" value="2">已发货</option>
											<option id="sex" value="3">已签收</option>
										</c:if>
										<c:if test="${order.status eq 2}">			
											<option id="sex" value="2">已发货</option>								
											<option id="sex" value="0">未支付</option>
											<option id="sex" value="1">待发货</option>												
											<option id="sex" value="3">已签收</option>
										</c:if>	
										<c:if test="${order.status eq 3}">
											<option id="sex" value="3">已签收</option>											
											<option id="sex" value="0">未支付</option>
											<option id="sex" value="1">待发货</option>
											<option id="sex" value="2">已发货</option>
											<option id="sex" value="3">已签收</option>
										</c:if>																	
									</select>
								</td>							
								<td align="center" bgcolor="#ffffff">
									<input id="orderId" type="hidden" class="orderId" value="${order.orderId }" >
									<a style="text-decoration:none; ">
										<input  type="button" value="更新 " class="bnt_blue_1" onclick="updateOrder(this)">
									</a>&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
									<a style="text-decoration:none;" href="javascript:if (confirm('您确实要把该商品移出购物车吗？')) location.href='/cart/deleteCart?cartId=${map.cartId}'; ">
										<input  type="button" value="删除 " class="bnt_blue_1" >
									</a>&nbsp;&nbsp;&nbsp;&nbsp;
									
								</td>
							</tr>								
	       			
	       				</c:forEach>
						</table>
					</tbody>
					<%@ include file="/pages/common/page.jsp" %>
					<div class="blank5">
				</div>
				<link type="text/css" rel="stylesheet" href="//www.aitaocui.cn/themes/mmb_taocui/store_css/pages.css">
				<div style="width:100%; text-align:center" id="dandu">
					<form name="selectPageForm" action="/user.php" method="get" style="100%">
						<div id="pager1" class="pagebar1"></div>
						<div style="clear: both;"></div>
					</form>
				</div>
			
           </div>
           </div>
         
<!--            </div> -->
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

