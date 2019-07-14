package lambda;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;


/**
 * @author :qiang
 * @date :Created in 2019/7/7 下午10:02
 * @description :lambda表达式学习
 * @other :
 */
public class LambdaTest {

    public static void main(String[] args) {

        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars"};
        System.out.println("排序前输出：" + Arrays.toString(planets));

        //进行排序
        Arrays.sort(planets);
        System.out.println("排序后输出：" + Arrays.toString(planets));
        System.out.println("sort by length:");
        Arrays.sort(planets, (first, second) ->
                first.length() - second.length()
        );

        System.out.println("通过长度进行排序：" + Arrays.toString(planets));


        Timer t = new Timer(1000, (event) ->
                System.out.println("the time is :"+ new Date())
        );
        t.start();

        JOptionPane.showConfirmDialog(null,"Quit!");

        System.exit(0);

    }
}
