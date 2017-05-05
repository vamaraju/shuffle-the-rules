package model;

/**
 * Base code from: http://tutorials.jenkov.com/java-concurrency/locks.html
 * Accessed on: May 4, 2017.
 */
public class GameLock {

    private boolean locked = false;

    public synchronized void lock() {
        try {
            while(locked) {
                wait();
            }
            locked = true;
        } catch(InterruptedException e) {
            System.out.println("Thread interrupted!");
            e.printStackTrace();
        }
    }

    public synchronized void unlock() {
        locked = false;
        notify();
    }
}
