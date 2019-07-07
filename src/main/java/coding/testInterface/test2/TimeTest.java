package coding.testInterface.test2;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author :qiang
 * @date :Created in 2019/7/7 下午8:03
 * @description :定时器测试类
 * @other :
 */
public class TimeTest {

    public static void main(String[] args) {

        ActionListener listener = new TimePrinter();

        Timer t = new Timer(1000, listener);

        t.start();

        JOptionPane.showConfirmDialog(null,"Quit program!");
        System.exit(0);
    }


}
