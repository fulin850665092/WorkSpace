import com.at.PersonFa;
import com.at.PersonInter;
import lombok.Data;

@Data
public class Person extends PersonFa implements PersonInter  {

    private String name;
    private  int age;
    public  static    String address;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Person.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public  String  name(String name){

        return "姓名："+name;
    }
    private   String  priName(String name){

        return "姓名："+name;
    }
    private static    String  priNames(String name){

        return "姓名："+name;
    }

}
