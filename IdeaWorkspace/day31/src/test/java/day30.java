import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.tomcat.jni.User;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.Connection;
import java.util.Properties;


public class day30 {

        @Test
    public void  test() throws Exception {
            Class clas= Person.class;
            Person person = (Person) clas.newInstance();

            person.setAge(4);
            System.out.println(person);

            //最常见
            Class<?> aClass = Class.forName("Person");
            Person o = (Person) aClass.newInstance();
            o.setAge(3);
            System.out.println(o);

            Class class3= new Person().getClass();
            Person person1 = (Person) class3.newInstance();

            person1.setName("dd");
            System.out.println(person1);

            Class<?> aClass1 = day30.class.getClassLoader().loadClass("Person");


            System.out.println(clas==class3);
            System.out.println(clas==aClass);
        }
        @Test
        public  void Pro() throws Exception {

                Properties properties= new Properties( );
                properties.load(new FileInputStream("jdbc.properties"));
                System.out.println(properties.getProperty("username"));
        }

        @Test
        public  void Pro2() throws IOException {

                Properties properties  =new Properties();
                InputStream asStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

                properties.load(asStream);

                System.out.println(properties.getProperty("username"));


        }
    //获取运行时类的所有构造器
    @Test
    public  void ffssdda(){
        Class ss= Person.class;
        Constructor[] constructors = ss.getDeclaredConstructors();
        for (Constructor constructor: constructors) {
            System.out.println(constructors);

        }
    }
    //获取运行时类的父类
    @Test
    public  void ffssss(){
        Class ss= Person.class;
        Class superclass = ss.getSuperclass();
        System.out.println(superclass);
    }
    //获取运行时类所在的包
    @Test
    public  void ffsss(){
        Class ss= Person.class;
        Package aPackage = ss.getPackage();
        System.out.println(aPackage);
        }
    //获取运行时类的注解
    @Test
    public  void ffss(){
        Class ss= Person.class;
        Annotation[] annotations = ss.getAnnotations();
        for (Annotation annotation: annotations) {
            System.out.println(annotations);

        }
    }
    //获取运行时类所实现的接口
    @Test
    public  void ffs(){
        Class ss= Person.class;
        Class[] interfaces = ss.getInterfaces();
        for (Class Interface: interfaces) {
            System.out.println(interfaces);

        }


    }
        //获取运行时类的，带泛型的，父类
        @Test
        public  void ff(){
            Class ss= Person.class;
            Type genericSuperclass = ss.getGenericSuperclass();
            System.out.println(genericSuperclass);

        }
        //获取运行时类，父类的范型
        @Test
        public  void test2(){
            Class clasee= Person.class;
            Type t = clasee.getGenericSuperclass();
            ParameterizedType parameterizedType= (ParameterizedType) t;
            Type[] arguments = parameterizedType.getActualTypeArguments();
            System.out.println(arguments[0].getTypeName());
        }

        @Test
    public  void test3() throws Exception {

            Class classss=Person.class;
            Person p = (Person) classss.newInstance();
            //私有属性private
            Field name = classss.getDeclaredField("name");
            name.setAccessible(true);
            name.set(p,"zhangsan ");
            System.out.println(p);
            //静态的属性
            Field address = classss.getDeclaredField("address");
            address.setAccessible(true);
            address.set(p,"中国");
            //静态的可以使用null接受静态属性
            System.out.println(address.get(null));
            System.out.println(address.get("address"));

        }

    @Test
    public  void test4() throws Exception {

        Class classss=Person.class;
        Person p = (Person) classss.newInstance();
        //私有方法
        Method method = classss.getDeclaredMethod("priName",String.class);
        method.setAccessible(true);
        String invoke = (String) method.invoke(p, "张三");
        System.out.println(invoke);
        //静态的方法
        Method method1 = classss.getDeclaredMethod("priNames",String.class);
        method1.setAccessible(true);
        Object invoke1 = method1.invoke(null, "李四");
        //静态的可以使用null接受静态属性
        System.out.println(invoke1);


    }

    @Test
    public  void test17() throws Exception {

        Class<?> aClass = Class.forName("com.atguigu.java");
        User user = (User) aClass.newInstance();
        Method method = aClass.getDeclaredMethod("show");
        method.setAccessible(true);
        method.invoke(user);

    }

    @Test
    public void test5() throws Exception {
        //1.加载配置文件
        Properties pros = new Properties();
        InputStream is = day30.class.getClassLoader().getResourceAsStream("druid.properties");
        pros.load(is);

        //2. 使用配置文件，返回DataSource的实例
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pros);
        //3. 获取连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
