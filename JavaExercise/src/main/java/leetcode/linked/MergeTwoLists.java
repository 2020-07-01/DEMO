package linked;

/**
 * @author :qiang
 * @date :2019/10/1 下午3:51
 * @description :合并两个链表
 * @other :
 */
public class MergeTwoLists {

    static class ListNode {
        private int val;
        private ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 将两个有序链表合并为一个新的有序链表并返回
     * 新链表是通过拼接给定的两个链表的所有节点组成的
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        /**
         * 进行指针的赋值很消耗内存时间
         */
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        //创建链表头
        ListNode result = new ListNode(0);

        ListNode temp = result;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        while (l1 != null) {
            temp.next = l1;
            l1 = l1.next;
            temp = temp.next;
        }

        while (l2 != null) {
            temp.next = l2;
            l2 = l2.next;
            temp = temp.next;
        }
        return result.next;
    }
}
