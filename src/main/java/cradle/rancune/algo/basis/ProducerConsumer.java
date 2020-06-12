package cradle.rancune.algo.basis;

import java.util.concurrent.TimeUnit;

public class ProducerConsumer {
    public static class Resources {
        public static final int MAX_SIZE = 10;
        private int res = 0;

        public synchronized void produce() {
            if (res < MAX_SIZE) {
                res ++;
                System.out.println(Thread.currentThread().getName() + " 生产一个资源，总共" + res + "个资源");
                notifyAll();
            } else {
                System.out.println(Thread.currentThread().getName() + "等待消费者消费");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void consume() {
            if (res > 0) {
                res --;
                System.out.println(Thread.currentThread().getName() + " 消耗了一个资 源，总共" + res + "个资源");
                notifyAll();
            } else {
                System.out.println(Thread.currentThread().getName() + "等待生产者生产");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ProduceThread extends Thread {

        public final Resources res;

        public ProduceThread(String name, Resources res) {
            super(name);
            this.res = res;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                res.produce();
            }
        }
    }

    public static class ConsumerThread extends Thread {

        public final Resources res;

        public ConsumerThread(String name, Resources res) {
            super(name);
            this.res = res;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                res.consume();
            }
        }
    }

    public static void main(String[] args) {
        Resources res = new Resources();
        Thread p1 = new ProduceThread("ProduceThread01", res);
        Thread p2 = new ProduceThread("ProduceThread02", res);
        Thread c1 = new ConsumerThread("ConsumerThread01", res);
        Thread c2 = new ConsumerThread("ConsumerThread02", res);
        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
