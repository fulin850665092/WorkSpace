package atguigu.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestZookeeper {

    private  String connecStr="hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private  int  timeOut=10000;
    private  ZooKeeper zkClient;
    /**
     * 创建zoopeeper客户端
     * @throws Exception
     */
    @Before
    public  void getZookClient() throws  Exception{
        zkClient = new ZooKeeper(connecStr, timeOut, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("Zookeeper Client create Success!");
            }
        });

    }
    /**
     * 判断Znode是否存在
     *
     */
    @Test
    public  void  isExist() throws Exception{
        Stat exists = zkClient.exists("/atguigu1", false);
        if (exists==null){
            System.out.println("Znode不存在！");
        }else{
            System.out.println("Znode存在！");
        }
    }
    /**
     * 获取字节点并监听(只监听一次)
     */
    @Test
    public  void getLisetZnodes3() throws  Exception{
        List<String> children = zkClient.getChildren("/", new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("===========");
            }
        });
        System.out.println(children);
        Thread.sleep(Long.MAX_VALUE);
    }
    /**
     * 获取字节点并监听(只监听一次)
     */
    @Test
    public  void getLisetZnodes2() throws  Exception{
        List<String> children = zkClient.getChildren("/", true);
        System.out.println(children);
        Thread.sleep(Long.MAX_VALUE);
    }
    /**
     * 获取字节点
     */
    @Test
    public  void getLisetZnodes() throws  Exception{
        List<String> children = zkClient.getChildren("/", false);
        System.out.println(children);
        Thread.sleep(Long.MAX_VALUE);
    }
    /**
     * 创建zNode
     * @throws Exception
     */
    @Test
    public  void createZnode() throws Exception {
        String path = zkClient.
                create("/atguigu", "shangguigu".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }
    /**
     * 删除zNode
     */
    @Test
    public  void deleteZnode()throws  Exception{
        zkClient.delete("/atguigu",-1);
    }
    /**
     * 获取Znode的数据
     */
    @Test
    public  void getZnodeDate() throws  Exception{
        byte[] zoneData = zkClient.getData("/atguigu", false, null);
        System.out.println(new String(zoneData));
    }
    /**
     * 修改Znode的数据
     */
    @Test
    public  void setZnodeDate() throws  Exception{
        Stat stat = zkClient.setData("/atguigu", "woxiugaile".getBytes(), -1);
//        System.out.println(stat);
    }
    /**
     * 关闭zookeeper客户端
     * */
    @After
    public  void closeZkClient(){
        try {
            System.out.println("Zookeeper Client close!");
            zkClient.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
