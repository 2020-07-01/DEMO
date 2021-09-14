package enumsustom.enumInterface;

import java.util.Random;

/**
 * @author :qiang
 * @date :2019/10/19 下午3:19
 * @description :
 * @other :
 */
public class Enums {

    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T rand(Class<T> ec) {
        return random(ec.getEnumConstants());
    }


    //参数为枚举数组
    private static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
