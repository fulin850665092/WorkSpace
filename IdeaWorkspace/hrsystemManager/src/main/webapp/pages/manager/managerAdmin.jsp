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
	<%@ include file="/pages/common/adminManagerCom.jsp" %>
	</head>
	<body style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体; color: rgb(102, 102, 102);">
		
		<div class="AreaR3" >
<!-- 				<div class="box_1"> -->
					<div class="userCenterBox boxCenterList clearfix" style="_height:1%; line-height:26px;margin-top: -390px; margin-bottom: 120px;margin-left: -55px;">
						<font class="f5">
							尊敬的管理者：
							<b class="f14">${user.username}</b>
							 ，您好！						
						</font>
						<br>
						<div class="blank"></div>   
	         	 			 欢迎您回到淘翠网！<br>
	         			<div class="blank5"></div>
						<div class="blank"></div>   
	         	 			您的上一次登录时间: ${lastDate }<br>
	         			<div class="blank5"></div>
		          <div style="display:none">
		          	您的等级是 注册用户  ,您还差 1000000 积分达到 VIP用户 <br>
		          </div>
	         	  <div class="blank5"></div>
	          	  <div style="margin:5px 0; border:1px solid #060;padding:10px 20px; background-color:#fff;">
		          		<img src="/static/images/manager/note.gif" alt="note">&nbsp;用户中心公告！<br/>		          		
		          		<textarea id="gonggao"  name="textfield" rows=3 style="width: 1024px;">
		          			${gonggaoStr.trim() }
		          		</textarea>
		          </div>
		          <input type="button" value="发布"  onclick="gonggao()"/>
		          <script type="text/javascript">
		          		function gonggao(){
		          			var gonggao=$("#gonggao").val();
		          		    location.href = "http://localhost:8001/adminManager/savaGongGao.html?gonggao=" + gonggao
		          		}
		          </script>
		          <br><br>
		          <div class="f_l" style="width:490px;">
		          	 <h5>
		          	 <span>您的账户</span></h5><div class="blank">
		          </div>
		          	<c:if test="${huiyuanCount eq null}">
                   		会员总数：0人<br>
                   	</c:if>
                   	<c:if test="${huiyuanCount ne  null}">
                   		会员总数:${huiyuanCount}人<br>
                   	</c:if>
<!-- 				          余额:<a href="user.php?act=account_log" style="color:#b00;">￥0.00元</a><br> -->
<!-- 				          红包:<a href="user.php?act=bonus" style="color:#b00;">共计 0 个,价值 ￥0.00元</a><br> -->
				    <c:if test="${adminCount eq null}">
                   		管理员总数:0人<br>
                   	</c:if>
                   	<c:if test="${adminCount ne  null}">
                   		管理员总数:${adminCount}人<br>
                   	</c:if>
                   	订单总数：${allOrderCount}<br>
				          </div>
		          <div class="f_r" style="width:490px;">
<!-- 		          		<h5><span>用户提醒</span></h5> -->
		          		<div class="blank"></div>
                          	
		          		</div>
		          		<div class="blank5"></div>
		         </div>
		     </div>
<!-- 		</div> -->
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

