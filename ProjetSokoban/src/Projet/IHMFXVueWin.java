package Projet;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class IHMFXVueWin {

    public GridPane gridPane = new GridPane();
    private CommandeInt commandeIntLevel;
    private CommandeInt commandeIntNbCoup;
    private CommandeInt commandeIntNbPoussee;
    private CommandeInt commandeIntChrono;
    private Label label = new Label("vous avez gagné :");
    private Label labelLevel = new Label();
    private Label labelChrono = new Label();
    private Label labelNbCoup = new Label();
    private Label labelNbPoussee = new Label();

    @SuppressWarnings("WeakerAccess")
    public IHMFXVueWin(Controleur controleur) {
        commandeIntLevel = controleur.commandeLevel();
        commandeIntChrono = controleur.commandeChrono();
        commandeIntNbCoup = controleur.commandeNbCoup();
        commandeIntNbPoussee = controleur.commandeNbPoussee();
        dessine();
    }


    public void dessine() {
        gridPane.getChildren().remove(0, gridPane.getChildren().size());
        labelLevel.setText("     le niveau " + commandeIntLevel.exec());
        labelChrono.setText("     en " + commandeIntChrono.exec() + " secondes");
        labelNbCoup.setText("     en " + commandeIntNbCoup.exec() + " deplacements");
        labelNbPoussee.setText("     en " + commandeIntNbPoussee.exec() + " poussées");
        gridPane.add(label, 0, 0);
        gridPane.add(labelLevel, 0, 1);
        gridPane.add(labelChrono, 0, 2);
        gridPane.add(labelNbCoup, 0, 3);
        gridPane.add(labelNbPoussee, 0, 4);
    }

}
