import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class day27 {

    //客户度
    @Test
    public  void test() throws Exception {

        //创建Socket，指定ip地址和端口号
        Socket socket= new Socket("127.0.0.1",9999);

        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        System.out.println(br.readLine());

        OutputStream os = socket.getOutputStream();

        PrintStream ps = new PrintStream(os);

        ps.println("您好，我想报名就业班！");


        System.out.println(br.readLine());

        ps.println("好吧，再见！");

        socket.close();
    }

    //服务端
    @Test
    public void  serverTest() throws Exception {

        ServerSocket server = new ServerSocket(9999);
        Socket socket = server.accept();
        OutputStream os = socket.getOutputStream();

        PrintStream ps = new PrintStream(os);
        ps.println("欢迎咨询尚硅谷@！");

        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        System.out.println(socket.getInetAddress().getHostAddress()+"留言"+ LocalDateTime.now());

        System.out.println(br.readLine()+"\n");
        ps.println("报满了,请报下一期吧");

        System.out.println(socket.getInetAddress().getHostAddress() + "留言：" + LocalDateTime.now());
        System.out.println(br.readLine());

        server.close();
        socket.close();


    }

    @Test
    public void serverTest2() throws IOException {
        ServerSocket server = new ServerSocket(9999); // 创建服务器
        while (true) {
            final Socket socket = server.accept(); // 接受客户端的请求
            new Thread() {
                public void run() {
                    try {
                        PrintStream ps = new PrintStream(socket.getOutputStream());
                        ps.println("欢迎咨询尚硅谷");
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        System.out.println(socket.getInetAddress().getHostAddress() + "留言：" + LocalDateTime.now());
                        System.out.println(br.readLine() + "\n");
                        ps.println("报满了,请报下一期吧");
                        System.out.println(socket.getInetAddress().getHostAddress() + "留言：" + LocalDateTime.now());
                        System.out.println(br.readLine() + "\n");
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

}
