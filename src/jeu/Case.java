package jeu;

public class Case {

    char nature;

    /**
     * Constructeur de Case
     *
     * @param nature_
     */
    Case (char nature_) {
        this.nature = nature_;
    }

    /**
     * get la nature d'une case
     *
     * @return nature de la case
     */
    public char getNature() {
        return this.nature;
    }

    /**
     * Permet de donner/changer la nature d'une case
     *
     * @param nat la nature à donner
     */
    public void setNature(char nat) {
        this.nature = nat;
    }
}