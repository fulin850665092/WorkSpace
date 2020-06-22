<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript">
	function updePassword(){
		var newpasswordd=$("#newpasswordd").val();
		var newpassword=$("#newpassword").val();
		var oldpassword=$("#oldpassword").val();
		if( check()){
			if (confirm("您确实要修改用户密码吗？")){
				
				location.href="http://localhost:8001/manager/updePassword.html?oldpassword="+oldpassword+"&newpassword="+newpassword;
			}
		}
			
	}
	function check(){
		var newpasswordd=$("#newpasswordd").val();
		var newpassword=$("#newpassword").val();
		var oldpassword=$("#oldpassword").val();
		if(oldpassword){
			if(newpassword){
				if(newpasswordd){		
					if(newpassword===newpasswordd){					
						return true;												
					}else{
						alert("确认密码与新密码不一致！");
						return false;
					}							
				}else{
					alert("确认密码不能为空！");
					return false;
				}
			}else{
				alert("新密码不能为空！");
				return false;
			}
		}else{
			alert("旧密码不能为空！");
			return false;
		}
		
	}

</script>
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
<!-- 			<div class="box_1">					 -->
				<div class="userCenterBox boxCenterList clearfix" style="_height:1%; line-height:26px;margin-top: -334px; margin-bottom: 22px;margin-left: -65px;">
					<script type="text/javascript">
	                      var bonus_sn_empty = "请输入您要添加的红包号码！";
	                      var bonus_sn_error = "您输入的红包号码格式不正确！";
	                      var email_empty = "请输入您的电子邮件地址！";
	                      var email_error = "您输入的电子邮件地址格式不正确！";
	                      var old_password_empty = "请输入您的原密码！";
	                      var new_password_empty = "请输入您的新密码！";
	                      var confirm_password_empty = "请输入您的确认密码！";
	                      var both_password_error = "您现两次输入的密码不一致！";
	                      var msg_blank = "不能为空";
	                      var no_select_question = "- 您没有完成密码提示问题的操作";
                  </script>
                  <h5><span>个人资料</span></h5>
                  <div class="blank"></div>
                  <form name="formEdit" action="/manager/userUpde.html" method="post" onsubmit="return userEdit()">
                  	<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
                  		<tbody>
                  			<tr>
			                 <td width="28%" align="right" bgcolor="#FFFFFF">出生日期： </td>
			                  <td width="72%" align="left" bgcolor="#FFFFFF">
				                  <input name="brithday" type="text" value="${brithdayStr}" size="25" class="inputBg">
				                  <span style="color:#FF0000"> *</span>
			                  </td>
			                </tr>
                  			<tr>
			                  <td width="28%" align="right" bgcolor="#FFFFFF">性　别： </td>
							  <td width="72%" align="left" bgcolor="#FFFFFF">
							  	<c:if test="${userInf.sex eq '0'}">
				                  	<input type="radio" name="sex" value="保密" checked="checked">
				                   		 保密&nbsp;&nbsp;
				                  	<input type="radio" name="sex" value="男">
				                  		  男&nbsp;&nbsp;
				                   	<input type="radio" name="sex" value="女">
				                  		女&nbsp;&nbsp; 
				                </c:if>
				                <c:if test="${userInf.sex eq '男' }">
				                  	<input type="radio" name="sex" value="保密" >
				                   		 保密&nbsp;&nbsp;
				                  	<input type="radio" name="sex" value="男" checked="checked">
				                  		  男&nbsp;&nbsp;
				                   	<input type="radio" name="sex" value="女">
				                  		女&nbsp;&nbsp; 
				                </c:if>
				                <c:if test="${userInf.sex eq '女' }">
				                  	<input type="radio" name="sex" value="保密" >
				                   		 保密&nbsp;&nbsp;
				                  	<input type="radio" name="sex" value="男">
				                  		  男&nbsp;&nbsp;
				                   	<input type="radio" name="sex" value="女" checked="checked">
				                  		女&nbsp;&nbsp; 
				                </c:if>
				                   
			                  </td>
                  			</tr>
                  			<tr>
			                  <td width="28%" align="right" bgcolor="#FFFFFF">电子邮件地址： </td>
			                  <td width="72%" align="left" bgcolor="#FFFFFF">
				                  <input name="email" type="text" value="${userInf.email }" size="25" class="inputBg">
				                  <span style="color:#FF0000"> *</span>
			                  </td>
                  			</tr>
                  			<tr>
			                  <td width="28%" align="right" bgcolor="#FFFFFF" id="extend_field5i">手机：</td>
			                  <td width="72%" align="left" bgcolor="#FFFFFF">
				                  <input name="phone" type="text" class="inputBg" value="${userInf.phone }">
				                  <span style="color:#FF0000"> *</span>
			                  </td>
                  			</tr>
                 			 <tr>
			                  <td colspan="2" align="center" bgcolor="#FFFFFF">
				                  <input name="act" type="hidden" value="act_edit_profile">
				                  <input name="submit" type="submit" value="确认修改" class="bnt_blue_1">
			                  </td>
                  			</tr>
                  		</tbody>
                  	</table>
                 </form>
                 <form >
                  	<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
                  		<tbody>
                  			<tr>
			                  <td width="28%" align="right" bgcolor="#FFFFFF">原密码：</td>
				                  <td width="76%" align="left" bgcolor="#FFFFFF">
				                  <input name="oldPassword" type="password" id="oldpassword" size="25" class="inputBg">
			                  </td>
                  			</tr>
                  			<tr>
			                  <td width="28%" align="right" bgcolor="#FFFFFF">新密码：</td>
				                  <td align="left" bgcolor="#FFFFFF">
				                  <input name="newPassword" type="password" id="newpassword" size="25" class="inputBg">
			                  </td>
                  			</tr>
                  			<tr>
			                  <td width="28%" align="right" bgcolor="#FFFFFF">确认密码：</td>
			                  <td align="left" bgcolor="#FFFFFF">
			                	  <input name="comfirm_password" type="password" id="newpasswordd" size="25" class="inputBg">
			                  </td>
                  			</tr>
                  			<tr>
			                  <td colspan="2" align="center" bgcolor="#FFFFFF">
				                  <input name="act" type="hidden" value="act_edit_password">
				                  <input name="submit" type="button" class="bnt_blue_1" value="确认修改" onclick="updePassword()">
			                  </td>
                  			</tr>
                  		</tbody>
                  	</table>
                </form>
             </div>
         </div>
<!--     </div> -->
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

