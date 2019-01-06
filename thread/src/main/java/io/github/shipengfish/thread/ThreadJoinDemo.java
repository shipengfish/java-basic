package io.github.shipengfish.thread;

/**
 * @author shipengfish
 * @version 1.0
 * @date 2019/1/6
 * @description
 * @since 1.0
 */
public class ThreadJoinDemo implements Runnable {
    @Override
    public void run() {
        System.out.println("join thread run...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadJoinDemo joinDemo = new ThreadJoinDemo();
        Thread thread = new Thread(joinDemo);

        thread.start();


        // 相当于 0ms,也就是说一直会等待，demo 中会等待 3s.
//        thread.join();

        // 等待 10ms 后，不管原执行的线程是否调用结束，都将进行后续任务.
        // thread.join(10);

        System.out.println("hello world");
    }
}
