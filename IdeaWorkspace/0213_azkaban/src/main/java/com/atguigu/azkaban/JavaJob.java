package com.atguigu.azkaban;

import java.io.FileOutputStream;
import java.io.IOException;

public class JavaJob {
        public void run() throws IOException {
            // 根据需求编写具体代码
            FileOutputStream fos = new FileOutputStream("/opt/module/datas/azkaban/output.txt");
            fos.write("this is a java progress".getBytes());
            fos.close();
        }

        public static void main(String[] args) throws IOException {
            JavaJob javaJob = new JavaJob();
            javaJob.run();
        }
    }


