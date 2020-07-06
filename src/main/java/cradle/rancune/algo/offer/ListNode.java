package cradle.rancune.algo.offer;

/**
 * Created by Rancune@126.com on 2020/7/4.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(this.val);
        ListNode p = next;
        while (p != null) {
            builder.append(",");
            builder.append(p.val);
            p = p.next;
        }
        builder.append("}");
        return builder.toString();
    }
}
