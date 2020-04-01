package dataStructure.collection_method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/10/15 上午11:04
 * @description :未获得支持的操作
 * @other :
 */
public class UnSupported {

    static void test(String msg, List<String> list){

        Collection<String> c = list;
        Collection<String> sublist = list.subList(1,8);

        Collection<String> c2 = new ArrayList<>(sublist);

    }
}
