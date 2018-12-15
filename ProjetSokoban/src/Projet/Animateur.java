package Projet;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Animateur {

    private Controleur controleur;
    @SuppressWarnings("FieldCanBeLocal")
    private double interval = 1;
    private Timeline timer;
    private int valeur = 0;

    @SuppressWarnings("WeakerAccess")
    public Animateur(Controleur controleur) {
        this.controleur = controleur;
        actualiseTimer();
    }

    private void actualiseTimer() {
        timer = new Timeline(
                new KeyFrame(Duration.seconds(interval),
                        (ActionEvent event) -> controleur.incrementerAnimateur()
                )
        );
        timer.setCycleCount(Animation.INDEFINITE);
    }

    public void incrementer() {
        valeur++;
    }

    public void demarer() {
        timer.play();
    }

    public void pause() {
        timer.pause();
    }

    public void areter() {
        timer.stop();
    }

    public void reinitialiser() {
        valeur = 0;
    }

    public int getTime() {
        return valeur;
    }

    public Timeline getTimer() {
        return timer;
    }

}
