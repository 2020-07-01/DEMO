package designMode.singlePattern.demo1;

/**
 * @author yq
 * @date 2020/8/22 14:34
 */
public class JavaDemo {

    public static void main(String[] args) {

        for(int i = 0;i < 10;i++){
            new Thread(()->{
                Singleton singleton = Singleton.getInstance();
                singleton.print();
                System.out.println(singleton.toString());

            }).start();
        }
    }
}
