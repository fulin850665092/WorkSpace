package com.hengyangshiyuan.hrsystem.controlle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userServer")
public class UserServiceController {

	@RequestMapping("/peisong.html")
	public String  peiSong() {
		return  "server/peisong/peisong";
 		
	}
	
	@RequestMapping("/peisongshuoming.html")
	public String  peiSongShuoMing() {
		return  "server/peisong/peisongshuoming";
 		
	}
	
	@RequestMapping("/qianshoujushou.html")
	public String  qianShouJuShou() {
		return  "server/peisong/qianshoujushou";
 		
	}
	
	@RequestMapping("/peisongwenti.html")
	public String  peiSongWenTi() {
		return  "server/peisong/peisongwenti";
 		
	}
	
	@RequestMapping("/zhifubao.html")
	public String  zhiFuBao() {
		return  "server/zhifu/zhifubao";
 		
	}
	@RequestMapping("/yinghang.html")
	public String  yingHang() {
		return  "server/zhifu/yinghang";
 		
	}
	@RequestMapping("/zhifubaowenti.html")
	public String  zhiFuBaoWenTi() {
		return  "server/zhifu/zhifubaowenti";
 		
	}
	@RequestMapping("/wangshangzhifuwenti.html")
	public String  wangshangzhifuwenti() {
		return  "server/zhifu/wangshangzhifuwenti";
 		
	}
	@RequestMapping("/youshi.html")
	public String  youshi() {
		return  "server/baozhang/youshi";
 		
	}
	@RequestMapping("/zhengshu.html")
	public String  zhengshu() {
		return  "server/baozhang/zhengshu";
 		
	}
	@RequestMapping("/kehu.html")
	public String  kehu() {
		return  "server/baozhang/kehu";
 		
	}
	@RequestMapping("/liucheng.html")
	public String  liucheng() {
		return  "server/zhinan/liucheng";
 		
	}
	@RequestMapping("/shuoming.html")
	public String  shuoming() {
		return  "server/zhinan/shuoming";
 		
	}
	@RequestMapping("/wenti.html")
	public String  wenti() {
		return  "server/zhinan/wenti";
 		
	}
	@RequestMapping("/zuce.html")
	public String  zuce() {
		return  "server/zhinan/zuce";
 		
	}
	
	
}
