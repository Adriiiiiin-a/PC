package BACK;

import BACK.TOOLS.DIRECTIONS;
import javafx.beans.InvalidationListener;
import java.util.Observable;

import java.io.IOException;

public class JEU extends Observable implements Runnable {


    private GRILLE planche;

    private DIRECTIONS derniereDirection;

    private DIRECTIONS newDirection;

    private static boolean isBoost;

    private static JOUEUR joueur;

    private boolean stop;

    private int stopBoost;



    public JEU(String pseudo) throws IOException {

        super();
        this.planche = new GRILLE();
        this.derniereDirection = DIRECTIONS.DROITE;
        this.newDirection = null;
        this.isBoost = false;
        this.stop = false;
        this.stopBoost = 1000;

        this.joueur = new JOUEUR(pseudo);



    }

    public static boolean isIsBoost() {
        return isBoost;
    }

    public static void setIsBoost(boolean isBoost) {
        JEU.isBoost = isBoost;
    }

    public static JOUEUR getJoueur() {
        return joueur;
    }

    public static void setJoueur(JOUEUR joueu) {
        joueur = joueu;
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
                //System.out.println("lol");
                boost();



            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }



        }

    }

    public void pacman(){

        try {
            System.out.println("Coordonnées PACMAN: " + this.getPlanche().trouverPacman().getXlocation() + " " + this.getPlanche().trouverPacman().getYlocation());
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            if(this.getPlanche().trouverPacman().peutTraverser(newDirection)){

                this.getPlanche().trouverPacman().deplacerEntite(newDirection);
                derniereDirection = newDirection;

            }
            else{
                this.getPlanche().trouverPacman().deplacerEntite(derniereDirection);

            }
        } catch (Exception e) {
            e.printStackTrace();
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


    public void stop() {

        this.stop = true;
        System.out.println("Score final:" + this.getJoueur().getScore());

    }
}
