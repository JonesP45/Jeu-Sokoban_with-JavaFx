package Projet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class FacadeModele {

    private ModeleNbCoup modeleNbCoup = new ModeleNbCoup(new ModeleConcret());
    private ModeleNbPoussee modeleNbPoussee = new ModeleNbPoussee(modeleNbCoup);


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

    class Anime implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            modeleNbPoussee.redo();
        }
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

//    private ModeleNbCoup modeleNbCoup = new ModeleNbCoup(new ModeleConcret());
//
//
//    public void move(String direction) {
//        modeleNbCoup.move(direction);
//    }
//
//    public void undo() {
//        modeleNbCoup.undo();
//    }
//
//    public void redo() {
//        modeleNbCoup.redo();
//    }
//
//    public void reset() {
//        modeleNbCoup.reset();
//    }
//
//    class Anime implements EventHandler<ActionEvent> {
//        public void handle(ActionEvent event) {
//            modeleNbCoup.redo();
//        }
//    }
//
//    public int getNbCoup() {
//        return modeleNbCoup.getNbCoup();
//    }
//
//    public int getNbPoussee() {
//        return 0;
//    }
//
//    public char[][] getPlateau() {
//        return modeleNbCoup.getPlateau();
//    }
//
//    public ArrayList<String> getMoves() {
//        return modeleNbCoup.getMoves();
//    }

}
