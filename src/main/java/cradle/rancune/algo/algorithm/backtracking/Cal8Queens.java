package cradle.rancune.algo.algorithm.backtracking;

/**
 * Created by Rancune@126.com on 2019-06-27.
 */
public class Cal8Queens {
    private int[] result = new int[8];

    private void row(int row) {
        if (row == 8) {
            // 如果 0-7没问题了，那么就是放完整了
            print(result);
            return;
        }
        for (int column = 0; column < 8; column++) {
            // 对于第row行的，有8种放置方式
            if (isOk(row, column)) {
                result[row] = column;
                // 继续放下一行
                row(row + 1);
            }
        }
    }

    private boolean isOk(int row, int column) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            // 前面有同一列的 直接return false
            if (result[i] == column) return false;
            // 前一行所在列在左对象线上
            if (leftUp >= 0 && result[i] == leftUp) return false;
            if (rightUp < 8 && result[i] == rightUp) return false;
            leftUp--;
            rightUp++;
        }
        return true;
    }

    private void print(int[] result) {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Cal8Queens().row(0);
    }
}
