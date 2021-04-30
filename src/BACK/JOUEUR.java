package BACK;

import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JOUEUR {

    private String nomJoueur;

    private int score;

    private int nombreVies;


    public JOUEUR(){

        this.score = 0;

        this.nomJoueur = "Pedro";

        this.nombreVies = 3;

    }

    public JOUEUR(String nomJoueur){

        this.score = 0;

        this.nomJoueur = nomJoueur;

        this.nombreVies = 3;

    }


    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNombreVies() {
        return nombreVies;
    }

    public void setNombreVies(int nombreVies) {
        this.nombreVies = nombreVies;
    }
}
