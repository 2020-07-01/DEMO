/**
 * @author :qiang
 * @date :2019/10/27 下午3:34
 * @description :
 * @other :
 */
public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.test();
    }


    public void test() {

        Class c = this.getClass();
        System.out.println(c.getName());

        Class clazz = super.getClass();
        System.out.println(clazz.getName());
    }
}
