package cradle.rancune.algo.sort;

/**
 * Created by Rancune@126.com on 2019-06-15.
 */
public class FindMaxN {

    public static int findMaxN(int[] a, int n) {
        if (a == null) {
            return -1;
        }
        int size = a.length;
        if (n > size) {
            return -1;
        }
        int partition = Sort.partition(a, 0, size - 1);
        while ((partition + 1) != n) {
            if (partition + 1 > n) {
                partition = Sort.partition(a, 0, partition - 1);
            } else {
                partition = Sort.partition(a, partition + 1, size - 1);
            }
        }
        return a[partition];
    }
}
