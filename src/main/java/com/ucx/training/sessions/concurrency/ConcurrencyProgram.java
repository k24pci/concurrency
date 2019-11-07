package com.ucx.training.sessions.concurrency;

import java.util.logging.Logger;

public class ConcurrencyProgram {
    private static final Logger LOGGER = Logger.getLogger(ConcurrencyProgram.class.getName());

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int counter = 0;
            for (int i =0; i<100; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    LOGGER.info(e.getMessage());
                    LOGGER.info("Counter value from thread 1: " + counter);
//                    return;
                }
                counter++;
            }
            LOGGER.info("Counter value from thread 1: " + counter);
        });

        thread.start();
        System.out.println("Thread 1 executed successfully.");

        Thread thread2 = new Thread(() -> {
            int counter = 0;
            for (int i =0; i<1000; i++){
                counter++;
            }
            LOGGER.info("Counter value from thread 2: " + counter);
        });
        thread2.start();
        System.out.println("Thread 2 executed successfully.");

        if (thread.isAlive()){
            thread.join(5000);
            thread.interrupt();
        }


    }
}
