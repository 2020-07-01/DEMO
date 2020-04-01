package object;

/**
 * @author :qiang
 * @date :2019/10/17 下午11:41
 * @description :基本数据类型的散列码
 * @other :
 */
public class HashCode_Main {

    public static void main(String[] args) {

        Integer i = 1;
        char c = 90;
        byte b = 127;
        short s = 4234;
        System.out.println("char类型的散列码：" + new Character(c).hashCode());
        System.out.println("int类型的散列码：" + i.hashCode());
        System.out.println("byte类型的散列码：" + new Byte(b).hashCode());
        System.out.println("short类型的散列码：" + new Short(s).hashCode());

        float f = 1.23445f;
        System.out.println("float类型的散列码："+new Float(f).hashCode());


        String string = "1";
        System.out.println("String类型的散列码："+string.hashCode());

        long l = 1234564565445653453L;
        System.out.println("long类型的散列码："+new Long(l).hashCode());

        double d = 1.12345679;
        System.out.println("double类型的散列码："+new Double(d).hashCode());
    }

    /**
     * char类型的散列码：65
     * int类型的散列码：1
     * byte类型的散列码：127
     * short类型的散列码：4234
     */
}
