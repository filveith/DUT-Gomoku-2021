package jeu;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class UI {

    static private Scanner in = new Scanner(System.in);
    static private PrintStream out = System.out;

    /**
     * Choix de la taille du plateau
     * 
     * @return la taille du plateau
     */
    public int taillePlateau() {
        int taillePlateauInt = 10;
        out.println("Quel taille de plateau voulez-vous ? (entre 5 et 26)");    //si aucune valeur de l'utilisateur alors taille = 10
        while (true) {
            String taillePlateau = in.nextLine().trim();
            try {
                taillePlateauInt = Integer.parseInt(taillePlateau);
            } catch (Exception e) {
                out.println("erreur: la valeur entrée n'est pas valable ");
            }

            if (taillePlateauInt <= 26 && taillePlateauInt >= 5) {
                return taillePlateauInt;
            }
            out.println("erreur: la valeur entrée n'est pas valable ");
        }
    }

    /**
     * Choix du nom du joueur
     * 
     * @param nbJoueur le numéro du joueur (1 ou 2)
     * @return le nom de joueur
     */
    public String choixNomJoueur(int nbJoueur) {
        out.println("Nom du joueur " + nbJoueur + " : ");
        String nomJoueur = in.nextLine().trim();
        if (nomJoueur.equals(""))
            nomJoueur = "Joueur" + nbJoueur;
        return nomJoueur;
    }

    /**
     * Methode permettant d'afficher le necessaire au(x) joueur(s)
     *
     * @param nomJoueur le nom du joueur actuel
     * @param coupJoues la liste des coups joués par le joueur actuel
     * @param screen_ le screen actuel
     * @return l'interaction avec le joueur
     */
    public String userInterface(String nomJoueur, List<String> coupJoues, Screen screen_) {
        Main m = new Main();
        boolean boucler = true;
        while (boucler) {
            out.println("Où voulez vous jouer " + nomJoueur + " ? ");
            String commande = in.nextLine().trim();
            out.println("\n\n");
            switch (commande) {
            case "/quit":
                out.println("-> Bye.");
                boucler = false;
                System.exit(0);
            case "/aide":
                screen_.display(1);
                break;
            case "/plateau":
                screen_.display(0);
                break;
            case "/coup":
                screen_.getAllPossiblePlays(true);
                break;
            case "/redemarrer":
                screen_.clear();
                m.clearCoupJoues();
                screen_.display(0);
                break;
            case "/joues":
                for (String cJ : coupJoues) {
                    out.println("" + cJ);
                }
                out.println();
                break;
            default:
                if (commande.length() == 2 || commande.length() == 3) {
                    return commande;
                }
                out.println("-> commande inconnue '" + commande + "'");
                break;
            }
        }
        return "";
    }
}