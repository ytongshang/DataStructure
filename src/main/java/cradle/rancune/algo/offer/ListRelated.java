package cradle.rancune.algo.offer;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Rancune@126.com on 2020/7/4.
 */
public class ListRelated {

    // 剑指 Offer 06. 从尾到头打印链表
    // https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
    public static int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        if (head.next == null) {
            return new int[]{head.val};
        }
        Stack<Integer> stack = new Stack<>();
        ListNode p = head;
        while (p != null) {
            stack.push(p.val);
            p = p.next;
        }
        int[] result = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }
        return result;
    }

    // 剑指 Offer 18. 删除链表的节点
    // https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
    public static ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode before = dummy;
        ListNode p = head;
        while (p != null && p.val != val) {
            before = p;
            p = p.next;
        }
        if (p != null) {
            before.next = p.next;
        }
        return dummy.next;
    }

    // 剑指 Offer 22. 链表中倒数第k个节点
    // https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k <= 0) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i <= k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // 剑指 Offer 24. 反转链表
    // https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
    public static ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode head = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = head;
            head = node;
            node = next;
        }
        return head;
    }

    // 剑指 Offer 25. 合并两个排序的链表
    // https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                tail.next = p1;
                tail = tail.next;
                p1 = p1.next;
            } else {
                tail.next = p2;
                tail = tail.next;
                p2 = p2.next;
            }
        }
        if (p1 != null) {
            tail.next = p1;
        }
        if (p2 != null) {
            tail.next = p2;
        }
        return dummy.next;
    }

    static class Node {

        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 剑指 Offer 35. 复杂链表的复制
    // https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        while (p != null) {
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while (p != null) {
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

    // 剑指 Offer 52. 两个链表的第一个公共节点
    // https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 != null ? p1.next : headB;
            p2 = p2 != null ? p2.next : headA;
        }
        return p1;
    }


    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        System.out.println(node);
        System.out.println(reverse(node));
    }
}
