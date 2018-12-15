package Projet;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class IHMFXVueMenu {

    public GridPane gridPane = new GridPane();
    public Label label = new Label("Sokoban");

    @SuppressWarnings("WeakerAccess")
    public IHMFXVueMenu() {
        gridPane.add(label, 0, 0);
        dessine();
    }

    @SuppressWarnings("WeakerAccess")
    public void dessine() {

    }

}
