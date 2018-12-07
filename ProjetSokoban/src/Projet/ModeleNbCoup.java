package Projet;

public class ModeleNbCoup implements Modele {

    Modele modele;
    int nbCoup =0;

    public ModeleNbCoup(Modele modele) {
        this.modele = modele;
    }

    public int nbCoup(){
        return nbCoup;
    }

    public void move(String direction) {
        nbCoup++;
        modele.move(direction);
    }

    public void reset(){
        nbCoup=0;
        modele.reset();
    }

    public char[][] getPlateau() {
        return modele.getPlateau();
    }

}
