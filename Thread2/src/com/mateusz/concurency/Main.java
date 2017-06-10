package com.mateusz.concurency;

public class Main {

    public static void main(String[] args) {
        //Example without synchronization
//        Countdown countdown = new Countdown();
//
//        CountdownUnSynchronizedThread thread1 = new CountdownUnSynchronizedThread(countdown);
//        thread1.setName("Thread1");
//
//        CountdownUnSynchronizedThread thread2 = new CountdownUnSynchronizedThread(countdown);
//        thread2.setName("Thread2");
//
//        thread1.start();
//        thread2.start();
//
//        System.out.println("------");
        //Example with synchronization

        Countdown countdown = new Countdown();

        CountdownSynchronizedThread sThread1 = new CountdownSynchronizedThread(countdown);
        sThread1.setName("Thread1");
        CountdownSynchronizedThread sThread2 = new CountdownSynchronizedThread(countdown);
        sThread2.setName("Thread2");
        sThread1.start();
        sThread2.start();



    }
}

class Countdown {
    //Threads use same variable stored on heap
    private Integer i = 10;

    public void doUnSynchronizedCountdown() {
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread1":
                color = ThreadColour.ANSI_CYAN;
                break;
            case "Thread2":
                color = ThreadColour.ANSI_PURPLE;
                break;
            default:
                color = ThreadColour.ANSI_GREEN;
                break;
        }

        for (i = 10; i > 0; i--) {
            System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
        }
    }

    public void doSynchronizedCountdown() {
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread1":
                color = ThreadColour.ANSI_CYAN;
                break;
            case "Thread2":
                color = ThreadColour.ANSI_PURPLE;
                break;
            default:
                color = ThreadColour.ANSI_GREEN;
                break;
        }
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
        }
    }
}

class CountdownUnSynchronizedThread extends Thread {
    private Countdown threadCountdown;

    public CountdownUnSynchronizedThread(Countdown countdown) {
        this.threadCountdown = countdown;
    }

    @Override
    public void run() {
        threadCountdown.doUnSynchronizedCountdown();
    }
}

class CountdownSynchronizedThread extends Thread {
    private Countdown threadCountdown;

    public CountdownSynchronizedThread(Countdown countdown) {
        this.threadCountdown = countdown;
    }

    @Override
    public void run() {
        threadCountdown.doSynchronizedCountdown();
    }
}