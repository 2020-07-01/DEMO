package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yq
 * @date 2020/7/26 15:42
 * list集合的stream操作
 */
public class ListStreamTest {

    /**
     * 流操作
     */
    public void arrayListTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("fafa");
        arrayList.add("fafeadasfdfv");
        arrayList.add("fasaea");
        arrayList.add("egfdd");

        //获取一个顺序流
        Stream<String> stringStream = arrayList.stream();

        List<String> result = stringStream.filter(p->p.length() > 2).filter(p->p.contains("fa")).collect(Collectors.toList());
        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        ListStreamTest listStreamTest = new ListStreamTest();
        listStreamTest.arrayListTest();
    }
}
