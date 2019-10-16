package dataStructure.set;


import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

class SetType {

    int i;

    public SetType(int n) {
        this.i = n;
    }

    /**
     * Object中：判断引用地址是否相同
     * 重写后一是判断类型是否相同，二是判断对象内容是否相同
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof SetType && i == ((SetType) obj).i;
    }

    @Override
    public String toString() {
        return Integer.toString(i);
    }
}

class HashType extends SetType {

    //显示调用有参构造方法
    public HashType(int n) {
        super(n);
    }

    @Override
    public int hashCode() {
        return i;
    }
}


class TreeType extends SetType implements Comparable<TreeType> {

    public TreeType(int n) {
        super(n);
    }

    @Override
    public int compareTo(TreeType o) {
        return (o.i < i ? -1 : (o.i == i ? 0 : 1));
    }
}

/**
 * @author :qiang
 * @date :2019/10/16 上午10:24
 * @description :
 * @other :
 */
public class TypesForSets {

    //初始化set并返回
    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        try {
            for (int i = 0; i < 10; i++) {
                //存储的是Integer对象
                set.add(type.getConstructor(int.class).newInstance(i));
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return set;
    }


    static <T> void test(Set<T> set, Class<T> type) {
        fill(set, type);
        fill(set, type);
        fill(set, type);
        System.out.println(set);
    }

    public static void main(String[] args) {
        test(new HashSet<HashType>(),HashType.class);
    }

}



