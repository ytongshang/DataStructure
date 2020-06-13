package cradle.rancune.algo.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Rancune@126.com on 2020/4/9.
 * https://blog.csdn.net/zhangxiangdavaid/article/details/37115355
 */
public class Tree {

    private static void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.val);
            System.out.print(" ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private static void preOrder2(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node;
        while (!stack.empty() || p != null) {
            while (p != null) {
                print(p);
                stack.push(p);
                p = p.left;
            }
            if (!stack.empty()) {
                p = stack.pop();
                p = p.right;
            }
        }
    }

    private static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        print(node);
        inOrder(node.right);
    }

    private static void inOrder2(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node;
        while (p != null || !stack.empty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
                print(p);
                p = p.right;
            }
        }
    }

    private static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        print(node);
    }

    private static void postOrder2(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode p = node;
        TreeNode lastVisit = null;
        Stack<TreeNode> stack = new Stack<>();
        //先把pCur移动到左子树最下边
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        while (!stack.empty()) {
            p = stack.pop();
            //一个根节点被访问的前提是：无右子树或右子树已被访问过
            if (p.right == null || p.right == lastVisit) {
                print(p);
                lastVisit = p;
            } else {
                //根节点再次入栈
                stack.push(p);
                //进入右子树，且可肯定右子树一定不为空
                p = p.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
            }
        }
    }

    private static void level(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            print(n);
            if (n.left != null) {
                queue.offer(n.left);
            }
            if (n.right != null) {
                queue.offer(n.right);
            }
        }
    }

    static void print(TreeNode node) {
        if (node != null) {
            System.out.print(node.val);
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);

//        level(node);
//        System.out.println();
//
//        preOrder(node);
//        System.out.println();
//        preOrder2(node);
//        System.out.println();
//
//        inOrder(node);
//        System.out.println();
//        inOrder2(node);
//        System.out.println();
//
//        postOrder(node);
//        System.out.println();
//        postOrder2(node);
//        System.out.println();

        inOrder(node);

    }

}
