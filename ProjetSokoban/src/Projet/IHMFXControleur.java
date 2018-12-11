package Projet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.util.ArrayList;

public class IHMFXControleur {

    private class ArraylistToArray {

        private int ligne;
        private int colonne;
        private char valeur;

        private ArraylistToArray(int l, int c, char v) {
            ligne = l;
            colonne = c;
            valeur = v;
        }

    }

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

    private int level = 0;
    private ArrayList<char[][]> theFiles = new ArrayList<>();

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
                load("MicroCosmos.txt");
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException");
            } catch (Exception e) {
                System.out.println("Exception");
            }
        }
    }

    private void load(String fileName) throws Exception {
        ArrayList<ArraylistToArray> list = new ArrayList<>();
        int largeur = 0;
        int hauteur = 0;
        FileInputStream fis = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line = reader.readLine();
        while (line != null) {
            if (line.charAt(0) != ';') {
                if (line.length() > largeur) {
                    largeur = line.length();
                }
                for (int i = 0; i < line.length(); i++) {
                    list.add(new ArraylistToArray(hauteur, i, line.charAt(i)));
                }
                hauteur++;
                line = reader.readLine();
            } else {
                char[][] plateau = new char[hauteur][largeur];
                int i = 0;
                int j = 0;
                for (int k = 0; k < list.size(); k++) {
                    ArraylistToArray tmp = list.get(k);
                    if (tmp.ligne == i && tmp.colonne == j) {
                        plateau[tmp.ligne][tmp.colonne] = tmp.valeur;
                        j++;
                        if (j % largeur == 0) {
                            i++;
                            j = 0;
                        }
                    } else {
                        while (j < largeur) {
                            plateau[i][j] = ' ';
                            j++;
                        }
                        i++;
                        j = 0;
                        plateau[tmp.ligne][tmp.colonne] = tmp.valeur;
                        j++;
                    }
                } // fin for
                while (i < hauteur && j < largeur) {
                    plateau[i][j] = ' ';
                    j++;
                }
                theFiles.add(plateau);
                list.clear();
                largeur = 0;
                hauteur = 0;
                line = reader.readLine();
                line = reader.readLine();
            } //finElse
            level++;
        }
        level = 0;
    }

    class ActionPreviousLevel implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (level == 0) {
                level = theFiles.size() - 1;
            } else {
                level--;
            }
        }
    }

    class ActionNextLevel implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (level == theFiles.size() - 1) {
                level = 0;
            } else {
                level++;
            }
        }
    }

    class ActionPlay implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.play(cloneChar2DTab(theFiles.get(level)));
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
                controleur.play(cloneChar2DTab(theFiles.get(level)));
                controleur.reset();
            } else if (input.equals(KeyCode.P)) {
                controleur.play(cloneChar2DTab(theFiles.get(level)));
                controleur.replay();
            }
        }
    }

    class ActionReset implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controleur.play(cloneChar2DTab(theFiles.get(level)));
            controleur.reset();
        }

    }

    private char[][] cloneChar2DTab(char[][] tab) {
        char[][] res = new char[tab.length][tab[0].length];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                res[i][j] = tab[i][j];
            }
        }
        return res;
    }

}
