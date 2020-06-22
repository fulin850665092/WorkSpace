<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>衡阳师范学院学生信息管理系统注册页面</title>

<script type="text/javascript" src="/static/js/jquery-1.7.2.js"></script>
<link type="text/css" rel="stylesheet" href="/static/css/style1.css" >
<%-- jsp头部的引入
	包含 CSS样式
	Jquery的引入
	Base标签
 --%>
<%@ include file="/pages/common/header.jsp" %>
<script type="text/javascript">
	//页面加载完成之后！
	$(function(){
		$("#kaptcha_img").click(function(){
			// img图片对象有src属性。可以设置。获取图片地址。
			this.src = "kaptcha.jpg?d=" + new Date();
			
		});
		
		
		$("#sub_btn").click(function(){
			
			//1.先取用户名输入框中的内容。
			var usernameTextValue = $("#registusername").val();

// 			alert(usernameTextValue)
			//2.创建一个正则表达式去验证
			// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
			var patt = /^\w{5,12}$/;
			//3。提示用户验证结果
			if (!patt.test(usernameTextValue)) {
				$("span.errorMsg").text("用户名不合法！");
				return false;
			}
			
			//1.先取密码输入框中的内容。
			var passwordTextValue = $("#registpassword").val();
			//2.创建一个正则表达式去验证
			// 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
			var passPatt = /^\w{5,12}$/;
			//3。提示用户验证结果
			if (!passPatt.test(passwordTextValue)) {
				$("span.errorMsg").text("密码不合法！");
				return false;
			}
			
			// 验证确认密码：和密码相同
			var repwdTextValue = $("#repwd").val();
			if (repwdTextValue != passwordTextValue) {
				$("span.errorMsg").text("密码和确认密码不一致！");
				return false;	
			}
			
			// 邮箱验证：xxxxx@xxx.com
			//1.先获取邮箱中的文本内容
			var emailTextValue = $("#email").val();
			//2.然后创建正则表达式
			var emailPatt = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			//3.验证完成之后。要提示用户。
			if (!emailPatt.test(emailTextValue)) {
				$("span.errorMsg").text("邮箱格式不合法！");
				return false;		
			}
			
			// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
			var codeTextValue = $("#code").val();
// 			alert("去掉空格前：[" + codeTextValue + "]");
			var codeTextValue = $.trim(codeTextValue);
// 			alert("去掉空格后：[" + codeTextValue + "]");
			
			if (codeTextValue == "") {
				$("span.errorMsg").text("验证码不能为空！");
				return false;	
			}
			
			$("span.errorMsg").text("");
			return true;
		});
	});

</script>


<style type="text/css">
	.login_form{
		height:440px;
/* 		margin-top: 20px; */
	}
	
</style>
</head>
<body>

		<div id="login_header">
			<img src="/static/img/userimg_1.jpg" height="400px" style="padding-left: 80px;"/>
		</div>		
			<div class="login_banner">			
				<div id="content" style="margin-top: -60px;">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1 style="padding-left:60px">欢迎注册</h1>
								<div>
									<span class="errorMsg" style="padding-right: 270px">
										${ requestScope.msg }
									</span>		
								</div>
													
							</div>
							<br/>
							<div class="form">
								<form action="/user/regist.html" method="post">
									<input type="hidden" name="action" value="regist" />
									<label>用户名：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" 
										autocomplete="off" tabindex="1" name="registusername" id="registusername" 
										value="${ requestScope.username }"
										/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" 
										autocomplete="off" tabindex="1" name="registpassword" id="registpassword" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" 
										autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" 
										autocomplete="off" tabindex="1" name="email" id="email" 
										value="${ requestScope.email }"
										/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 100px;" name="code" id="code"/>
<!-- 									<img id="kaptcha_img" src="kaptcha.jpg" width="100" height="30" style="float: right; margin-right: 40px; ">									 -->
									    <img alt="验证码" width="100" height="30" style="float: right; margin-right: 60px; "onclick="this.src='/user/kaptchaImage?d='+new Date()*1" src="/user/kaptchaImage"/>
									<br />
									<br />
									<div style="padding-left:65px;">
										<input type="submit" value="注册" id="sub_btn" />
										
									</div>
								</form>
								<a href="/user/login.html" style="padding-left:10px;float: right;"><div><span style="color: red;">我已有账号，我要登录!</span></div></a>								
							</div>						
						</div>
					</div>
				</div>
			</div>
		

	
	<%-- 这是页脚的引入 --%> 
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>