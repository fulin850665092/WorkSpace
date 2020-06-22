import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class testclass {


    @Test
    public  void  test(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入一个字符:");

        String next = scanner.next();

        System.out.println(next);

        scanner.close();
    }

    public static void main(String[] args) throws UnknownHostException {

        //通过域名或者ip都能创建实列
        InetAddress byName = InetAddress.getByName("114.114.114.114");
        System.out.println(byName);
        System.out.println(byName.getHostAddress());
        System.out.println(byName.getHostName());

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);


    }

    @Test
    public  void  clent() throws Exception {
        //指明对方的ip
        InetAddress inetAddress = InetAddress.getByName("192.168.3.2");

        //创建socket实例
        Socket socket = new Socket(inetAddress,9999);

        //获取输出流
        OutputStream os = socket.getOutputStream();

        os.write("你好，我是付林！".getBytes());

        os.close();

        socket.close();

    }

    @Test
    public   void server() throws Exception {

        //创建服务端scoket
        ServerSocket serverSocket = new ServerSocket(9999);
        //接受客户端的scoket
        Socket socket = serverSocket.accept();
        //获取输入流
        InputStream is = socket.getInputStream();
        //获取数据
        int len;
        byte[]bytes= new byte[1024];
        while ((len=is.read(bytes))!=-1){
            String srt= new String(bytes,0,len);
            System.out.println(srt);
        }
        //关闭资源
        is.close();
        socket.close();
        //一般服务器端都是开启端

    }




}

