package Projet;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHMFX extends Application implements Observateur {

    private static IHMFX singletonIHMFX;

    public static IHMFX getIHMFX() {
        if (singletonIHMFX == null)
            singletonIHMFX = new IHMFX();
        return singletonIHMFX;
    }


    private IHMFXVueNbPoussee vueNbPoussee;
    private IHMFXVueNbCoup vueNbCoup;
    private IHMFXVueChrono vueChrono;
    private IHMFXVueLevel vueLevel;
    public IHMFXVue vue;
    @SuppressWarnings("FieldCanBeLocal")
    private IHMFXVueMenu vueMenu;
    private IHMFXVueWin vueWin;


    public void actualise() {
        Platform.runLater(() -> {
                vueNbPoussee.dessine();
                vueNbCoup.dessine();
                vueChrono.dessine();
                vueLevel.dessine();
                vueWin.dessine();
                vue.dessine();
        });
    }


    public Stage menuStage;
    public Stage jeuStage;
    public Stage winStage;

    @Override
    public void start(Stage primaryStage) {
        menuStage = primaryStage;
        jeuStage = new Stage();
        winStage = new Stage();

        Controleur controleur = Controleur.getControleur();
        controleur.abonne(this);

        vueMenu = new IHMFXVueMenu();
        vueMenu.gridPane.setAlignment(Pos.CENTER);
        vue = new IHMFXVue(controleur);
        vue.gridPane.setAlignment(Pos.CENTER);
        vueWin = new IHMFXVueWin(controleur);
        vueWin.gridPane.setAlignment(Pos.CENTER);
        vueLevel = new IHMFXVueLevel(controleur);
        vueLevel.label.setAlignment(Pos.CENTER);
        vueChrono = new IHMFXVueChrono(controleur);
        vueChrono.label.setAlignment(Pos.CENTER);
        vueNbCoup= new IHMFXVueNbCoup(controleur);
        vueNbCoup.label.setAlignment(Pos.CENTER);
        vueNbPoussee = new IHMFXVueNbPoussee(controleur);
        vueNbPoussee.label.setAlignment(Pos.CENTER);

        IHMFXControleur IHMFXControleur = new IHMFXControleur(controleur, this);

        /* montage du menu */
        MonteurScene monteurSceneMenu = new MonteurScene();
        Scene sceneMenu = monteurSceneMenu.
                setCentre(vueMenu.gridPane).
                ajoutBas(IHMFXControleur.load).
                ajoutBas(IHMFXControleur.previousLevel).
                ajoutBas(vueLevel.label).
                ajoutBas(IHMFXControleur.nextLevel).
                ajoutBas(IHMFXControleur.play).
                setLargeur(500).
                setHauteur(300).
                retourneScene();


        /* montage de la scene */
        MonteurScene monteurScene = new MonteurScene();
        Scene scene = monteurScene.
                setCentre(vue.gridPane).
                ajoutBas(vue.label).
                ajoutBas(IHMFXControleur.up).
                ajoutBas(IHMFXControleur.down).
                ajoutBas(IHMFXControleur.left).
                ajoutBas(IHMFXControleur.right).
                ajoutBas(IHMFXControleur.undo).
                ajoutBas(IHMFXControleur.redo).
                ajoutBas(IHMFXControleur.replay).
                ajoutBas(IHMFXControleur.reset).
                ajoutBas(vueNbCoup.label).
                ajoutBas(vueNbPoussee.label).
                ajoutBas(vueChrono.label).
                ajoutBas(IHMFXControleur.closeJeu).
                setLargeur(800).
                setHauteur(700).
                retourneScene();


        MonteurScene monteurSceneWin = new MonteurScene();
        Scene sceneWin = monteurSceneWin.
                setCentre(vueWin.gridPane).
                ajoutBas(IHMFXControleur.close).
                setHauteur(200).
                setLargeur(400).
                retourneScene();


        winStage.setScene(sceneWin);
        winStage.setTitle("Winner");

        jeuStage.setScene(scene);
        jeuStage.setTitle("Jeu");

        menuStage.setScene(sceneMenu);
        menuStage.setTitle("Menu");
        menuStage.show();
    }

    public void lance() {
        launch();
    }

}
