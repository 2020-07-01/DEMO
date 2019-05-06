package thread.test;
/**
 * 测试主线程与子线程
 * @author qiang
 *
 */
public class MainThread {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getName());
		System.out.println("parent thread begin ");  
        
        ChildThread t1 = new ChildThread("thread1"); 
        t1.setName("thread1");
        ChildThread t2 = new ChildThread("thread2");
        t2.setName("thread2");
        Thread.sleep(9000);//main线程进入睡眠状态
        t1.start();  
        t2.start();  
  
        System.out.println("parent thread over ");  
	}

}


class ChildThread extends Thread {
	private String name = null;  
	  
    public ChildThread(String name)  
    {  
        this.name = name;  
    }  
  
    @Override  
    public void run()  
    {  
        System.out.println(this.name + "--child thead begin");  
  
        try  
        {  /*此时t1线程进入睡眠状态，后面的语句不再执行，t2线程开始执行
        	*当t2线程执行到此时也进入睡眠状态
        	*/
            Thread.sleep(500);   
            //输出的是当前子线程
            System.out.println(Thread.currentThread().getName());
        }  
        catch (InterruptedException e)  
        {  
            System.out.println(e);  
        }  
  
        System.out.println(this.name + "--child thead over");  
    }  
}  
  
