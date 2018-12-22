package Projet;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class IHMFXVue {

    private CommandeTab2DChar commandeGetPlateau;
    public GridPane gridPane = new GridPane();
    private int taille = 40;
    public Label label = new Label("Click\nOR\nPress");

    private Image mur = new Image(getClass().getResourceAsStream("WallRound_Brown.png"), taille, taille, false, false);
    private Image soko = new Image(getClass().getResourceAsStream("Character4.png"), taille, taille, false, false);
    private Image caisse = new Image(getClass().getResourceAsStream("CrateDark_Beige.png"), taille, taille, false, false);
    private Image caisseBut = new Image(getClass().getResourceAsStream("CrateDark_Blue.png"), taille, taille, false, false);
    private Image sokoBut = new Image(getClass().getResourceAsStream("Character7.png"), taille, taille, false, false);
    private Image but = new Image(getClass().getResourceAsStream("EndPoint_Red.png"), taille, taille, false, false);
    private Image sol = new Image(getClass().getResourceAsStream("Ground_Sand.png"), taille, taille, false, false);

    @SuppressWarnings("WeakerAccess")
    public IHMFXVue(Controleur controleur) {
        commandeGetPlateau = controleur.commandeGetPlateau();
        dessine();
    }

    public void dessine() {
        gridPane.getChildren().remove(0, gridPane.getChildren().size());
        initialiseFond();
        for (int i = 0; i < commandeGetPlateau.exec().length; i++) {
            for (int j = 0; j < commandeGetPlateau.exec()[0].length; j++) {
                char caze = commandeGetPlateau.exec()[i][j];
                Image tmp = null;
                switch (caze) {
                    case '#': tmp = mur; break;
                    case '@': tmp = soko; break;
                    case '$': tmp = caisse; break;
                    case '.': tmp = but; break;
                    case ' ': tmp = sol; break;
                    case '+': tmp = sokoBut; break;
                    case '*': tmp = caisseBut; break;
                }
                gridPane.add(new ImageView(tmp), j, i);
            }
        }
    }

    private void initialiseFond() {
        for (int i = 0; i < commandeGetPlateau.exec().length; i++) {
            for (int j = 0; j < commandeGetPlateau.exec()[0].length; j++) {
                char caze = commandeGetPlateau.exec()[i][j];
                if (caze != 'm')
                    gridPane.add(new ImageView(sol), j, i);
            }
        }
    }

}
