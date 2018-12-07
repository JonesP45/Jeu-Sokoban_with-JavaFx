package Projet;

public class FacadeModele {

    ModeleNbCoup modeleNbCoup = new ModeleNbCoup(new ModeleConcret());

    public void move(String direction) {
        modeleNbCoup.move(direction);
    }

    public void reset() {
        modeleNbCoup.reset();
    }

    public int nbCoup() {
        return modeleNbCoup.nbCoup;
    }

    public char[][] getPlateau() {
        return modeleNbCoup.getPlateau();
    }

}
