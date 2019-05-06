package thread.testSynchronized;

public class TestSy01 implements Runnable{
    private int number;

    TestSy01(){
        number = 0;
    }

    public void m1(){
    	synchronized (TestSy01.class) {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (number++));
					Thread.sleep(500);
				} catch (Exception e) {
					System.out.println("异常");
				}
			}
		}
    }

    public synchronized  void m2(){
    	for (int i = 0; i < 3; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (number++));
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println("异常");
			}
		}

        
    }
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        if(name.equalsIgnoreCase("thread1")){
            m1();
        }else{
            m2();
        }
    }


    public static void main(String[] args){
        TestSy01 testSy1 = new TestSy01();
        Thread thread1 = new Thread(testSy1,"thread1");
        Thread thread2 = new Thread(testSy1,"thread2");
        thread1.start();
        thread2.start();
    }
}