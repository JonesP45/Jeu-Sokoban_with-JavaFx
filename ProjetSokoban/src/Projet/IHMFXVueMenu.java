package Projet;

import javafx.scene.layout.GridPane;

public class IHMFXVueMenu {

    public GridPane gridPane = new GridPane();
    public boolean vueCourante;

    @SuppressWarnings("WeakerAccess")
    public IHMFXVueMenu() {
        dessine();
    }

    public void dessine() {

    }

    public void setVueCourante(boolean bool) {
        vueCourante = bool;
    }

}
