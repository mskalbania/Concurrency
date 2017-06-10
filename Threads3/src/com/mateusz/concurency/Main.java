package com.mateusz.concurency;

import java.util.Random;

public class Main {
    //Example of Producer <-> Consumer
    public static void main(String[] args) {

        Message message = new Message();
        Thread writerThread = new Thread(new Writer(message));
        Thread readerThread = new Thread(new Reader(message));

        writerThread.start();
        readerThread.start();
    }
}

class Message {
    private String message;
    private boolean empty = true;

    public synchronized String read() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void write(String message) {
        while (!empty) {
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}

class Writer implements Runnable {
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String messages[] = {
                "Message1", "Message2", "Message3", "Message4", "Message5"
        };

        Random random = new Random();

        for(String string : messages){
            message.write(string);
            try {
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        message.write("Finished");
    }
}

class Reader implements Runnable {
    private Message message;

    public Reader(Message message){
        this.message = message;
    }

    @Override
    public void run(){
        Random random = new Random();

        for(String lastMessage = message.read(); !lastMessage.equals("Finished"); lastMessage = message.read()){
            System.out.println(lastMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}