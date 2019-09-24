
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * @author :qiang
 * @date :2019/9/11 下午8:22
 * @description :
 * @other :
 */
public class Main {

    public static void main(String[] args) throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);


        Iterator iterator = linkedList.iterator();
        iterator.forEachRemaining(item -> System.out.println(item));
        HashSet hashSet = new HashSet();



        System.out.println();

    }
}
