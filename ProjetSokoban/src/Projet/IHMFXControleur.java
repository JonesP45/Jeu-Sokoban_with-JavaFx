package Projet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;

public class IHMFXControleur {

    private IHMFX ihmfx;
    private Controleur controleur;
    @SuppressWarnings("FieldCanBeLocal")
    private IHMFXVue vue;

    //menu
    public Button load;
    public Button nextLevel;
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


    IHMFXControleur(Controleur controleur, IHMFX ihmfx) {
        this.ihmfx = ihmfx;
        this.controleur = controleur;
        this.vue = ihmfx.vue;

        //menu
        load = new Button("Load");
        load.setOnAction(new ActionLoad());
        previousLevel = new Button("Previous level");
        previousLevel.setOnAction(new ActionPreviousLevel());
        nextLevel = new Button("Next Level");
        nextLevel.setOnAction(new ActionNextLevel());
        play = new Button("Play");
        play.setOnAction(new ActionPlay());

        //jeu
        this.vue.gridPane.setOnKeyPressed(new ActionKeyboard());

        up = new Button("Up");
        up.setOnAction(null);
        down = new Button("Down");
        down.setOnAction(null);
        left = new Button("Left");
        left.setOnAction(null);
        right = new Button("Right");
        right.setOnAction(null);
        undo = new Button("Undo");
        undo.setOnAction(null);
        redo = new Button("Redo");
        redo.setOnAction(null);
        replay = new Button("Replay");
        replay.setOnAction(null);
        reset = new Button("Reset");
        reset.setOnAction(new ActionReset());
    }


    class ActionLoad implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                controleur.load("MicroCosmos.txt");
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException");
            } catch (Exception e) {
                System.out.println("Exception");
            }
        }
    }

    class ActionPreviousLevel implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.previousLevel();
        }
    }

    class ActionNextLevel implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.nextLevel();
        }
    }

    class ActionPlay implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.play();
            ihmfx.menuStage.close();
            ihmfx.jeuStage.show();
            vue.gridPane.requestFocus();
            up.setFocusTraversable(false);
            down.setFocusTraversable(false);
            left.setFocusTraversable(false);
            right.setFocusTraversable(false);
            undo.setFocusTraversable(false);
            redo.setFocusTraversable(false);
            replay.setFocusTraversable(false);
            reset.setFocusTraversable(false);
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
                controleur.play();
                controleur.reset();
            } else if (input.equals(KeyCode.P)) {
                controleur.play();
                controleur.replay();
            }
        }
    }

    class ActionReset implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.play();
            controleur.reset();
        }

    }

//    private char[][] cloneChar2DTab(char[][] tab) {
//        char[][] res = new char[tab.length][tab[0].length];
//        for (int i = 0; i < tab.length; i++) {
//            for (int j = 0; j < tab[0].length; j++) {
//                res[i][j] = tab[i][j];
//            }
//        }
//        return res;
//    }

}
