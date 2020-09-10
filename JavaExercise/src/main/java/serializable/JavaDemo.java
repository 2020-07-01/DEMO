package serializable;

import java.io.*;

/**
 * @ClassName : JavaDemo
 * @Author : yq
 * @Date: 2020-09-10
 * @Description : 序列化 反序列化
 */
public class JavaDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        /*Person person = new Person();
        person.setName("q");
        person.setAge(1);
        person.setZone("w");
        File file = new File("D:\\data\\person.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.close();*/

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\data\\person.txt"));
        Person p = (Person) objectInputStream.readObject();
        System.out.println(p.toString());
    }
}
