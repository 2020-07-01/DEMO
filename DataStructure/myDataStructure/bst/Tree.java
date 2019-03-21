package bst;

/**
 * 定义树的所有常用操作
 * @author qiang
 *
 * @param <E>
 */
public interface Tree<E> extends Iterable<E>{
	//搜索元素
	public abstract boolean search(E e);
	
	//插入元素
	public  boolean insert(E e); 
	
	//删除元素
	public boolean delete(E e);
	
	//中序遍历
	public void inorder();
	
	//谦虚遍历
	public void preorder();
	
	//后序遍历
	public void postorder();
	
		
		
}
