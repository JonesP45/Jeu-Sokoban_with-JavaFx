package Projet;

import javafx.scene.control.Label;

public class IHMFXVueNbPoussee {

    Label label = new Label("nb poussée :\n0");
    private CommandeInt commande;

    @SuppressWarnings("WeakerAccess")
    public IHMFXVueNbPoussee(Controleur controleur) {
        commande = controleur.commandeNbPoussee();
    }


    public void dessine() {
        label.setText("nb poussée :\n" + commande.exec() + "");
    }

}
