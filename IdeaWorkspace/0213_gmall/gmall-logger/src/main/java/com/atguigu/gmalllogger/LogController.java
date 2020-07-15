package com.atguigu.gmalllogger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.dw.gmall.common.constant.GmallConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    // 初始化 Logger 对象
    private final Logger logger = LoggerFactory.getLogger(LogController.class);

    // 使用注入的方式来实例化 KafkaTemplate. Spring boot 会自动完成
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 向kafka发送数据
     * @param log
     * @return
     */
    @PostMapping("/log")
    public String getLog(String log){
        // 日志转成 JSONObject
        JSONObject logObj = JSON.parseObject(log);
        // 添加时间戳
        logObj = addTS(logObj);
        // 日志落盘
        saveLog(logObj);
        // 发送到 kafka
        sendToKafka(logObj);
        return "success";
    }

    /**
     * 添加时间戳
     * @param logObj
     * @return
     */
    public JSONObject addTS(JSONObject logObj){
        logObj.put("ts", System.currentTimeMillis());
        return logObj;
    }

    /**
     * 日志落盘
     * 使用 log4j
     * @param logObj
     */
    public void saveLog(JSONObject logObj) {
        logger.info(logObj.toJSONString());
    }

    /**
     * 发送日志到 kafka
     *
     * @param logObj
     */
    private void sendToKafka(JSONObject logObj) {
        String logType = logObj.getString("logType");
        String topicName = GmallConstant.TOPIC_STARTUP;

        if ("event".equals(logType)) {
            topicName = GmallConstant.TOPIC_EVENT;
        }
        kafkaTemplate.send(topicName, logObj.toJSONString());
    }



}
