package com.atguigu.hbase.xm.constan;

public class Names {
    //namespace
    public static final String NAMESPACE_WEIBO = "weibo";

    //列族
    public static final String TABLE_WEIBO = "weibo:weibo";
    public static final String TABLE_RELATION = "weibo:relation";
    public static final String TABLE_INBOX = "weibo:inbox";
    //列名
    public static final String WEIBO_FAMILY_DATA = "data";
    public static final String RELATION_FAMILY_DATA = "data";
    public static final String INBOX_FAMILY_DATA = "data";

    public static final String WEIBO_COLUMN_CONTENT = "content";
    public static final String RELATION_COLUMN_TIME = "time";

    public static final Integer WEIBO_VERSIONS = 1;
    public static final Integer RELATION_VERSIONS = 1;
    public static final Integer INBOX_VERSIONS = 3;
}
