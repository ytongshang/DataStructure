package cradle.rancune.algo.offer;

import java.util.Stack;

public class StackRelated {
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

}
