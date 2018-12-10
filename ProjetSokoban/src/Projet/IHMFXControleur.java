package Projet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class IHMFXControleur {

    private Controleur controleur;
    @SuppressWarnings("FieldCanBeLocal")
    private IHMFXVue vue;

    //menu
    public Button load;
    public Button nextlevel;
    public Button previousLevel;
    public Button play;

    //jeu
    public Button up;
    public Button down;
    public Button left;
    public Button right;
    public Button undo;
    public Button redo;
    public Button replay;
    public Button reset;

    public ArrayList<char[][]> theFiles = new ArrayList<>();

    IHMFXControleur(Controleur controleur, IHMFXVue vue) {
        this.controleur = controleur;
        this.vue = vue;

        //menu
        load = new Button("Load");
        load.setOnAction(new ActionLoad());
        previousLevel = new Button("Previous level");
        nextlevel = new Button("Next Level");
        play = new Button("Play");
        play.setOnAction(new ActionPlay());

        //jeu
        this.vue.gridPane.setOnKeyPressed(new ActionKeyboard());

        up = new Button("Up");
        down = new Button("Down");
        left = new Button("Left");
        right = new Button("Right");
        undo = new Button("Undo");
        redo = new Button("Redo");
        replay = new Button("Replay");
        reset = new Button("Reset");
        reset.setOnAction(new ActionReset());
    }



    class ActionLoad implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //methode pour charger les fichiers
        }

    }

    class ActionPlay implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
//            vueMenu.setVueCourante(false);
            controleur.play();
        }
    }


    class ActionKeyboard implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode input = event.getCode();
            if (input.equals(KeyCode.LEFT)) {
                controleur.move("left");
            } else if (input.equals(KeyCode.RIGHT)) {
                controleur.move("right");
            } else if (input.equals(KeyCode.DOWN)) {
                controleur.move("down");
            } else if (input.equals(KeyCode.UP)) {
                controleur.move("up");
            } else if (input.equals(KeyCode.U)) {
                controleur.undo();
            } else if (input.equals(KeyCode.R)) {
                controleur.redo();
            } else if (input.equals(KeyCode.Z)) {
                controleur.reset();
            } else if (input.equals(KeyCode.P)) {
                controleur.replay();
            }
        }
    }

    class ActionReset implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.reset();
        }

    }

}
