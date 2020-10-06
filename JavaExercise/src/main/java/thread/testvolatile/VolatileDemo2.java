package thread.testvolatile;

/**
 * @ClassName : VolatileDemo2
 * @Author : yq
 * @Date: 2020-09-16
 * @Description : volatile 指令重排
 */
public class VolatileDemo2 {

    static int a = 10;
    static boolean flag = false;

    public static void changeStatus(){
        //操作1
        a = 100;
        //操作2
        flag = true;
    }

    public static void operation(){
        //操作3
        if(flag){
            System.out.println(a);
        }
    }

    public static void main(String[] args) {

        /**
         * 单线程环境执行
         * 操作1与操作2不存在依赖关系，无论如何重排序
         * 均不会影响最终执行结果
         */
        /*changeStatus();
        operation();*/


        /**
         * 多线程环境下
         * 如果操作2先于操作1执行，则操作3的结果就是10
         */
        changeStatus();

        new Thread(new Runnable() {
            @Override
            public void run() {
                operation();
            }
        }).start();


    }
}
