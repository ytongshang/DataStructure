package cradle.rancune.algo.offer;

import java.util.*;


public class Tree {
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode p = node;
        while (!deque.isEmpty() || p != null) {
            while (p != null) {
                System.out.print(p.val + " ");
                deque.addLast(p);
                p = p.left;
            }
            if (!deque.isEmpty()) {
                p = deque.removeLast();
                p = p.right;
            }
        }
    }

    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode p = node;
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (p != null || !deque.isEmpty()) {
            while (p != null) {
                deque.addLast(p);
                p = p.left;
            }
            if (!deque.isEmpty()) {
                p = deque.removeLast();
                System.out.print(p.val + " ");
                p = p.right;
            }
        }
    }

    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode lastVisit = null;
        TreeNode p = node;
        while (p != null) {
            deque.addLast(p);
            p = p.left;
        }
        while (!deque.isEmpty()) {
            p = deque.removeLast();
            if (p.right == null || p.right == lastVisit) {
                System.out.print(p.val + " ");
                lastVisit = p;
            } else {
                deque.addLast(p);
                p = p.right;
                while (p != null) {
                    deque.addLast(p);
                    p = p.left;
                }
            }
        }
    }

    public static void level(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            System.out.print(p.val + " ");
            if (p.left != null) {
                queue.add(p.left);
            }
            if (p.right != null) {
                queue.add(p.right);

            }
        }
    }

    public static void zPrint(TreeNode node) {
        if (node == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        boolean left2Right = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode n = null;
            for (int i = 0; i < size; i++) {
                if (left2Right) {
                    n = queue.removeFirst();
                    if (n.left != null) {
                        queue.addLast(n.left);
                    }
                    if (n.right != null) {
                        queue.addLast(n.right);
                    }
                } else {
                    n = queue.removeLast();
                    if (n.right != null) {
                        queue.addFirst(n.right);
                    }
                    if (n.left != null) {
                        queue.addFirst(n.left);
                    }
                }
                System.out.print(n.val + " ");
            }
            left2Right = !left2Right;
        }
    }

    // 面试题7 重建二叉树
    // https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        if (preorder.length != inorder.length) {
            return null;
        }
        return buildInternal(preorder, inorder, 0, preorder.length - 1,
                0, inorder.length - 1);
    }

    private static TreeNode buildInternal(int[] preorder, int[] inorder,
                                          int preStart, int preStop,
                                          int inStart, int inStop) {
        if (preStart > preStop) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        if (preStart == preStop) {
            return root;
        }
        int rootIndex = -1;
        for (int i = inStart; i <= inStop; i++) {
            if (inorder[i] == preorder[preStart]) {
                rootIndex = i;
                break;
            }
        }
        int size = rootIndex - inStart;
        root.left = buildInternal(preorder, inorder,
                preStart + 1, preStart + size,
                inStart, rootIndex - 1);
        root.right = buildInternal(preorder, inorder,
                preStart + size + 1, preStop,
                rootIndex + 1, inStop);
        return root;
    }

    // 面试题26 树的子结构
    // https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
    public boolean isSubStructure(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return false;
        }
        return isSame(a, b) || isSubStructure(a.left, b) || isSubStructure(a.right, b);
    }

    public boolean isSame(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isSame(a.left, b.left) && isSame(a.right, b.right);
    }

    // 面试题27 二叉树镜像
    // https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    // 面试题27 对称二叉树
    // https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricInternal(root.left, root.right);
    }

    private boolean isSymmetricInternal(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.val == b.val && isSymmetricInternal(a.left, b.right)
                && isSymmetricInternal(a.right, b.left);
    }

    // 面试题32_1 从上到下打印二叉树
    // https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        if (root.left == null && root.right == null) {
            return new int[]{root.val};
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(root);
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            result[i] = list.get(i);
        }
        return result;
    }

    // 面试题32_2 从上到下打印二叉树
    // https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            result.add(list);
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node = null;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    // 面试题32_3 从上到下打印二叉树
    // https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            result.add(list);
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode n = null;
        boolean left2Right = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                n = queue.removeFirst();
                if (left2Right) {
                   list.addLast(n.val);
                } else {
                    list.addFirst(n.val);
                }
                if (n.left != null) {
                    queue.addLast(n.left);
                }
                if (n.right != null) {
                    queue.addLast(n.right);
                }
            }
            result.add(list);
            left2Right = !left2Right;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);

        System.out.print("前序:");
        preOrder(node);
        System.out.println();

        System.out.print("中序:");
        inOrder(node);
        System.out.println();

        System.out.print("后序:");
        postOrder(node);
        System.out.println();

        System.out.print("层序:");
        level(node);
        System.out.println();

        System.out.print("Z:");
        zPrint(node);
        System.out.println();
    }
}
