package com.cc.learn.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
* 描述：     在16个线程下使用LongAdder
 * 结论：如果我们的场景仅仅是需要用到加和减操作的话，
 * 那么可以直接使用更高效的 LongAdder，但如果我们需要利用 CAS 比如 compareAndSet 等操作的话，就需要使用 AtomicLong 来完成。
*/
public class LongAdderDemo {
 
   public static void main(String[] args) throws InterruptedException {
       LongAdder counter = new LongAdder();
       ExecutorService service = Executors.newFixedThreadPool(16);
       for (int i = 0; i < 100; i++) {
           service.submit(new Task(counter));
       }
 
       Thread.sleep(2000);
       System.out.println(counter.sum());
       service.shutdown();
   }
   static class Task implements Runnable {
 
       private final LongAdder counter;
 
       public Task(LongAdder counter) {
           this.counter = counter;
       }
 
       @Override
       public void run() {
           counter.increment();
       }
   }
}