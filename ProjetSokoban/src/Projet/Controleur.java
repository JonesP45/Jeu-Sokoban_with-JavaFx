package Projet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.util.ArrayList;

public class Controleur implements Sujet {

    private static Controleur singletonControleur;

    public static Controleur getControleur() {
        if (singletonControleur == null)
            singletonControleur = new Controleur(new FacadeModele());
        return singletonControleur;
    }


    private FacadeModele facadeModele;
    private ArrayList<Observateur> observateurs = new ArrayList<>();
    private char[][] plateau;

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

    public void play(char[][] plateau) {
        this.plateau = plateau;
        facadeModele.play(plateau);
        notifie();
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
        play(plateau);
        reset();
        Timeline timer = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        (ActionEvent event) -> {
                            switch (theMoves.get(i)) {
                                case "right":
                                    move("right"); break;
                                case "rightCaisse":
                                    move("right"); break;
                                case "up":
                                    move("up"); break;
                                case "upCaisse":
                                    move("up"); break;
                                case "down":
                                    move("down"); break;
                                case "downCaisse":
                                    move("down"); break;
                                case "left":
                                    move("left"); break;
                                case "leftCaisse":
                                    move("left"); break;
                            }
                            i++;
                        })
        );
        timer.setCycleCount(theMoves.size());
        if (theMoves.size() > 0) {
            timer.play();
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
