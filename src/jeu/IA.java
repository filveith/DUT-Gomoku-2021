package jeu;

import java.util.ArrayList;
import java.util.Random;

public class IA {

    final String name;
    private final Random random = new Random();

    /**
     * Le constructeur de la classe IA
     *
     * @param name_ le nom de l'IA
     */
    IA(String name_) {
        this.name = name_;
    }

    /**
     * Renvoie le nom de l'IA
     *
     * @return le nom de l'IA
     */
    public String getName() {
        return this.name;
    }

    /**
     * Permet à l'IA de jouer un coup choisi au hasard choisi parmi les coups possibles
     *
     * @param coupsPossibles la liste des coups possibles
     * @return un coup choisi au hasard
     */
    public Case joueUnCoupRandom(ArrayList<Case> coupsPossibles) {
        return coupsPossibles.get(random.nextInt(coupsPossibles.size()));
    }
}