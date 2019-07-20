package innerClass;

import coding.testInterface.test2.TimePrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author :qiang
 * @date :2019/7/20 下午1:50
 * @description :测试内部类
 * @other :
 */
public class InnerClsssTest {


    public static void main(String[] args) {

        //创建外部类对象
        TalkingClock talkingClock = new TalkingClock(1000, true);


    }

}

/**
 * 创建外部类
 */
class TalkingClock {

    //创建两个私有域
    private int interval;
    private boolean beep;

    //创建外部类的构造方法
    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    //创建方法
    public void start() {

        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval, listener);
        t.start();
    }


    //创建私有内部类，只有他的外部类才可以调用此内部类
    private class TimerPrinter implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            start();
            System.out.println("at the tone,the time is : " + new Date());
            //在内部类中有一个隐式引用，指向外部类对象
            if (beep) Toolkit.getDefaultToolkit().beep();
        }


    }
}
