package enumsustom;

import dataStructure.fillContainer.Generator;

import java.util.Random;

/**
 * @author :qiang
 * @date :2019/10/19 下午2:15
 * @description : 测试时现多个接口
 * @other : 所有的枚举类都继承自Enum,它不支持多个继承，但是支持实现多个接口
 */
public enum CartoonCharacter implements Generator<CartoonCharacter> {

    //创建枚举实例
    SLAPPY, SPANKY, PUNCHY, SILLY, NUTTY, BOB;

    Random random = new Random(47);

    @Override
    public CartoonCharacter next() {
        //随即返回数组中的值
        return values()[random.nextInt(values().length)];
    }

    //进行输出
    public static void printNext(CartoonCharacter c) {

        System.out.println(c.next());
    }

    public static void main(String[] args) {

        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 5; i++) {
            printNext(cc);
        }
    }


}
