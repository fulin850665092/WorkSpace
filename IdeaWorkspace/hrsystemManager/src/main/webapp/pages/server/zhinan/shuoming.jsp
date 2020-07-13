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
                </script><div id="KinSlideshowss" style="visibility: visible; width: 1200px; height: 128px; overflow: hidden; position: relative;"><div id="KSS_moveBox" style="width: 1200px; height: 128px; overflow: hidden; position: relative;"><div id="KSS_XposBox" style="float: left; width: 2000%;"><div id="KSS_content" style="float: left;"><a href="http://www.aitaocui.cn/mingjiangdashi/" target="_blank"><img src="//www.aitaocui.cn/images/201802/1518369294944580763.jpg" width="1200" height="128" border="0" style="border: 0px;"></a></div><div id="KSS_contentClone" style="float: left;"><a href="http://www.aitaocui.cn/mingjiangdashi/" target="_blank"><img src="//www.aitaocui.cn/images/201802/1518369294944580763.jpg" width="1200" height="128" border="0" style="border: 0px;"></a></div></div></div> <div class="KSS_titleBar" style="height: 30px; width: 100%; position: absolute; bottom: 0px; left: 0px; opacity: 0.5;"></div><div class="KSS_titleBox" style="height: 30px; width: 100%; position: absolute; bottom: 0px; left: 0px;"><h2 class="title" style="margin: 3px 0px 0px 6px; padding: 0px; font-size: 12px; color: rgb(255, 255, 255); font-family: Verdana; font-weight: normal;"></h2></div></div></div><div class="article_topad" style="display: none;"><div class="indent0"><div class="bx-wrapper" style="max-width: 100%;"><div class="bx-viewport" style="width: 100%; overflow: hidden; height: 378px; position: relative;"><ul class="nSlides bx_slide" style="display: none; width: 215%; position: relative;"></ul></div><div class="bx-controls bx-has-pager bx-has-controls-direction"><div class="bx-pager"></div><div class="bx-controls-direction"><a class="bx-prev" href="">Prev</a><a class="bx-next" href="">Next</a></div></div></div></div></div></div><div class="block" style="width: 1200px;"><div class="urlbox ur_box"><strong>当前位置:</strong><a href="https://www.aitaocui.cn/">淘翠网</a><code>&gt;</code><a href="http://www.aitaocui.cn/bangzhuzhongxin/article/">使用帮助</a><code>&gt;</code><a href="http://www.aitaocui.cn/taocuihelp/article/">淘翠网帮助</a><code>&gt;</code><a href="http://www.aitaocui.cn/guide/article/">新手指南</a><code>&gt;</code> 商品说明      </div></div><div class="block mt10 clearfix" style="width: 1200px;"><div class="article_slide f_l mt10" style="width: 200px;"><div class="help_left"><dl class="help_menu_item_0"><dt><span>新手指南</span></dt><dd><a href="http://www.aitaocui.cn/guide/48.html" title="用户注册"> 用户注册</a></dd><dd><a href="http://www.aitaocui.cn/guide/50.html" title="购物流程"> 购物流程</a></dd><dd><a href="http://www.aitaocui.cn/guide/51.html" title="常见问题"> 常见问题</a></dd><dd class="nav"><a href="http://www.aitaocui.cn/guide/81.html" title="商品说明"> 商品说明</a></dd></dl><dl class="help_menu_item_0"><dt><span>购物保障</span></dt><dd><a href="http://www.aitaocui.cn/shipping/52.html" title="五大优势"> 五大优势</a></dd><dd><a href="http://www.aitaocui.cn/shipping/55.html" title="手镯测量"> 手镯测量</a></dd><dd><a href="http://www.aitaocui.cn/shipping/66.html" title="客户见证"> 客户见证</a></dd><dd><a href="http://www.aitaocui.cn/shipping/4086.html" title="关于我们"> 关于我们</a></dd></dl><dl class="help_menu_item_0"><dt><span>交易方式</span></dt><dd><a href="http://www.aitaocui.cn/pay/57.html" title="支付宝"> 支付宝</a></dd><dd><a href="http://www.aitaocui.cn/pay/69.html" title="银行汇款/转账"> 银行汇款/转账</a></dd><dd><a href="http://www.aitaocui.cn/pay/78.html" title="支付宝常见问题"> 支付宝常见问题</a></dd><dd><a href="http://www.aitaocui.cn/pay/79.html" title="网上支付常见问题"> 网上支付常见问题</a></dd><dd><a href="http://www.aitaocui.cn/pay/236.html" title="信用卡分期付款"> 信用卡分期付款</a></dd><dd><a href="http://www.aitaocui.cn/pay/3731.html" title="淘翠网淘宝官方店"> 淘翠网淘宝官方店</a></dd><dd><a href="http://www.aitaocui.cn/pay/3739.html" title="订购及鉴赏"> 订购及鉴赏</a></dd></dl><dl class="help_menu_item_0"><dt><span>配送服务</span></dt><dd><a href="http://www.aitaocui.cn/peisongfuwu/60.html" title="送货范围"> 送货范围</a></dd><dd><a href="http://www.aitaocui.cn/peisongfuwu/61.html" title="配送说明"> 配送说明</a></dd><dd><a href="http://www.aitaocui.cn/peisongfuwu/62.html" title="签收与拒收"> 签收与拒收</a></dd><dd><a href="http://www.aitaocui.cn/peisongfuwu/82.html" title="常见配送问题"> 常见配送问题</a></dd></dl><dl class="help_menu_item_0"><dt><span>售后服务</span></dt><dd><a href="http://www.aitaocui.cn/shouhoufuwu/54.html" title="证书查询"> 证书查询</a></dd><dd><a href="http://www.aitaocui.cn/shouhoufuwu/56.html" title="订单查询"> 订单查询</a></dd><dd><a href="http://www.aitaocui.cn/shouhoufuwu/63.html" title="发票说明"> 发票说明</a></dd></dl><dl class="help_menu_item_0"><dt><span>免责条款</span></dt><dd><a href="http://www.aitaocui.cn/memberrule/65.html" title="法律声明"> 法律声明</a></dd><dd><a href="http://www.aitaocui.cn/memberrule/68.html" title="免责条款"> 免责条款</a></dd></dl><dl class="help_menu_item_0"><dt><span>帮助中心</span></dt><dd><a href="http://www.aitaocui.cn/bangzhuzhongxin/107.html" title="注册协议"> 注册协议</a></dd><dd><a href="http://www.aitaocui.cn/bangzhuzhongxin/112.html" title="保护客户隐私的声明"> 保护客户隐私的声明</a></dd></dl></div></div><script src="/static/js/jquery.KinSlideshow-1.2.1.min.js" type="text/javascript"></script><div class="article_main f_r" style="padding-left:70px;width: 980px;"><h2 class="article_name">商品说明</h2><div class="article_name2 clearfix"><span>来源：淘翠网</span><span>时间：2012-12-05</span><div class="bdsharebuttonbox fenxiang bdshare-button-style0-16" data-bd-bind="1552959706051"><h4 style="display: inline-block; ">分享到：</h4><a class="jiathis_button_tsina" data-cmd="tsina" title="分享到新浪微博"><img src="/themes/mmb_taocui/images/tsina.png" alt="" style="width: 20px;">
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
                    </style><p> 一、淘翠网所有宝贝为实物多角度拍摄，实物比照片和视频更加精彩灵动。视频由于透光性不作为颜色参考，仅为观察翡翠内部结构之用。<br><br>
 二、手拿实物与显示器图片对比肯定不一致，拍摄地点在窗口，而非显示器旁。所以请在相同拍摄环境，室内窗口旁观赏实物！多台显示器对比结果为：没有一台显示器能准确还原肉眼所见实物效果。<br><br>
 三、为清晰观察宝贝细节，图片和视频比实物多倍放大，比肉眼近距察看更为清晰。标准游标卡尺测量最长、最宽、最厚，误差不超过1mm(毫米)左右。将宝贝描述中标注尺寸画在纸上，体会实际大小概念，勿以想象论！<br><br>
 四、没有一台数码照相机能做到100%地还原色彩，都存在细微的色彩偏差。淘翠网是采用佳能相机拍摄，从不同光线、不同角度、不同背景展现宝贝风采，拍摄的照片都会产生不同的色彩效果，最终以实物为参考！没有单纯的白色，我们看到的白色是由红、橙、黄、绿、青、蓝、紫七种颜色组成。所以无色（白色）的翡翠在不同的人眼里，会出现不同的颜色感受。<br><br>
 五、谁也无法统一所有显示器的颜色显示效果。“颜色”在不同人、不同显示器参数设置中都有不同的显示效果。白天察看显示器图片较暗，晚上察看显示器图片较亮。综合以上原因，图片和视频可能存在主观意念对比性色差。鉴于目前以液晶显示器为主流，建议浏览时多频道进行调节，以柔和顺眼为好。最终以实物为参考！<br><br>
六、在珠宝行业中，玉石中的棉、纹、筋、翠性、杂色，宝石中的色带、轻微裂、矿缺（小坑）、包裹体为天然表现，也是鉴定真伪的依据。玉器表面的抛光、边缘的打磨偶尔可能存在少许粗糙、不平之处，是全手工雕刻的特征，不属瑕疵之列！几乎每一块翡翠上均会存在不完美之处，希望能坦然接受大自然造物留下的痕迹。<br><br>
七、观赏翡翠在不同光源下，其颜色和水头会表现出不同的效果。室内光线偏暗、灯光下会产生光污染、室外阳光直射过于强烈，都不适合欣赏。观赏翡翠最佳光线环境：晴朗光照上午10时至下午3时的自然光线。观赏时用绒布擦干净翡翠，靠近室内窗户或阳台欣赏，这是珠宝界公认的最佳环境和时间段。<br><br>
 八、淘翠网承诺所有宝贝均为自然光拍摄，拍后我们只对其大小进行压缩以利于上传到网站，对图片的对比度、亮度、色彩等参数不做任何调整,但由于拍摄及显示器等原因，视频与图片难免有因透光度引起的色差，本站所有翡翠产品以图片为参照标准，视频展示为辅！</p></div><!--dl class="wxbox clearfix"><dd><img src="http://www.aitaocui.cn/static/images/wx_pic.jpg" /></dd><dd class="wx_text"><span class="c00">【关注淘翠网】</span><br>
        微信号：aitaocuicn<br>
        扫一扫轻松享优惠
        </dd></dl--><!-- <div><img src="http://www.aitaocui.cn/images/wdmark.jpg" /></div> --><div class="next_but">
                
                上一篇:<a href="http://www.aitaocui.cn/guide/51.html" class="f2">常见问题</a></div><!--div class="xgwz"><script type="text/javascript" id="wumiiRelatedItems"></script></div><script type="text/javascript">
var wumiiPermaLink = "http://www.aitaocui.cn/guide/81.html"; //请用代码生成文章永久的链接
var wumiiTitle = "商品说明"; //请用代码生成文章标题
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

