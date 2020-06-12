package cradle.rancune.algo.tree;

import java.util.*;

/**
 * Created by Rancune@126.com on 2019-06-20.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;
    private int size = 0;

    private static class Node<T> {
        Node<T> left;
        Node<T> right;
        T value;

        Node(T value) {
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public void insert(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        size++;
        if (root == null) {
            root = new Node<>(t);
            return;
        }
        Node<T> p = root;
        while (p != null) {
            if (t.compareTo(p.value) < 0) {
                if (p.left == null) {
                    p.left = new Node<>(t);
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new Node<>(t);
                    return;
                }
                p = p.right;
            }
        }
    }

    public Node find(T t) {
        Node<T> p = root;
        while (p != null) {
            if (p.value.compareTo(t) == 0) {
                return p;
            } else if (t.compareTo(p.value) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public void delete(T t) {
        if (root == null) {
            return;
        }
        Node<T> p = root;
        Node<T> pp = null;
        while (p != null && t.compareTo(p.value) != 0) {
            pp = p;

            if (t.compareTo(p.value) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            // 没有找到想要删除的节点
            return;
        }
        if (p.left != null && p.right != null) {
            // 找到右子树的最小节点/左子数最大节点
            Node<T> minP = p.right;
            Node<T> minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            // 将换右子树的最小节点与p的值交换，
            // 相当于删除了p节点的值，只要后面删掉minP就可以了
            p.value = minP.value;
            p = minP;
            pp = minPP;
        }
        // 这里，p变为了叶子节点或者只有一个节点
        Node<T> child;
        if (p.left != null) {
            child = p.left;
        } else {
            child = p.right;
        }

        if (pp == null) {
            root = null;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }

    public List<T> preOrder() {
        return _preOrder(root);
    }

    private List<T> _preOrder(Node<T> node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        list.add(node.value);
        if (node.left != null) {
            list.addAll(_preOrder(node.left));
        }
        if (node.right != null) {
            list.addAll(_preOrder(node.right));
        }
        return list;
    }

    public List<T> inOrder() {
        return _inOrder(root);
    }

    private List<T> _inOrder(Node<T> node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        if (node.left != null) {
            list.addAll(_inOrder(node.left));
        }
        list.add(node.value);
        if (node.right != null) {
            list.addAll(_inOrder(node.right));
        }
        return list;
    }

    public List<T> postOrder() {
        return _postOrder(root);
    }

    private List<T> _postOrder(Node<T> node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        if (node.left != null) {
            list.addAll(_postOrder(node.left));
        }
        if (node.right != null) {
            list.addAll(_postOrder(node.right));
        }
        list.add(node.value);
        return list;
    }

    public List<T> levelOrder() {
        return _levelOrder(root);
    }

    private List<T> _levelOrder(Node<T> node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(node);
        Node<T> p;
        while (!queue.isEmpty()) {
            p = queue.poll();
            if (p.left != null) {
                queue.offer(p.left);
            }
            if (p.right != null) {
                queue.offer(p.right);
            }
            list.add(p.value);
        }
        return list;
    }

    public static void main() {
        Node<Integer> node = new Node<>(1);
        node.left = new Node<>(2);
        node.right = new Node<>(3);
        node.left.left = new Node<>(4);
        node.left.right = new Node<>(5);
        node.right.left = new Node<>(6);
        node.right.right = new Node<>(7);

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.root = node;
    }
}
