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
		<link href="https://www.aitaocui.cn/static/stylenew.css" rel='stylesheet' type='text/css' />
		<script type="text/javascript">	
				
		
		</script>
	</head>
	<body style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体; color: rgb(102, 102, 102);">
		<div style="margin-top:10px;">
			<img src="//www.aitaocui.cn/images/201802/1518369294944580763.jpg" width="100%" height="90px;" border="0" style="border: 0px;">
		</div>	
		<div class="block" style="width: 1200px; margin: 0 auto;">
		<script>
		  function testClick(){		
			
				var form = document.getElementById("theForm");
				if(check()){				
					form.submit();
				}
			}
		  function check(){
			//1.先取用户名输入框中的内容。
				var recname = $("#recname").val();
// 			alert(recname)
				if (!recname) {
					alert("收件人姓名不能为空！")
					return false;
				}else{
					var address = $("#address").val();
					if (!address) {
						alert("收件人地址不能为空！")
						return false;
					}else{
						var mobile = $("#mobile").val();
						if (!mobile) {
							alert("收件人电话不能为空！")
							return false;
						}else{
						
							return true;
						}
					}
				}	
		  }
		  function updateAdress(){
			  var recname = $("#recname").val();
			  var addressStr = $("#address").val();
			  var phone = $("#mobile").val();
			  var email = $("#email").val();
			  location.href = "http://localhost:8001/order/updateAdress.html?recname="+recname+"&addressStr="+addressStr+"&phone="+phone+"&email="+email;	
		
		  }
		</script>
			<form action="/order/payForInto.html" method="post" name="theForm" id="theForm" onsubmit="return checkOrderForm(this)">
				<script type="text/javascript">
			        var flow_no_payment = "您必须选定一个支付方式。";
			        var flow_no_shipping = "您必须选定一个配送方式。";
		        </script>
		        <h6 class="zf_title_1" style="background: #026302;border-radius: 2px 2px 0 0;">
			       	 <span>商品列表</span>
			       	 <a href="flow.php" style=" margin-right:10px;">修改</a>
		        </h6>
	        	<div class="flowBox flowbox-2">
	       			 <table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#cccccc" class="flow_table">
				      	<tbody>
					        <tr>
						        <th bgcolor="#eff2f0" style="text-align: center;">商品名称</th>
						        <th bgcolor="#eff2f0" style="text-align: center;">属性</th>
						        <th bgcolor="#eff2f0" style="text-align: center;">市场价：</th>
						        <th bgcolor="#eff2f0" style="text-align: center;">淘翠价：</th>
						        <th bgcolor="#eff2f0" style="text-align: center;">购买数量</th>
						        <th bgcolor="#eff2f0" style="text-align: center;">小计</th>
					        </tr>
	        				<tr><!-- <td colspan="6" bgcolor="#eff2f0">淘翠自营&nbsp;&nbsp;|&nbsp;&nbsp;<font color="red">自营产品可支持货到付款，如同时订购非自营产品可采用自营产品单独下单，或者多件产品一起选择在线支付。</font></td> -->
	        				</tr>
	        				<c:forEach items="${pathList}" var="map">
		       					<tr>
		      						<td bgcolor="#eff2f0" align="center">
		       							 <a href="/commodity/getCommodityDetails.html?labelId=${map.labelId}" target="_blank" class="">${map.name}</a>
		        					</td>
							        <td bgcolor="#eff2f0">规 格:尺寸：${map.specification } <br></td>
							        <td align="center" bgcolor="#eff2f0">${map.price}</td>
							        <td bgcolor="#eff2f0" align="center">${map.marketPrice}</td>
							        <td bgcolor="#eff2f0" align="center">${map.count }</td>
							        <td bgcolor="#eff2f0" align="center">${map.marketPrice*map.count}</td>
		       					</tr>
		       				</c:forEach>
		        				<tr>
		        					<td bgcolor="#eff2f0" colspan="7">
		        					购物金额小计 ￥${marketPriceCountStr}元，比市场价 ￥${priceCountStr }元 节省了 ￥${chaPriceStr }元 (${baifenBiStr })		                            		
		        					</td>
		        				</tr>		        		
	        			</tbody>
	       			 </table>
	        	</div>
	        	<div class="blank"></div>
			        <h6 class="zf_title tabzftitle">
			        <span>收货人信息</span>
			         <input type="button" value="修改" style="height: 33px;" onclick="updateAdress()">
<!-- 			        <a href="flow.php?step=consignee" class="f6">修改</a> -->
			        </h6>
	       			<div class="flowBox">
	       				<table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#ffffff">
	       				 	<tbody>
	       				 		<tr>
	        						<td bgcolor="#ffffff">
	       								 <font class="red">*</font>收货人姓名:
	        						</td>
	        						<td bgcolor="#ffffff">
	        							<input id="recname" type="text" value="${address.recname }" name="recname" >
	        						</td>
	        						<td bgcolor="#ffffff">
	        							<font class="red">*
	        							</font>手机:
	        						</td>
	        						<td bgcolor="#ffffff">
	        							<input id="mobile" type="text" value="${address.phone }" name="mobile">
	        						</td>
	        					</tr>
	        					<tr>
	        						<td bgcolor="#ffffff">
	        							<font class="red">*</font>详细地址:
	        						</td>
							        <td bgcolor="#ffffff">
							       	 	<input id="address" type="text" value="${address.addressStr }" style="width: 281px;" name="address">
							        </td>
							        <td bgcolor="#ffffff">
							       		<font  class="red"></font>电子邮件地址:
							        </td>
							        <td bgcolor="#ffffff">
							        	<input id="email" type="text" value="${address.email }" name="email">
							        </td>
	       	 					</tr>
	        				</tbody>
	        			</table>
	        		</div>
	        <div class="blank">
	     	</div>
	       	 <input name="shipping" type="radio" value="12" checked="checked" style="display:none">
	        <div class="blank"></div><!--h6 class="zf_title"><span>其它信息</span></h6><div class="flowBox"-->
	        <table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#ffffff"><!--tr>
	        <td colspan="2" bgcolor="#ffffff"><input type="checkbox" name="isopen" value="1" />
	        <strong>大师帮助开光</strong>&nbsp;&nbsp;注：勾选此选项,所选购物品如果包含翡翠则将送往福建林阳禅寺，由释智琳大师亲自为您开光祈福。
	        <br />
	        </td>
	        </tr>
	        <tr>
	        <td align="center" valign="middle" bgcolor="#ffffff" width="12%">
	        <strong>淘翠网开光<br />翡翠相关事宜</strong>
	        </td>
	        <td bgcolor="#ffffff">凡是在淘翠网请购翡翠需要开光的翠友，都需要预先付款。翡翠开光是修行得道高人通过一种仪式接引灵性注入翡翠之中，此事乃是与佛教相关之事，需慎重而待，不要有怠慢之心，故不支持货到付款。翡翠本身的灵性可让佩戴者修身养性同时为佩戴者驱灾避邪，带去好运连连与平安如意。
	        </td>
	        </tr>
	        <tr>
	        <td align="center" valign="top" bgcolor="#ffffff">
	        <strong>订单附言</strong>
	        </td>
	        <td bgcolor="#ffffff">
	        <textarea name="postscript" cols="80" rows="3" id="postscript" style="border:1px solid #cccccc;"></textarea>
	        </td></tr--></table><div class="blank">
	        </div>
	        
	        
			        <h6 class="zf_title tabzftitle">
			        	<span>费用总计</span>
			        </h6>
	        		<div class="flowBox">
	        			<div id="ECS_ORDERTOTAL">
	        				<table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#eeeeee">
	       						 <tbody>
	        						<tr>
	        							<td align="left" bgcolor="#ffffff">
	     									 商品总价: <font class="f4_b">￥${marketPriceCountStr}元</font>
	      								</td>
	      							</tr>
							        <tr>
								        <td align="left" bgcolor="#ffffff"> 应付款金额: 
								      		<font class="f4_b">￥${marketPriceCountStr}元</font>
							      		</td>
							      	</tr>
	      						</tbody>
	      					</table>
	      				</div>
	      			</div>
	      <div class="blank"> </div>
		      <h6 class="zf_toptxt">
			      <b>选择你方便的支付方式：</b>没有合适的支付方式？立即
		    	  <a href="javascript:void(0);" rel="nofollow">
		    			<img src="/static/img/zf_lx.jpg" onclick="javascript:window.open('http://chat16.live800.com/live800/chatClient/chatbox.jsp?companyID=532682&amp;configID=75008&amp;jid=8714029053', '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');">
		   		  </a>为你解决
	    	  </h6>
	    	  <div class="flowBox flowzffs">
	    		<div id="tabT" class="tabzftitle clearfix">
	    			<div class="title hover">
	    				<span>扫码支付</span>
	    			</div>
	    			<div class="title">
	    				<span>银行转账</span>
	    			</div>
	    			<div class="title">
	    				<span>境外汇款</span>
	    			</div>
	    		</div>
	    		<div id="tabC" class="zftabbody">
	    			<div class="flowhdfk clearfix" payment="7">
	   					 <strong style="font-size:14px;">扫码付款支持：微信、支付宝、花呗、信用卡及储蓄卡等多种支付方式。确认提交订单后，扫描订单生成的二维码进行支付即可完成。</strong>
	    			</div>
	    		<div class="flowyhzz clearfix" payment="2" style="display:none">
	    		<div class="cx_txt">
	  				  汇款前请先联系在线客服(热线电话：400-1010-871)，确认您要订购的翡翠及金额。汇款成功后，银行会即时通知我们的财务专员，我们将在第一时间内向您确认。八成以上素未谋面的翠友都是直接通过银行给我们汇款，金额有数万元的也有数百万元的，而我们也自始至终恪守商业道德，资金上从未有过有一分一厘的差错。
	    		</div>
	    <table width="100%" cellpadding="8" cellspacing="0">
		    <thead>
			    <tr>
				    <th style="text-align:center">银行</th>
				    <th>卡号</th>
				    <th style="text-align:center">帐号</th>
				    <th>开户行</th>
			    </tr>
		    </thead>
	    	<tbody>
			    <tr>
				    <td align="center">
				    	<input type="radio" name="payment_id" value="CCB">
				    	<img src="/static/images/yinhang/CCB.jpg">
				    </td>
				    <td>6227 0018 2335 0133 5xx</td>
				    <td align="center">卢飞妃</td>
				    <td>中国建设银行股份有限公司福州市津泰分理处</td>
			    </tr>
	   			<tr>
			    	<td align="center">
				   	 	<input type="radio" name="payment_id" value="BOC"><img src="/static/images/yinhang/BOC.jpg">
				    </td>
				    <td>6217 8664 0000 2803 3xx</td>
				    <td align="center">卢飞妃</td>
				    <td>中国银行福建省分行营业部</td>
			    </tr>
	    		<tr>
	    			<td align="center">
					    <input type="radio" name="payment_id" value="ABC">
					    <img src="/static/images/yinhang/ABC.jpg"></td>
				    <td>6228 4800 6309 4265 0xx</td>
				    <td align="center">卢飞妃</td>
				    <td>中国农业银行股份有限公司福建省分行营业部营业厅</td>
			    </tr>
	   		 	<tr>
				    <td align="center">
					    <input type="radio" name="payment_id" value="ZSBC">
					    <img src="/static/images/yinhang/ZSBC.jpg">
				    </td>
				    <td>6214 8359 1743 77xx</td>
				    <td align="center">卢飞妃</td>
				    <td>招商银行福州晋安支行</td>
			    </tr>
	    	</tbody>
		</table>
	</div>
	<div class="flowyhzz clearfix" payment="2" style="display:none">
				<div class="cx_txt">
	   				 汇款前请先联系在线客服(热线电话：400-1010-871)，确认您要订购的翡翠及金额。汇款成功后，银行会即时通知我们的财务专员，我们将在第一时间内向您确认。八成以上素未谋面的翠友都是直接通过银行给我们汇款，金额有数万元的也有数百万元的，而我们也自始至终恪守商业道德，资金上从未有过有一分一厘的差错。
	    		</div>
	    		<table width="100%" cellpadding="8" cellspacing="0">
		    		<thead>
			    		<tr>
				    		<th style="text-align:center">银行</th>
				    		<th>卡号</th>
				    		<th style="text-align:center">帐号</th>
				    		<th>开户行</th>
			    		</tr>
		    		</thead>
		    		<tbody>
		    		<tr>
			    		<td align="center"><input type="radio" name="payment_id" value="PSBC"><img src="/static/images/yinhang/CEB.jpg"></td>
			    		<td>6226 6317 0044 76xx</td>
			    		<td align="center">FEIFEI LU</td>
		    			<td>
		    				<span>中国光大银行福州鼓楼支行 CHINA EVERBRIGHT BANK FUZHOU BR.</span>
		    				<br>
			    			<span>银行地址：NO.148, BEI HUAN MID. ROAD, FUZHOU, FUJIAN, CHINA</span><br>
							SWIFT 代码: EVERCNBJFZ1
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	   <script type="text/javascript">
	    function tabs(b, a, c) {
	        $(c).each(function() {
	            $(this).children().eq(0).show();
	        });
	        $(b).each(function() {
	            $(this).children().eq(0).addClass(a);
	        });
	        $(b).children().hover(function() {
	            $(this).addClass(a).siblings().removeClass(a);
	            var d = $(b).children().index(this);
	            $(c).children().eq(d).show().siblings().hide();
	        })
	    }
	    tabs("#tabT","hover","#tabC");
	    </script>
	    <input type="hidden" id="payment" name="payment" value="">
	    <input type="hidden" id="data" name="data" value="">
	    </div>
			   
	</form>
	 <div align="center" style="margin:30px auto 8px;">
				    <input type="image" src="/static/img/bnt_subOrder.gif" id="" onclick="testClick()">
				    <input type="hidden" name="step" value="done">
			    </div>
</div>
	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

