package Projet;

import java.util.ArrayList;

public class ModeleNbPoussee implements Modele {

    private Modele modele;
    private int nbPoussee = 0;

    @SuppressWarnings("WeakerAccess")
    public ModeleNbPoussee(Modele modele) {
        this.modele = modele;
    }

    public int getNbPoussee() {
        return nbPoussee;
    }

    @Override
    public void charger(char[][] plateau) {
        modele.charger(plateau);
    }

    @Override
    public boolean[] move(String direction) {
        boolean[] coupPoussee = modele.move(direction);
        if (coupPoussee[0] && coupPoussee[1])
            nbPoussee++;
        return coupPoussee;
    }

    @Override
    public void undo() {
        modele.undo();
    }

    @Override
    public void redo() {
        modele.redo();
    }

    @Override
    public void reset(){
        nbPoussee = 0;
        modele.reset();
    }

    @Override
    public void clear() {
        nbPoussee = 0;
        modele.clear();
    }

    @Override
    public char[][] getPlateau() {
        return modele.getPlateau();
    }

    @Override
    public ArrayList<String> getMoves() {
        return modele.getMoves();
    }

    @Override
    public boolean getEtat() {
        return modele.getEtat();
    }

}
