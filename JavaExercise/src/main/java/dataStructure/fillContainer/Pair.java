package dataStructure.fillContainer;

/**
 * @author :qiang
 * @date :2019/10/15 下午9:21
 * @description :
 * @other :
 */
public class Pair<K, V> {

    /**
     * final：修饰的变量可以在声明时进行初始化
     * 也可以在构造方法中进行初始化
     */
    public final K key;
    public final V value;

    public Pair(K k, V v) {
        key = k;
        value = v;
    }
}
