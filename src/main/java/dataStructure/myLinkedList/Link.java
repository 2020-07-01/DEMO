package dataStructure.myLinkedList;

/**
 * @author :qiang
 * @date :2019/9/29 下午9:53
 * @description :双向链表接口
 * @other :
 */
public interface Link<E> {

    //添加元素，默认从尾部添加
    boolean add(E value);

    //从头部添加元素
    boolean addFirst(E value);

    //元素数量
    public int size();

    //删除指定元素
    boolean remove(E value);

    //添加元素在指定的位置处
    boolean add(int index, E value);

    //获取指定位置处的元素
    E get(int index);


}
