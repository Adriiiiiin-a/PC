package VUE;

import BACK.JEU;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;


import javafx.scene.image.ImageView;
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

        this.jeu.pacman();

        //this.jeu.fantomes();

        majImage();




    }

    /**
     * Fonction by Sedrick  https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column
     * @param row
     * @param column
     * @param gridPane
     * @return
     */
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    public void majImage() throws ArrayIndexOutOfBoundsException{

        for (int x = 0; x < this.jeu.getPlanche().getPlancheEntites().length; x++) {

            for (int y = 0; y < this.jeu.getPlanche().getPlancheEntites()[x].length; y++) {

                //System.out.println(y);



                ImageView ancienne =(ImageView) getNodeByRowColumnIndex(x,y,this.gridPane);
                String str = this.jeu.getPlanche().getPlancheEntites()[x][y].getCheminImageEntite();
                Image im = new Image(str);
                ancienne.setImage(im);



            }

        }


    }


}
