package com.atguigu.String;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fulin
 * @date 2020/02/02
 */
public class Testt {
    //极限情况下，Hashmap
    public static void main(String[] args) {
        Map<User, User> userHashMap = new HashMap<User, User>(16);
        for (int i = 0; i <20 ; i++) {
            userHashMap.put(new User(),new User());
            System.out.println(i);
        }
    }

}
class  User{
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}