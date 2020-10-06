package lambda;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author :qiang
 * @date :Created in 2019/7/7 下午10:02
 * @description :lambda表达式学习
 * @other :
 */
public class LambdaTest {

    /**
     * 代码更加紧凑
     * 可以为方法传递函数作为参数
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1fas");
        arrayList.add("2fa");
        arrayList.add("3fasfas");
        arrayList.add("4fasfas");

        //排序
        Collections.sort(arrayList,(a,b)->{
            if(a.length() > b.length()){
                return 1;
            } else if (a.length() < b.length()){
                return -1;
            }else {
                return 0;
            }
        });

        arrayList.forEach(p->{
            System.out.println(p);
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("此为匿名函数!");
            }
        }).start();


        new Thread(()->{
            System.out.println("此为lambda表达式匿名函数!");
        }).start();

    }
}
