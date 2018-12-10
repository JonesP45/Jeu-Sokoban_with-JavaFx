package Projet;

import java.util.ArrayList;

public class ModeleNbCoup implements Modele {

    private Modele modele;
    private int nbCoup = 0;

    public ModeleNbCoup(Modele modele) {
        this.modele = modele;
    }

    public int getNbCoup(){
        return nbCoup;
    }

    @Override
    public void move(String direction) {
        nbCoup++;
        modele.move(direction);
    }

    @Override
    public void undo() {
        if (nbCoup > 0) {
            nbCoup--;
            modele.undo();
        }
    }

    @Override
    public void redo() {
        nbCoup++;
        modele.redo();
    }

    @Override
    public void reset(){
        nbCoup = 0;
        modele.reset();
    }

    @Override
    public char[][] getPlateau() {
        return modele.getPlateau();
    }

    @Override
    public ArrayList<String> getMoves() {
        return modele.getMoves();
    }

}
