package leetcode.leetcode2020;

/**
 * @ClassName : MainSeptember
 * @Author : yq
 * @Date: 2020-08-31
 * @Description :
 */
public class MainSeptember {

    /**
     * 剑指 Offer 18. 删除链表的节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        if (head.val == val) {
            head = head.next;
            return head;
        }

        ListNode node = new ListNode(head.val);
        node = head;

        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
                return head;
            }
            node = node.next;
        }
        return head;

    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     * 递归求解
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode1(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        if (head.val == val) {
            return head.next;
        }

        head.next = deleteNode1(head.next, val);
        return head;
    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     * 双指针法求解
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode2(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = new ListNode(head.val);
        ListNode cur = new ListNode(head.val);
        pre = head;
        cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            }
            pre = pre.next;
            cur = cur.next;
        }
        return head;
    }

    int max = 0;
    int cur = 0;


    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * max 为左子树深度和右子树深度的最大值+1
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {

         if(root == null){
             return 0;
         }
         return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }




    public void DFS(TreeNode treeNode) {
        if (treeNode != null) {
            cur++;
            DFS(treeNode.left);
            DFS(treeNode.right);
        } else {
            max = max > cur ? max : cur;
            cur = 0;
        }
    }


    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     * 空间复杂度 O(1)
     * 时间复杂度 O(n)
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int num = 0;
        for(int i = 0;i<nums.length;i++){
            num = num ^ nums[i];
        }
        //获取位数不相同的数
        int A = 0;
        int B = 0;
        int C = num & (-num);
        for(int i = 0;i<nums.length;i++){
            if((C & nums[i]) == 1){
                A  = A ^ nums[i];
            }else {
                B = B ^ nums[i];
            }
        }
        return new int[]{A,B};
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {

        System.out.println((12) & (-7));
    }
}
