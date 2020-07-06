package cradle.rancune.algo.offer;

import java.util.*;

public class StackRelated {
    // 剑指 Offer 09. 用两个栈实现队列
    // https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
    class CQueue {
        private final Stack<Integer> stackA = new Stack<>();
        private final Stack<Integer> stackB = new Stack<>();

        public CQueue() {
        }

        public void appendTail(int value) {
            stackA.push(value);
        }

        public int deleteHead() {
            if (stackB.isEmpty() && !stackA.isEmpty()) {
                while (!stackA.isEmpty()) {
                    stackB.push(stackA.pop());
                }
            }
            if (!stackB.isEmpty()) {
                return stackB.pop();
            }
            return -1;
        }
    }

    // 面试题30. 包含min函数的栈
    // https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
    class MinStack {

        private final Stack<Integer> stackA = new Stack<>();
        private final Stack<Integer> stackB = new Stack<>();

        /**
         * initialize your data structure here.
         */
        public MinStack() {
        }

        public void push(int x) {
            stackA.push(x);
            // ！！！！！！！！这里一定要判断=号
            if (stackB.isEmpty() || x <= stackB.peek()) {
                stackB.push(x);
            }
        }

        public void pop() {
            int value = stackA.pop();
            if (value == stackB.peek()) {
                stackB.pop();
            }
        }

        public int top() {
            return stackA.peek();
        }

        public int min() {
            return stackB.peek();
        }
    }

    // 面试题31. 栈的压入、弹出序列
    // https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
    // 模拟入栈操作，如日不同要继续入栈，否则需要pop出来
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i : pushed) {
            stack.push(i);
            // 这里是while，因为移除一个，后面也可能相同了
            // 必须要先判断stack.isEmpty
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }

    // 面试题59 - I. 滑动窗口的最大值
    // https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
    // https://www.cnblogs.com/rosending/p/5722541.html
    // 队列中存的是index
    // 如果新来的值比队列尾部的数小，那就追加到后面，因为它可能在前面的最大值划出窗口后成为最大值
    // 如果新来的值比尾部的大，那就删掉尾部（因为有更大的在后面，所以它不会成为最大值，划出也是它先划出，不影响最大值），再追加到后面，循环下去直到小于
    // 如果追加的值比的索引跟队列头部的值的索引超过窗口大小，那就删掉头部的值
    // 其实这样每次队列的头都是最大的那个
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k < 1 || nums.length < k) {
            return new int[0];
        }
        int length = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (!deque.isEmpty() && (i - deque.peekFirst()) == k) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            if (i >= k - 1) {
                list.add(nums[deque.peekFirst()]);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
