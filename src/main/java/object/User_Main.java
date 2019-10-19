package object;

import java.util.HashSet;

/**
 * @author :qiang
 * @date :2019/10/17 下午11:13
 * @description : 对User类进行测试
 * @other :
 */
public class User_Main {

    public static void main(String[] args) {

        //创建User类的对象
        User user1 = new User("yuqiang", 24);
        User user2 = new User("yuqiang", 24);

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1.equals(user2));

        //创建set对象进行存储
        HashSet<User> hashSet = new HashSet<>();
        hashSet.add(user1);
        hashSet.add(user2);

        System.out.println("hashSet的元素个数：" + hashSet.size());
        //进行遍历
        for (User item : hashSet) {
            System.out.println(item.toString());
        }


    }
    /**
     * 结果：
     * 24
     * 24
     * true
     * hashSet的元素个数：1
     * name:yuqiang  age:24
     */

}
