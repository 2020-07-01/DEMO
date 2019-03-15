package myLinkedList;

import java.util.Iterator;

public abstract class MyAbstactList<E> implements MyList<E>{
	
	protected int size = 0;
	
	public MyAbstactList() {
		// TODO Auto-generated constructor stub
	}
	
	public MyAbstactList(E[] objects) {
		for(int i = 0;i<objects.length;i++)
			add(objects[i]);
	}
	
	
	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public void add(E e) {
		add(size,e);
	}

	@Override
	public void add(int index, E e) {
		// TODO Auto-generated method stub
		
	}
	
	public int size() {
		return size;
	}

}
