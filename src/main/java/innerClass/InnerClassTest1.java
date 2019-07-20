package innerClass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author :qiang
 * @date :2019/7/20 下午2:00
 * @description :内部类测试
 * @other :
 */
public class InnerClassTest1 {

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass(100, "hello world");


    }

}

//创建外部类
class OuterClass {

    //创建两个私有域
    private int outerInt;
    private String outerString;


    public OuterClass(int outerInt, String outerString) {
        this.outerInt = outerInt;
        this.outerString = outerString;
    }


    //创建外部类的方法
    public void outerMethod(InnerClass innerClass) throws Exception {
        innerClass.innerMethod();
        if (false) {
            new Throwable("123");
            throw new Exception();
        }
    }

    //创建一个内部类
    class InnerClass implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println(e.getWhen());
            System.out.println("访问外部类的私有域：" + "int:" + outerInt + "outerString" + outerString);
        }

        public void innerMethod() {
            System.out.println("访问外部类的私有域：" + "int:" + outerInt + "outerString" + outerString);

        }

    }
}
