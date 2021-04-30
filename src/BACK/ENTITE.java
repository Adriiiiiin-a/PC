package BACK;

import BACK.TOOLS.DIRECTIONS;
import BACK.TOOLS.FONCTIONS;
import BACK.TOOLS.TYPEENTITES;

import javax.swing.text.html.ImageView;

import static BACK.TOOLS.TYPEENTITES.*;

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
                 if(this.typeEntite.equals(PACMAN)){
                     this.cheminImageEntite = FONCTIONS.getCheminPacmanHaut();
                 }
                 this.moulinette2(-1,0);
                break;
             }
             case BAS -> {
                 if(this.typeEntite.equals(PACMAN)){
                     this.cheminImageEntite = FONCTIONS.getCheminPacmanBas();
                 }
                 this.moulinette2(+1,0);
                 break;
             }
             case GAUCHE -> {
                 if(this.typeEntite.equals(PACMAN)){
                     this.cheminImageEntite = FONCTIONS.getCheminPacmanGauche();
                 }
                 this.moulinette2(0,-1);
                 break;
             }
             case DROITE -> {
                 if(this.typeEntite.equals(PACMAN)){
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

    public TYPEENTITES caseContient(int x, int y){

         ENTITE enti = grille.getPlancheEntites()[x][y];

         return enti.getTypeEntite();


    }


    public void moulinette2(int xToAdd, int yToAdd){


        boolean bonbon = this.caseContient(BONBON, (Xlocation+xToAdd),(Ylocation+yToAdd));
        boolean grosBonbon = this.caseContient(GROS_BONBON, (Xlocation+xToAdd),(Ylocation+yToAdd));
        boolean collision = false; // Sera a true s'il y a une collision
        boolean trav = this.grille.checkTraversable((Xlocation+xToAdd),(Ylocation+yToAdd)); //On verifie que la prochaine case est traversable

        // Gestion des éventuelles collisions: Fantome rencontre pacman avec & sans boost

        if (this.typeEntite.equals(FANTOME_ORANGE) || this.typeEntite.equals(FANTOME_ROUGE) || this.typeEntite.equals(FANTOME_ROSE)){
            if (!JEU.isBoost()){
                collision = true;
            }
        }
        else if (this.typeEntite.equals(PACMAN)){

            if (this.caseContient(FANTOME_ROUGE, (Xlocation+xToAdd), (Ylocation+yToAdd)) || this.caseContient(FANTOME_ROSE, (Xlocation+xToAdd), (Ylocation+yToAdd)) || this.caseContient(FANTOME_ORANGE, (Xlocation+xToAdd), (Ylocation+yToAdd))){

                collision = true;
            }
        }

        // ON VERIF S'IL Y A COLLISION
        if(collision){

            this.grille.setPerdue(); //TODO grille.setPerdue() => Perdue à True && grille.setNonPerdue() => Perdue à False

        }



        if (!collision && trav){

            // FANTOMES

            if (this.typeEntite.equals(FANTOME_ORANGE) || this.typeEntite.equals(FANTOME_ROUGE) || this.typeEntite.equals(FANTOME_ROSE)){

                //TODO  implementer une fonction permettant de faire faire demi tour aux fantomes en cas de rencontre

                //if ()

                if (this.caseContient(BONBON, (Xlocation+xToAdd),(Ylocation+yToAdd))){

                    this.grille.getPlancheEntites()[this.Xlocation][this.Ylocation] = new ENTITE(BONBON,this.grille, this.Xlocation,this.Ylocation,FONCTIONS.getCheminBonbon());

                }

                switch(this.caseContient((this.Xlocation+xToAdd),(this.Ylocation+yToAdd))){

                    case BONBON -> {
                        this.grille.getPlancheEntites()[this.Xlocation][this.Ylocation] = new ENTITE(BONBON,this.grille, this.Xlocation,this.Ylocation,FONCTIONS.getCheminBonbon());
                        break;
                    }
                    case GROS_BONBON -> {
                        this.grille.getPlancheEntites()[this.Xlocation][this.Ylocation] = new ENTITE(GROS_BONBON,this.grille, this.Xlocation,this.Ylocation,FONCTIONS.getCheminGrosBonbon());
                        JEU.setIsBoost(true); //TODO JEU.setisBoost()
                        break;
                    }
                    default -> {
                        this.grille.getPlancheEntites()[this.Xlocation][this.Ylocation] = new ENTITE(VIDE,this.grille, this.Xlocation,this.Ylocation,FONCTIONS.getCheminVide());
                    }
                }

                this.grille.getPlancheEntites()[Xlocation+xToAdd][Ylocation+yToAdd] = this;

            }

            /// PACMAN

            if (this.typeEntite.equals(PACMAN)){

                switch(this.caseContient((this.Xlocation+xToAdd),(this.Ylocation+yToAdd))){

                    case BONBON -> grille.bonbon();
                    case GROS_BONBON -> grille.grosBonbon(); //TODO grille.bonbon & grille.grosBonbon

                }
                this.grille.getPlancheEntites()[Xlocation+xToAdd][Ylocation+yToAdd] = this;
                this.grille.getPlancheEntites()[this.Xlocation][this.Ylocation] = new ENTITE(VIDE,this.grille, this.Xlocation,this.Ylocation,FONCTIONS.getCheminVide());
            }
            this.Xlocation += xToAdd;
            this.Ylocation += yToAdd;


        }







    }
}
