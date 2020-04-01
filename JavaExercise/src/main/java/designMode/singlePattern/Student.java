package designMode.singlePattern;

/**
 * @author :qiang
 * @date :2019/10/20 下午4:47
 * @description : 懒汉式的单例模式
 * @other :
 */
public class Student {
    /**
     * 懒汉式：在创建类的时候不创建对象，而在使用的时候再创建对象，但是要加上同步，否则创建多个实例
     */

    private static Student student;

    private Student() {
    }

    public static Student getInstance() {
        //此时需要加锁
        synchronized (Student.class) {
            //如果未初始化则先进行初始化
            if (student == null) {
                student = new Student();
            }
        }
        //如果已经初始化则直接返回这个对象
        return student;
    }
}

