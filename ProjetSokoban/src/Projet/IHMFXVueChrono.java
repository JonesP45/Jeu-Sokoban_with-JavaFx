package Projet;

import javafx.scene.control.Label;

public class IHMFXVueChrono {

    public Label label = new Label("temps :\n0");
    private CommandeInt commandeInt;

    @SuppressWarnings("WeakerAccess")
    public IHMFXVueChrono(Controleur controleur) {
        commandeInt = controleur.commandeChrono();
    }

    public void dessine() {
        label.setText("temps :\n" + commandeInt.exec() + "");
    }

}
