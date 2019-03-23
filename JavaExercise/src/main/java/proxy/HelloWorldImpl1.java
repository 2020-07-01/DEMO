package proxy;

/**
 * 真实类
 * sayHelloWorld():真实对象的方法，在调用此方法之前要先调用逻辑方法
 * @author qiang
 *
 */
public class HelloWorldImpl1 implements HelloWorld {

	@Override
	public void sayHelloWorld() {
		System.out.println("Hello World!");
	}
	
}
