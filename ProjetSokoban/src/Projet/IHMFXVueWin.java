package Projet;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class IHMFXVueWin {

    public GridPane gridPane = new GridPane();
    private CommandeInt commandeIntLevel;
    private CommandeInt commandeIntChrono;
    public Label labelLevel = new Label();
    public Label labelChrono = new Label();

    @SuppressWarnings("WeakerAccess")
    public IHMFXVueWin(Controleur controleur) {
        commandeIntLevel = controleur.commandeLevel();
        commandeIntChrono = controleur.commandeChrono();
        dessine();
    }


    public void dessine() {
        gridPane.getChildren().remove(0, gridPane.getChildren().size());
        labelChrono.setText("vous avez gagné en " + commandeIntChrono.exec() + " secondes");
        labelLevel.setText("vous avez gagné le niveau " + commandeIntLevel.exec());
        gridPane.add(labelLevel, 0, 0);
        gridPane.add(labelChrono, 0, 1);
    }

}
