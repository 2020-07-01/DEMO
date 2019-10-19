package dataStructure.map;

import javafx.scene.shape.VLineTo;

import java.util.WeakHashMap;

/**
 * @author :qiang
 * @date :2019/10/18 下午3:41
 * @description : 测试WeakHashMap
 * @other :
 */
public class CanonicalMapping {

    public static void main(String[] args) {

        int size = 1000;
        if (args.length > 0)
            size = new Integer(args[0]);
        Key[] keys = new Key[size];

        //创建弱引用的散列对象
        WeakHashMap<Key, Value> map = new WeakHashMap<Key, Value>();

        for (int i = 0; i < size; i++) {
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if (i % 3 == 0)
                keys[i] = k; // Save as "real" references
            map.put(k, v);
        }

        System.gc();
    }
}


class Element {

    private String ident;

    public Element(String ident) {
        this.ident = ident;
    }

    @Override
    public String toString() {
        return ident;
    }

    @Override
    public int hashCode() {
        //返回的是字符串的散列码，字符串的散列码是根据内容生成
        return ident.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Element) && ((Element) obj).ident.equals(ident);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalizing" + getClass().getCanonicalName() + " " + ident);
        super.finalize();
    }
}


class Key extends Element {

    public Key(String indent) {
        super(indent);
    }

}

class Value extends Element {

    public Value(String ident) {
        super(ident);
    }

}
