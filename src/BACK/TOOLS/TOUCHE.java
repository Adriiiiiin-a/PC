package BACK.TOOLS;


import BACK.GRILLE;
import BACK.JEU;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class TOUCHE {

    Scene scene;
    JEU partie;

    GRILLE grille;


    public DIRECTIONS lastTouche;

    public TOUCHE(Stage stage, JEU partie) {
        this.scene = stage.getScene();

        this.partie = partie;
        this.grille = partie.getPlanche();
        this.lastTouche = DIRECTIONS.DROITE;



    }


    public void touches() {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle (KeyEvent entre){


            switch (entre.getCode()) {
                case ENTER:
                    System.out.println("marche Entrer");
                    break;
                case UP:
                    System.out.println("OK");
                    try {
                        if (grille.trouverPacman().peutTraverser(DIRECTIONS.HAUT)) {
                            System.out.println("haut marche");
                            partie.setNewDirection(DIRECTIONS.HAUT);

                        } else {
                            partie.setDerniereDirection(DIRECTIONS.HAUT);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case DOWN:
                    System.out.println("OK");
                    try {
                        if (grille.trouverPacman().peutTraverser(DIRECTIONS.BAS)) {
                            System.out.println("bas marche");
                            partie.setNewDirection(DIRECTIONS.BAS);

                        } else {
                            partie.setDerniereDirection(DIRECTIONS.BAS);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case RIGHT:
                    System.out.println("OK");
                    try {
                        if (grille.trouverPacman().peutTraverser(DIRECTIONS.DROITE)) {
                            System.out.println("droite marche");
                            partie.setNewDirection(DIRECTIONS.DROITE);

                        } else {
                            partie.setDerniereDirection(DIRECTIONS.DROITE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case LEFT:
                    System.out.println("OK");
                    try {
                        if (grille.trouverPacman().peutTraverser(DIRECTIONS.GAUCHE)) {
                            //System.out.println("gauche marche");
                            partie.setNewDirection(DIRECTIONS.GAUCHE);

                        } else {
                            partie.setDerniereDirection(DIRECTIONS.GAUCHE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }


            });
    }
}