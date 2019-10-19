package object;

/**
 * @author :qiang
 * @date :2019/10/17 下午11:13
 * @description : 创建user类
 * @other : 在User类中没有重写hashCode()和equals()方法
 */
public class User {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    @Override
    public String toString() {
        return "name:" + this.name + "  " + "age:" + this.age;
    }

    //此方法获取的是对象的散列码
    @Override
    public int hashCode() {

        return this.age;
    }


    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof User)) {
            return false;
        }

        //进行类型的转型
        User user = (User) obj;

        //如果姓名和年龄都相同则重复
        if (user.name.equals(this.name) && user.age == this.age) {
            return true;
        } else {
            return false;
        }
    }
}
