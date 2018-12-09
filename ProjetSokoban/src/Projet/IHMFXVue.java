package Projet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class IHMFXVue {

    private CommandeTab2DChar commandeGetPlateau;
    GridPane gridPane = new GridPane();
    private int taille = 40;

    private Image mur = new Image(new FileInputStream("WallRound_Brown.png"), taille, taille, false, false);
    private Image soko = new Image(new FileInputStream("Character4.png"), taille, taille, false, false);
    private Image caisse = new Image(new FileInputStream("CrateDark_Beige.png"), taille, taille, false, false);
    private Image caisseBut = new Image(new FileInputStream("CrateDark_Blue.png"), taille, taille, false, false);
    private Image sokoBut = new Image(new FileInputStream("Character7.png"), taille, taille, false, false);
    private Image but = new Image(new FileInputStream("EndPoint_Red.png"), taille, taille, false, false);
    private Image sol = new Image(new FileInputStream("Ground_Sand.png"), taille, taille, false, false);


    public IHMFXVue(Controleur controleur) throws FileNotFoundException {
        commandeGetPlateau = controleur.commandeGetPlateau();
        dessine();
    }

    public void dessine() {
        for (int i = 0; i < commandeGetPlateau.exec().length; i++) {
            for (int j = 0; j < commandeGetPlateau.exec()[0].length; j++) {
                char caze = commandeGetPlateau.exec()[i][j];
                Image tmp = null;
                switch (caze) {
                    case 'm': tmp = mur; break;
                    case 's': tmp = soko; break;
                    case 'c': tmp = caisse; break;
                    case 'b': tmp = but; break;
                    case ' ': tmp = sol; break;
                    case '$': tmp = sokoBut; break;
                    case '€': tmp = caisseBut; break;
                }
                gridPane.add(new ImageView(tmp), j, i);
            }
        }
    }

}
