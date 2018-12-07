package Projet;

import javafx.scene.control.Label;

public class IHMFXVueNbCoup {
    Controleur controleur;
    Label label = new Label("0");
    CommandeInt commande;

    public IHMFXVueNbCoup(Controleur controleur) {
        this.controleur=controleur;
        commande = controleur.commandeNbCoup();
    }


    public void dessine() {
        label.setText(commande.exec() + "");
    }

}
