package com.ucx.training.sessions.concurrency;

import java.util.logging.Logger;

public class ConcurrencyProgram {
    private static final Logger LOGGER = Logger.getLogger(ConcurrencyProgram.class.getName());

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("Current thread: " + Thread.currentThread().getName());
        Thread thread = new Thread(() -> {
            LOGGER.info("Current thread: " + Thread.currentThread().getName());
            int counter = 0;
            for (int i =0; i<1_000_000; i++){
                counter++;
                if (Thread.interrupted()){
                    LOGGER.info("Thread is interrupted. Current counter value is: " + counter);
                    return;
                }
            }
            LOGGER.info("Counter value from thread 1: " + counter);
        });
        thread.setName("Kushtrim's thread");
        thread.start();
        System.out.println("Thread 1 executed successfully.");

        Thread thread2 = new Thread(() -> {
            LOGGER.info("Current thread: " + Thread.currentThread().getName());
            int counter = 0;
            for (int i =0; i<1000; i++){
                counter++;
            }
            if (thread.isAlive()){
                thread.interrupt();
            }

            LOGGER.info("Counter value from thread 2: " + counter);
        });
        thread2.setName("Thread 2");
        thread2.start();
        System.out.println("Thread 2 executed successfully.");




    }
}
