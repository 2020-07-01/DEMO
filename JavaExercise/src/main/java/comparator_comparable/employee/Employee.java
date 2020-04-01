package comparator_comparable.employee;

/**
 * @author :qiang
 * @date :2019/10/22 下午8:36
 * @description :
 * @other :
 */
public class Employee implements Comparable {

    private String name;
    private int age;


    public Employee(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        Employee other = (Employee) o;

        /**
         *
         */
        return Double.compare(other.age, this.age);
    }

    @Override
    public String toString() {
        return this.name + this.age;
    }
}
