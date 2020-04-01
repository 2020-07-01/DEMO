import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author :qiang
 * @date :2019/10/27 下午3:34
 * @description :
 * @other :
 */
public class Main {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger);
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger);

    }
}
