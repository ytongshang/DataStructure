package cradle.rancune.algo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Rancune@126.com on 2020/7/10.
 */
public class ConsumerProducer {

    public static AtomicInteger atomicInteger = new AtomicInteger();
    public volatile boolean flag = true;
    public static final int MAX_COUNT = 10;
    public static final List<Integer> pool = new ArrayList<>();

    public void produce() {
        // 判断，干活，通知
        while (flag) {
            synchronized (pool) {
                //池子满了，生产者停止生产
                while (pool.size() == MAX_COUNT) {
                    try {
                        System.out.println("pool is full, waiting...");
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //干活
                pool.add(atomicInteger.incrementAndGet());
                System.out.println("produce number:" + atomicInteger.get() + "\t" + "current size:" + pool.size());
                //通知
                pool.notifyAll();
            }
        }
    }

    public void consume() {
        while (flag) {
            synchronized (pool) {
                while (pool.size() == 0) {
                    try {
                        System.out.println("pool is empty, wating...");
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //干活
                int temp = pool.get(0);
                pool.remove(0);
                System.out.println("consume number:" + temp + "\t" + "current size:" + pool.size());
                //通知
                pool.notifyAll();
            }
        }
    }

    public void stop() {
        flag = false;
    }

    public static void main(String[] args) {
        ConsumerProducer shareDataV1 = new ConsumerProducer();
        new Thread(shareDataV1::produce, "AAA").start();
        new Thread(shareDataV1::consume, "BBB").start();
        new Thread(shareDataV1::produce, "CCC").start();
        new Thread(shareDataV1::consume, "DDD").start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shareDataV1.stop();
    }
}