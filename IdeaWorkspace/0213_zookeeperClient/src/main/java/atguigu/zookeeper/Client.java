package atguigu.zookeeper;

import org.apache.zookeeper.*;

import java.util.List;

//监听Zookeeper组册的服务器的上下线，实时获取到当前的可用服务节点
public class Client {
    private  String connectStr="hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private  int timeOut=10000;
    private  ZooKeeper zkClient;
    private String  parentPath="/servers";
    public static void main(String[] args) throws  Exception{
        Client client= new Client();
        //初始化，获取zk的客户端对象
        client.init();
        //获取当前可用的服务器的节点
        List<String> servers = client.getServers();
        System.out.println("当前可用的节点为："+servers);
        //客户端的业务处理
        client.doBusiness();
    }

    private void doBusiness() throws  Exception{
        Thread.sleep(Long.MAX_VALUE);
    }

    private List<String> getServers() throws  Exception{
        List<String> nodes = zkClient.getChildren(parentPath, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //实时获取新的服务器节点信息(递归)
                try {
                    List<String> newNodes = getServers();
                    System.out.println("当前可用的新节点为："+newNodes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return  nodes;
    }

    private void init() throws  Exception{
         zkClient=new ZooKeeper(connectStr, timeOut, new Watcher() {
             public void process(WatchedEvent watchedEvent) {
             }
         });
    }
}
