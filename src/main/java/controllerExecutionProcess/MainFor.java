package controllerExecutionProcess;

/**
 * @author :qiang
 * @date :2019/10/5 下午1:56
 * @description :测试for循环
 * @other :
 */
public class MainFor {

    /**
     * for循环的三个表示式都不为空
     */
    public static void for1() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
    }

    /**
     * 步进表示式为空时,会出现死循环
     */
    public static void for2() {
        for (int i = 0; i < 10; ) {
            System.out.println(i);
        }
    }

    /**
     * 当布尔表达时为空时则会出现不可预知的错误
     */
    public static void for3() {
        for (int i = 0; ; i++) {
            //可以在此处进行布尔值的判断
            System.out.println(i);
        }
    }


    /**
     * 无限循环
     */
    public static void for4() {
        for (; ; ) {
            System.out.println("此循环体为无限循环");
        }
    }

    public static void main(String[] args) {
        for3();
    }
}
