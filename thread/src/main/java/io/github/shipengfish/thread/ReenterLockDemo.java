package io.github.shipengfish.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shipengfish
 * @version 1.0
 * @date 2019/2/13
 * @description
 * @since 1.0
 */
public class ReenterLockDemo implements Runnable {

    private static int index = 0;

    private ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        ReenterLockDemo d1 = new ReenterLockDemo();
        Thread t1 = new Thread(d1);
        t1.start();

        Thread t2 = new Thread(d1);
        t2.start();

        t1.join();
        t2.join();

        System.out.println(index);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            lock.lock();
            index++;
            lock.unlock();
        }
    }
}

class TimeLock implements Runnable {

    private ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);

        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 获取锁");
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getName() + " 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

}