package generics;

/**
 * @author :qiang
 * @date :2019/10/10 下午7:18
 * @description :持有Object了类型的对象。存储多个不同类型的对象
 * @other :
 */
public class Holder2 {

    private Object a;

    public Holder2(Object a) {
        this.a = a;
    }

    public void set(Object a) {
        this.a = a;
    }

    public Object get() {
        return a;
    }

    public static void main(String[] args) {
        //
        Holder2 h2 = new Holder2(new Automobile());
        Automobile a = (Automobile) h2.get();

        h2.set("not an automobile");
        String s = (String) h2.get();

        h2.set(1);
        Integer x = (Integer) h2.get();


    }

}
