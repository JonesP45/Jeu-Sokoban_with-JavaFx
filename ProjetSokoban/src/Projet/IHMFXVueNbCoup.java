package Projet;

import javafx.scene.control.Label;

public class IHMFXVueNbCoup {

    private Controleur controleur;
    Label label = new Label("nb coup :\n0");
    private CommandeInt commande;

    public IHMFXVueNbCoup(Controleur controleur) {
        this.controleur = controleur;
        commande = controleur.commandeNbCoup();
    }


    public void dessine() {
        label.setText("nb coup :\n" + commande.exec() + "");
    }

}
