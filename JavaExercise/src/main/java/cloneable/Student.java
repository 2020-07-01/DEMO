package cloneable;

/**
 * @ClassName:  Student   
 * @Description:对浅拷贝进行测试
 * 浅拷贝：对于数据类型为基本数据类型的属性，会直接进行值的传递，对于引用类型，传递的是成员变量的引用值，此时不会创建新的对象
 * 深拷贝：基本数据类型拷贝方式与浅拷贝一样，但是对于引用类型，深拷贝将会创建新的对象。
 * 
 * @author: caiji
 * @date: 2019年5月10日 下午7:05:22
 */
public class Student implements Cloneable {
	private int number;
	private String name;

	public Student() {
	}

	public Student(int number, String name) {
		this.number = number;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

	//测试
	public static void main(String[] args) throws CloneNotSupportedException {
		Student student = new Student(1,"name1");
		//对先的拷贝
		Student student2 = (Student) student.clone();
		
		//两个对象的地址不同，说明创建了新的对象，而不是将原对象的地址赋值给了一个新的对象
		System.out.println("student地址："+student+"\nstudent2地址："+student2);
		System.out.println("student2:  "+student2.getName()+" "+student2.getNumber());
	
		
		System.out.println("student: "+student.getName().hashCode());
		System.out.println("student2:"+student2.getName().hashCode());
		String result = student.getName().hashCode() == student2.getName().hashCode()   
		        ? "clone是浅拷贝的" : "clone是深拷贝的";  
		
		System.out.println(result);
		
		
	}
}
