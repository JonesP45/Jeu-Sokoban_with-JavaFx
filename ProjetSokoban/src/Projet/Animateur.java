package Projet;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Animateur {

    private Controleur controleur;
    private double interval = 1;
    private Timeline timer;

    public Animateur(Controleur controleur) {
        this.controleur = controleur;
        actualiseTimer();
    }

    private void actualiseTimer() {
        timer = new Timeline(
                new KeyFrame(Duration.seconds(interval),
                        (ActionEvent event) -> {

                        }
                )
        );
    }

    public void actualise() {
        if (timer.getStatus() == Animation.Status.RUNNING) {
            areter();
            actualiseTimer();
            demarer(1);
        } else {
            actualiseTimer();
        }
    }


    public void demarer(int time) {
        timer.setCycleCount(time);
        timer.play();
    }

    public void areter() {
        timer.stop();
    }

    public void remettreAZero() {

    }

    public Timeline getTimer() {
        return timer;
    }

}
