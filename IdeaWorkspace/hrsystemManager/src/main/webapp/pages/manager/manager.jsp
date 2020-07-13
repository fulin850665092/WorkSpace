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
		
		<div class="AreaR3" >
<!-- 				<div class="box_1"> -->
					<div class="userCenterBox boxCenterList clearfix" style="_height:1%; line-height:26px;margin-top: -334px; margin-bottom: 120px;margin-left: -55px;">
						<font class="f5">
							<b class="f14">${user.username}</b>
							 欢迎您回到 淘翠网！
						</font>
						<br>
						<div class="blank"></div>   
	         	 			您的上一次登录时间: ${lastDate }<br>
	         			<div class="blank5"></div>
		   
	         	  <div class="blank5"></div>
	          	  <div style="margin:5px 0; border:1px solid #060;padding:10px 20px; background-color:#fff;">
		          		<img src="/static/images/manager/note.gif" alt="note">&nbsp;用户中心公告！
		          		<input type="text" id="gonggao" disabled="disabled" value="	${gonggaoStr}" style="width: 1024px;color: red;">
		          		
		          		</input>
		          </div>
		          <br><br>
		        <div class="f_l" style="width:490px;">
		          	 <h5>
		          	 <span>您的账户</span></h5><div class="blank">
		          </div>
		          <c:if test="${count eq null}">
                   		您共提交了0个订单<br>
                   	</c:if>
                   	<c:if test="${count ne  null}">
                   		您共提交了${count}个订单<br>
                   	</c:if>
<!-- 				          余额:<a href="user.php?act=account_log" style="color:#b00;">￥0.00元</a><br> -->
<!-- 				          红包:<a href="user.php?act=bonus" style="color:#b00;">共计 0 个,价值 ￥0.00元</a><br> -->
				    <c:if test="${jifen eq null}">
                   		积分:0积分<br><br>
                   	</c:if>
                   	<c:if test="${jifen ne  null}">
                   		积分:${jifen}积分<br>
                   	</c:if>
				          </div>
		          		<div class="blank5"></div>
		         </div>
		     </div>
<!-- 		</div> -->
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

