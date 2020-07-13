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
		function  updateCommodity(Obj){
			var name=null;
			var labelId=null;
			var water=null;
			var price=null;
			var marketPrice=null;
			var count=null;
			var display=null;
			$('table tr').each(function(){
				labelId=$(Obj).closest("tr").find("input[class='labelId']").val();
			 	water=$(Obj).closest("tr").find("input[class='water']").val();
				name=$(Obj).closest("tr").find("input[class='name']").val();
				price=$(Obj).closest("tr").find("input[class='price']").val();
				marketPrice=$(Obj).closest("tr").find("input[class='marketPrice']").val();
				count=$(Obj).closest("tr").find("input[class='count']").val();
				display=$(Obj).closest("tr").find("select[class='display']").val();	
			})		
			console.log(display);
			if (confirm("您确实要修改"+name+"该商品吗？")){
				if (check(water,name,price,marketPrice,count)) {
					location.href="/adminManager/updateCommodity.html?labelId="+labelId+"&count="+count+"&display="+display+"&marketPrice="+marketPrice+"&price="+price+"&water="+water+"&name="+name;
				}
			}else{
				return;
			}
		}
		
		function check(water,name,price,marketPrice,count){
			if(water){
				if(name){
					if(price){
						if(marketPrice){
							if(count){
								return true;
							}else{
								alert("数量不能为空！");
								return false;
							}
						}else{
							alert("淘翠价格不能为空！");
							return false;
						}
					}else{
						alert("价格不能为空！");
						return false;
					}
				}else{
					alert("商品名不能为空！");
					return false;
				}
			}else{
				alert("种水不能为空！");
				return false;
			}
		}
		
	</script>
	<body style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体; color: rgb(102, 102, 102);">
		<div class="AreaR3" style="width:1015px !important;">
<!-- 			<div class="box_1"> -->
				<div class="userCenterBox boxCenterList clearfix" style="_height:1%; line-height:26px;margin-top: -394px; margin-bottom: 0px;margin-left: -65px;">
					<h5><span>商品管理</span></h5>
					<div class="blank">
				</div>
					<table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#cccccc">
						<tbody>							 
							<tr>								
								<th bgcolor="#ffffff" style="text-align: center;">商品名称</th>
								<th bgcolor="#ffffff" style="text-align: center;">属性</th>
								<th bgcolor="#ffffff" style="text-align: center;">种水</th>
								<th bgcolor="#ffffff" style="text-align: center;">市场价：</th>
								<th bgcolor="#ffffff" style="text-align: center;">淘翠价：</th>
								<th bgcolor="#ffffff" style="text-align: center;">购买数量</th>
								<th bgcolor="#ffffff" style="text-align: center;">是否展示</th>
								<th bgcolor="#ffffff" style="text-align: center;">操作</th>
							</tr>
							<c:forEach items="${commodityList}" var="commodity">
								<tr>
									<td bgcolor="#ffffff" align="center">
										<input type="hidden" class="labelId" name="labelId" id="labelId" value="${commodity.labelId } " >																																	
										<img src="${commodity.remark}" width="60" border="0" title="${commodity.name}"><br>						
										<input  type="text"  class="name" name="name" id="name" value="${commodity.name } " size="4" class="inputBg" style="text-align:center;width: 190px; " >																			
									</td>
									<td bgcolor="#ffffff" style="width: 235px;">${commodity.specification }</td>
									<td align="center" bgcolor="#ffffff">
										<input type="text" class="water" name="water" id="water" value="${commodity.water }" size="4" class="inputBg" style="text-align:center " >											
									</td>
									<td align="center" bgcolor="#ffffff">									
										<input type="text" class="price" name="price" id="price" value="${commodity.price }" size="4" class="inputBg" style="text-align:center;width:80px;" />				
									</td>
									<td align="center" bgcolor="#ffffff">
										<input type="text" class="marketPrice"  name="marketPrice" id="marketPrice" value="${commodity.marketPrice }" size="4" class="inputBg" style="text-align:center;width:80px;">				
									</td>
									<td align="center" bgcolor="#ffffff">
										<input type="text"  class="count" name="count" id="count" value="${commodity.count }" size="4" class="inputBg" style="text-align:center; " />
									</td>
									<td align="center" bgcolor="#ffffff">
										<select class="display">
											<c:if test="${commodity.display eq 'N'}">
												<option id="usertype" value="N">不展示</option>
												<option id="usertype" value="Y">展示</option>	
											</c:if>
											<c:if test="${commodity.display eq 'Y'}">
												<option id="usertype" value="Y">展示</option>
												<option id="usertype" value="N">不展示</option>
											</c:if>										
										</select>
									</td>

									<td align="center" bgcolor="#ffffff">
										&nbsp;<input  type="button" value="更新 " class="bnt_blue_1" onclick="updateCommodity(this)">									
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="/pages/common/page.jsp" %>			
          </div>
       </div>
         
<!--            </div> -->
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>