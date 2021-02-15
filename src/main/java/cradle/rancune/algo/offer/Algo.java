package cradle.rancune.algo.offer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Rancune@126.com on 2020/7/14.
 */
public class Algo {
    // 剑指 Offer 19. 正则表达式匹配
    // https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        // 第一个字符相同，或者pattern为. 可以满足替换成任意字符
        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // 要么表示0个，要么表示多个
            // 表示0个，也就是用后面的比较
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    // 剑指 Offer 63. 股票的最大利润
    // https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/
    public int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }
        int length = prices.length;
        if (length <= 1) {
            return 0;
        }
        int maxProfit = prices[1] - prices[0];
        int min = Math.min(prices[0], prices[1]);
        for (int i = 2; i < length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        // 小于0不买就好了
        return Math.max(maxProfit, 0);
    }

    // 剑指 Offer 60. n个骰子的点数
    // 1 <= n <= 11
    // https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/
    public double[] twoSum(int n) {
        int[] points = new int[6 * n + 1];
        for (int i = 1; i <= 6; i++) {
            points[i] = 1;
        }
        // 一次一个骰子
        for (int i = 2; i <= n; i++) {
            // i 个 最小值 i, 最大值 6* i
            for (int p = 6 * i; p >= i; p--) {
                points[p] = 0;
                for (int k = 1; k <= 6; k++) {
                    if ((p - k) < i - 1) {
                        // 本轮k, 总分p, 上一轮p -k 不可能比i-1小
                        break;
                    }
                    // 上一轮为p -k 的次数加上本轮的k可以到达p, 本身也有p
                    points[p] = points[p] + points[p - k];
                }
            }
        }
        double[] rate = new double[6 * n - n + 1];
        double count = Math.pow(6, n);
        int k = 0;
        for (int i = n; i <= 6 * n; i++) {
            rate[k++] = points[i] * 1.0 / count;
        }
        return rate;
    }

    // 剑指 Offer 12. 矩阵中的路径
    // https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
    public static class Exist {
        public boolean exist(char[][] board, String word) {
            int row = board.length;
            int column = board[0].length;
            char[] words = word.toCharArray();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (existInternal(board, words, 0, 0, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean existInternal(char[][] board, char[] word, int i, int j, int k) {
            int row = board.length;
            int column = board[0].length;
            if (i < 0 || i >= row || j < 0 || j >= column || board[i][j] != word[k]) {
                return false;
            }
            if (k == word.length - 1) {
                return true;
            }
            char temp = board[i][j];
            board[i][j] = '\0';
            boolean result = existInternal(board, word, i + 1, j, k + 1)
                    || existInternal(board, word, i - 1, j, k + 1)
                    || existInternal(board, word, i, j + 1, k + 1)
                    || existInternal(board, word, i, j - 1, k + 1);
            board[i][j] = temp;
            return result;
        }
    }

    // 剑指 Offer 13. 机器人的运动范围
    // https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
    public static class MovingCount {
        private int result = 0;
        private int m;
        private int n;
        private boolean[][] visited;

        public int movingCount(int m, int n, int k) {
            this.visited = new boolean[m][n];
            this.m = m;
            this.n = n;
            movingCountInternal(0, 0, k);
            return result;
        }

        public void movingCountInternal(int i, int j, int k) {
            if (i < 0 || i >= m || j < 0 || j >= n) {
                return;
            }
            if (visited[i][j]) {
                return;
            }
            if (digitSum(i) + digitSum(j) > k) {
                return;
            }
            visited[i][j] = true;
            result++;
            movingCountInternal(i + 1, j, k);
            movingCountInternal(i, j + 1, k);
        }

        private int digitSum(int n) {
            int sum = 0;
            while (n > 0) {
                sum += n % 10;
                n = n / 10;
            }
            return sum;
        }
    }

    // 剑指 Offer 29. 顺时针打印矩阵
    // 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int[] result = new int[matrix.length * matrix[0].length];
        int k = 0;
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                result[k++] = matrix[top][i];
            }
            if ((++top) > bottom) break;

            for (int i = top; i <= bottom; i++) {
                result[k++] = matrix[i][right];
            }
            if (--right < left) break;

            for (int i = right; i >= left; i--) {
                result[k++] = matrix[bottom][i];
            }
            if (--bottom < top) break;
            for (int i = bottom; i >= top; i--) {
                result[k++] = matrix[i][left];
            }
            if (++left > right) break;
        }
        return result;
    }

    public static class Permutation {
        private char[] chars;
        private final List<String> result = new LinkedList<>();

        public String[] permutation(String s) {
            if (s == null) {
                return new String[0];
            }
            if (s.length() == 1) {
                return new String[]{s};
            }
            chars = s.toCharArray();
            internal(0);
            return result.toArray(new String[result.size()]);
        }

        // 第index轮固定第index位的值
        private void internal(int index) {
            if (index == chars.length - 1) {
                result.add(String.valueOf(chars));
                return;
            }
            Set<Character> set = new HashSet<>();
            // 前面的都固定了，只能从index开始的每一位
            for (int i = index; i < chars.length; i++) {
                // 本轮包含，说明前面有字母与这个相同，那么这两个是一样的，就不用再做了
                if (set.contains(chars[i])) {
                    continue;
                }
                set.add(chars[i]);
                // 做选择，index使用i位置的值
                swap(chars, index, i);
                internal(index + 1);
                // 撤销选择
                swap(chars, index, i);
            }
        }

        private static void swap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }

    // 剑指 Offer 46. 把数字翻译成字符串
    // https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
    // x0x1x2...xn-2xn-1xn
    // 如果xn-1xn能翻译那么 f(n-2) + f(n-1), xn-1xn放在一块，或者不放在一块
    // 如果xn-1xn不能翻译，f(n-1)
    public int translateNum(int num) {
        if (num < 9) {
            return 1;
        }
        int a = 1;
        int b = 1;
        String str = String.valueOf(num);
        for (int i = 2; i <= str.length(); i++) {
            String prefix = str.substring(i - 2, i);
            int c;
            if (prefix.compareTo("10") >= 0 && prefix.compareTo("25") <= 0) {
                // 两位可以放一起翻译
                c = a + b;
            } else {
                c = b;
            }
            a = b;
            b = c;
        }
        return b;
    }

    public int maxValue(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        return 0;
    }
}
