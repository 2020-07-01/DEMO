package comparator_comparable.employee;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/22 下午8:38
 * @description :
 * @other :
 */
public class TestEmployee {

    public static void main(String[] args) {

        Employee[] staff = new Employee[3];

        staff[0] = new Employee("A", 12);
        staff[1] = new Employee("yq", 34);
        staff[2] = new Employee("qiang", 56);

        /**
         * Arrays.sort():此方法的参数为数组，作用是为此数组进行排序
         * 要求：数组中的所有元素必须继承Comparable接口并实现compareTo()方法
         */
        Arrays.sort(staff);

        for (Employee item : staff) {
            System.out.println(item.toString());
        }


        int[] array1 = {1, 2, 2532, 4, 54, 523, 523, 532, 532};
        Arrays.sort(array1);//默认进行生序排序
        for (Integer item : array1) {
            System.out.print(item + " ");
        }


    }
}
