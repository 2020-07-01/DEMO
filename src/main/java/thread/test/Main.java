package thread.test;

/**
 * @ClassName:  Main   
 * @Description:测试Thread中的方法
 * @author: caiji
 * @date: 2019年6月7日 下午8:47:19
 */
public class Main {

	public static void main(String[] args) {
 
		System.out.println("===========================");
		MyThread thread = new MyThread();
		thread.start();
		System.out.println("tostring:" + thread.toString());
		System.out.println("线程组：" + thread.getThreadGroup());
		System.out.println(thread.getId());
		System.out.println(thread.isAlive());
	}
}

class MyThread extends Thread {

	@Override
	public void run() {
		System.out.println("重写run方法");
	}

}
