package dataStructure.bst;

public class Main {
	public static void main(String[] args) {
		BST<Integer> bst = new BST<Integer>();
		
		
		bst.insert(10);
		bst.insert(8);
		bst.insert(7);
		bst.insert(4);
		bst.insert(3);
		bst.insert(0);
		bst.insert(9);
		bst.insert(6);
		bst.insert(30);
		bst.insert(13);
		bst.insert(10);
		bst.insert(6);
		
		
		System.out.print("inorder:");
		bst.inorder();
		System.out.print("\n"+"postorder:");
		bst.postorder();
		System.out.print("\n"+"preorder:");
		bst.preorder();
		
		System.out.println(bst.delete(60));
		
		
		
		
		
	}
}
