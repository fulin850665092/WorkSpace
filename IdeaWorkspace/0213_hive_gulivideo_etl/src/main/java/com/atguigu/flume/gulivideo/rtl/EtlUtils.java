package com.atguigu.flume.gulivideo.rtl;

public class EtlUtils {

    public static String getStr(String line) {
//gZ_eaBgaDzY	Kontrabandman	736	People & Blogs	54	76086	4.44	261	174
// x5THGTVjiQo	PM7EhnLkLN8	2zhOHF9EtDA	5C7v5vwSvIc	GujYYYHYzHY	CnXULd9fB3M	FROS4b5QHJ0	DxOjlATR-Lw	2wQa6Qa-6Ew	MDh4rJgBZ7w	uMoQO4_Faxw	OMWPlKbhCR4	PJE7Uzy8qTM	8lGapsHINqM	ZedCXQhBF9I	32eDuxX_5rc	hzTZSghDfh0	TjmOK4TTvwg	XEJDGLF-e18	JMvMzQ4Vu-8
        String[] split = line.split("\t");
        StringBuffer sb= new StringBuffer();
        //长度小于9的不要
        if (split.length < 9) {
            return null;
        }
        //替换第四列的空格People & Blogs
        split[3] = split[3].replace(" ", "");
        //循环处理
        for (int i = 0; i <split.length ; i++) {
            //处理每条数据的前九个
            if (i<9){
                //先判断没有相关视频id的情况
                  //判断是不是最后一个
                if (i==split.length-1){
                    sb.append(split[i]);
                }else {
                    //每一个都拼接一个'\t'
                    sb.append(split[i]+'\t');
                }
                //else处理大于9的数据
            }else {
                //判断是不是最后一个
                if (i==split.length-1){
                    sb.append(split[i]);
                }else {
                    sb.append(split[i]+"&");
                }
            }
        }
        return  sb.toString();
    }
}