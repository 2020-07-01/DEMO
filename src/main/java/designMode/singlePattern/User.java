package designMode.singlePattern;

/**
 * @author :qiang
 * @date :2019/10/20 下午4:29
 * @description : User 类
 * @other :
 */
public class User {

    /**
     * 思想：只能创建并获取该类的一个实例对象
     * 单例模式:首先声明单例模式不能传入参数，否则在传入参数时已经构造了不同的对象
     * 饿汉式：在创建类的时候已经创建好对象，供系统使用，以后不再改变，因此是线程安全的
     */
    private static User user = new User();

    //将构造方法进行私有化,使其外部不能通过对象来创建对象
    private User() {
    }

    //获取对象的唯一实例
    public static User getInstance() {
        return user;
    }


}
