package callback_function.example2;

/**
 * @author :qiang
 * @date :2019/11/10 下午4:18
 * @description :
 * @other :
 */
public class Main {


    //要运行的方法
    public void excute() {
        for (int i = 0; i < 1000000000; i++) {
        }
    }


    public void testTime(Callback callback) {
        System.out.println("运行开始的时间：" + System.currentTimeMillis());
        callback.method();
        System.out.println("运行结束的时间：" + System.currentTimeMillis());
    }


    public static void main(String[] args) {

        Main main = new Main();

        main.testTime(new Callback() {
            @Override
            public void method() {
                main.excute();
            }
        });


    }
}
