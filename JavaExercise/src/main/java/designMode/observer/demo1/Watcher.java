package designMode.observer.demo1;

import java.util.Observable;
import java.util.Observer;

/**
 * @ClassName : Watcher
 * @Author : yq
 * @Date: 2021-01-02
 * @Description : 观察者
 */
public class Watcher implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if (arg.toString().equals("openWindow")) {
            System.out.println("打开窗口");
        }
        if (arg.toString().equals("closeWindow")) {
            System.out.println("关闭窗口");
        }
    }
}
