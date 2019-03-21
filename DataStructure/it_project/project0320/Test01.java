package project0320;


/**
 * 实现二叉查找树的查找，插入
 * @author qiang
 * @param <E>
 *
 */
public class Test01 {
	
	//创建根节点
	public static TreeNode<Integer> root;
	//创建左节点
	public TreeNode<Integer> left;
	//创建右节点
	public TreeNode<Integer> right;
	
 
	
	//创建静态节点类
	public static class TreeNode<E> {
		protected E element;
		protected TreeNode<E> left;
		protected TreeNode<E> right;
		
		public TreeNode(E data) {
			this.element = data;
		}
	}
	
	//查找节点从根节点开始，直到找到节点或者最后一个节点位空为止
	//查找元素的方法
	//如果找到返回false
	public static boolean search(Integer element) {
		
		TreeNode<Integer> cur = root;
		
		while(cur != null) {
			if(element < cur.element)
			{
				cur = cur.left;
			}
			else if(element > cur.element)
			{
				cur = cur.right;
			}
			else 
				return true;
		}
		return false;
	}
	
	/*
	 * 实现插入操作
	 * 1. 如果这棵树是空的，则创建此节点作为根节点
	 * 2. 否则，寻找新元素的父节点，为该元素创建一个节点，然后将该节点连接到他的父节点上
	 */
	public static boolean insert(Integer newdata) {
		TreeNode<Integer> cur  = root;
		TreeNode<Integer> parent = root;
		if(root == null)
		{
			root = new TreeNode<Integer>(newdata);
			return true;
		}
		else {
			while(cur != null)
			{
				if(cur.element < newdata)
				{
					parent = cur;
					cur = cur.right;
				}
				else if(cur.element > newdata)
				{
					parent = cur;
					cur = cur.left;
				}
				else {
					return false;
				}
				TreeNode newNode = new TreeNode<Integer>(newdata);
				if(parent.element > newdata)
					parent.left = newNode;
				else
					parent.right = newNode ;
				
			}
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		 Test01 test01 = new Test01();
		 
		 test01.root = new TreeNode<Integer>(60);
		 root.left = new TreeNode<Integer>(40);		 
		 root.right = new TreeNode<Integer>(70);
		 
		 root.left.left = new TreeNode<Integer>(30);
		 root.left.right = new TreeNode<Integer>(35);
		 
		 root.right.left = new TreeNode<Integer>(65);
		 root.right.right = new TreeNode<Integer>(80);
		 
		 
		 
		 boolean p = search(30);
		 System.out.println(p);
		 
		 
		 boolean p2 = insert(100);
		 System.out.println(p2);
		 
		 
	}
}
