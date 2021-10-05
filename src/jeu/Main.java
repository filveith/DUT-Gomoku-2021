package jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static private final Scanner in = new Scanner(System.in);
    static private char x;
    static private int y;
    static private boolean boot = true;
    static private char nature = 'X';
    static private Joueur jH = new Joueur("Joueur1", 0, 'X', false);
    static private Joueur j2;
    static private IA playerIA = new IA("IA");
    static private int playersTurn = 1;

    /**
     * La fonction Main du programme
     * 
     * @param args
     */
    public static void main(String[] args) {
        UI m = new UI();
        Screen s = new Screen(bootMain()); // max 26 because the alphabet is 26 char long :)

        s.clear();
        s.display(0);

        System.out.println("* Menu *");
        while (true) {
            String input = "";
            if (playersTurn == 1) {
                input = m.userInterface(jH.nomJoueur, jH.coupJoues, s);
            } else if (playersTurn == 2){
                if (j2.isIA) {
                    input = Screen.getStringFromCase(playerIA.joueUnCoupRandom(s.getAllPossiblePlays(false)));
                } else {
                    input = m.userInterface(j2.nomJoueur, j2.coupJoues, s);
                }
            }

            try {
                if (input.length() == 2) {  //Formate l'entrée utlisateur en Char et int
                    x = input.charAt(0);
                    y = Integer.parseInt(input.substring(1));
                } else if (input.length() == 3) {
                    x = input.charAt(0);
                    y = Integer.parseInt(input.substring(1, 3));
                }
            } catch (Exception e){
                System.out.println("La valeur entrée n'est pas valable");
            }


            if (s.checkUserInput(x, y)) {
                if (playersTurn == 1) {
                    jH.addCoupJoues(input);
                    nature = jH.natureJoueur;
                    playersTurn++;
                } else if (playersTurn == 2) {
                    if (j2.isIA) {
                        System.out.println("L'IA joue : " + input);
                    }
                    j2.addCoupJoues(input);
                    nature = j2.natureJoueur;
                    playersTurn--;
                }

                s.setPoint(x, y, nature);
                s.display(0);
                s.getAllPlayedCase();
            }
            s.checkIfWin();
        }
    }

    /**
     * Est utilisé une fois au demarage de chaque partie pour choisir la taille du plateau et le nombre de joueurs
     *
     * @return 0
     */
    public static int bootMain() {
        UI m = new UI();
        if (boot) {
            boot = false;
            intro();
            return (m.taillePlateau());
        }
        return 0;
    }

    /**
     * Supprime tout les coups joués par les joueurs
     */
    public void clearCoupJoues(){
        jH.coupJoues.clear();
        j2.coupJoues.clear();
    }

    /**
     * Introduction + Choix de l'adversaire et des noms
     */
    public static void intro() {
        UI m = new UI();
        String adversaire;
        boolean boucle = true;

            System.out.println("\n\n");
            System.out.println("                ** Bienvenue au jeu du Gomoku ** \n"+
                                "-Pour gagner il faut aligner 5 pions dans n'importe quel direction\n"+
                                "-Si jamais vous êtes bloqué(e) vous pouvez utiliser la commande /aide \n\n"+
                                "Aller, c'est parti !\n\n"+
                                "Voulez-vous jouer contre l'ordinateur(O) ou contre un humain(H) ? ");
        while (boucle) {
            adversaire = in.nextLine().trim();
            try {
                char choixAdversaire = adversaire.charAt(0);
                if (choixAdversaire == 'H' || choixAdversaire == 'h') {
                    j2 = new Joueur("Joueur2", 1, 'O', false);
                    jH.nomJoueur = m.choixNomJoueur(1);
                    j2.nomJoueur = m.choixNomJoueur(2);
                    boucle = false;
                } else if (choixAdversaire == 'O' || choixAdversaire == 'o') {
                    j2 = new Joueur(playerIA.getName(), 1, 'O', true);
                    jH.nomJoueur = m.choixNomJoueur(1);
                    j2.nomJoueur = j2.getNomJoueur();
                    boucle = false;
                } else {
                    System.out.println("erreur: la valeur entrée n'est pas valable");
                }
            } catch (Exception e) {
                System.out.println("erreur: la valeur entrée n'est pas valable");
                //System.out.println("erreur: la valeur entrée n'est pas valable "+e);
            }
        }
    }

    public String getJoueurFromChar(char symbole) {
        if (symbole == jH.getNature()) {
            return jH.getNomJoueur();
        } else {
            return j2.getNomJoueur();
        }
    }

    /**
     * Affiche tout les coups joués de chaques joueurs à la fin
     */
    public void afficherToutLesCoupsJoues(){
        System.out.println("Tout les coups joués dans cette partie : ");

        ArrayList<String> coupJoues = new ArrayList<>();
        String nom = jH.nomJoueur;
        String nom2 = j2.nomJoueur;
        String indent = "              "; // 20 spaces.
        int l;
        int add1 = nom.length()/2;
        int add2 = nom2.length()/2;

        if (nom.length() == 1){
            nom = " " + nom + indent.charAt(0);
            add1 = 1;
        } else if (nom.length() == 2){
            nom += indent.charAt(0);
        }

        l = nom2.length();
        nom2 = " " + nom2;
        if (l == 1){
            nom2 += " |";
            add2 = 1;
        } else if (l == 2){
            nom2 += indent.substring(0, 2) + "|";
        } else {
            nom2 += " |";
        }
        System.out.println("| "+nom+" |"+nom2);

        coupJoues.addAll(jH.coupJoues);
        coupJoues.addAll(j2.coupJoues);

        for (int p1 = 0, p2 = (jH.coupJoues).size(); p1 < (jH.coupJoues).size() || p2 < (jH.coupJoues).size()+(j2.coupJoues).size(); p1++, p2++) {

            String coord1 = "";
            String coord2 = "";

            if (p2 == (jH.coupJoues).size()+(j2.coupJoues).size()) {
                coord1 = coupJoues.get(p1);
            }
            else if(p1 == (jH.coupJoues).size()) {
                coord2 = coupJoues.get(p2);
            }
            else {
                coord1 = coupJoues.get(p1);
                coord2 = coupJoues.get(p2);
            }

            l = coord1.length();
            coord1 = indent.substring(0, add1) + coord1 + indent.substring(0, add1);
            if(l == 2) coord1 += " ";

            l = coord2.length();
            coord2 = indent.substring(0, add2) + coord2 + indent.substring(0, add2);
            if (l == 2) coord2 += " ";


            if (p2 == (jH.coupJoues).size()+(j2.coupJoues).size()) System.out.println("|"+coord1+"| "+indent.substring(0,(j2.nomJoueur).length()+1)+" |");
            else if(p1 == (jH.coupJoues).size()) System.out.println("| "+indent.substring(0,(jH.nomJoueur).length())+" |"+coord2+"|");
            else System.out.println("|"+coord1+"|"+coord2+"|");

        }
    }
}