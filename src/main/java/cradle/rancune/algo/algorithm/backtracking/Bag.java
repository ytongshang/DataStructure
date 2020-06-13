package cradle.rancune.algo.algorithm.backtracking;

/**
 * Created by Rancune@126.com on 2019-06-27.
 */
public class Bag {
    private int max = Integer.MIN_VALUE;
    private int[] bags = {2, 2, 4, 6, 3};
    private int maxCount = 5;
    private int maxWeight = 9;

    public void f(int index, int weight) {
        if (index == maxCount || weight == maxWeight) {
            if (weight > max) {
                max = weight;
            }
            return;
        }
        f(index + 1, weight);
        if ((weight + bags[index]) <= maxWeight) {
            f(index + 1, weight + bags[index]);
        }
    }

    public static void main(String[] args) {
        Bag bag = new Bag();
        bag.f(0, 0);
        System.out.println(bag.max);
    }
}
