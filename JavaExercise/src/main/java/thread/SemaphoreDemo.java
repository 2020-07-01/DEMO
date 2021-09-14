package thread;
import java.util.concurrent.Semaphore;

/**
 * @ClassName : Semaphore
 * @Author : yq
 * @Date: 2021-08-29
 * @Description :
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        int states = 3;
        Semaphore semaphore = new Semaphore(states);

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        threadB.start();

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire(3);
                } catch (Exception e) {
                    e.printStackTrace();
                    semaphore.release(3);
                    System.out.println("availablePermits:" + semaphore.availablePermits());
                    System.out.println(semaphore.drainPermits());
                }
            }
        });
        threadC.start();
        threadC.interrupt();
    }
}
