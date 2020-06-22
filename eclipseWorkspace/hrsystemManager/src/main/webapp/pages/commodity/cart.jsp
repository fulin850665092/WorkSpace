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
		<link href="/static/css/cart.css" rel='stylesheet' type='text/css' />
		<script type="text/javascript">	
		
			$(document).ready(function(){
				$("#checkout").click(function(){
					var list="${pathList}";

// 					var count=$("#goods_number_84512").val();
// 					console.log(count)

					if(list.length==2){
						alert("你的购物车为空，请您先添加商品！");		
						 
					}else{
						location.href = "http://localhost:8001/order/creatOrder.html"		
					}
					
				})
			})
		
				function getCount(Obj){	
					var count=null;
					var cartId=null;
					$('table tr').each(function(){
						count=$(Obj).closest("tr").find("input[class='count']").val();
						cartId=$(Obj).closest("tr").find("input[class='cartId']").val();
					})				
// 					alert(count)
// 					alert(cartId)
					if (confirm("您确实要修改该商品数量修改为"+count+"吗？")){
						if (count>=1) {
							location.href="/cart/updateCount.html?cartId="+cartId+"&count="+count;
						}else {
							alert("修改商品数量应该大于0！")
						}
						
					}else{
						return;
					}
					
				
				}
		
		</script>
	</head>
	<body style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体; color: rgb(102, 102, 102);">
	
		<div style="margin-top:10px;">
					<img src="//www.aitaocui.cn/images/201802/1518369294944580763.jpg" width="100%" height="90px;" border="0" style="border: 0px;">
	
		</div>	
		<h6 class="zf_title_1" style="margin-top: 0px;"><span>购物车</span></h6>
		<div class="flowBox flowbox-2">
		<form id="formCart" class="f0a" name="formCart" method="post" action="flow.php">
					<table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#cccccc">
						<tbody>							 
							<tr>								
								<th bgcolor="#ffffff" style="text-align: center;">商品名称</th>
								<th bgcolor="#ffffff" style="text-align: center;">属性</th>
								<th bgcolor="#ffffff" style="text-align: center;">市场价：</th>
								<th bgcolor="#ffffff" style="text-align: center;">淘翠价：</th>
								<th bgcolor="#ffffff" style="text-align: center;">购买数量</th>
								<th bgcolor="#ffffff" style="text-align: center;">小计</th>
								<th bgcolor="#ffffff" style="text-align: center;">操作</th>
							</tr>
							<c:forEach items="${pathList}" var="map">
								<tr>
									<td bgcolor="#ffffff" align="center">
<!-- 										<input name="Fruit" type="checkbox" value="" /> -->
										<a href="/commodity/getCommodityDetails.html?labelId=${map.labelId}" target="_blank">
										<img src="${map.pathSrc}" width="150" border="0" title="${map.name}">
										</a><div class="blank5"></div><a href="/commodity/getCommodityDetails.html?labelId=${map.labelId}" target="_blank">${map.name }</a>
									</td>
									<td bgcolor="#ffffff">${map.specification } <br></td>
									<td align="center" bgcolor="#ffffff">${map.price}</td>
									<td align="center" bgcolor="#ffffff">${map.marketPrice}</td>
									<td align="center" bgcolor="#ffffff">
										<input type="text" class="count" name="goods_number[84512]" id="goods_number_84512" value="${map.count }" size="4" class="inputBg" style="text-align:center " onkeydown="showdiv(this)">
									</td>
									<td align="center" bgcolor="#ffffff">${map.wyPriceCountStr}</td>
									<td align="center" bgcolor="#ffffff">
										<input id="cartId" class="cartId" type="text" value="${map.cartId }" style="display: none;">
										<a style="text-decoration:none; ">
											<input  type="button" value="更新 " class="bnt_blue_1" onclick="getCount(this)">
										</a>&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										<a style="text-decoration:none;" href="javascript:if (confirm('您确实要把该商品移出购物车吗？')) location.href='/cart/deleteCart.html?cartId=${map.cartId}'; ">
											<input  type="button" value="删除 " class="bnt_blue_1" >
										</a>&nbsp;&nbsp;&nbsp;&nbsp;
										
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#cccccc">
							<tbody>
								<tr>
									<td bgcolor="#ffffff">
									                    购物金额小计 ￥${priceCountStr }元，比市场价 ￥${marketPriceCountStr}元 节省了 ￥${chaPriceStr }元 (${baifenBiStr })</td>
									<td align="right" bgcolor="#ffffff">
																	
									<a href="javascript:if (confirm('您确实要清空购物车吗？'))location.href='/cart/clear.html'; "><input  type="button" value="清空购物车" class="bnt_blue_1" ></a>
<%-- 									<input name="submit" type="submit" class="bnt_blue_1" value="更新购物车"  onclick="location.href='/cart/update?cartId=${map.cartId}'"> --%>
									</td>
								</tr>
							</tbody>
				</table>
		 <input type="hidden" name="step" value="update_cart">
	   </form>
		 <table width="99%" align="center" border="0" cellpadding="5" cellspacing="0" bgcolor="#cccccc">
				<tbody>
					<tr>
						<td bgcolor="#f1f1f1"><a href="/commodity/page.html">
							<img src="/static/images/cart/continue.gif" alt="continue"></a>
						</td>
						<td bgcolor="#f1f1f1" align="right">
<!-- 							<a href="/order/creatOrder"> -->
								<img src="/static/images/cart/checkout.gif" alt="checkout" id="checkout">
<!-- 							</a> -->
						</td>
					</tr>
		   </tbody>
		</table>
	</div>
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

