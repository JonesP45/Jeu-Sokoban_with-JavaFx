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


    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                vue.gridPane.getChildren().clear();
                vueNbPoussee.dessine();
                vueNbCoup.dessine();
                vue.dessine();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controleur controleur = Controleur.getControleur();
        controleur.abonne(this);

        vue = new IHMFXVue(controleur);
        IHMFXControleur IHMFXControleur = new IHMFXControleur(controleur,vue);
        vue.gridPane.setAlignment(Pos.CENTER);
        vueNbCoup= new IHMFXVueNbCoup(controleur);
        vueNbCoup.label.setAlignment(Pos.CENTER);
        vueNbPoussee = new IHMFXVueNbPoussee(controleur);
        vueNbPoussee.label.setAlignment(Pos.CENTER);

        /* montage de la scene */
        MonteurScene monteurSceneMenu = new MonteurScene();
        Scene sceneMenu = monteurSceneMenu.
                retourneScene();

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

        primaryStage.setScene(scene);
        primaryStage.setTitle("Chameaux");
        primaryStage.show();

        vue.gridPane.requestFocus();
        IHMFXControleur.reset.setFocusTraversable(false);
    }

    public void lance() {
        launch(new String[]{});
    }

}
