package Projet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;

public class Controleur implements Sujet {

    private static Controleur singleton;

    public static Controleur getControleur() {
        if (singleton == null)
            singleton = new Controleur(new FacadeModele());
        return singleton;
    }


    private FacadeModele facadeModele;
    private ArrayList<Observateur> observateurs = new ArrayList<Observateur>();

    private Controleur(FacadeModele facadeModele) {
        this.facadeModele = facadeModele;
    }

    public void abonne(Observateur observateur) {
        observateurs.add(observateur);
    }

    @Override
    public void notifie() {
        for (Observateur observateur:observateurs)
            observateur.actualise();
    }

    public void move(String direction) {
        facadeModele.move(direction);
        notifie();
    }

    public void undo() {
        facadeModele.undo();
        notifie();
    }

    public void redo() {
        facadeModele.redo();
        notifie();
    }

    private static int i = 0;
    public void replay() {
        ArrayList<String> theMoves = facadeModele.getMoves();
        reset();
        Timeline timer = new Timeline(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                        if (theMoves.get(i).equals("right") || theMoves.get(i).equals("rightCaisse")) {
                            move("right");
                        } else if (theMoves.get(i).equals("up") || theMoves.get(i).equals("upCaisse")) {
                            move("up");
                        } else if (theMoves.get(i).equals("down") || theMoves.get(i).equals("downCaisse")) {
                            move("down");
                        } else if (theMoves.get(i).equals("left") || theMoves.get(i).equals("leftCaisse")) {
                            move("left");
                        }
                        i++;
                    }
                })
        );
        timer.setCycleCount(theMoves.size());
        if (theMoves.size() > 0) {
            timer.play();
//            timer.stop();
        }
        i = 0;
        notifie();
    }

    public void reset() {
        facadeModele.reset();
        notifie();
    }

    public CommandeInt commandeNbCoup() { return () -> facadeModele.getNbCoup(); }

    public CommandeInt commandeNbPoussee() { return () -> facadeModele.getNbPoussee(); }

    public CommandeTab2DChar commandeGetPlateau() { return () -> facadeModele.getPlateau(); }

}
