package generics.wildcardCharacter;

import java.util.Arrays;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/10/11 下午3:18
 * @description :
 * @other :
 */
public class CompilerIntelligence {

    public static void main(String[] args) {

        List<? extends Fruit> list = Arrays.asList(new Apple());
        Apple a = (Apple) list.get(0);

        list.contains(new Apple());
        list.indexOf(new Apple());

    }
}
