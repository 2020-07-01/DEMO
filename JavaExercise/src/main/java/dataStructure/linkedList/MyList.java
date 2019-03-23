package dataStructure.linkedList;

import java.util.Iterator;

public interface MyList<E> extends Iterable<E>{
	
	//添加元素
	public void add(E e);
	//在指定位置添加元素
	public void add(int index,E e);
	Iterator<E> iterator();

}
