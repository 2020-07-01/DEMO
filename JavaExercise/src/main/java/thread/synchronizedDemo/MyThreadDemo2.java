package thread.synchronizedDemo;

/**
 * @author yq
 * @date 2020/8/20 19:18
 * 生产者-消费者
 */
public class MyThreadDemo2 {

    public static void main(String[] args) {

        Message message = new Message();
        new Thread(new Producer(message)).start();
        new Thread(new Consumer(message)).start();
    }
}


class Consumer implements Runnable {

    Message message;

    public Consumer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int x = 0; x < 100; x++) {
            System.out.println(this.message.get());
        }
    }

}

class Producer implements Runnable {
    Message message;

    public Producer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                this.message.set("生产者1", "我是生产者1");
            } else {
                this.message.set("生产者2", "我是生产者2");
            }
        }
    }
}

/**
 * 最原始的控制等待同步方案
 * 暂停的是生产者和消费者
 */
class Message {

    private String title;
    private String content;
    private boolean flag = true;

    public synchronized void set(String title, String content) {
        //无法进行生产，等待消费
        if(this.flag == false){
            try {
                System.out.println(this.getClass().getName());
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.title = title;
        this.content = content;
        this.flag = false;
        super.notify();
    }

    public synchronized String get() {
        //等待生产
        if(this.flag == true){
            try {
                System.out.println(this.getClass().getName());
                super.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            return "title-" + this.title + " " + "content-" + this.content;
        }finally {
            this.flag = true;
            super.notify();

        }
    }

}
