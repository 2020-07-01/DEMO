package container.fillContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/10/15 上午10:22
 * @description :容器的填充
 * @other :
 */
public class FillingLists {

    public static void main(String[] args) {

        /**
         * nCopies()方法会返回一个list
         * 此list中存储的是相同相同对象的引用
         */
        List<StringAddress> list2 = Collections.nCopies(4, new StringAddress("hello"));
        System.out.println(list2.toString());
        List<StringAddress> list1 = new ArrayList<>(list2);
        System.out.println(list1);


        /**
         * fill()方法只能替换已经存在的元素，不能填充新的元素
         */
        List<StringAddress> list = new ArrayList<>();
        Collections.fill(list, new StringAddress("World"));
        System.out.println(list);
    }
}

class StringAddress {
    private String s;

    public StringAddress(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return super.toString() + " " + s;
    }
}
