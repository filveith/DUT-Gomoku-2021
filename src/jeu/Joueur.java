package jeu;
import java.util.ArrayList;
import java.util.List;

public class Joueur {

    String nomJoueur;
    int numJoueur;
    char natureJoueur;
    boolean isIA;
    List<String> coupJoues = new ArrayList<>();

    /**
     * Le constructeur de la classe Joueur
     *
     * @param nomJoueur le nom de joueur
     * @param nbJoueur le numéro du joueur
     * @param natureJoueur le symbole qu'utilisera le joueur
     * @param status est-ce que le joueur est une IA ou pas
     */
    Joueur(String nomJoueur, int nbJoueur, char natureJoueur, boolean status) {
        this.nomJoueur = nomJoueur;
        this.numJoueur = nbJoueur;
        this.natureJoueur = natureJoueur;
        this.isIA = status;
    }

    /**
     * Ajoute un coup aux coups joués du joueur
     *
     * @param coupJoue le coup joué
     */
    public void addCoupJoues(String coupJoue){
        this.coupJoues.add(coupJoue);
    }

    /**
     * Change le nom du joueur
     *
     * @param nomJoueur le nom à utiliser
     */
    public void setNomJoueur(String nomJoueur){
        this.nomJoueur = nomJoueur;
    }

    /**
     * Renvoie le nom du joueur
     *
     * @return le nom du joueur
     */
    public String getNomJoueur(){
        return this.nomJoueur;
    }

    /**
     * Change le numéro du joueur
     *
     * @param numJoueur le numéro à utiliser
     */
    public void setNumJoueur(int numJoueur){
        this.numJoueur = numJoueur;
    }

    /**
     * Renvoie le numéro du joueur
     *
     * @return le numéro du joueur
     */
    public int getNumJoueur(){
        return this.numJoueur;
    }

    /**
     * Renvoie la nature du joueur
     *
     * @return la nature du joueur
     */
    public char getNature() {
        return this.natureJoueur;
    }

    /**
     * Renvoie le statut du joueur
     *
     * @return le statut du joueur
     */
    public boolean getStatus() {
        return isIA;
    }

    /**
     * Change le statut du joueur
     *
     * @param status le statut à utiliser
     */
    public void setStatus(boolean status) {
        this.isIA = status;
    }

}
