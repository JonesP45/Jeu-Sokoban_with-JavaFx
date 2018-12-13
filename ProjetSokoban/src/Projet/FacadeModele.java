package Projet;

import java.util.ArrayList;

public class FacadeModele {

    private ModeleNbCoup modeleNbCoup = new ModeleNbCoup(new ModeleConcret());
    private ModeleNbPoussee modeleNbPoussee = new ModeleNbPoussee(modeleNbCoup);


    public void play(char[][] plateau) {
        modeleNbPoussee.charger(plateau);
    }

    public void move(String direction) {
        modeleNbPoussee.move(direction);
    }

    public void undo() {
        modeleNbPoussee.undo();
    }

    public void redo() {
        modeleNbPoussee.redo();
    }

    public void reset() {
        modeleNbPoussee.reset();
    }

    public void clear() {
        modeleNbPoussee.clear();
    }

    public int getNbCoup() {
        return modeleNbCoup.getNbCoup();
    }

    public int getNbPoussee() {
        return modeleNbPoussee.getNbPoussee();
    }

    public char[][] getPlateau() {
        return modeleNbPoussee.getPlateau();
    }

    public ArrayList<String> getMoves() {
        return modeleNbPoussee.getMoves();
    }

    public boolean getEtat() {
        return modeleNbPoussee.getEtat();
    }

}
