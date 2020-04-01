package exception;

import cloneable.Student;

/**
 * @author :qiang
 * @date :2019/10/10 下午2:15
 * @description :在return中使用finally
 * @other :
 */
public class MultipleReturns {
    /**
     * 在try块中进行retunr返回时，finally块总是会执行
     *
     * @param i
     */

    public static void f(int i) {
        System.out.println("initialization that requires cleanup");

        try {
            System.out.println("point 1");
            if (i == 1)
                return;
            System.out.println("point 2");
            if (i == 2)
                return;
            System.out.println("point 3");
            if (i == 3)
                return;
            System.out.println("end");
            return;
        } finally {
            System.out.println("performing cleanup");
        }
    }

    public static void main(String[] args) {


        for (int i = 1; i <= 4; i++) {
            f(i);
        }
    }
}
