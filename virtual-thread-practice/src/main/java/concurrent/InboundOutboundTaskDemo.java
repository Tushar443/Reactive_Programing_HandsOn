package concurrent;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {

    private static final int MAX_PLATFORM = 10;
    private static final int MAX_VIRTUAL = 10;

    public static void main(String[] args) throws InterruptedException {
//        platformThreadDemo1();
//        platformThreadDemo2();
//        platformThreadDemo3();
        virtualThread();
    }


    /**
     * create a platform thread using Thread
     */
    private static void platformThreadDemo1(){
        for(int i = 0 ; i<MAX_PLATFORM;i++){
            int finalI = i;
            Thread thread = new Thread(()-> Task.ioIntensive(finalI));
            thread.start();
        }
    }

    /**
     * create a platform thread using Thread.Builder
     */
    private static void platformThreadDemo2(){
        Thread.Builder.OfPlatform builder = Thread.ofPlatform().name("THUNDER ", 1);
        for(int i = 0 ; i<MAX_PLATFORM;i++){
            int finalI = i;
            Thread thread = builder.unstarted(()-> Task.ioIntensive(finalI));
            thread.start();
        }
    }

    /**
     * create a daemon thread using Thread.Builder
     */
    private static void platformThreadDemo3() throws InterruptedException {
        // adding because main thread not waiting to complete their task and exit directly
        CountDownLatch countDownLatch = new CountDownLatch(MAX_PLATFORM);
        Thread.Builder.OfPlatform builder = Thread.ofPlatform().daemon().name("demon", 1);
        for(int i = 0 ; i<MAX_PLATFORM;i++){
            int finalI = i;
            Thread thread =builder.unstarted(()-> {
                Task.ioIntensive(finalI);
                countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
    }

    /**
     * create a virtual thread using Thread.Builder
     * - virtual thread are daemon thread by default
     * - virtual thread do not have any default name
     */
    private static void virtualThread() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(MAX_VIRTUAL);
        Thread.Builder.OfVirtual builder = Thread.ofVirtual().name("virtual ", 1);
        for(int i = 0 ; i<MAX_VIRTUAL;i++){
            int finalI = i;
            Thread thread = builder.unstarted(()->{
             Task.ioIntensive(finalI);
             countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
    }
}
