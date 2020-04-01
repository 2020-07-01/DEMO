package comparator_comparable;

import java.util.Comparator;

/**
 * @ClassName:  TestComparator   
 * @Description:Comparator是一个外比较器，可以实现自己与自己进行比较，也可以实现不同对象之间的比较
 * @author: caiji
 * @date: 2019年5月5日 下午2:56:11
 */
public class TestComparator implements Comparator<TestComparator> {

	private String str;

	public TestComparator(String string) {
		this.str = string;
	}

	public String getStr() {
		return str;
	}

	public int compare(TestComparator domain1, TestComparator domain2) {
		if (domain1.getStr().compareTo(domain2.getStr()) > 0)
			return 1;
		else if (domain1.getStr().compareTo(domain2.getStr()) == 0)
			return 0;
		else
			return -1;
	}
	public static void main(String[] args) {
		TestComparator t1 = new TestComparator("124");
		TestComparator t2 = new TestComparator("9234");
		System.out.println(t2.compare(t1, t2));
	}

}
