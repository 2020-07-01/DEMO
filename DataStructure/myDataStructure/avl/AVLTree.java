package avl;



public class AVLTree<E extends Comparable<E>> extends BST<E> {

	// 创建一个空树
	public AVLTree() {
	}

	// 从数组中创建一棵树
	public AVLTree(E[] objects) {
		super(objects);
	}

	// 创建并返回一个平衡结点
	public AVLTreeNode<E> createdNewNode(E e) {
		return new AVLTreeNode<E>(e);
	}

	// 插入操作,重写父类的insert方法
	public boolean insert(E e) {
		boolean successful = super.insert(e);
		if (successful != true)// 如果插入不成功返回false
		{
			return false;
		} else {
			balancePath(e);
		}
		return true;// 插入成功
	}

	// 重置指定结点的高度
	public void updateHeight(AVLTreeNode<E> node) {
		
		if(node.left == null && node.right == null)//此节点为叶结点
		{
			node.hight = 0;
		}
		else if(node.left == null)
		{
			node.hight = 1 + ((AVLTreeNode<E>) (node.right)).hight; 
		}
		else if(node.right == null)
		{
			node.hight = 1 + ((AVLTreeNode<E>) (node.left)).hight;
		}
		else {
			node.hight = 1 + Math.max(((AVLTreeNode<E>) (node.right)).hight,((AVLTreeNode<E>) (node.left)).hight);
		}
		
	}

	// 如果必要将该元素到根的路径上的结点进行平衡
	public void balancePath(E e) {

	}
	//返回结点的平衡因子
	private int balanceFactor(AVLTreeNode<E> node) {
		
		return 0;
	}
	
	//创建静态内部类-平衡树结点类
	protected static class AVLTreeNode<E extends Comparable<E>> extends BST.TreeNode<E> {

		protected int hight;
		public AVLTreeNode(E data) {
			super(data);
		}
	}
}
