package Projet;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHMFX extends Application implements Observateur {

    private IHMFXVueNbPoussee vueNbPoussee;
    private IHMFXVueNbCoup vueNbCoup;
    private IHMFXVue vue;
    private IHMFXVueMenu vueMenu;


    public void actualise() {
        Platform.runLater(() -> {
                vueNbPoussee.dessine();
                vueNbCoup.dessine();
                vue.dessine();
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controleur controleur = Controleur.getControleur();
        controleur.abonne(this);

        vueMenu = new IHMFXVueMenu();
        vueMenu.gridPane.setAlignment(Pos.CENTER);
        vue = new IHMFXVue(controleur);
        vue.gridPane.setAlignment(Pos.CENTER);
        vueNbCoup= new IHMFXVueNbCoup(controleur);
        vueNbCoup.label.setAlignment(Pos.CENTER);
        vueNbPoussee = new IHMFXVueNbPoussee(controleur);
        vueNbPoussee.label.setAlignment(Pos.CENTER);

        IHMFXControleur IHMFXControleur = new IHMFXControleur(controleur,vue);

        /* montage du menu */
        MonteurScene monteurSceneMenu = new MonteurScene();
        Scene sceneMenu = monteurSceneMenu.
                setCentre(vueMenu.gridPane).
                ajoutBas(IHMFXControleur.load).
                ajoutBas(IHMFXControleur.previousLevel).
                ajoutBas(IHMFXControleur.nextlevel).
                ajoutBas(IHMFXControleur.play).
                setLargeur(500).
                setHauteur(500).
                retourneScene();


        Stage secondaryStage = new Stage();

        /* montage de la scene */
        MonteurScene monteurScene = new MonteurScene();
        Scene scene = monteurScene.
                setCentre(vue.gridPane).
                ajoutBas(IHMFXControleur.reset).
                ajoutBas(vueNbCoup.label).
                ajoutBas(vueNbPoussee.label).
                setLargeur(800).
                setHauteur(700).
                retourneScene();


        secondaryStage.setScene(scene);
        secondaryStage.setTitle("Sokoban\nJeu");
        secondaryStage.show();

        primaryStage.setScene(sceneMenu);
        primaryStage.setTitle("Sokoban\nMenu");
        primaryStage.show();


        vue.gridPane.requestFocus();
        IHMFXControleur.up.setFocusTraversable(false);
        IHMFXControleur.down.setFocusTraversable(false);
        IHMFXControleur.left.setFocusTraversable(false);
        IHMFXControleur.right.setFocusTraversable(false);
        IHMFXControleur.undo.setFocusTraversable(false);
        IHMFXControleur.redo.setFocusTraversable(false);
        IHMFXControleur.replay.setFocusTraversable(false);
        IHMFXControleur.reset.setFocusTraversable(false);
    }

    public void lance() {
        launch();
    }

}
