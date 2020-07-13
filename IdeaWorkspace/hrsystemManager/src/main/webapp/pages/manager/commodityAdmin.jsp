<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<!-- 	<script src="js/jquery.min.js"></script> -->
	<script src="/static/js/uploadImg.js"></script>
	<link rel="stylesheet" type="text/css" href="/static/css/uploadImg.css">
<%-- jsp头部的引入
		包含 CSS样式
		Jquery的引入
		Base标签
	 --%>
	<%@ include file="/pages/common/header.jsp" %>
	<%@ include file="/pages/common/adminManagerCom.jsp" %>
	</head>
	<body style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体; color: rgb(102, 102, 102);">
		<div class="AreaR3" style="width:1015px !important;">
			<div class="userCenterBox boxCenterList clearfix" style="_height:1%; line-height:26px;margin-top: -394px; margin-bottom: 0px;margin-left: -65px;">
				<h5><span>商品录入</span></h5>
				<div class="blank"></div>
			</div>
			
               	<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
               		<tbody>
               			<tr>
			                  <td  bgcolor="#FFFFFF">
			                  		商品名：<input name="areaName" id="areaName"style="height: 25px; width: 220px;" type="text" value="${brithdayStr}" size="25" class="inputBg">
			                  </td>
			                  <td  bgcolor="#FFFFFF">
			                  		商品类型：<select class="typeid">										 
											<option id="typeid" value="1">吊坠</option>
											<option id="typeid" value="2">手镯</option>
											<option id="typeid" value="3">戒指</option>
											<option id="typeid" value="4">珠链</option>
										  </select>			          
			                  </td>							  
			               </tr>
               			   <tr>
			                  <td  bgcolor="#FFFFFF">
			                  		商品规格：<input name="specification" id="specification" style="height: 25px; width: 220px;" type="text" value="长宽厚:00-00-00mm 重量：0.00g" size="25" class="inputBg">
			                   </td>
			                
			                  <td  bgcolor="#FFFFFF" id="extend_field5i">种水：
			                  	 <input name="water"  id="water"type="text"style="height: 25px; width: 220px;" class="inputBg" value="糯冰">
			                  </td>			              
               				</tr>
               				<tr>
			                  <td  bgcolor="#FFFFFF">
			                  		商品价格：<input name="price" id="price"type="text" style="height: 25px; width: 220px;"value="" size="25" class="inputBg">
			                   </td>
			                
			                  <td  bgcolor="#FFFFFF" id="extend_field5i">淘翠价：
			                  	 <input name="marketprice" id="marketprice" type="text" style="height: 25px; width: 220px;"class="inputBg" value="">
			                  </td>			              
               				</tr>
               				<tr>
				                  <td  bgcolor="#FFFFFF">
				                  		商品数量：<input name="count" id="count" style="height: 25px; width: 220px;"type="text" value="1" size="25" class="inputBg">
				                   </td>
				                
				                  <td  bgcolor="#FFFFFF" id="extend_field5i">是否展示：
				                  	<select class="display">										 
										<option id="display" value="N">不展示</option>
										<option id="display" value="Y">展示</option>								
									  </select>		
				                  </td>	                 
			                </tr>		
               			
			                   		                            		
               			</tbody>
               		</table><br/>
               		<div style="    padding-left: 10px;">
               			商品描述：<input name="depiction" id="depiction" type="text" value="完整无纹裂，种好水头足，玉质细腻，雕工精美，黄翡色泽绮丽，明亮夺目。" size="25" style="width: 620px;height: 25px;padding: 10px 10px 10px 10px;" class="inputBg"><br/><br/>
				                            随单配件： <input name="accessory" id="accessory" type="text" class="inputBg" style="width: 620px;height: 25px;padding: 10px 10px 10px 10px;"  value="精美珠宝盒或首饰袋一个、国家检测A货证书及挂件另赠精美挂绳一条;赠送配件按实物邮寄为准。">		
               		</div>  
               		<br/>   
				<div id="inputBox" style="    margin-bottom: 10px;">
					<input type="file" name="file" title="请选择图片" id="file" multiple="multiple" accept="image/png,image/jpg,image/gif,image/JPEG"/>选择图片
					
				</div>
				<span style="color: red;">（请上传6张商品展示图！）</span>
			    <div id="imgBox" style="border: 1px solid #007e56; ">
			    </div>
			    
			    <!-- <button id="btn">上传</button> -->
			    
			    <!--  上传按钮的标签在form表单里面最好不要写成button，在非IE内核的浏览器会
			    	  直接提交form的action,而不是我们自己ajax里面写的地址。具体可百度看
			    	  button 和 input type="button" 的区别
			     -->			    
			    <input type="submit" value="添加商品" id="btn">
				
		</div>
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
	<script type="text/javascript">
	
		imgUpload({
			inputId:'file', //input框id
			imgBox:'imgBox', //图片容器id
			buttonId:'btn', //提交按钮id
			upUrl:'/adminManager/uploadImg.html',  //提交地址
			data:'file', //参数名
			num:"10"//最多上传图片个数
		})
	</script>
	
</html>

