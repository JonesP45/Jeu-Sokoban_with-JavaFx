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
    private boolean loadOnAction = false;
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

    //win
    public Button close;


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
        up.setOnAction(new ActionUp());
        down = new Button("Down");
        down.setOnAction(new ActionDown());
        left = new Button("Left");
        left.setOnAction(new ActionLeft());
        right = new Button("Right");
        right.setOnAction(new ActionRight());
        undo = new Button("Undo");
        undo.setOnAction(new ActionUndo());
        redo = new Button("Redo");
        redo.setOnAction(new ActionRedo());
        replay = new Button("Replay");
        replay.setOnAction(new ActionReplay());
        reset = new Button("Reset");
        reset.setOnAction(new ActionReset());

        //win
        close = new Button("Close");
        close.setOnAction(new ActionClose());
    }


    class ActionLoad implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                controleur.load("MicroCosmos.txt");
                loadOnAction = true;
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
            if (loadOnAction)
                controleur.previousLevel();
        }
    }

    class ActionNextLevel implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (loadOnAction)
                controleur.nextLevel();
        }
    }

    class ActionPlay implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (loadOnAction) {
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
    }



    class ActionKeyboard implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode input = event.getCode();
            if (input.equals(KeyCode.LEFT)) {
                if (controleur.move("left")) {
                    ihmfx.winStage.show();
                }
            } else if (input.equals(KeyCode.RIGHT)) {
                if (controleur.move("right")) {
                    ihmfx.winStage.show();
                }
            } else if (input.equals(KeyCode.DOWN)) {
                if (controleur.move("down")) {
                    ihmfx.winStage.show();
                }
            } else if (input.equals(KeyCode.UP)) {
                if (controleur.move("up")) {
                    ihmfx.winStage.show();
                }
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


    class ActionUp implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.move("up");
        }

    }

    class ActionDown implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.move("down");
        }

    }

    class ActionLeft implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.move("left");
        }

    }

    class ActionRight implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.move("right");
        }

    }

    class ActionUndo implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.undo();
        }

    }

    class ActionRedo implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.redo();
        }

    }

    class ActionReplay implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.play();
            controleur.replay();
        }

    }

    class ActionReset implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.play();
            controleur.reset();
        }
    }


    class ActionClose implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ihmfx.winStage.close();
            controleur.clear();
            controleur.play();
        }
    }

}
