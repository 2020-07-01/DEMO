package coding.testExtend;

import org.omg.CORBA.IntHolder;

/**
 * @author :qiang
 * @date :Created in 2019/7/7 下午3:50
 * @description :测试类
 * @other :
 */
public class Test{

    //改变x的值
    public static void set(IntHolder x){
        x.value = x.value * 3;
    }

    public static void set1(int x){
        x = x * 3;

    }

    //参数数量可变的方法
    public static int max(int a,int...b){
        int s = 0;
        for(int i:b)
        {
            s = s + 1;
        }
        return  s + a;

    }


    public static void main(String...args) {
        String s1 = "ok";
        String s2 = "ok";

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        IntHolder x = new IntHolder();
        x.value = 3;
        set(x);
        System.out.println(x.value);
        int y = 5;
        set1(5);

        System.out.println(y);


        //调用可变参数方法
        System.out.println(max(1,1,1,1,1,1,1,1));
    }
}
