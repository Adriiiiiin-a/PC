package BACK;

import BACK.TOOLS.DIRECTIONS;
import BACK.TOOLS.FONCTIONS;
import BACK.TOOLS.TYPEENTITES;

import javax.swing.text.html.ImageView;

public class ENTITE {

    private TYPEENTITES typeEntite;

    private GRILLE grille;

    private int Xlocation;

    private int Ylocation;

    private String cheminImageEntite;


     public ENTITE(TYPEENTITES type, GRILLE grille, int x, int y, String chemin){

         this.typeEntite = type;
         this.grille = grille;
         this.Xlocation = x;
         this.Ylocation = y;
         this.cheminImageEntite = chemin;
     }


    public TYPEENTITES getTypeEntite() {
        return typeEntite;
    }

    public GRILLE getGrille() {
        return grille;
    }

    public int getXlocation() {
        return Xlocation;
    }

    public int getYlocation() {
        return Ylocation;
    }

    public String getCheminImageEntite() {
        return cheminImageEntite;
    }

    public void setCheminImageEntite(String cheminImageEntite) {
        this.cheminImageEntite = cheminImageEntite;
    }

    public boolean peutTraverser(DIRECTIONS direction){
         switch (direction){
             case BAS -> {
                 return this.grille.checkTraversable((this.Xlocation+1),(this.Ylocation));
             }
             case HAUT -> {
                 return this.grille.checkTraversable((this.Xlocation-1),(this.Ylocation));
             }
             case GAUCHE -> {
                 return this.grille.checkTraversable(this.Xlocation,(this.Ylocation-1));
             }
             case DROITE -> {
                 return this.grille.checkTraversable(this.Xlocation,(this.Ylocation+1));
             }
         }
         return false;
    }

    public void deplacerFantome(){


         return;





    }

    public void deplacerEntite(DIRECTIONS direction){

         switch (direction){
             case HAUT -> {
                 if(this.typeEntite.equals(TYPEENTITES.PACMAN)){
                     this.cheminImageEntite = FONCTIONS.getCheminPacmanHaut();
                 }
                 this.moulinette2(-1,0);
                break;
             }
             case BAS -> {
                 if(this.typeEntite.equals(TYPEENTITES.PACMAN)){
                     this.cheminImageEntite = FONCTIONS.getCheminPacmanBas();
                 }
                 this.moulinette2(+1,0);
                 break;
             }
             case GAUCHE -> {
                 if(this.typeEntite.equals(TYPEENTITES.PACMAN)){
                     this.cheminImageEntite = FONCTIONS.getCheminPacmanGauche();
                 }
                 this.moulinette2(0,-1);
                 break;
             }
             case DROITE -> {
                 if(this.typeEntite.equals(TYPEENTITES.PACMAN)){
                     this.cheminImageEntite = FONCTIONS.getCheminPacmanDroite();
                 }
                 this.moulinette2(0,+1);
                 break;
             }
         }



    }

    public boolean caseContient( TYPEENTITES type,int x, int y){

         ENTITE toCheck = grille.getPlancheEntites()[x][y];

         TYPEENTITES typeToCheck = toCheck.getTypeEntite();

         if(type.equals(typeToCheck)){

             return true;
         }else{
             return false;
         }


    }

    public void moulinette2(int xToAdd, int yToAdd){


        boolean bonbon = this.caseContient(TYPEENTITES.BONBON, (Xlocation+xToAdd),(Ylocation+yToAdd));
        boolean grosBonbon = this.caseContient(TYPEENTITES.GROS_BONBON, (Xlocation+xToAdd),(Ylocation+yToAdd));
        boolean collision = false; // Sera a true s'il y a une collision
        boolean trav = this.grille.checkTraversable((Xlocation+xToAdd),(Ylocation+yToAdd)); //On verifie que la prochaine case est traversable

        // Gestion des éventuelles collisions: Fantome rencontre pacman avec & sans boost

        if (this.typeEntite.equals(TYPEENTITES.FANTOME_ORANGE) || this.typeEntite.equals(TYPEENTITES.FANTOME_ROUGE) || this.typeEntite.equals(TYPEENTITES.FANTOME_ROSE)){
            if (!JEU.isBoost()){
                collision = true;
            }
        }
        else if (this.typeEntite.equals(TYPEENTITES.PACMAN)){

            if (this.caseContient(TYPEENTITES.FANTOME_ROUGE, (Xlocation+xToAdd), (Ylocation+yToAdd)) || this.caseContient(TYPEENTITES.FANTOME_ROSE, (Xlocation+xToAdd), (Ylocation+yToAdd)) || this.caseContient(TYPEENTITES.FANTOME_ORANGE, (Xlocation+xToAdd), (Ylocation+yToAdd))){

                collision = true;
            }
        }

        if (!collision && trav){

            if (this.typeEntite.equals(TYPEENTITES.FANTOME_ORANGE) || this.typeEntite.equals(TYPEENTITES.FANTOME_ROUGE) || this.typeEntite.equals(TYPEENTITES.FANTOME_ROSE)){

                //TODO  implementer une fonction permettant de faire faire demi tour aux fantomes en cas de rencontre

                if ()


            }

        }







    }
}
