<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员登录</title>

<%-- jsp头部的引入
	包含 CSS样式
	Jquery的引入
	Base标签
 --%>
<%@ include file="/pages/common/header.jsp" %>
<script type="text/javascript" src="/static/js/jquery-1.7.2.js"></script>
<link type="text/css" rel="stylesheet" href="/static/css/style1.css" >
<script type="text/javascript">
	function submit_form(){		
		var form = document.getElementById("loin_table");
		if(check()){
			form.submit();
		}else{
			$("span.errorMsg").text("用户名或密码错误");
		}
		
	}

	//页面加载完成之后！

	function check(){			
// 			//1.先取用户名输入框中的内容。
			var username = $("#loginusername").val();

// 			//2.先取密码输入框中的内容。
			var password = $("#loginpassword").val();
			
//  			alert(password)
			var flag;
			$.ajax({
				             type: "GET",
				             url: "<c:url value='/user/check.html'/>"+"?username="+username+"&password="+password,
				             contentType: 'application/x-www-form-urlencoded;charset=utf-8',				         
				             dataType: "json",
				             async: false,
				             success: function(data){
					            if(data){
					            	flag= true
					            }	else{
					            	flag= false 
					            }		                    
				             },
				             error:function(e){
				                   console.log(e);
				             }
			});		
			return flag
	}
</script>

</head>
<body>
		<div id="login_header">
			<img src="/static/img/userimg_1.jpg" height="400px" style="padding-left: 80px;"/>
		</div>	
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit" style="padding-left:40px;">
								<h1>欢迎登录 <a href="/user/regist.html">立即注册</a></h1>
								
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
<%-- 									<%=request.getAttribute("msg") == null ? "请输入用户名和密码" : request.getAttribute("msg") %> --%>
<%-- 									${ empty requestScope.msg ? "请输入用户名和密码" : requestScope.msg} --%>
								</span>
							</div>
							<div class="form">
								<form action="/user/ifLogin.html" method="post" id="loin_table">
									<input type="hidden" name="action" value="login" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" 
										tabindex="1" name="loginusername" id="loginusername" 
										value="${ requestScope.username }"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" 
										tabindex="1" name="loginpassword" id="loginpassword" />
									<br />
									<br />
									<div style="padding-left:45px;">
										<input type="button" value="登录" id="sub_btn" onclick="submit_form()"/>
									</div>
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>

	
	<%-- 这是页脚的引入 --%> 
	<%@ include file="/pages/common/footer.jsp" %>

</body>
</html>