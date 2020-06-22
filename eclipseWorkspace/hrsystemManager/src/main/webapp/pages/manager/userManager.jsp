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
		function updateUser(Obj){
			var id=null;
			var sex=null;
			var name=null;
			var brithday=null;
			var phone=null;
			var userType=null;
			$('table tr').each(function(){
				id=$(Obj).closest("tr").find("input[class='id']").val();
				name=$(Obj).closest("tr").find("input[class='name']").val();
				brithday=$(Obj).closest("tr").find("input[class='brithday']").val();
				phone=$(Obj).closest("tr").find("input[class='phone']").val();				
				userType=$(Obj).closest("tr").find("select[class='userType']").val();	
				sex=$(Obj).closest("tr").find("select[class='sex']").val();	
			})
// 			alert(userType)
// 			return ;
			if (confirm("您确实要修改"+name+"的人员信息吗？")){
				if (check(sex,name,brithday,phone,userType)) {
					location.href="/adminManager/updateUser.html?id="+id+"&sex="+sex+"&name="+name+"&brithday="+brithday+"&phone="+phone+"&userType="+userType;
				}
			}else{
				return;
			}
		}
		function check(sex,name,brithday,phone,userType){
// 			var sex=$(".sex").val();
// 			var name=$("#name").val();
// 			var brithday=$("#brithday").val();
// 			var phone=$("#phone").val();
// 			var userType= $(".userType").val();		
			if(sex){
				if(name){
					if(userType){
						if(brithday){
							if(phone){
								return true;
							}else{
								alert("电话不能为空！");
								return false;
							}
						}else{
							alert("出生年月不能为空！");
							return false;
						}
					}else{
						alert("人员类型不能为空！");
						return false;
					}
				}else{
					alert("姓名不能为空！");
					return false;
				}
			}else{
				alert("性别不能为空！");
				return false;
			}
		}
	</script>
	<body style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体; color: rgb(102, 102, 102);">
		<div class="AreaR3" style="width:1015px !important;">
<!-- 			<div class="box_1"> -->
				<div class="userCenterBox boxCenterList clearfix" style="_height:1%; line-height:26px;margin-top: -394px; margin-bottom: 0px;margin-left: -65px;">
					<h5><span>用户信息管理</span></h5>
					<div class="blank">
				</div>
					<table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#cccccc">
						<tbody>							 
							<tr>								
								<th bgcolor="#ffffff" style="text-align: center;">用户id</th>
								<th bgcolor="#ffffff" style="text-align: center;">姓名</th>
								<th bgcolor="#ffffff" style="text-align: center;">性别</th>
								<th bgcolor="#ffffff" style="text-align: center;">生日</th>
								<th bgcolor="#ffffff" style="text-align: center;">手机</th>
								<th bgcolor="#ffffff" style="text-align: center;">用户类型</th>
								<th bgcolor="#ffffff" style="text-align: center;">操作</th>
							</tr>
							<c:forEach items="${userList}" var="user">
							<tr>
									<td bgcolor="#ffffff" align="center">
										<div class="blank5"></div>
										${user.id}
									</td>
									<td align="center" bgcolor="#ffffff">
										<input type="text" class="name" name="name" id="name" value="${user.username}" size="4" class="inputBg" style="text-align:center;height:30px;width: 109px; " onkeydown="showdiv(this)">
									<br></td>
									<td align="center" bgcolor="#ffffff">
										<select class="sex">
											<c:if test="${user.sex ne '女'}">
												<option id="sex" value="男">男</option>
												<option id="sex" value="女">女</option>
											</c:if>
											<c:if test="${user.sex eq '女'}">
												<option id="sex" value="女">女</option>
												<option id="sex" value="男">男</option>
											</c:if>																
										</select>
									</td>
									<td align="center" bgcolor="#ffffff">
										<input type="text" class="brithday"name="brithday" id="brithday" value="${user.remark }" size="4" class="inputBg" style="text-align:center;height:30px;width: 109px; " onkeydown="showdiv(this)">
									</td>
									<td align="center" bgcolor="#ffffff">
										<input type="text" class="phone"name="phone" id="phone" value="${user.phone}" size="4" class="inputBg" style="text-align:center;height:30px;width: 109px; " onkeydown="showdiv(this)">
									</td>
									<td align="center" bgcolor="#ffffff">
										<select class="userType">
											<c:if test="${user.usertype eq 'A'}">
												<option id="usertype" value="A">会员</option>
												<option id="usertype" value="S">管理员</option>	
											</c:if>
											<c:if test="${user.usertype eq 'S'}">
												<option id="usertype" value="S">管理员</option>
												<option id="usertype" value="A">会员</option>
											</c:if>										
										</select>
									</td>
																
									<td align="center" bgcolor="#ffffff">
										<input id="id" class="id" type="text" value="${user.id }" style="display: none;">
										<a style="text-decoration:none; ">
											<input  type="button" value="更新 " class="bnt_blue_1" onclick="updateUser(this)">
										</a>&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										<a style="text-decoration:none;" href="javascript:if (confirm('您确实要把该商品移出购物车吗？')) location.href='/adminManager/deleteUser.html?id=${user.id}'; ">
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
				<script type="Text/Javascript" language="JavaScript">
						<!--
						
								
						function selectPage(sel)
						{
						  sel.form.submit();
						}
				
						//-->
				</script>
				<script language="javascript">
					<!--
					function keyUp(e) {
					if(navigator.appName == "Microsoft Internet Explorer")
					{
					var keycode = event.keyCode;
					var realkey = String.fromCharCode(event.keyCode);
					}else{
					var keycode = e.which;
					var realkey = String.fromCharCode(e.which);
					}
					if(keycode==39){
					next=document.getElementById("next").href;
					window.location.href=next;
					}
					if(keycode==37){
					prev=document.getElementById("prev").href;
					window.location.href=prev;
					}
					}
					document.onkeydown = keyUp;
					//-->
				</script>
           </div>
           </div>
<!--            </div> -->
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

