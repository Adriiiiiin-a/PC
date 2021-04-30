package BACK;

import BACK.TOOLS.FONCTIONS;
import BACK.TOOLS.TYPEENTITES;

import java.io.*;

public class GRILLE {



    private int nombreBonbons;

    private static ENTITE[][] plancheEntites;


    public GRILLE() throws IOException {

        this.plancheEntites = laMoulinette();

        this.nombreBonbons = compteBonbons();

    }

    public int getNombreBonbons() {
        return nombreBonbons;
    }

    public ENTITE[][] getPlancheEntites() {
        return plancheEntites;
    }

    public void setNombreBonbons(int nombreBonbons) {
        this.nombreBonbons = nombreBonbons;
    }

    /**
     * Génère le tableau double d'entites
     * @return le tableau double d'entites
     * @throws IOException
     */
    private ENTITE[][] laMoulinette() throws IOException {

        char[][] tab = new char[22][22];

        ENTITE[][] toReturn = new ENTITE[22][22];

        // Le fichier d'entrée
        File file = new File(FONCTIONS.getCheminMap());
        // Créer l'objet File Reader
        FileReader fileReader = new FileReader(file);
        // Créer l'objet BufferedReader
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int c = 0;
        // Lire caractère par caractère

        int x = 0;
        int y = 0;
        while((c = bufferedReader.read()) != '-')
        {
            // convertir l'entier en char
            char ch = (char) c;
            // Afficher le caractère
            //System.out.print(ch);


            //System.out.println(ch);


            if (ch != '\n') {
                //tab[x][y] = ch;


                switch (ch) {
                    case '0':
                        toReturn[x][y] = new ENTITE(TYPEENTITES.BONBON, this, x,y,FONCTIONS.getCheminBonbon());
                        break;
                    case '1':
                        toReturn[x][y] = new ENTITE(TYPEENTITES.MUR, this, x,y,FONCTIONS.getCheminMur());
                        break;
                    case '2':
                        toReturn[x][y] = new ENTITE(TYPEENTITES.VIDE, this, x,y,FONCTIONS.getCheminVide());
                        break;
                    case '3':
                        toReturn[x][y] = new ENTITE(TYPEENTITES.PORTE, this, x,y,FONCTIONS.getCheminPorte());
                        break;
                    case 'R':
                        toReturn[x][y] = new ENTITE(TYPEENTITES.FANTOME_ROUGE, this, x,y,FONCTIONS.getCheminFantomeRouge());
                        break;
                    case 'S':
                        toReturn[x][y] = new ENTITE(TYPEENTITES.FANTOME_ROSE, this, x,y,FONCTIONS.getCheminFantomeRose());
                        break;
                    case 'O':
                        toReturn[x][y] = new ENTITE(TYPEENTITES.FANTOME_ORANGE, this, x,y,FONCTIONS.getCheminFantomeOrange());
                        break;
                    case '5':
                        toReturn[x][y] = new ENTITE(TYPEENTITES.GROS_BONBON, this, x,y,FONCTIONS.getCheminGrosBonbon());
                        break;

                    case 'P':
                        toReturn[x][y] = new ENTITE(TYPEENTITES.PACMAN, this, x,y,FONCTIONS.getCheminPacmanDroite());
                        break;

                    default:
                        System.out.println(ch);
                        System.out.println("ERREUR INITIALISATION INDEX: " + x +" "+ y);


                }



                //System.out.print(toReturn[x][y] + " ");

                //System.out.print(convert + " ");

                //System.out.print("Pedro");
                //System.out.print(tab[x][y]);
                y++;

            }
            else{
                //System.out.print('\n');
                y=0;
               x++;
            }

        }
        return toReturn;

    }


    /**
     * Vérifie que la case demandée est traversable
     * @param x coordonée x de la case voulue
     * @param y coordonée y de la case voulue
     * @return true si la case est traversable, false sinon
     */
    public static boolean checkTraversable(int x, int y){

        if ((x >= 0) && (y >= 0) && (x <= 21) && (y <= 21)){

            if(!(plancheEntites[x][y].getTypeEntite().equals(TYPEENTITES.MUR))){
                return true;

            }else{
                return false;
            }
        }

        return false;


    }



    /**
     * Compte les bonbons de la grille
     * @return le nombre de bonbons dans la planche de jeu: INT
     */
    public int compteBonbons(){

        int cpt = 0;

        for (ENTITE[] ligne:this.plancheEntites) {

            for (ENTITE toCheck:ligne) {

                if (toCheck.getTypeEntite().equals(TYPEENTITES.BONBON) || toCheck.getTypeEntite().equals(TYPEENTITES.GROS_BONBON)){

                    cpt += 1;

                }

            }

        }

        return cpt;




    }


    public ENTITE trouverPacman() throws Exception {

        for (ENTITE[] ligne:this.plancheEntites) {

            for (ENTITE toCheck:ligne) {

                if (toCheck.getTypeEntite().equals(TYPEENTITES.PACMAN)){
                    return toCheck;
                }

            }


        }
        throw new Exception("Pacman not found");
        
    }

    private ENTITE[] trouverFrantomes() {

        ENTITE[] toReturn = new ENTITE[3];


        for (ENTITE[] ligne:this.plancheEntites) {

            for (ENTITE toCheck:ligne) {
                
                switch(toCheck.getTypeEntite()) {

                    case FANTOME_ROSE -> toReturn[0] = toCheck;
                    case FANTOME_ROUGE -> toReturn[1] = toCheck;
                    case FANTOME_ORANGE -> toReturn[2] = toCheck;
                }
            }
        }
        return toReturn;
    }



    public void fantomeBoost(){
        for (ENTITE fantome:trouverFrantomes()) {
            fantome.setCheminImageEntite(FONCTIONS.getCheminFantomeMangeable());
        }
    }

    public void fantomeNormal(){

        for (ENTITE fantome:trouverFrantomes()) {
            switch(fantome.getTypeEntite()){
                case FANTOME_ORANGE -> fantome.setCheminImageEntite(FONCTIONS.getCheminFantomeOrange());
                case FANTOME_ROUGE -> fantome.setCheminImageEntite(FONCTIONS.getCheminFantomeRouge());
                case FANTOME_ROSE -> fantome.setCheminImageEntite(FONCTIONS.getCheminFantomeRose());
            }
        }

    }

        







}


