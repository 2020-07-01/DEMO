package javaReflect.student;

import java.util.ArrayList;

/**
 * @ClassName:  TestMain   
 * @Description:测试Java三种反射方式
 * @author: caiji
 * @date: 2019年5月7日 上午10:51:40
 */
public class TestMain {

	public static void main(String[] args) throws ClassNotFoundException {
		//通过建立对象进行反射
		Student student = new Student();
		Class class1 = student.getClass();
		System.out.println("class1:" + class1);

		//通过类的class属性进行反射
		Class class2 = Student.class;
		System.out.println("class2:" + class2);

		//通过类的路径进行反射
		Class class3 = Class.forName("javaReflect.student.Student");
		System.out.println("class3:" + class3);

	 
	}

}
