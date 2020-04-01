package generics;

/**
 * @author :qiang
 * @date :2019/10/10 下午7:16
 * @description : 持有单一对象的类
 * @other :
 */
public class Holder1 {

    private Automobile a;

    public Holder1(Automobile a) {
        this.a = a;
    }

    Automobile get() {
        return a;
    }
}
