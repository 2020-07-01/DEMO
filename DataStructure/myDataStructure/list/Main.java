package list;

public class Main {

	public static void main(String[] args) {
		 MyLinkedList list = new MyLinkedList();
		 
		 list.addFirst(1);
		 list.addFirst(2);
		 list.addFirst(3);
		 list.addFirst(4);
		 list.addEnd(5);
		 
		 System.out.println(list.size());
		 
		 list.remove(3);
		 System.out.println(list.size());
	}
}
