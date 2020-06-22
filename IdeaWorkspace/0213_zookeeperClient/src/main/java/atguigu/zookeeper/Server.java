package atguigu.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

//服务器上线，注册服务zookeeper
public class Server {
    private  String connectStr="hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private  int timeOut=10000;
    private  ZooKeeper zkClient;
    private String  parentPath="/servers";
    public static void main(String[] args) throws Exception{
        Server server= new Server();
        //初始化，获取zookeeper客户端对象
        server.init();
        //判断指定的路径是否存在，不存在，创建
        server.checkPathIdExists();
        //将当前服务器相关的数据,注册到对应的路径下
        server.regist(args[0]);
        //当前服务器的业务逻辑
        server.doBusiness();
    }

    private void doBusiness() throws  Exception{
        Thread.sleep(Long.MAX_VALUE);
    }

    private void regist(String data) throws  Exception{
        String path =
                zkClient.create(parentPath + "/server", data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(path+"is on line!");
    }

    private void checkPathIdExists() throws  Exception{
        Stat stat = zkClient.exists(parentPath, false);
        if (stat==null){
             zkClient.create(parentPath,"".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    private   void init() throws  Exception{
        zkClient = new ZooKeeper(connectStr, timeOut, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
