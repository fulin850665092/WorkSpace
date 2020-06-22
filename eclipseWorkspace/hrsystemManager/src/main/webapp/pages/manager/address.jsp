<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>

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
				<div class="userCenterBox boxCenterList clearfix" style="_height:1%; line-height:26px;margin-top: -334px; margin-bottom: 22px;margin-left: -65px;">
				<h5><span>收货人信息</span></h5>
				<div class="blank"></div>
				<script type="text/javascript" src="http://www.aitaocui.cn/js/utils.js"></script>
				<script type="text/javascript" src="http://www.aitaocui.cn/js/transport.js"></script>
				<script type="text/javascript" src="http://www.aitaocui.cn/js/region.js"></script>
				<script type="text/javascript" src="http://www.aitaocui.cn/js/shopping_flow.js"></script>
				<script type="text/javascript">
	         		function addAddress(){
		         		  var recname = $("#recname").val();
		       			  var addressStr = $("#addressStr").val();
		       			  var phone = $("#phone").val();
		       			  var email = $("#email").val();
		       			  var zipcode = $("#zipcode").val();
		       			  if(check()){
		       				if(confirm("确定新增收件人为"+recname+"的地址吗？")){
								
			    				  location.href = "http://localhost:8001/manager/addAddress.html?recname="+recname+"&addressStr="+addressStr+"&phone="+phone+"&email="+email+"&zipcode="+zipcode;	
		       				}
		       			}  
	         			
	         		}
	         		
	         		  function check(){
	         				//1.先取用户名输入框中的内容。
	         					var recname = $("#recname").val();
//	         	 			alert(recname)
	         					if (!recname) {
	         						alert("收件人姓名不能为空！")
	         						return false;
	         					}else{
	         						var address = $("#addressStr").val();
	         						if (!address) {
	         							alert("收件人地址不能为空！")
	         							return false;
	         						}else{
	         							var mobile = $("#phone").val();
	         							if (!mobile) {
	         								alert("收件人电话不能为空！")
	         								return false;
	         							}else{
	         							
	         								return true;
	         							}
	         						}
	         					}	
	         			  }

	         		  function updateType(Obj){	         		
	         			 var index =$(Obj).attr('btnindex');
// 	         			 alert(index)
	         			 var type=$('.addressTypeItem').eq(index).find('.typeString').val()	
	         			 var id=$('.addressTypeItem').eq(index).find('.id').val()
	         			 var addressString=$('.addressTypeItem').eq(index).find('.addressString').val()
// 					     alert(type)
// 					     alert(id)
						if(type=="S"){
							if(confirm("确定要将"+addressString+"地址设置默认地址吗？")){	
								
								  location.href = "http://localhost:8001/manager/updateType.html?type="+type+"&id="+id;
								}
						 }
	         			 if(type=="A"){
							if(confirm("确定要将"+addressString+"地址设置其他吗？")){	
								
								  location.href = "http://localhost:8001/manager/updateType.html?type="+type+"&id="+id;
								}
						 }
						
	         		  } 
	         		  function deleteAddress(Obj){	         		
		         			 var index =$(Obj).attr('btnindex');
//	 	         			 alert(index)
		         			 var type=$('.addressTypeItem').eq(index).find('.typeString').val()	
		         			 var id=$('.addressTypeItem').eq(index).find('.id').val()	
		         			 var addressString=$('.addressTypeItem').eq(index).find('.addressString').val()
//	 					     alert(type)
// 	 					     alert(addressString)
							if(confirm("确定要删除"+addressString+"改地址吗")){
								
								 location.href = "http://localhost:8001/manager/deleteAddress.html?type="+type+"&id="+id;	
							}
		         		  }
		        </script>
		        <form>
		       		<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
		        		<tbody>
		        		
	                  		<tr>
			                  	<td align="right" bgcolor="#ffffff">收货人姓名：</td>
			                  	<td align="left" bgcolor="#ffffff">
	                  				<input name="recname" type="text" class="inputBg" id="recname" value="${queryAddress.recname }">
	                  				(必填) 
	                  			</td>
	                  			 <td align="right" bgcolor="#ffffff">手机号码：</td>
				                  <td align="left" bgcolor="#ffffff">
					                  <input name="phone" type="text" class="inputBg" id="phone" value="${queryAddress.phone }">
					                  (必填)
				                  </td>
				               
		                  			
	                  		</tr>
	                  		<tr>
			                  	<td align="right" bgcolor="#ffffff">详细地址：</td>
			                  	<td align="left" bgcolor="#ffffff">
			                  		<input name="addressStr" type="text" class="inputBg" id="addressStr" value="${queryAddress.addressStr}">
				                  (必填)
				                </td>
	                  			<td align="right" bgcolor="#ffffff">邮政编码：</td>
	                  			<td align="left" bgcolor="#ffffff">
	                  				<input name="zipcode" type="text" class="inputBg" id="zipcode" value="${queryAddress.zipcode}">
	                  			</td>
	                 		 </tr>
	                  		 <tr>
				                 <td align="right" bgcolor="#ffffff">电子邮件地址：</td>
		                  			<td align="left" bgcolor="#ffffff">
		                  			<input name="email" type="text" class="inputBg" id="email" value="${queryAddress.email }">
		                  			
	                  			</td>
	                  		 </tr>
	                 		 
	                  		<tr>
				                  <td align="right" bgcolor="#ffffff">&nbsp;</td>
				                  <td colspan="3" align="center" bgcolor="#ffffff">
					                  <input type="button" name="submit" class="bnt_blue_2" value="新增收货地址" onclick="addAddress()">		                  			
	                  			  </td>
	                  		</tr>
	                    </tbody>
	                  </table>
	                </form>
	                <div style=" margin-left: 37px;margin-bottom: 30px;">
		                <form>
				       		<table id="addressType" width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
				        		<tbody>
				        			<c:forEach items="${list}" var="map" >
			                  		<tr class="addressTypeItem">
					                  	<td align="right" bgcolor="#ffffff">我的地址：</td>
					                  	<td align="left" bgcolor="#ffffff">
					                  		<input type="hidden" name="id" class="id" value="${map.id }" >		                  							                  					                  
			                  				<input  disabled="disabled"  type="text" class="addressString" style="width: 562px;" id="consignee" value="${map.addressString}">
			                  				<c:if test="${map.typeString eq 'S'}">
				                  				<select class="typeString">
				                  					<option id="type" value="S">默认</option>
				                  					<option id="type" value="A">其他</option>
				                  				</select>
			                  				</c:if>
			                  				<c:if test="${map.typeString eq 'A'}">
				                  				<select class="typeString">
				                  					<option id="type" value="A">其他</option>
				                  					<option id="type" value="S">默认</option>
				                  				</select>
			                  				</c:if>
			                  			</td>	
			                  			<td style="    padding-right: 120px;">
			                  				<input type="button"  style="margin-left: 34px;    width: 47px;" btnindex="${map.index}" class="bnt_blue_2 updateData" value="修改" onclick="updateType(this)"/>		                  						                  				
				                  			<input type="button"  style="margin-left: 34px;    width: 47px;" btnindex="${map.index}" class="bnt_blue_2 updateData" value="删除" onclick="deleteAddress(this)"/>		                  						                  							
				                  		</td>		              			 
				                  	</tr>
				                  	
			                  		</c:forEach>
			                  		
			                    </tbody>
			                  </table>
		                </form>
	                </div>
	              </div>
           </div>
<!--       </div> -->
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

