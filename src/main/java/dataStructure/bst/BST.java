package dataStructure.bst;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 实现二叉查找树
 * @author qiang
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> extends AbstractTree<E> {
	
	protected int size = 0;
	protected TreeNode<E> root;// 创键根结点

	// 构造方法
	public BST() {
		// TODO Auto-generated constructor stub
	}

	// 从一个数组中创建二叉查找树
	public BST(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			insert(objects[i]);
	}

	// 创建新的结点
	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<E>(e);
	}

 
	public boolean search(E e) {
		TreeNode<E> cur = root;

		while (cur != null) {
			if (e.compareTo(cur.element) < 0)
				cur = cur.left;
			else if (e.compareTo(cur.element) > 0)
				cur = cur.right;
			else {
				return true;
			}
		}
		return false;
	}

	public boolean insert(E e) {
		if (root == null) {
			root = new TreeNode<E>(e);
		}

		else {
			TreeNode<E> cur = root;
			TreeNode<E> parrent = null;

			while (cur != null) {
				if (e.compareTo(cur.element) < 0) {
					parrent = cur;// 当前结点为此元素的父结点
					cur = cur.left;// 当前结点为他的左节点
				} else if (e.compareTo(cur.element) > 0) {
					parrent = cur;
					cur = cur.right;
				} else {
					return false;
				}
			}

			if (e.compareTo(parrent.element) < 0) {
				parrent.left = createNewNode(e);
			} else {
				parrent.right = createNewNode(e);
			}
		}

		size++;
		return false;

	}

	public boolean delete(E e) {
		TreeNode<E> parent = null;
		TreeNode<E> cur = root;
		// 先定位e结点
		while (cur != null) {
			if (e.compareTo(cur.element) < 0) {
				parent = cur;
				cur = cur.left;
			}

			else if (e.compareTo(cur.element) > 0) {
				parent = cur;
				cur = cur.right;
			} else {
				break;// 此时当前元素为要删除的元素
			}
		}
		// 如果当前元素为空,即此元素不存在
		if (cur == null)
			return false;
		/*
		 * 第一种情况：元素没有左子树,建立此节点的父节点与此节点的右节点的之间的关系即可
		 */
		if (cur.left == null) {
			// 如果父元素为空，则删除的结点为根结点
			if (parent == null)
				root = cur.right;
			else {
				if (e.compareTo(parent.element) < 0)
					parent.left = cur.right;
				else
					parent.right = cur.right;
			}
		}
		/*
		 * 第二种情况：元素有左子树 需要定位当前元素的前驱结点：当前元素左子树中的最大元素
		 */
		else {
			TreeNode<E> rightMost = null;// 前驱结点,此节点必为一个右结点
			TreeNode<E> parentofmost = cur.left;// 前驱结点的父节点

			while (rightMost.right != null) {
				parentofmost = rightMost;
				rightMost = rightMost.right;
			}
			// 使当前结点与前驱结点进行交换//即删除了当前结点
			cur.element = rightMost.element;
			// 将parentofmost与rightMost的左子结点相连，删除前驱结点rightMost
			if (parentofmost.right == rightMost)
				parentofmost.right = rightMost.left;
			else
				// 如果前驱结点不是右结点
				parentofmost.left = rightMost.right;
		}
		size--;
		return true;
	}

	// 中序遍历
	public void inorder() {
		inorder(root);
	}

	protected void inorder(TreeNode<E> root) {
		if (root == null)
			return;

		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}

	// 后序遍历
	public void postorder() {
		postorder(root);
	}

	protected void postorder(TreeNode<E> root) {
		if (root == null)
			return;
		System.out.print(root.element + " ");
		postorder(root.right);
		postorder(root.left);

	}

	// 前序遍历
	public void preorder() {
		preorder(root);
	}

	protected void preorder(TreeNode<E> root) {
		if (root == null)
			return;
		preorder(root.right);
		preorder(root.left);
		System.out.print(root.element + " ");

	}

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 创建内部类
	private class InorderIterator implements Iterator<E> {
		// 创建线性表存储排序后的元素
		ArrayList<E> list = new ArrayList<E>();
		private int current = 0;

		public InorderIterator() {
			inorder();// 中序遍历
		}

		private void inorder() {
			inorder(root);
		}

		private void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}

		// 判断是否还有下一个
		public boolean hasNext() {
			if (current < list.size())
				return true;

			return false;
		}

		// 返回下一个元素
		public E next() {
			return list.get(current++);
		}

		// 移除当前元素
		public void remove() {
			delete(list.get(current));
			list.clear();
			inorder();
		}
	}
	
	
	/*
	 *判断是不是为满二叉树
	 *除叶结点外的任何结点都有两个子结点
	 */
	public boolean isFullBst() {
		return false;
	}
	
	/*
	 * 判断是否为完全二叉树
	 * 设二叉树的深度为h，除第h层外，其他各层的结点数达到最大个数个，第h层的所有结点都连续集中在左边
	 */
	public boolean isWholeBst() {
		return false;
	}

}
