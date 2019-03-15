package project0315;
/**
 * 获取两个节点的公共部分
 * @author qiang
 *
 */
public class Node {
	
	public int value;
	public Node next;
	public Node(int data) {
		this.value = data;
	}
	
	//获取两个节点的公共部分
	public void nodeSort(Node head1,Node head2) {
		while(head1 != null && head2 != null) {
			if(head1.value > head2.value) {
				head1 = head1.next;
			}
			else if(head1.value < head2.value) {
				head2 = head2.next;
			}
			else 
				System.out.println("  "+head1.value);
		
			head1 = head1.next;
			head2 = head2.next;
		}
		System.out.println();
		
	}
	 
}
