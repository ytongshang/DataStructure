package cradle.rancune.algo.offer;

import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree {
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }
    }

    public static Node find(Node root, int val) {
        if (root == null) {
            return null;
        }
        while (root != null) {
            if (root.val == val) {
                return root;
            } else if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }

    public static void insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return;
        }
        Node p = root;
        while (p != null) {
            if (val < p.val) {
                if (p.left == null) {
                    p.left = new Node(val);
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new Node(val);
                    return;
                }
                p = p.right;
            }
        }
    }

    // 二叉搜索树的 删除
    // https://time.geekbang.org/column/article/68334
    public static Node delete(Node root, int val) {
        if (root == null) {
            return null;
        }
        Node pp = null;
        Node p = root;
        while (p != null && p.val != val) {
            if (p.val < val) {
                p = p.right;
            } else {
                p = p.left;
            }
            pp = p;
        }
        if (p == null) {
            // 没找到
            return root;
        }
        if (p.left != null && p.right != null) {
            // 找到左子树最大的/右子树最小的
            Node maxP = p.left;
            Node maxPP = p;
            while (maxP.right != null) {
                maxPP = maxP;
                maxP = maxP.right;
            }
            // 找到了左子树的最大值
            // 替换要被删的点的值为左子树最大值
            p.val = maxP.val;
            // 要被删的点变为了左子树的最大值的那个点
            p = maxP;
            pp = maxPP;
        }
        Node child;
        // 这里只会有一个成立
        // 左子树的最大值，那么p.right一定为Null
        // 右子树的最小值，那么p.left一定为null
        if (p.left != null) {
            child = p.left;
        } else {
            child = p.right;
        }
        if (pp == null) {
            // root
            return null;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
        return root;
    }

    // 二叉查找树的前驱后继
    // 二叉查找树中序是有序的，所以前驱就是其中序的前一个节点，后驱就是中序的后一个节点
    public static Node preMax(Node node) {
        if (node == null) {
            return null;
        }
        // 若一个节点有左子树，那么该节点的前驱节点是其左子树中val值最大的节点（也就是左子树中所谓的rightMostNode）
        // 若一个节点没有左子树，那么判断该节点和其父节点的关系
        // 2.1 若该节点是其父节点的右边孩子，那么该节点的前驱结点即为其父节点。
        // 2.2 若该节点是其父节点的左边孩子，那么需要沿着其父亲节点一直向树的顶端寻找，直到找到一个节点P，P节点是其父节点Q的右边孩子，那么Q就是该节点的后继节点
        if (node.left != null) {
            Node leftMax = node.left;
            while (leftMax.right != null) {
                leftMax = leftMax.right;
            }
            return leftMax;
        }
        if (node.parent.right == node) {
            return node.parent;
        }
        Node p = node.parent;
        while (p != null && p.parent.right != p) {
            p = p.parent;
        }
        return p.parent;
    }

    public static Node postMax(Node node) {
        if (node == null) {
            return null;
        }
        // 若一个节点有右子树，那么该节点的后继节点是其右子树中val值最小的节点（也就是右子树中所谓的leftMostNode）
        // 若一个节点没有右子树，那么判断该节点和其父节点的关系
        // 2.1 若该节点是其父节点的左边孩子，那么该节点的后继结点即为其父节点
        // 2.2 若该节点是其父节点的右边孩子，那么需要沿着其父亲节点一直向树的顶端寻找，直到找到一个节点P，P节点是其父节点Q的左边孩子（可参考例子2的前驱结点是1），那么Q就是该节点的后继节点
        if (node.right != null) {
            Node rightMin = node.right;
            while (rightMin.left != null) {
                rightMin = rightMin.left;
            }
            return rightMin;
        }
        if (node.parent.left == node) {
            return node.parent;
        }
        Node p = node.parent;
        while (p != null && p.parent.left != p) {
            p = p.parent;
        }
        return p.parent;
    }

    // 面试题33 二叉搜索树的后序遍历序列
    // https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
    // 左，右，root
    public static boolean verifyPostorder(int[] postorder) {
        if (postorder == null) {
            return false;
        }
        if (postorder.length == 1) {
            return true;
        }
        return verifyPostOrderInternal(postorder, 0, postorder.length - 1);
    }

    // 采用递归的写法，首先确定root
    // 找到第一个比root大的m，那么就是右子树的起点
    // [start, m-1] 左子数， [m, end-1] 右子树
    // 需要满足，左子树的都比root小，右子数的都比root大
    public static boolean verifyPostOrderInternal(int[] postOrder, int start, int end) {
        if (start >= end) {
            // 只有一个节点，肯定是的
            return true;
        }
        // root是最后一个节点
        int root = postOrder[end];
        int i = start;
        while (postOrder[i] < root) {
            i++;
        }
        // left只到i -1;
        int left = i - 1;
        while (postOrder[i] > root) {
            i++;
        }
        return i == end && verifyPostOrderInternal(postOrder, start, left)
                && verifyPostOrderInternal(postOrder, left + 1, end - 1);
    }

    // 面试题34 二叉树中和为某一值的路径
    // https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
    // 回溯算法
    public static class Path2Sum {
        private List<List<Integer>> result = new LinkedList<>();
        private LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            if (root == null) {
                return result;
            }
            pathSumInternal(root, sum);
            return result;
        }

        public void pathSumInternal(TreeNode root, int sum) {
            if (root == null) {
                // 到达了叶子节点
                return;
            }
            path.add(root.val);
            sum = sum - root.val;
            if (sum == 0 && root.left == null && root.right == null) {
                // 是root到叶子的路径
                result.add(new LinkedList<>(path));
            }
            pathSumInternal(root.left, sum);
            pathSumInternal(root.right, sum);
            path.removeLast();
        }
    }

    public static class Tree2DoublyList {
        private Node pre;
        private Node head;

        public Node treeToDoublyList(Node root) {
            if (root == null) {
                return null;
            }
            treeToDoublyListInternal(root);
            head.left = pre;
            pre.right = head;
            return head;
        }

        private void treeToDoublyListInternal(Node node) {
            if (node == null) {
                return;
            }
            treeToDoublyListInternal(node.left);
            if (pre == null) {
                head = node;
            } else {
                pre.right = node;
            }
            node.left = pre;
            pre = node;
            treeToDoublyListInternal(node.right);
        }
    }
}
