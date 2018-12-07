package Projet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class IHMFXControleur {

    private Controleur controleur;
    private IHMFXVue vue;
    Button reset;

    IHMFXControleur(Controleur controleur, IHMFXVue vue) {
        this.controleur = controleur;
        this.vue = vue;

        this.vue.gridPane.setOnKeyPressed(new ActionMove());
        this.vue.gridPane.requestFocus();

        reset = new Button("Reset");
        reset.setOnAction(new ActionReset());
    }

    class ActionReset implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            controleur.reset();
        }

    }

    class ActionMove implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            System.out.println("thgfhg");
            KeyCode input = event.getCode();
            String direction;
            if (input.equals(KeyCode.LEFT)) {
                direction = "left";
            } else if (input.equals(KeyCode.RIGHT)) {
                direction = "right";
            } else if (input.equals(KeyCode.UP)) {
                direction = "up";
            } else if (input.equals(KeyCode.DOWN)) {
                direction = "down";
            } else {
                direction = "null";
            }
            controleur.move(direction);
        }
    }

}
