package Projet;

public class FacadeModele {

    private ModeleNbCoup modeleNbCoup = new ModeleNbCoup(new ModeleConcret());


    public void move(String direction) {
        modeleNbCoup.move(direction);
    }

    public void undo() {
        modeleNbCoup.undo();
    }

    public void redo() {
        modeleNbCoup.redo();
    }

    public void reset() {
        modeleNbCoup.reset();
    }

    public int getNbCoup() {
        return modeleNbCoup.getNbCoup();
    }

    public int getNbPoussee() {
        return 0;
    }

    public char[][] getPlateau() {
        return modeleNbCoup.getPlateau();
    }

}
