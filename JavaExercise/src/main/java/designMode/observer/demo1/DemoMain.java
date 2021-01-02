package designMode.observer.demo1;

/**
 * @ClassName : DemoMain
 * @Author : yq
 * @Date: 2021-01-02
 * @Description :
 */
public class DemoMain {

    public static void main(String[] args) {
        WatchEd watched = new WatchEd();
        Watcher watcher = new Watcher();

        watched.addObserver(watcher);

        //通知所有的观察者打开或者关闭窗户
        watched.notifyObservers("openWindow");
        watched.notifyObservers("closeWindow");
    }
}
