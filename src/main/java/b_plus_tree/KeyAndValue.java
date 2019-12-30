package b_plus_tree;

/**
 * @program: KeyAndValue
 * @description: 存储关键字和数据的类，类似于数据库中的一条记录
 * @create: 2019-12-29 14:24
 **/
public class KeyAndValue implements Comparable<KeyAndValue> {
    //存储索引关键字
    private int key;
    //存储数据
    private Object value;

    public KeyAndValue(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(KeyAndValue o) {
        //正序排列
        return this.key - o.key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
