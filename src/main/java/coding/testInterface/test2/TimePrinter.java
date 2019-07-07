package coding.testInterface.test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author :qiang
 * @date :Created in 2019/7/7 下午8:01
 * @description :定时器实现类
 * @other :
 */
public class TimePrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("the time is " + new Date());

        Toolkit.getDefaultToolkit().beep();
    }
}


