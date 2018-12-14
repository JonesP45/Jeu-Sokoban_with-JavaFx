package Projet;

import javafx.scene.control.Label;

public class IHMFXVueNbCoup {

    public Label label = new Label("nb coup :\n0");
    private CommandeInt commande;

    @SuppressWarnings("WeakerAccess")
    public IHMFXVueNbCoup(Controleur controleur) {
        commande = controleur.commandeNbCoup();
    }


    public void dessine() {
        label.setText("nb coup :\n" + commande.exec() + "");
    }

}
