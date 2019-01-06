package io.github.shipengfish.thread;

/**
 * @author shipengfish
 * @version 1.0
 * @date 2019/1/6
 * @description
 * @since 1.0
 */
public class SleepWaitDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());

        t1.start();
        t2.start();
    }

}

class T1 implements Runnable {

    @Override
    public void run() {
        synchronized (SleepWaitDemo.class) {
            System.out.println("t1 is waiting");
            try {
                SleepWaitDemo.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 is going on");
            System.out.println("t1 is over");
        }
    }
}

class T2 implements Runnable {

    @Override
    public void run() {
        synchronized (SleepWaitDemo.class) {
            System.out.println("t2 is sleeping");
            SleepWaitDemo.class.notify();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 is going on");
            System.out.println("t2 is over");
        }
    }
}

