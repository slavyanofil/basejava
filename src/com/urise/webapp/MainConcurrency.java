package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    static final Integer a = 1000;
    static final Integer b = 1000;
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);

        //Deadlock calling
        send(a, b);
        send(b, a);
    }

    private static void send(Integer a, Integer b) {
        Integer[] accounts = new Integer[2];
        accounts[0] = a;
        accounts[1] = b;
        new Thread(() -> {
            System.out.println(accounts[0]);
            synchronized (accounts[0]) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (accounts[1]) {
                    accounts[0] -= 100;
                    accounts[1] += 100;
                    System.out.println("a = " + a);
                    System.out.println("b = " + b);
                }
            }
        }).start();
    }

    private synchronized void inc() {
        counter++;
    }
}