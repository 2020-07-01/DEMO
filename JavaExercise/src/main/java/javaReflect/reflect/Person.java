package javaReflect.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: qiang
 * @Description: 反射
 * @other:
 * @Date: 2019/9/23 10:59
 */
public class Person {

	//创建私有属性
	public String name;
	private int age;
	private String sex;

	//构造方法
	Person(String string) {
		System.out.println("123");
	}

	Person() { }

	Person(String name, int age, String sex) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}


	public String getName() {
		return name;
	}

	//get和set方法
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private String getSex() {
		return sex;
	}

	private void setSex(String sex) {
		this.sex = sex;
	}

	public void method() {
		System.out.println("这是一个空方法");
	}

	;

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
		Person person = new Person();
		/*
		 * 获取代表运行时类的Class对象
		 */
		//1.通过Object.getClass()
		Class class1 = person.getClass();
		System.out.println(class1);

		//2.通过运行时类的class属性
		Class class2 = Person.class;

		//3.通过Class类的forName()方法
		Class class3 = Class.forName("reflect.Person");
		System.out.println(class3);

		Object object = class1.newInstance();
		System.out.println(object);


		//获取类的所有公共构造方法
		Constructor[] constructors1 = class1.getConstructors();
		for (Constructor item : constructors1) {
			System.out.println(item);
		}
		//获取类的所有构造方法
		Constructor[] constructors2 = class1.getDeclaredConstructors();


		//获取类的私有构造方法
		Constructor constructor3 = class1.getDeclaredConstructor();

		Constructor constructor4 = class1.getDeclaredConstructor(new Class[]{});


		System.out.println("___________Method___________");
		//获取类下的一个具体public 类型的方法
		Method method11 = class1.getMethod("method", new Class[]{});


		//获取类下的所有方法,包括父类的方法
		Method[] method1 = class1.getMethods();
		for (Method item : method1) {
			System.out.println(item);
		}
		//获取类的自定义的方法，
		System.out.println("declaredMethods==================");
		Method[] methods = class1.getDeclaredMethods();
		for (Method item : methods) {
			System.out.println(item);

		}
		System.out.println("==================Field===================");
		//获取指定的public成员变量
		Field field = class1.getField("name");
		System.out.println("获取指定的name成员变量：" + field);

		//获取类的所有成员变量
		Field[] fields = class1.getDeclaredFields();
		System.out.println("获取类的所有成员变量：");
		for (Field item : fields) {
			System.out.print(item + "   ");
		}

		//获取类的public成员变量
		Field[] fields1 = class1.getFields();
		System.out.println("获取类的所有public成员变量：");
		for (Field item : fields1) {
			System.out.print(item + "   ");
		}
	}
}
