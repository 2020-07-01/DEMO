package nowcoder;

 
import java.awt.List;
import java.util.Stack;

import javax.net.ssl.ExtendedSSLSession;

public class TestString01 {
	// 二维数组顺时针旋转90度
	public static int[][] transformImage(int[][] mat, int n) {
		// 创建二维数组
		int[][] newmat = new int[n][n];

		int j = 0;
		int k = 0;

		for (int i = n - 1; i >= 0; i--) {
			for (int h = 0; h < mat[i].length; h++) {
				newmat[j++][k] = mat[i][h];
			}
			k++;
		}
		return newmat;
	}

	// 判断是否为合法的括号串
	public static boolean chkParenthesis(String A, int n) {

		if (A == null || A.length() != n) {
			return false;
		}

		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < A.length(); i++) {
			Character c = A.charAt(i);
			if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				if (stack.isEmpty())
					return false;
				else
					stack.pop();
			} else {
				return false;
			}
		}

		if (stack.isEmpty())
			return true;
		else
			return false;

	}

	public static class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	// 判断是否为会文链表
	public boolean isPalindrome(ListNode pHead) {

		ListNode fast = pHead;
		ListNode slow = pHead;
		
		Stack<Integer> stack = new Stack<Integer>();

		while (fast != null && fast.next != null) {
			stack.push(slow.val);
			slow = slow.next;
			fast = fast.next.next;
		}
		
		
		// 当链表为奇数个时，跳过中间元素
		if (fast != null) {
			slow = slow.next;
		}
		while (slow != null) {
			// 如果两者不相同，则该链表不是回文串
			if (stack.pop() != slow.val) {
				return false;
			}
			slow = slow.next;
		}
		return true;
	}

	//两个链表表示的整数相加
	public static ListNode plusAB(ListNode a, ListNode b) {
		
		// write code here
        ListNode start = null;
        ListNode current = null;
        if(a == null && b == null) return start;
        
        int plus = 0;
        while(a!=null || b!= null){
            int tmp = plus;//循环加1
            if(a!=null)
                tmp += a.val;
            if(b!=null)
                tmp+=b.val;
            if(start == null){
                current = new ListNode(tmp%10);//余数
                start = current;
                plus = tmp/10;
                 
            }else {
               current.next=new ListNode(tmp%10);
               current  = current.next;
               plus = tmp/10;
           }
            if(a==null&& b!=null)
                b=b.next;
            else if(a!=null && b== null)
                a=a.next;
            else {
                a=a.next;
                b=b.next;
           }
             
        }
        //最后一位
       if(plus == 1)
            current.next = new ListNode(plus);
        
        return start;
   }
	
	//删除给定的节点
	 public boolean removeNode(ListNode pNode) {
		
		 if(pNode == null || (pNode != null && pNode.next == null))
			 return false;
		 
			 pNode.val = pNode.next.val;
			 pNode.next  = pNode.next.next;
		 
		 
		 return true;
	    }

	 //输出链表中的倒数第k个节点
	 
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		a.next = new ListNode(4);
		a.next.next = new ListNode(9);
		
		
		ListNode b = new ListNode(1);
		b.next = new ListNode(9);
		b.next.next = new ListNode(9);
		
		
		ListNode c = plusAB(a,b);
		
		while(c!= null) {
			System.out.print(c.val + " ");
			c = c.next;
		}

		
		
		
 	}

}
