package jeu;

public class Conversion {

    /**
     * Retourne le char d'une case en String (ex: A10 --> A)
     *
     * @param caseEnString la case en String
     * @return x le char de la case
     */
    public char getCharFromString(String caseEnString){
        char x = ' ';
        if (caseEnString.length() == 2) {
            x = caseEnString.charAt(0);
        } else if (caseEnString.length() == 3) {
            x = caseEnString.charAt(0);
        }
        return x;
    }

    /**
     * Retourne l'entier d'une case en String (ex: A10 --> 10)
     *
     * @param caseEnString la case en String
     * @return y
     */
    public int getIntFromString(String caseEnString){
        int y = 0;
        if (caseEnString.length() == 2) {
            y = Integer.parseInt(caseEnString.substring(1));
        } else if (caseEnString.length() == 3) {
            y = Integer.parseInt(caseEnString.substring(1, 3));
        }
        return y;
    }

    public void formatCoupJoues(String coord1, String coord2, String nom, String nom2){
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

        l = coord1.length();
        coord1 = indent.substring(0, add1) + coord1 + indent.substring(0, add1);
        if(l == 2){
            coord1 += " ";
        }

        l = coord2.length();
        coord2 = indent.substring(0, add2) + coord2 + indent.substring(0, add2);
        if (l == 2){
            coord2 += " ";
        }

        System.out.println("| "+nom+" |"+nom2);
        System.out.println("|"+coord1+"|"+coord2+"|\n");
    }
}
