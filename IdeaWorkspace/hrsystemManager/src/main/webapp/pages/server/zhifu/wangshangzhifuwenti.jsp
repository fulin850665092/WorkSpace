<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
	<style type="text/css">
		.help_left {
    width: 200px;
    color: #333;
    float: left;
    background: #fbfbfb;
    height: 100%;
    border: 1px solid #d3d3d3;
    padding-bottom: 25px;
}
	
	</style>
<%-- jsp头部的引入
		包含 CSS样式
		Jquery的引入
		Base标签
	 --%>
	 <link href="/static/css/header.css" rel='stylesheet' type='text/css' />
	  <link href="/static/css/article_c.css" rel='stylesheet' type='text/css' />
	<%@ include file="/pages/common/header.jsp" %>
<%-- 	<%@ include file="/pages/common/adminManagerCom.jsp" %> --%>
	</head>
	<body style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体; color: rgb(102, 102, 102);">
	<div class="block" style="width: 1200px; margin: 10px auto;"><div style="display: block;" class="article_topad mt10"><div class="indent0"><script type="text/javascript">
                $(function() {
                    $("#KinSlideshowss").KinSlideshow({
                        moveStyle: "right",
                        titleBar: {
                            titleBar_height: 30,
                            titleBar_bgColor: "",
                            titleBar_alpha: 0.5
                        },
                        titleFont: {
                            TitleFont_size: 12,
                            TitleFont_color: "#FFFFFF",
                            TitleFont_weight: "normal"
                        },
                        btn: {
                            btn_bgColor: "#FFFFFF",
                            btn_bgHoverColor: "",
                            btn_fontColor: "#000000",
                            btn_fontHoverColor: "#FFFFFF",
                            btn_borderColor: "",
                            btn_borderHoverColor: "",
                            btn_borderWidth: 1
                        }
                    });
                })
                </script><div id="KinSlideshowss" style="visibility: visible; width: 1200px; height: 128px; overflow: hidden; position: relative;"><div id="KSS_moveBox" style="width: 1200px; height: 128px; overflow: hidden; position: relative;"><div id="KSS_XposBox" style="float: left; width: 2000%;"><div id="KSS_content" style="float: left;"><a href="http://www.aitaocui.cn/mingjiangdashi/" target="_blank"><img src="//www.aitaocui.cn/images/201802/1518369294944580763.jpg" width="1200" height="128" border="0" style="border: 0px;"></a></div><div id="KSS_contentClone" style="float: left;"><a href="http://www.aitaocui.cn/mingjiangdashi/" target="_blank"><img src="//www.aitaocui.cn/images/201802/1518369294944580763.jpg" width="1200" height="128" border="0" style="border: 0px;"></a></div></div></div> <div class="KSS_titleBar" style="height: 30px; width: 100%; position: absolute; bottom: 0px; left: 0px; opacity: 0.5;"></div><div class="KSS_titleBox" style="height: 30px; width: 100%; position: absolute; bottom: 0px; left: 0px;"><h2 class="title" style="margin: 3px 0px 0px 6px; padding: 0px; font-size: 12px; color: rgb(255, 255, 255); font-family: Verdana; font-weight: normal;"></h2></div></div></div><div class="article_topad" style="display: none;"><div class="indent0"><div class="bx-wrapper" style="max-width: 100%;"><div class="bx-viewport" style="width: 100%; overflow: hidden; height: 378px; position: relative;"><ul class="nSlides bx_slide" style="display: none; width: 215%; position: relative;"></ul></div><div class="bx-controls bx-has-pager bx-has-controls-direction"><div class="bx-pager"></div><div class="bx-controls-direction"><a class="bx-prev" href="">Prev</a><a class="bx-next" href="">Next</a></div></div></div></div></div></div><div class="block" style="width: 1200px;"><div class="urlbox ur_box"><strong>当前位置:</strong><a href="https://www.aitaocui.cn/">淘翠网</a><code>&gt;</code><a href="http://www.aitaocui.cn/bangzhuzhongxin/article/">使用帮助</a><code>&gt;</code><a href="http://www.aitaocui.cn/taocuihelp/article/">淘翠网帮助</a><code>&gt;</code><a href="http://www.aitaocui.cn/pay/article/">交易方式</a><code>&gt;</code> 网上支付常见问题      </div></div><div class="block mt10 clearfix" style="width: 1200px;"><div class="article_slide f_l mt10" style="width: 200px;"><div class="help_left"><dl class="help_menu_item_0"><dt><span>新手指南</span></dt><dd><a href="http://www.aitaocui.cn/guide/48.html" title="用户注册"> 用户注册</a></dd><dd><a href="http://www.aitaocui.cn/guide/50.html" title="购物流程"> 购物流程</a></dd><dd><a href="http://www.aitaocui.cn/guide/51.html" title="常见问题"> 常见问题</a></dd><dd><a href="http://www.aitaocui.cn/guide/81.html" title="商品说明"> 商品说明</a></dd></dl><dl class="help_menu_item_0"><dt><span>购物保障</span></dt><dd><a href="http://www.aitaocui.cn/shipping/52.html" title="五大优势"> 五大优势</a></dd><dd><a href="http://www.aitaocui.cn/shipping/55.html" title="手镯测量"> 手镯测量</a></dd><dd><a href="http://www.aitaocui.cn/shipping/66.html" title="客户见证"> 客户见证</a></dd><dd><a href="http://www.aitaocui.cn/shipping/4086.html" title="关于我们"> 关于我们</a></dd></dl><dl class="help_menu_item_0"><dt><span>交易方式</span></dt><dd><a href="http://www.aitaocui.cn/pay/57.html" title="支付宝"> 支付宝</a></dd><dd><a href="http://www.aitaocui.cn/pay/69.html" title="银行汇款/转账"> 银行汇款/转账</a></dd><dd><a href="http://www.aitaocui.cn/pay/78.html" title="支付宝常见问题"> 支付宝常见问题</a></dd><dd class="nav"><a href="http://www.aitaocui.cn/pay/79.html" title="网上支付常见问题"> 网上支付常见问题</a></dd><dd><a href="http://www.aitaocui.cn/pay/236.html" title="信用卡分期付款"> 信用卡分期付款</a></dd><dd><a href="http://www.aitaocui.cn/pay/3731.html" title="淘翠网淘宝官方店"> 淘翠网淘宝官方店</a></dd><dd><a href="http://www.aitaocui.cn/pay/3739.html" title="订购及鉴赏"> 订购及鉴赏</a></dd></dl><dl class="help_menu_item_0"><dt><span>配送服务</span></dt><dd><a href="http://www.aitaocui.cn/peisongfuwu/60.html" title="送货范围"> 送货范围</a></dd><dd><a href="http://www.aitaocui.cn/peisongfuwu/61.html" title="配送说明"> 配送说明</a></dd><dd><a href="http://www.aitaocui.cn/peisongfuwu/62.html" title="签收与拒收"> 签收与拒收</a></dd><dd><a href="http://www.aitaocui.cn/peisongfuwu/82.html" title="常见配送问题"> 常见配送问题</a></dd></dl><dl class="help_menu_item_0"><dt><span>售后服务</span></dt><dd><a href="http://www.aitaocui.cn/shouhoufuwu/54.html" title="证书查询"> 证书查询</a></dd><dd><a href="http://www.aitaocui.cn/shouhoufuwu/56.html" title="订单查询"> 订单查询</a></dd><dd><a href="http://www.aitaocui.cn/shouhoufuwu/63.html" title="发票说明"> 发票说明</a></dd></dl><dl class="help_menu_item_0"><dt><span>免责条款</span></dt><dd><a href="http://www.aitaocui.cn/memberrule/65.html" title="法律声明"> 法律声明</a></dd><dd><a href="http://www.aitaocui.cn/memberrule/68.html" title="免责条款"> 免责条款</a></dd></dl><dl class="help_menu_item_0"><dt><span>帮助中心</span></dt><dd><a href="http://www.aitaocui.cn/bangzhuzhongxin/107.html" title="注册协议"> 注册协议</a></dd><dd><a href="http://www.aitaocui.cn/bangzhuzhongxin/112.html" title="保护客户隐私的声明"> 保护客户隐私的声明</a></dd></dl></div></div><script src="/static/js/jquery.KinSlideshow-1.2.1.min.js" type="text/javascript"></script><div class="article_main f_r" style="padding-left:70px;width: 980px;"><h2 class="article_name">网上支付常见问题</h2><div class="article_name2 clearfix"><span>来源：淘翠网</span><span>时间：2012-09-25</span><div class="bdsharebuttonbox fenxiang bdshare-button-style0-16" data-bd-bind="1552958960700"><h4 style="display: inline-block; ">分享到：</h4><a class="jiathis_button_tsina" data-cmd="tsina" title="分享到新浪微博"><img src="/themes/mmb_taocui/images/tsina.png" alt="" style="width: 20px;">
                    微博
                    </a><a class="jiathis_button_weixin" data-cmd="weixin" title="分享到微信"><img src="/themes/mmb_taocui/images/weixin.png" alt="" style="width: 20px;margin-top: 2px;">
                    微信</a><a class="jiathis_button_qzone" data-cmd="qzone" title="分享到QQ空间"><img src="/themes/mmb_taocui/images/qzone.png" alt="" style="width: 20px;">
                    空间</a></div><script>
window._bd_share_config = {
    "common": {
        "bdSnsKey": {},
        "bdText": "",
        "bdMini": "2",
        "bdPic": "",
        "bdStyle": "0",
        "bdSize": "16"
    },
    "share": {}
};
with(document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
</script></div><style type="text/css"></style><div id="anchor" class="n-anchor" style="display: none;"><div class="l"><div class="tit">目录</div></div><div class="r"><ul class="con" id="titles"><!--<li><a href=""></a></li><li><a href=""></a></li>--></ul></div><div class="clear"></div></div><div class="article_content"><style type="text/css">
                    .duan{display: block; float: left; width: 840px;}
                    .article_content:after {content: "."; display: block;height: 0;clear: both;visibility: hidden;} 
                    .duan .duan:after {content: "."; display: block;height: 0;clear: both;visibility: hidden;}
                    .duan h2{display: block; width: 800px; height: 60px; background:none; margin-left: 20px; border-bottom: 1px #8E8A8A dashed; line-height: 60px; font-size: 1.5em; font-weight: normal;font-family: "微软雅黑";text-align: left; float: left;color: #000; margin-bottom: 10px}/*h2表示标题*/
                    .duan h3{display: block; width: 800px; height: auto; background:none;  margin-left: 20px; line-height: 30px; font-size:1em; font-weight: normal;font-family: "微软雅黑";text-align: left; float: left;}/*h3表示内容*/
                    .duan p{display: block; width: 800px; height: auto; background:none;  margin-left: 20px; line-height: 30px; font-size:1em; font-weight: normal;font-family: "微软雅黑";text-align: left; float: left; text-indent:2em;}
                    .duan h3 em{display: block; width: 6px; height: 6px; float: left; line-height:0;font-size:0; background-color: #d0bb92; margin:12px 10px 12px 12px;} /*表示小点*/
                    </style><p><strong>网上支付常见问题<br><br>
1．支付常见问题<br></strong><br>
（1）支付过程中出现乱码，可能是支付接口的错误，您可以取消该订单，重新下单进行支付。<br><br>
（2）输入支付密码需要安装插件，不能支付的情况，您可以退出登录，使用IE浏览器重新登陆，安装相应的插件进行支付。<br><strong><br>
2．支付宝支持银行及限额<br></strong></p><table border="1" cellspacing="1" cellpadding="1" width="600"><tbody><tr><td style="text-align:center;"><strong>银行</strong></td><td style="text-align:center;"><strong>限额</strong></td><td style="text-align:center;"><strong>支持卡种</strong></td><td style="text-align:center;"><strong>银行客服电话</strong></td></tr><tr><td><img alt="" width="138" height="36" src="/images/upload/Image/zh.jpg"></td><td width="260"><p>
					信用卡单笔限额499.99元,单日额度由信用卡额度定<br>
；大众版单笔和单日限额5000，通过网上注册开通<br>
的保护期内每日累计不超过500元
				</p></td><td>
				信用卡/借记卡
			</td><td>
				95555
			</td></tr><tr><td><img alt="" width="136" height="37" src="/images/upload/Image/jh.jpg"></td><td width="250">
				签约用户准贷记卡与借记卡单笔限额5000元,日累计10000元；信用卡不分签约非签约客户笔和单日累计的最高支付金额均为500元
			</td><td>
				准贷记卡/借记<br>
卡/信用卡
			</td><td>
				95533
			</td></tr><tr><td><img alt="" width="137" height="36" src="/images/upload/Image/nh.jpg"></td><td width="250">
				移动证书用户或者是已办理动态口令卡的IE浏览器证<br>
书用户暂无限额
			</td><td>
				借记卡/准贷记<br>
卡
			</td><td>
				95599
			</td></tr><tr><td><img alt="" width="138" height="39" src="/images/upload/Image/szfz.jpg"></td><td>
				动态口令刮刮卡日累计限额5000元，动态口令刮刮<br>
卡使用次数为28次，使用完后需到银行柜台续卡；编<br>
码器和数字证书无限额方面的限制
			</td><td>
				借记卡
			</td><td>
				95501
			</td></tr><tr><td><img alt="" width="137" height="39" src="/images/upload/Image/pf.jpg"></td><td><p>
					借记卡/活期一本通/准贷记卡动态密码版客户单笔和<br>
每日限额50000元.数字证书版(签约客户)自行设置；<br>
信用卡业务网上支付设置；信用卡业务网上支付限额<br>
由浦发银行信用卡中心根据风险管理的需要集中管理<br>
，每个客户或商户可能有所不同，相关详情请致电浦<br>
发银行信用卡中心客服热线——800-820-8788或021<br>
-38784988
				</p></td><td><p class="p0"><span style="font-family:Arial;font-size:9pt;">借记卡<span>/</span><span>活期一<br>
本通</span><span>/</span><span>准贷记卡<br></span><span>/</span><span>信用卡</span></span></p></td><td>
				95528
			</td></tr><tr><td><img alt="" width="138" height="35" src="/images/upload/Image/gdfz.jpg"></td><td><p>
					通用版：信用卡、借记卡单笔不超过500元，每日累<br>
计不超过1500元；证书版信用卡：单笔不超过500元，每日累计不超过1500元。借记卡单笔不超过500元，若不设定支付限额，当日累计不限制
				</p></td><td><p class="p0"><span style="font-family:Arial;font-size:9pt;">信用卡<span>/</span><span>借记卡</span></span></p></td><td>
				95508
			</td></tr><tr><td><img alt="" width="139" height="38" src="/images/upload/Image/xy(1).jpg"></td><td>
				证书客户无限额；非证书客户有1000和5000两种限<br>
额，由客户在开通网上购物功能时自行选择；e卡限<br>
额日累计5000元
			</td><td><p class="p0"><span style="font-family:Arial;font-size:9pt;">借记卡</span></p></td><td>
				95561
			</td></tr><tr><td><img alt="" width="139" height="35" src="/images/upload/Image/ms.jpg"></td><td>
				借记卡:大众版单笔限额300元，累计限额300元；贵<br>
宾版单笔限额1000元，日限额5000元；VIP+（含单<br>
KEY版）支付限额与柜台签约对外支付限额相同，最<br>
高单笔限额50万，日限额200万。
			</td><td><p class="p0"><span style="font-family:Arial;font-size:9pt;">借记卡</span></p></td><td>
				95568
			</td></tr><tr><td><img alt="" width="139" height="38" src="/images/upload/Image/jt.jpg"></td><td>
				准贷记卡，借记卡手机注册版限额日累计5000，证书<br>
版50000；信用卡手机注册版限额为5000元与信用卡<br>
本身透支额度孰低
			</td><td>
				借记卡/准贷记<br>
卡/信用卡
			</td><td>
				95559
			</td></tr><tr><td><img alt="" width="135" height="39" src="/images/upload/Image/yz.jpg"></td><td><p>
					单张汇票收据的限额最高是5000元，邮政网汇e充值<br>
有次数上的限制，普通账户1次/日；已开通短信提醒<br>
的账户3次/日；已安装数字证书的账户不限次数
				</p></td><td>
				不需要邮政账<br>
户也可以
			</td><td>
				95580
			</td></tr><tr><td><img alt="" width="135" height="38" src="/images/upload/Image/zx.jpg"></td><td>
				借记卡文件证书用户单笔限额1000.00元，日限额50<br>
00.00元；移动证书信用卡用户每月累计不超过1000元。
			</td><td>
				信用卡/准贷记<br>
卡/借记卡
			</td><td>
				95558
			</td></tr><tr><td><img alt="" width="136" height="41" src="/images/upload/Image/spf.jpg"></td><td>
				客户自行设定
			</td><td>
				浦发企业账户
			</td><td>
				95528
			</td></tr><tr><td><img alt="" width="140" height="41" src="/images/upload/Image/gd.jpg"></td><td>
				动态密码支付、浏览器证书客户单笔每日限额5000元<br>
，USBKey证书支付单笔限额20万，每日限额50万；<br>
信用卡用户单笔每日限额是300元
			</td><td>
				信用卡/借记卡<br>
/活期一本通
			</td><td>
				95595
			</td></tr><tr><td><img alt="" width="139" height="38" src="/images/upload/Image/vist.jpg"></td><td>
				单笔支付金额不得低于1元，日限额客户自行设定
			</td><td>
				香港地区开户
			</td><td>
				800-96-7025
			</td></tr></tbody></table><p><br><strong>3．支付宝支付流程<br></strong><br>
（1）选择您想购买的翡翠产品，然后点击立即购买。<br><br>
（2）进入支付页面后填写订单相关信息， 您可以选择及时到账交易，直接付款。建议您使用及时到账交易，直接付款进行交易，以下以直接付款为例。<br><br>
（3）直接付款分为网上银行付款和支付宝账户付款两种付款方式，网上银行付款可以参考网银付款流程。以下以支付宝账户付款为例，输入账户名和支付密码后，点击确认购买。<br><br>
（4）进入支付宝账户后，输入支付密码，点击确认无误，付款按钮。<br><br>
（5） 支付完成，自动转入淘翠网网站提示付款成功页。
</p></div><!--dl class="wxbox clearfix"><dd><img src="http://www.aitaocui.cn/static/images/wx_pic.jpg" /></dd><dd class="wx_text"><span class="c00">【关注淘翠网】</span><br>
        微信号：aitaocuicn<br>
        扫一扫轻松享优惠
        </dd></dl--><!-- <div><img src="http://www.aitaocui.cn/images/wdmark.jpg" /></div> --><div class="next_but">
                下一篇:<a href="http://www.aitaocui.cn/pay/236.html" class="f2">信用卡分期付款</a><br>
                
                上一篇:<a href="http://www.aitaocui.cn/pay/78.html" class="f2">支付宝常见问题</a></div><!--div class="xgwz"><script type="text/javascript" id="wumiiRelatedItems"></script></div><script type="text/javascript">
var wumiiPermaLink = "http://www.aitaocui.cn/pay/79.html"; //请用代码生成文章永久的链接
var wumiiTitle = "网上支付常见问题"; //请用代码生成文章标题
var wumiiTags = ""; //请用代码生成文章标签，以英文逗号分隔，如："标签1,标签2"
var wumiiCategories = []; //请用代码生成文章分类，分类名放在 JSONArray 中，如: ["分类1", "分类2"]
var wumiiSitePrefix = "http://www.aitaocui.cn/";
var wumiiParams = "&num=5&mode=1&pf=JAVASCRIPT";
</script><script type="text/javascript" src="http://widget.wumii.cn/ext/relatedItemsWidget"></script--><div class="article_another"><p class="b0 f14"><strong>使命和宗旨：成为中国具有影响力的翡翠电子商务网站！</strong></p><div class="blank5"></div><div class="t30">淘翠网凭借其优良的品质、适中的价格等优势越来越被世人所青睐，一直以来坚持“诚信为本，顾客至上”的经营宗旨走在市场的前沿。以“物有所值，物超所值”的品质方针为广大客户提供品种更多，质量更高，服务更好的翡翠商品。淘翠网通过创新服务，深入挖掘翡翠内涵和客户需求，通过电子商务方式，不仅仅在网上卖翡翠，更是要传播源远流长，博大精深的中华翡翠文化！我们“淘翠网人”立志将淘翠网建成中国具有影响力的翡翠电子商务网站，成为全国知名的翡翠购物交流互动平台，打造成中国诚信翡翠品牌，让顾客安心，放心，省心，也让员工从工作中感到满足与骄傲，得到幸福！</div></div><div class="like clearfix" style="display:none;"><div class="guess_you_like"><div class="gl_top"><i class="n__red_pointer"></i>猜你喜欢</div><div class="txtSlides"><div class="bx-wrapper" style="max-width: 100%;"><div class="bx-viewport" style="width: 100%; overflow: hidden; height: 378px; position: relative;"><div class="nSlides bx_slide" style="width: 215%; position: relative;"></div></div><div class="bx-controls bx-has-pager bx-has-controls-direction"><div class="bx-pager"></div><div class="bx-controls-direction"><a class="bx-prev" href="">Prev</a><a class="bx-next" href="">Next</a></div></div></div></div></div><div class="relevance_news"><div class="rn_top"><i class="n__red_pointer"></i>相关新闻</div><div class="rn_middle"><ul></ul></div></div></div><!--div class="ad"><a href="#" target="_blank"><img src="/data/afficheimg/dsg.jpg" /></a></div--><!--<div class="clearfix"><div class="recommend"><div class="bx-wrapper" style="max-width: 100%;"><div class="bx-viewport" style="width: 100%; overflow: hidden; position: relative; height: 228px;"><div class="nSlides_rec" id="nSlides_rec" style="width: 715%; position: relative; -webkit-transition: 0.5s; transition: 0.5s; -webkit-transform: translate3d(-480px, 0px, 0px);"><a href="http://www.aitaocui.cn/shouzhuo-TMYS0540.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;" class="bx-clone"><img src="/data/afficheimg/meitijujiao_shouzuo.jpg" /></a><a href="http://www.aitaocui.cn/diaozhui-TMYS0814.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;"><img src="/data/afficheimg/meitijujiao_guazhui.jpg" /></a><a href="http://www.aitaocui.cn/diaozhui-TMYS0848.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;"><img src="/data/afficheimg/meitijujiao_xiaofo.jpg" /></a><a href="http://www.aitaocui.cn/diaozhui-TCMY00083.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;"><img src="/data/afficheimg/meitijujiao_xiangjin.jpg" /></a><a href="http://www.aitaocui.cn/gexingdingzhi-TMYS0671.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;"><img src="/data/afficheimg/meitijujiao_Personal.jpg" /></a><a href="http://www.aitaocui.cn/shouzhuo-TMYS0540.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;"><img src="/data/afficheimg/meitijujiao_shouzuo.jpg" /></a><a href="http://www.aitaocui.cn/shouzhuo-TCXDHS218.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;" class="bx-clone"><img src="/data/afficheimg/dsg1.jpg" /></a></div></div><div class="bx-controls"></div></div><div id="bx-pager"><a data-slide-index="0" href="javascript:;" class="">翡翠手镯</a><a data-slide-index="1" href="javascript:;" class="active">翡翠吊坠</a><a data-slide-index="2" href="javascript:;" class="">翡翠笑佛</a><a data-slide-index="3" href="javascript:;" class="">镶金翡翠</a><a data-slide-index="4" href="javascript:;" class="">个性定制</a></div></div><div class="gifts"><div class="bx-wrapper" style="max-width: 100%;"><div class="bx-viewport" style="width: 100%; overflow: hidden; position: relative; height: 228px;"><div class="nSlides_gifts" id="nSlides_gifts" style="width: 715%; position: relative; -webkit-transition: 0s; transition: 0s; -webkit-transform: translate3d(-1200px, 0px, 0px);"><a href="http://www.aitaocui.cn/diaozhui-TCMY00048.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;" class="bx-clone"><img src="/data/afficheimg/meitijujiao_baba.jpg" /></a><a href="http://www.aitaocui.cn/diaozhui-TCMY00089.html
" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;"><img src="/data/afficheimg/meitijujiao_mama.jpg" /></a><a href="http://www.aitaocui.cn/diaozhui-TMYS0652.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;"><img src="/data/afficheimg/meitijujiao_laogong.jpg" /></a><a href="http://www.aitaocui.cn/diaozhui-TCMY00079.html
" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;"><img src="/data/afficheimg/meitijujiao_laopo.jpg" /></a><a href="http://www.aitaocui.cn/diaozhui-TMYS0834.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;"><img src="/data/afficheimg/meitijujiao_leader.jpg" /></a><a href="http://www.aitaocui.cn/diaozhui-TCYZY144Y0134.html" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;"><img src="/data/afficheimg/meitijujiao_baba.jpg" /></a><a href="http://www.aitaocui.cn/diaozhui-TCMY00089.html
" target="_blank" rel="nofollow" style="float: left; list-style: none; position: relative; width: 240px;" class="bx-clone"><img src="/data/afficheimg/meitijujiao_mama.jpg" /></a></div></div><div class="bx-controls"></div></div><div id="bx-pager2"><a data-slide-index="0" href="javascript:;" class="">送爸爸</a><a data-slide-index="1" href="javascript:;" class="">送妈妈</a><a data-slide-index="2" href="javascript:;" class="">送老公</a><a data-slide-index="3" href="javascript:;" class="">送老婆</a><a data-slide-index="4" href="javascript:;" class="active">送领导</a></div></div
        <script type="text/javascript">
        jQuery(function(){
            
            jQuery('#nSlides_rec').bxSlider({
                pagerCustom: '#bx-pager',
                controls: false,
                captions: true,
                autoDelay: 100,
                auto: true
            }); 
            
            jQuery('#nSlides_gifts').bxSlider({
                pagerCustom: '#bx-pager2',
                controls: false,
                captions: true,
                autoDelay: 100,
                auto: true
            }); 			
            
        });
        </script></div>--><!--<div class="pinglun mt10 clearfix"><div id="uyan_frame"></div><script type="text/javascript" src="http://v2.uyan.cc/code/uyan.js?uid=1908321"></script></div>--></div><link rel="stylesheet" href="/article/templets/aitaocui/style/MobileReset.css"><style>
	.ma_silig{padding-bottom: 10px;padding-top: 10px;}
	.KinSlideshow_new{width: 290px;border: 1px solid #b3b3b5;margin-bottom: 10px;}
	.rem>a{position: relative;padding-left: 15px;width: 90%;}
	.rem>a em{background: url(//www.aitaocui.cn/static/images/dian.jpg);width: 3px;height: 3px;position: absolute;top: 19px;left: 3px;}
	<style>
	.catcontent dd img{width: 160px;height: 140px;}
		/* 推荐作品 */
.tuijin{width:90%; margin:0 auto!important; padding-top:20px; display:block; padding-bottom:10px;}
.tuijin li{width:25%; float:left;}
.tuijin li img{margin:0 auto; width:96%; display:block;}
.tuijin li p{width:100% ;text-align:center; line-height:30px; height:30px; overflow: hidden; font-size: 13px;}
.tuijin li b{width:100% ;display:block; text-align:center; color:red; font-size: 16px;}
/* 热门资讯 */
.rem{width:100%; border:1px solid #ccc;margin-top:20px; font-size: 12px;}
.rem>p{font-size: 15px;     line-height: 18px;}
.rem p{width:95%; margin:10px auto;}
.rem p i{display:block; background:#026402; width:6px; height:18px; float:left; margin-right:10px; border-radius:3px;}
.rem_img .rem_img_a{display:block; width:46%;*width:45%;margin:5px 2%; float:left; margin-bottom:0;}
.rem_img .rem_img_a img{width:100%;}
.rem_img .rem_img_a p{display:block; height:20px;color:#999; width:100%;white-space:nowrap;text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow: hidden;}
.rem>a{border-top:1px dotted #999; width:95%; display:block; margin:0px auto; height:40px;line-height:40px; color:#999;white-space:nowrap;text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow: hidden;}
.rem>a i{display:block; color:#026402; width:6px; height:18px; float:left; margin-right:10px;}
.rem>a:hover{color:red;}
/* 专题 */
.zhuanti{width:100%; border:1px solid #ccc; margin-top:20px;}
.zhuanti>p{font-size: 15px;     line-height: 18px;}
.zhuanti p{width:95%; margin:10px auto;}
.zhuanti p i{display:block; background:#026402; width:6px; height:18px; float:left; margin-right:10px; border-radius:3px;}
.zhuanti a{padding-right: 0px!important;}
/* 热门标签 */
.biaoqi{width:100%; border:1px solid #ccc; margin-top:20px;}
.biaoqi>p{font-size: 15px;line-height: 18px;}
.biaoqi p{width:100%; margin:10px auto;margin: 10px auto;margin-left: -7px;}
.biaoqi p i{display:block; background:#026402; width:6px; height:18px; float:left; margin-right:10px; border-radius:3px;}
</style><script src="/article/templets/aitaocui/js/ar_scrap.js"></script></div></div>

	<%-- 这是页脚的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

