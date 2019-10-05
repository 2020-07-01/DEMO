package controllerExecutionProcess;

/**
 * @author :qiang
 * @date :2019/10/5 下午2:28
 * @description :break和continue的用法
 * @other :
 */
public class BreakandContinue {

    public static void main(String[] args) {

        /**
         * break会彻底结束剩余的循环语句，中断内部迭代,回到外部的迭代
         * continue会跳过本地循环,然后退回到起始位位置开始下一次循环
         */
        for (int i = 0; i < 100; i++) {
            if (i == 90) {
                break;
            }
            if (i % 3 == 0)
                continue;

            System.out.print(i + " ");
        }
    }
}
