package Projet;

import java.util.ArrayList;

public class ModeleNbPoussee implements Modele {

    private Modele modele;
    private int nbPoussee = 0;

    public ModeleNbPoussee(Modele modele) {
        this.modele = modele;
    }

    public int getNbPoussee(){
        return nbPoussee;
    }

    @Override
    public void move(String direction) {
        nbPoussee++;
        modele.move(direction);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void replay() {

    }

    @Override
    public void reset(){
        nbPoussee = 0;
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
