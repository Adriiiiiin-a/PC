package VUE;

import BACK.JEU;
import BACK.TOOLS.TOUCHE;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    GridPane gridPane;
    TOUCHE touche;

    @Override
    public void start(Stage primaryStage) throws Exception{
        JEU jeu = new JEU();


        primaryStage.resizableProperty().set(true);
        // initialisation du modèle que l'on souhaite utiliser

        BorderPane border = new BorderPane();

        // permet de placer les diffrents éléments dans une grille
        GridPane gridPane = new GridPane();
        this.gridPane = gridPane;
        Affiche affiche = new Affiche(gridPane, jeu);
        jeu.addObserver(affiche);

        int column = 0;
        int row = 0;

        // création de la grille du jeu, placement des cases
        for(int i = 0; i < jeu.getPlanche().getPlancheEntites().length; i++)
        {
            for(int j = 0; j < jeu.getPlanche().getPlancheEntites()[i].length; j++)
            {
                ImageView img = new ImageView(jeu.getPlanche().getPlancheEntites()[i][j].getCheminImageEntite());

                gridPane.add(img, column++, row);

                if (column >= jeu.getPlanche().getPlancheEntites()[i].length)
                {
                    column = 0;
                    row++;
                }
            }
        }



        border.setCenter(gridPane);
        Scene scene = new Scene(border, Color.WHITESMOKE);
        primaryStage.setTitle("PACMAN FX");
        primaryStage.setScene(scene);

        touche = new TOUCHE(primaryStage, jeu);
        touche.touches();

        // on affiche la scène
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
