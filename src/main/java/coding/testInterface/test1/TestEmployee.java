package coding.testInterface.test1;

/**
 * @author :qiang
 * @date :Created in 2019/7/7 下午6:25
 * @description :测试接口
 * @other :
 */
public class TestEmployee {

    public static void main(String[] args) {

        Employee employee = new Employee("yq", 5000, 2019, 7, 1);
        Employee employee1 = new Employee("yq1", 6000, 2019, 7, 1);

        System.out.println(employee.compareTo(employee1));

    }

}
