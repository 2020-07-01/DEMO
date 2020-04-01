package comparator_comparable;

/**
 * @ClassName:  TestComparable   
 * @Description:Comparable是一个内比较器
 * @author: caiji
 * @date: 2019年5月5日 下午2:47:41
 */
public class TestComparable implements Comparable<TestComparable> {
	private String str;

	public TestComparable(String string) {
		this.str = string;
	}

	@Override
	public int compareTo(TestComparable testComparable) {
		if (this.str.compareTo(testComparable.str) > 0)
			return 1;
		else if (this.str.compareTo(testComparable.str) == 0)
			return 0;
		else
			return -1;
	}

	public static void main(String[] args) {

		TestComparable testComparable = new TestComparable("123");
		int a = testComparable.compareTo(new TestComparable("456"));

		System.out.println(a);

	}
}
