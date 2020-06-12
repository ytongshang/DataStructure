package cradle.rancune.algo.leetcode.old;

/**
 * Created by Rancune@126.com on 2019/6/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LeetCode {
    static class Node {
        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        Node append(int value) {
            Node newNode = new Node(value, null);
            this.next = newNode;
            return newNode;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            Node first = this;

            while (first != null) {
                if (first != this) {
                    builder.append(",");
                }
                builder.append(first.value);
                first = first.next;
            }
            builder.append("]");
            return builder.toString();
        }
    }

    /**
     * LeetCode 19
     * https://www.cnblogs.com/grandyang/p/4606920.html
     * <p>
     * Given a linked list, remove the nth node from the end of list and return its head.
     * For example,
     * Given linked list: 1->2->3->4->5, and n = 2.
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * Note:
     * Given n will always be valid.
     * Try to do this in one pass.
     */
    public static Node removeNthNode(Node head, int n) {
        Node p = head;
        Node q = head;
        for (int i = 0; i < n; i++) {
            p = p.next;
        }
        if (p.next == null) {
            return head.next;
        }
        while (p.next != null) {
            p = p.next;
            q = q.next;
        }
        q.next = q.next.next;
        return head;
    }

    /**
     * LeetCode 21
     * https://www.cnblogs.com/grandyang/p/4086297.html
     * <p>
     * Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     */
    public static Node mergeTwoSortedList(Node a, Node b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        Node result = null;
        Node last = null;
        while (a != null && b != null) {
            if (a.value <= b.value) {
                if (result == null) {
                    result = a;
                    last = result;
                } else {
                    last.next = a;
                    last = last.next;
                }
                a = a.next;
            } else {
                if (result == null) {
                    result = b;
                    last = result;
                } else {
                    last.next = b;
                    last = last.next;
                }
                b = b.next;
            }
        }
        if (a != null) {
            last.next = a;
        }
        if (b != null) {
            last.next = b;
        }
        return result;
    }

    /**
     * LeetCode 141
     * https://www.cnblogs.com/grandyang/p/4137187.html
     * <p>
     * Given a linked list, determine if it has a cycle in it.
     * To represent a cycle in the given linked list,
     * we use an integer pos which represents the position (0-indexed) in the linked list
     * where tail connects to.
     * If pos is -1, then there is no cycle in the linked list.
     */
    public static boolean circleInList(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * LeetCode 206
     * https://www.cnblogs.com/grandyang/p/4478820.html
     * <p>
     * Reverse a singly linked list.
     * Example:
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     * Follow up:
     * A linked list can be reversed either iteratively or recursively. Could you implement both?
     * <p>
     * 实际就是不断从老list中取出来放到新list的头部
     */
    public static Node reverseListIterratively(Node list) {
        if (list == null) {
            return null;
        }
        Node current = list;
        Node result = null;
        while (current != null) {
            Node next = current.next;
            current.next = result;
            result = current;
            current = next;
        }
        return result;
    }

    public static Node reverseListRecursively(Node list) {
        if (list == null || list.next == null) {
            return list;
        }
        Node reverse = reverseListRecursively(list.next);
        list.next.next = list;
        list.next = null;
        return reverse;
    }

    /**
     * LeetCode 876
     * https://www.cnblogs.com/grandyang/p/10817408.html
     * Given a non-empty, singly linked list with head node head,
     * return a middle node of linked list.
     * If there are two middle nodes, return the second middle node.
     */
    public static Node middle(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Node n0 = new Node(0, null);
        n0.append(1).append(2).append(3);
        System.out.println(n0);
        Node n0reverse = reverseListIterratively(n0);
        System.out.println(n0reverse);

        Node n1 = new Node(0, null);
        n1.append(1).append(2).append(3);
        System.out.println(n1);
        Node n1reverse = reverseListRecursively(n1);
        System.out.println(n1reverse);
    }
}
