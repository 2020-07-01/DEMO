package thread.threadDemo;

/**
 * @author yq
 * @date 2020/8/20 19:09
 */
public class DeadLock implements Runnable{

    Jian jian = new Jian();
    XiaoQiang xiaoQiang = new XiaoQiang();


    @Override
    public void run() {
        jian.say(xiaoQiang);

    }

    public DeadLock() {
        new Thread(this).start() ;
        xiaoQiang.say(jian) ;
    }

    public static void main(String[] args) {
        new DeadLock();
    }
}


class Jian {
    public synchronized void say(XiaoQiang xq) {
        System.out.println("阿健说，此路是我开，要想从此过，留下10块钱。") ;
        xq.get() ;
    }
    public synchronized void get() {
        System.out.println("阿健说，得到了10块钱，可以买饭吃了，于是让出了路");
    }
}
class XiaoQiang {
    public synchronized void say(Jian jj) {
        System.out.println("小强说，让我先过去，然后给你钱") ;
        jj.get();
    }
    public synchronized void get() {
        System.out.println("小强说，逃过了一劫，可以继续送快餐了。");
    }
}