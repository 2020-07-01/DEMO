package codingTest;

/**
 * @ClassName:  Test_initlist   
 * @Description:测试类的初始化顺序
 * @author: caiji
 * @date: 2019年5月7日 下午8:42:12
 */
//子类
public class Test_initlist extends A{

	static {
		System.out.println("子类的静态代码块");
	}
	 
	static String string = printStr1("子类的静态变量");
	
	String string1 = printStr1("子类的非静态变量");
	
	public Test_initlist() {
		System.out.println("子类的构造方法");
	}
	
	public void  MainTest(){
		System.out.println("子类的非静态代码块");
	}
	
	public static String printStr1(String string) {
		System.out.println(string);
		return null;
	}
	
	public static void main(String[] args) {
		new Test_initlist();
	}
}

//创建一个父类
class A {
	static {
		System.out.println("父类的静态代码块");
	}

	public A() {
		System.out.println("父类的构造方法");
	}
	
	String string = printStr("父类的非静态变量");
	static String string1 = printStr("父类的静态变量");
	public void TestMain() {
		System.out.println("父类的非静态代码块");
	}
	
	public static String printStr(String string) {
		System.out.println(string);
		return null;
	}
	 
	
	

}
