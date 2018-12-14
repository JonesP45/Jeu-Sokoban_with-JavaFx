package Projet;

import javafx.scene.control.Label;

public class IHMFXVueLevel {

    public Label label = new Label("level :\naucun");
    private CommandeInt commande;

    @SuppressWarnings("WeakerAccess")
    public IHMFXVueLevel(Controleur controleur) {
        commande = controleur.commandeLevel();
    }


    public void dessine() {
        label.setText("level :\n" + (commande.exec() + 1) + "");
    }

}
