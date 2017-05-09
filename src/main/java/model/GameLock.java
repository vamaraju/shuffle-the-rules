/*
* Requirements mandating inclusion:
*
* This class is used to lock the backend thread (rule interpreter) while a player is interacting with the GUI
* in Gameplay Mode. It is needed for all requirements involving Gameplay Mode:
*
* 3.2.2.1.3.1 User can view their Hand.
* 3.2.2.1.3.2 User can sort their Hand.
* 3.2.2.2.3.1 User can select Card(s) from Hand to play.
* 3.2.2.2.3.2 User can select Card(s) from Hand to discard.
* 3.2.2.2.3.3 User can choose Card orientation (face up, face down).
* 3.2.2.2.3.4 User can select Card(s) to swap between Piles.
* 3.2.2.2.3.5 User can draw Card(s) from a Pile.
* 3.2.2.2.3.6 User can place Card(s) on a Pile.
* 3.2.2.3.3.1 Player will be notified when their Turn begins.
* 3.2.2.3.3.2 Player will be notified when their Turn ends.
* 3.2.2.3.3.2 Player's Turn will be skipped if Player is inactive.
* 3.2.2.4.3.1 Player can end game.
* 3.2.2.5.3.1 Rule Interpreter will interpret and execute Game Rules for a Card Game.
* */

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
