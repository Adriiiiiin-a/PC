package VUE;

import BACK.JEU;
import javafx.scene.layout.GridPane;

import java.util.Observable;
import java.util.Observer;

public class Affiche implements Observer {

    private GridPane gridPane;

    private JEU jeu;

    public Affiche(GridPane gridPane, JEU j){

        this.jeu = j;

        this.gridPane = gridPane;

    }



    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void update(Observable o, Object arg) {

        //TODO CHECK VICTOIRE / DEFAITE

        //this.jeu.pacman();

        //this.jeu.fantomes();

        //majImage();




    }
}
