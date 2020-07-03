package cradle.rancune.algo.offer;

import java.util.*;


public class Tree {
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> deque = new Stack<>();
        TreeNode p = node;
        while (!deque.isEmpty() || p != null) {
            while (p != null) {
                System.out.print(p.val + " ");
                deque.push(p);
                p = p.left;
            }
            if (!deque.isEmpty()) {
                p = deque.pop();
                p = p.right;
            }
        }
    }

    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode p = node;
        Stack<TreeNode> deque = new Stack<>();
        while (p != null || !deque.isEmpty()) {
            while (p != null) {
                deque.push(p);
                p = p.left;
            }
            if (!deque.isEmpty()) {
                p = deque.pop();
                System.out.print(p.val + " ");
                p = p.right;
            }
        }
    }

    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> deque = new Stack<>();
        TreeNode lastVisit = null;
        TreeNode p = node;
        while (p != null) {
            deque.push(p);
            p = p.left;
        }
        while (!deque.isEmpty()) {
            p = deque.pop();
            if (p.right == null || p.right == lastVisit) {
                System.out.print(p.val + " ");
                lastVisit = p;
            } else {
                deque.push(p);
                p = p.right;
                while (p != null) {
                    deque.push(p);
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

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "#";
            }
            StringBuilder builder = new StringBuilder();
            preOrder(root, builder);
            return builder.toString();
        }

        private void preOrder(TreeNode node, StringBuilder builder) {
            if (node == null) {
                builder.append("#,");
                return;
            }
            builder.append(node.val);
            builder.append(",");
            preOrder(node.left, builder);
            preOrder(node.right, builder);
        }

        // Decodes your encoded data to tree.
        private int start = 0;

        public TreeNode deserialize(String data) {
            if ("#".equals(data)) {
                return null;
            }
            String[] splits = data.split(",");
            return build(splits);
        }

        private TreeNode build(String[] splits) {
            if ("#".equals(splits[start])) {
                start++;
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(splits[start]));
            start++;
            node.left = build(splits);
            node.right = build(splits);
            return node;
        }
    }

    public boolean isBalanced(TreeNode root) {
        int height = isBalancedInternal(root);
        return height != -1;
    }

    private static int isBalancedInternal(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = isBalancedInternal(node.left);
        if (left == -1) {
            return -1;
        }
        int right = isBalancedInternal(node.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1 : -1;
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

    // 面试题68 - II. 二叉树的最近公共祖先
    // https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
    //  p 和 q 在 root 的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
    //  p=root ，且 q 在 root 的左或右子树中；
    //  q=root ，且 p 在 root 的左或右子树中
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
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
