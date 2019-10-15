package dataStructure.collection;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author :qiang
 * @date :2019/10/15 上午10:48
 * @description :Collection方法测试
 * @other :
 */
public class CollectionMethod {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();

        collection.add("ten");
        collection.add("eleven");
        System.out.println(collection);

        //返回一个数组，该数组包含容器中的所有元素
        Object[] array = collection.toArray();
        System.out.println(Arrays.toString(array));

        //返回一个数组，该数组包含容器中的所有元素，并设置数组的大小
        String[] str = collection.toArray(new String[10]);
        System.out.println(Arrays.toString(str));

        Collection<String> collection1 = new ArrayList<>();
        collection1.add("1");
        collection1.add("2");
        collection1.addAll(collection);//将collection的元素添加在后面
        System.out.println(collection1);




    }
}
