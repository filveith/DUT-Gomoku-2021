package jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class Screen {

    private Main m = new Main();
    private Conversion c = new Conversion();
    static private Scanner in = new Scanner(System.in);

    private final int size;
    private static Case[][] image;

    private boolean win = false;

    private final static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
            
    String[] direction = {
        "NORD-OUEST",
        "NORD",
        "NORD-EST",
        "EST", 
        "SUD-EST",
        "SUD",
        "SUD-OUEST",
        "OUEST"
    };


    /**
     * Constructeur de la classe Screen
     * 
     * @param size_ la taille voulue par l'utilisateur
     */
    Screen(int size_) {
        size = size_;
        image = new Case[size * 2][size];
    }

    /**
     * Renvoie l'image de l'objet screen en question
     * 
     * @return l'image
     */
    public Case[][] getImage() {
        return image;
    }

    /**
     * Verifie si la valeur entrée par l'utilisateur est valable
     * 
     * @param letter la partie 'lettre' de l'entrée
     * @param chiffre la partie 'chiffre/nombre' de l'entrée
     * @return true si la valeur est valable
     */
    public boolean checkUserInput(char letter, int chiffre) {
        String play = letter + String.valueOf(chiffre);
        int value = new String(alphabet).indexOf(letter);

        if (value != -1 && 0 < chiffre && chiffre <= size) {
            ArrayList<String> possiblesMovesString;
            possiblesMovesString = getStringListFromCaseList(getAllPossiblePlays(false));

            if (possiblesMovesString.contains(play)) {
                // System.out.println("Possible");
                return true;
            } else {
                System.out.println("Le coup " + play + " n'est pas jouable");
                return false;
            }
        }
        System.out.println("Le coup " + play + " n'est pas valable");
        return false;
    }


    public boolean checkIfCasePossible(char letter, int chiffre) {
        String play = letter + String.valueOf(chiffre);
        int value = new String(alphabet).indexOf(letter);

        if (value != -1 && 0 < chiffre && chiffre <= size) {
            ArrayList<String> possiblesMovesString;
            possiblesMovesString = getStringListFromCaseList(getAllPossiblePlays(false));

            if (possiblesMovesString.contains(play)) {
                // System.out.println("Possible");
                return true;
            } else {
                possiblesMovesString = getStringListFromCaseList(getAllPlayedCase());
                return possiblesMovesString.contains(play);
            }
        }
        return false;
    }

    /**
     * Converti une list de Case en list de Strings
     *
     * @param possibleMoves liste de case des coups possibles
     * @return possiblesMovesString liste de Strings des coups possibles
     */
    public ArrayList<String> caseListToStringList(ArrayList<Case> possibleMoves) {
        ArrayList<String> possiblesMovesString = new ArrayList<>();
        for (Case case1 : possibleMoves) {
            possiblesMovesString.add(getStringFromCase(case1));
        }
        return possiblesMovesString;
    }

    /**
     * donne la position d'une lettre dans l'alphabet
     * @param letter    la lettre a chercher dans l'alphabet
     * @return la position d'une lettre dans l'alphabet - 1
     */
    public int getPositionOfLetter(char letter){
        return(new String(alphabet).indexOf(letter));
    }

    /**
     * Place un pion sur le plateau avec sa nature
     * 
     * @param letter la coordonnées 'lettre' du pion
     * @param chiffre la coordonées 'chiffre/nombre' du pion
     * @param nature sa nature
     */
    public void setPoint(char letter, int chiffre, char nature) {
        int x = getPositionOfLetter(letter);
        if (x == 0) {
            x++;
        } else if (x == 1) {
            x = x + 2;
        } else {
            x = x * 2 + 1;
        }
        image[x][chiffre - 1].setNature(nature);
    }

    /**
     * Supprime tout les pions sur le terrain
     */
    public void clear() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size * 2; c++) {
                image[c][r] = new Case(' ');
            }
        }
    }

    /**
     * Affiche le terrain avec les pions joués
     *
     * @param command permet d'ajouter des options lors de l'affichage
     */
    public void display(int command) {
        int i = 0;

        ArrayList<String> possiblesMovesString;
        possiblesMovesString = getStringListFromCaseList(getAllPossiblePlays(false));

        ArrayList<String> texte = new ArrayList<>();
        texte.add("/aide        : affiche l'aide");
        texte.add("/joues       : affiche les coups joués par joueur");
        texte.add("/plateau     : affiche le plateau");
        texte.add("/redemarrer  : redemarre la partie");
        texte.add("/quit        : stop la partie");

        for (String string : possiblesMovesString) {
            setPoint(c.getCharFromString(string), c.getIntFromString(string), '-');
        }
        
        displayLetters();
        displayBar();

        for (int r = 0; r < size; r++) {
            String lineNum = Integer.toString(r + 1);
            if (r < 9) {
                lineNum = (" " + (r + 1));
            }
            System.out.print(lineNum + "|");
            for (int c = 0; c < size * 2; c++) {
                System.out.print(image[c][r].nature);
            }

            if (command == 1) {
                if (i != 4) {
                    System.out.println(" |      " + texte.get(r));
                    i++;
                } else {
                    System.out.println(" |");
                }
            } else {
                System.out.println(" |");
            }
        }

        for (String string : possiblesMovesString) {
            setPoint(c.getCharFromString(string), c.getIntFromString(string), ' ');
        }
        displayBar();
    }

    /**
     * Dessine la barre supérieure et inférieur (ex: +-------------------+)
     */
    private void displayBar() {
        System.out.print("  +");
        for (int c = 0; c < size * 2; c++) {
            System.out.print("-");
        }
        System.out.println("-+");
    }

    /**
     * Dessine les lettres des colonnes
     */
    private void displayLetters() {
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(" " + alphabet[i]);
        }
        System.out.println();
    }

    /**
     * Retourne un String de position (ex: A5) en partant de coordonées dans le tableau de case
     * 
     * @param x la coordonée en x
     * @param y la coordonée en y
     * @return la position de la Case
     */
    static public String getStringFromInt(int x, int y) {
        return alphabet[x / 2] + String.valueOf(y + 1);
    }

    /**
     * Renvoie la case correspondant à la position (ex: A5) donnée
     *
     * @param string_ la position donnée
     * @return la Case correspondante
     */
    public static Case getCaseFromString(String string_) {
        char letter = string_.charAt(0);
        int indexOfLetter = (new String(alphabet).indexOf(letter)) *2 +1;
        int number = Integer.parseInt(String.valueOf(string_.charAt(1)))-1;
        //System.out.println("index of letter '" + letter + "'  = "+indexOfLetter+"    NUMBER ="+number+"    CASE DANS METHODE == "+image[indexOfLetter][number].getNature());
        try {
            return image[indexOfLetter][number];
        } catch (Exception e){
            //System.out.println("erreur : getCaseFromString : "+e);
        }
        return image[indexOfLetter][number-1];
    }

    /**
     * Renvoie la position (ex: A5) correspondant à une Case donnée
     *
     * @param case_ la Case donnée
     * @return la position de la Case correspondante
     */
    public static String getStringFromCase(Case case_) {
        String letter = "";
        String nb = "";
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] == case_) {
                    letter = Character.toString(alphabet[i / 2]);
                    nb = String.valueOf(j + 1);
                }
            }
        }
        return letter.concat(nb);
    }

    /**
     * Renvoie les positions (ex: A5) correspondantes à plusieurs Cases données
     * 
     * @param caseList les Cases données
     * @return les positions des Cases correspondantes
     */
    public ArrayList<String> getStringListFromCaseList(ArrayList<Case> caseList) {
        ArrayList<String> possiblesMovesString = new ArrayList<>();
        for (Case currentCase : caseList) {
            possiblesMovesString.add(getStringFromCase(currentCase));
        }
        return possiblesMovesString;
    }

    /**
     * Retourne le nombre de coups joués jusqu'ici dans la partie
     *
     * @return le nombre de coups joués
     */
    public int getNbCoupsJoues() {
        int nb = 0;
        for (Case[] cases : image) {
            for (Case aCase : cases) {
                if (aCase.getNature() != ' ') {
                    nb++;
                }
            }
        }
        if (nb == size*size){
            System.out.println("Tout les coups possibles ont était joués, la partie est terminée !");
            restart();
            return 0;
        }
        return nb;
    }

    /**
     * Retourne une liste des coups jouable actuellement dans la partie
     * 
     * @param afficher option permettant d'afficher ou pas la liste sous forme textuelle des coups jouables
     * @return la liste de Cases jouables
     */
    public ArrayList<Case> getAllPossiblePlays(boolean afficher) {
        ArrayList<Case> possibleMoves = new ArrayList<>();
        ArrayList<Case> adjacentCases = new ArrayList<>();
        int nbCoupsJoues = getNbCoupsJoues();

        possibleMoves.clear();

        // If there's at least one move already played
        if (nbCoupsJoues >= 1) {

            // Check every cases
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {

                    // If it finds a non empty case
                    if (image[i][j].getNature() != ' ') {
                        nbCoupsJoues++;
                        adjacentCases.clear();

                        // Check all around the case to see if there's another case
                        for (int a = -2; a <= 2; a = a + 2) {
                            for (int b = -1; b <= 1; b++) {
                                // If a case exists, add it
                                try {
                                    if (image[i + a][j + b] != null && image[i + a][j + b] != image[i][j] && image[i + a][j + b].nature == ' ') {
                                        adjacentCases.add(image[i + a][j + b]);
                                    }
                                } catch (Exception e) {
                                    // System.out.println("Erreur: " + e);
                                }
                            }
                        }
                        // Then, for each non empty case, add the adjacent cases to the list of possible
                        // moves
                        possibleMoves.addAll(adjacentCases);
                    }
                }
            }

        // Else (if there are no move already played)
        } else {
            // Add all cases
            for (int i = 0; i < image.length; i = i + 2) {
                for (int j = 0; j < image[i].length; j++) {
                    possibleMoves.add(image[i][j]);
                }
            }
        }
        if (possibleMoves.size() == image.length * image.length) {
            System.out.println("Play anywhere");

        } else if (afficher) {
            System.out.println("You can play at : ");
            for (Case c : possibleMoves) {
                System.out.println(getStringFromCase(c));
            }
        }
        return possibleMoves;
    }

    /**
     * Permet de se déplacer dans le tableau en suivant une direction
     *
     * @param current la Case de départ
     * @param dir_ la direction à suivre
     * @return la position de la Case d'arrivée
     */
    public String moveWithDir(Case current, String dir_) {

        int coX = 0;
        int coY = 0;

        for (int i = 1; i < image.length; i = i + 2) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] == current) {
                    coX = i;
                    coY = j;
                }
            }
        }

        switch (dir_) {
            case "NORD-OUEST":
                coX=coX-2;
                coY--;
                break;
            case "NORD-EST":
                coX=coX+2;
                coY--;
                break;
            case "SUD-OUEST":
                coX=coX-2;
                coY++;
                break;
            case "SUD-EST":
                coX=coX+2;
                coY++;
                break;
            case "NORD":
                coY--;
                break;
            case "SUD":
                coY++;
                break;
            case "OUEST":
                coX=coX-2;
                break;
            case "EST":
                coX=coX+2;
                break;
        }
        if (coY >= 0 && coY <= image.length && coX > 0 && coX < image.length) {
            return getStringFromInt(coX, coY);
        } else {
            return "er";
        }
    }

    /**
     * Renvoie toutes les cases où un coup a été joué
     *
     * @return une liste de toutes les cases jouées
     */
    public ArrayList<Case> getAllPlayedCase() {
        ArrayList<Case> allPlayed = new ArrayList<>();

        for (int i = 1; i < image.length; i = i + 2) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j].getNature() != ' ') {
                    allPlayed.add(image[i][j]);
                }
            }
        }
        return allPlayed;
    }

    /**
     * Vérifie si en partant d'une case et en suivant une direction, on obtient une condition de victoire
     *
     * @param current la case actuelle
     * @param dir la direction de vérification
     * @param nb le nombre de cases similaires déjà rencontrées
     */
    public void checkIfWinFromCase(Case current, String dir, int nb) {
        char symbole = current.nature;
        String afterMoving = moveWithDir(current, dir);
        if (!afterMoving.equals("er")) { //si il n'y a pas d'erreur on continue dans le if

            if (getCaseFromString(afterMoving) != null) {
                Case newCase = getCaseFromString(afterMoving);

                if (newCase.nature == symbole && checkIfCasePossible(c.getCharFromString(afterMoving),c.getIntFromString(afterMoving))) {
                    nb++;
                    if (nb == 5 && newCase.nature == symbole) {
                        win = true;
                    } else {
                        checkIfWinFromCase(newCase, dir, nb);
                    }
                }
            }
        }
    }

    /**
     * Vérifie si le plateau actuel présente une victoire pour un joueur
     */
    public void checkIfWin() {

        ArrayList<Case> allPlayed = getAllPlayedCase();

        allPlayed.forEach(current -> {
            for (String dir_ : direction) {
                checkIfWinFromCase(current, dir_, 1);
                if (win) {
                    System.out.println("Bravo "+m.getJoueurFromChar(current.nature)+" vous avez gagné(e) !! \n");
                    m.afficherToutLesCoupsJoues();
                    restart();
                    win=false;
                }
            }
        });
    }

    /**
     * Permets de redémarrer (ou non) une partie
     */
    public void restart(){
        System.out.println("\nVoulez-vous rejouer ? O/N");
        String commande = in.nextLine().trim();
        if (commande.equals("O") || commande.equals("o") || commande.equals("oui") || commande.equals("Oui")){
            clear();
            m.clearCoupJoues();
            System.out.println("\nAller c'est reparti !\n");
        } else {
            System.out.println("Merci d'avoir joué au gomoku fait par Roméo Tesei et Fil Veith,\nBonne Journée et à bientôt,");
            System.exit(0);
        }
    }
}