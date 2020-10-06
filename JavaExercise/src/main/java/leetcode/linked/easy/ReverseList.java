package linked.easy;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.List;
import java.util.Stack;

/**
 * @author :qiang
 * @date :2019/10/29 下午9:13
 * @description :反转链表
 * @other :
 */
public class ReverseList {

    /**
     * 结点类
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 遍历法反转链表，就是在遍历的过程中反转链表的指针
     * 每次将结点添加至头结点
     */
    public static ListNode reverseList1(ListNode head) {

        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;//完成指针反转
            pre = head;//此时pre为下一个结点
            head = next;//head结点下移
        }

        return pre;
    }

    /**
     * 使用递归方法反转链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode temp = head.next;
        ListNode newNode = reverseList2(head.next);

        temp.next = head;
        head.next = null;

        return newNode;
    }


    /**
     * 遍历链表，线存储到栈，然后弹栈
     *
     * @param head
     * @return
     */
    public static ListNode reverseList3(ListNode head) {

        if (head == null || head.next == null)
            return head;

        Stack<ListNode> stack = new Stack();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        //确定头结点
        head = stack.peek();
        ListNode newHead = stack.pop();

        while (!stack.empty()) {
            newHead.next = stack.pop();
            newHead = newHead.next;

        }
        //此处要使最后一个结点的后至结点为null
        newHead.next = null;
        return head;
    }

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);

        ListNode temp = reverseList3(node1);

        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
}
