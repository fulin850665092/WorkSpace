package com.atguigu.gmalllogger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @PostMapping("/log")
    public String getLog(String log){

        System.out.println(log);
        return log;
    }
}
