import java.io.File;
import java.io.IOException;
import java.util.*;

public class test {

    public static void main(String[] args) throws IOException {

        Map<String ,String> map= new HashMap<>();
            map.put("aa","aa");
            map.put("bb","bb");
            map.put("cc","cc");
            map.put("dd","dd");
            // 获取键值对的迭代器
            Iterator it = map.entrySet().iterator();
            List list= new ArrayList();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();

                list.add (entry.getValue());
            }
        List<String> list1 = new ArrayList<String>(map.values());
        List<String> list2 = new ArrayList<String>(map.keySet());
        getValue(map);
        //创建File对象
        File file=new File("/Users/fulin/Desktop/");
        if(file.isDirectory()){
            //判断File对象对应的目录是否存在
            String[] fileNames=file.list();//获得目录下所有的文件名
            System.out.println(file.list().length);
            for(String name:fileNames){
                //循环遍历，依次输出
                System.out.println(name);
            }
        }
        File file1=new File("/Users/fulin/Desktop/");
        //判断File对象对应的目录是否存在
        if(file1.isDirectory()){
            //获得目录下所有的文件
            File[] files=file1.listFiles();
            System.out.println(file1.list().length);
            for(File file2:files){
                System.out.println("file："+file2.getName()+"；大小："+file2.length()+"b");
            }
        }
        File file0=new File("/Users/fulin/Desktop/Idea中快捷键是.docx");
        String path=file0.getPath();
        String pathTest=path.substring(0,21);
        File file3=null;
        file3.createTempFile("abc",".txt", new File(pathTest));
        System.out.println(path);
        System.out.println( pathTest);


//        System.out.println("list"+ list);
//        System.out.println("list1"+list1);
//        System.out.println("list2"+list2);

    }
    public static List<String> getValue(Map<String,String> map){

        List<String> list= new ArrayList();
        if (null!=map){
            // 获取键值对的迭代器
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();

                list.add ((String) entry.getValue());
            }
        }
        System.out.println(list);
        return  list;
    }


    public static   List<String> getValue2(Map<String,String> map){

        List<String> list=null;
        if (null!=map){
            list= new ArrayList<String>(map.values());
        }
        System.out.println(list);
        return  list;
    }

}
