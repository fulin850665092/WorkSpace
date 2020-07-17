package com.atguigui.canal;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.atguigu.dw.gmall.common.constant.GmallConstant;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.List;

public class CanalClient {
    public static void main(String[] args) {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("hadoop202", 11111), "example", "", "");

        while (true){
            //连接canal
            connector.connect();
            //指定订阅的数据库
            connector.subscribe("gmall_realtime.*"); //监控单独的库下的所有表
//            connector.subscribe(".*\\..*");//监控所有的库
            //执行抓取数据操作
            Message message = connector.get(1024);

            //判断当前抓取的数据是否为空，如果为空，则休息会
            if (message.getEntries().size()==0){
                System.out.println("没有数据，休息一会！");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                //有数据，解析message
                for (CanalEntry.Entry entry : message.getEntries()) {
                    //对当前entry的类型做判断，只需要数据变化的内容，有事物的开启和关闭，以及心跳信息则过滤掉
                    if (CanalEntry.EntryType.ROWDATA.equals(entry.getEntryType())){
                        //获取当前数据的表名
                        String tableName = entry.getHeader().getTableName();
                        try {
                            //将getStoreValue 返序列化
                            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                            //提取行集以及事件类型
                            List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
                            CanalEntry.EventType eventType = rowChange.getEventType();
                            //处理数据
                            handler(tableName,rowDatasList,eventType);
                        } catch (InvalidProtocolBufferException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    private static void handler(String tableName, List<CanalEntry.RowData> rowDatasList, CanalEntry.EventType eventType) {
        //判断是否为订单表,并且只取新增的数据
        if("order_info".equals(tableName)&&CanalEntry.EventType.INSERT.equals(eventType)){
            for (CanalEntry.RowData rowData : rowDatasList) {
                //创建json对象用于存放多个对象
                JSONObject jsonObject =new JSONObject();
                // 遍历修改之后的数据列
                for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
                    jsonObject.put(column.getName(),column.getValue());
                }
                //当前打印实际是存在于kafka
                System.out.println(jsonObject.toString());
                MyKafkaSender.send(GmallConstant.GMALL_ORDER_INFO,jsonObject.toJSONString());
            }
        }
    }

}

