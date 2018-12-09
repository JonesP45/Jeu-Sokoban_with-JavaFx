package Projet;

import javafx.scene.control.Label;

public class IHMFXVueNbPoussee {

    private Controleur controleur;
    Label label = new Label("0");
    private CommandeInt commande;

    public IHMFXVueNbPoussee(Controleur controleur) {
        this.controleur = controleur;
        commande = controleur.commandeNbPoussee();
    }


    public void dessine() {
        label.setText(commande.exec() + "");
    }

}
