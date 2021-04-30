package BACK;

import BACK.TOOLS.DIRECTIONS;
import javafx.beans.InvalidationListener;
import java.util.Observable;

import java.io.IOException;

public class JEU extends Observable implements Runnable {


    private GRILLE planche;

    private DIRECTIONS derniereDirection;

    private DIRECTIONS newDirection;

    private boolean isBoost;

    private boolean stop;

    private int stopBoost;


    public JEU() throws IOException {

        super();
        this.planche = new GRILLE(); //TODO REFACTOR PLANCHE
        this.derniereDirection = DIRECTIONS.DROITE;
        this.newDirection = null;
        this.isBoost = false;
        this.stop = false;
        this.stopBoost = 1000;


    }



    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public synchronized void run() {

        while(!stop){

            try{

                setChanged();
                notifyObservers();
                Thread.sleep(100);
                boost();



            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }



        }

    }

    public GRILLE getPlanche() {
        return planche;
    }

    public DIRECTIONS getDerniereDirection() {
        return derniereDirection;
    }

    public DIRECTIONS getNewDirection() {
        return newDirection;
    }

    public static boolean isBoost() {
        return isBoost;
    }

    public boolean isStop() {
        return stop;
    }

    public int getStopBoost() {
        return stopBoost;
    }

    public void setPlanche(GRILLE planche) {
        this.planche = planche;
    }

    public void setDerniereDirection(DIRECTIONS derniereDirection) {
        this.derniereDirection = derniereDirection;
    }

    public void setNewDirection(DIRECTIONS newDirection) {
        this.newDirection = newDirection;
    }

    public void setBoost(boolean boost) {
        isBoost = boost;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setStopBoost(int stopBoost) {
        this.stopBoost = stopBoost;
    }

    public void boost(){

        if(this.isBoost){

            this.stopBoost -= 1;
            this.getPlanche().fantomeBoost();
            if(stopBoost == 0){
                this.isBoost = false;
                this.getPlanche().fantomeNormal();
            }
        }
    }

}
