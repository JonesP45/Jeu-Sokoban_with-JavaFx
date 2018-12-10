package Projet;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class ModeleConcret implements Modele {

    private final char mur = 'm';
    private final char soko = 's';
    private final char caisse = 'c';
    private final char but = 'b';
    private final char sol = ' ';
    private final char sokoSurBut = '$';
    private final char caisseSurBut = '€';

    private char[][] plateau = initialisePlateau();
    private int x_soko;
    private int y_soko;

//    boolean coupValide = false;
//    boolean pousseeValide = false;

    public ArrayList<String> theMoves = new ArrayList<>();
    private ArrayList<String> theUndos = new ArrayList<>();

    private void ajoutMove(int moveX, int moveXcaisse, int moveY, int moveYcaisse) {
        if (moveX != 0) {
            if (moveXcaisse == 0) {
                if (moveX > 0)
                    theMoves.add("down");
                else
                    theMoves.add("up");
            }
            else {
                if (moveX > 0)
                    theMoves.add("downCaisse");
                else
                    theMoves.add("upCaisse");
            }
        }
        else {
            if (moveYcaisse == 0) {
                if (moveY > 0)
                    theMoves.add("right");
                else
                    theMoves.add("left");
            }
            else {
                if (moveY > 0)
                    theMoves.add("rightCaisse");
                else
                    theMoves.add("leftCaisse");
            }
        }
    }

    private void afficherArrayListString(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        System.out.println();
    }


    private char[][] initialisePlateau() {
        int hauteur = 7; //ligne
        int largeur = 9; //colonne
        char[][] _plateau = new char[hauteur][largeur];

        for (int j = 0; j < _plateau[0].length; j++) {
            _plateau[0][j] = mur;
            _plateau[hauteur - 1][j] = mur;
        }
        for (int i = 0; i < _plateau.length; i++) {
            _plateau[i][0] = mur;
            _plateau[i][largeur - 1] = mur;
        }
        for (int i = 1; i < _plateau.length - 1; i++) {
            for (int j = 1; j < _plateau[0].length - 1; j++) {
                _plateau[i][j] = sol;
            }
        }

        x_soko = 1;
        y_soko = 1;
        _plateau[x_soko][y_soko] = soko;
        _plateau[2][4] = caisse;
        _plateau[3][4] = but;

        return _plateau;
    }

    private void afficherPlateau(char[][] _plateau) {
        for (int i = 0; i < _plateau.length; i++) {
            for (int j = 0; j < _plateau[0].length; j++) {
                System.out.print(_plateau[i][j] + " ");
            }
            System.out.println();
        }
    }


    @Override
    public void move(String direction) {
//        afficherPlateau(plateau);
        theUndos.clear();
        switch (direction) {
            case "up":
                moveUp();
                break;
            case "down" :
                moveDown();
                break;
            case "left" :
                moveLeft();
                break;
            case "right" :
                moveRight();
                break;
        }
    }


    private void moveUp() {
        if (plateau[x_soko][y_soko] == soko) {
            moveIt(-1, -2, 0, 0, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            moveIt(-1, -2, 0, 0, but);
        }
    }

    private void moveDown() {
        if (plateau[x_soko][y_soko] == soko) {
            moveIt(1, 2, 0, 0, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            moveIt(1, 2, 0, 0, but);
        }
    }

    private void moveLeft() {
        if (plateau[x_soko][y_soko] == soko) {
            moveIt(0, 0, -1, -2, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            moveIt(0, 0, -1, -2, but);
        }
    }

    private void moveRight() {
        if (plateau[x_soko][y_soko] == soko) {
            moveIt(0, 0, 1, 2, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            moveIt(0, 0, 1, 2, but);
        }
    }


    private void moveIt(int moveX1, int moveX2, int moveY1, int moveY2, char change) {
        switch (plateau[x_soko + moveX1][y_soko + moveY1]) {
            case sol:
                plateau[x_soko][y_soko] = change;
                x_soko += moveX1;
                y_soko += moveY1;
                plateau[x_soko][y_soko] = soko;
                ajoutMove(moveX1, 0, moveY1, 0);
                break;
            case but:
                plateau[x_soko][y_soko] = change;
                x_soko += moveX1;
                y_soko += moveY1;
                plateau[x_soko][y_soko] = sokoSurBut;
                ajoutMove(moveX1, 0, moveY1, 0);
                break;
            case caisse:
                if (plateau[x_soko + moveX2][y_soko + moveY2] == sol) {
                    plateau[x_soko][y_soko] = change;
                    x_soko += moveX1;
                    y_soko += moveY1;
                    plateau[x_soko][y_soko] = soko;
                    plateau[x_soko + moveX1][y_soko + moveY1] = caisse;
                    ajoutMove(moveX1, moveX2, moveY1, moveY2);
                }
                else if (plateau[x_soko + moveX2][y_soko + moveY2] == but) {
                    plateau[x_soko][y_soko] = change;
                    x_soko += moveX1;
                    y_soko += moveY1;
                    plateau[x_soko][y_soko] = soko;
                    plateau[x_soko + moveX1][y_soko + moveY1] = caisseSurBut;
                    ajoutMove(moveX1, moveX2, moveY1, moveY2);
                }
                break;
            case caisseSurBut:
                if (plateau[x_soko + moveX2][y_soko + moveY2] == sol) {
                    plateau[x_soko][y_soko] = change;
                    x_soko += moveX1;
                    y_soko += moveY1;
                    plateau[x_soko][y_soko] = sokoSurBut;
                    plateau[x_soko + moveX1][y_soko + moveY1] = caisse;
                    ajoutMove(moveX1, moveX2, moveY1, moveY2);
                }
                else if (plateau[x_soko + moveX2][y_soko + moveY2] == but) {
                    plateau[x_soko][y_soko] = change;
                    x_soko += moveX1;
                    y_soko += moveY1;
                    plateau[x_soko][y_soko] = sokoSurBut;
                    plateau[x_soko + moveX1][y_soko + moveY1] = caisseSurBut;
                    ajoutMove(moveX1, moveX2, moveY1, moveY2);
                }
                break;
        }
    }


    @Override
    public void undo() {
        if (theMoves.size() > 0) {
            char var1 = caisse;
            char var2 = sol;
            switch (theMoves.get(theMoves.size() - 1)) {
                case "up":
                    moveDown();
                    theUndos.add("down");
                    break;
                case "down":
                    moveUp();
                    theUndos.add("up");
                    break;
                case "right":
                    moveLeft();
                    theUndos.add("left");
                    break;
                case "left":
                    moveRight();
                    theUndos.add("right");
                    break;
                case "upCaisse":
                    if (plateau[x_soko][y_soko] == soko) {
                        if (plateau[x_soko - 1][y_soko] == caisseSurBut) {
                            var2 = but;
                        }
                    }
                    else if (plateau[x_soko][y_soko] == sokoSurBut) {
                        if (plateau[x_soko - 1][y_soko] == caisse) {
                            var1 = caisseSurBut;
                        }
                        else if (plateau[x_soko - 1][y_soko] == caisseSurBut) {
                            var1 = caisseSurBut;
                            var2 = but;
                        }
                    }
                    moveDown();
                    theUndos.add("down");
                    plateau[x_soko - 2][y_soko] = var2;
                    plateau[x_soko - 1][y_soko] = var1;
                    break;
                case "downCaisse":
                    if (plateau[x_soko][y_soko] == soko) {
                        if (plateau[x_soko + 1][y_soko] == caisseSurBut) {
                            var2 = but;
                        }
                    }
                    else if (plateau[x_soko][y_soko] == sokoSurBut) {
                        if (plateau[x_soko + 1][y_soko] == caisse) {
                            var1 = caisseSurBut;
                        }
                        else if (plateau[x_soko + 1][y_soko] == caisseSurBut) {
                            var1 = caisseSurBut;
                            var2 = but;
                        }
                    }
                    moveUp();
                    theUndos.add("up");
                    plateau[x_soko + 2][y_soko] = var2;
                    plateau[x_soko + 1][y_soko] = var1;
                    break;
                case "rightCaisse":
                    if (plateau[x_soko][y_soko] == soko) {
                        if (plateau[x_soko][y_soko + 1] == caisseSurBut) {
                            var2 = but;
                        }
                    }
                    else if (plateau[x_soko][y_soko] == sokoSurBut) {
                        if (plateau[x_soko][y_soko + 1] == caisse) {
                            var1 = caisseSurBut;
                        }
                        else if (plateau[x_soko][y_soko + 1] == caisseSurBut) {
                            var1 = caisseSurBut;
                            var2 = but;
                        }
                    }
                    moveLeft();
                    theUndos.add("left");
                    plateau[x_soko][y_soko + 2] = var2;
                    plateau[x_soko][y_soko + 1] = var1;
                    break;
                case "leftCaisse":
                    if (plateau[x_soko][y_soko] == soko) {
                        if (plateau[x_soko][y_soko - 1] == caisseSurBut) {
                            var2 = but;
                        }
                    }
                    else if (plateau[x_soko][y_soko] == sokoSurBut) {
                        if (plateau[x_soko][y_soko - 1] == caisse) {
                            var1 = caisseSurBut;
                        }
                        else if (plateau[x_soko][y_soko - 1] == caisseSurBut) {
                            var1 = caisseSurBut;
                            var2 = but;
                        }
                    }
                    moveRight();
                    theUndos.add("right");
                    plateau[x_soko][y_soko - 2] = var2;
                    plateau[x_soko][y_soko - 1] = var1;
                    break;
            }
            theMoves.remove(theMoves.size() - 1); //suppression du move undo
            theMoves.remove(theMoves.size() - 1); //suppression du move contraire au undo
        }
    }

    @Override
    public void redo() {
        if (theUndos.size() > 0) {
            switch (theUndos.get(theUndos.size() - 1)) {
                case "up":
                    moveDown();
                    theMoves.add("down");
                    break;
                case "down":
                    moveUp();
                    theMoves.add("up");
                    break;
                case "right":
                    moveLeft();
                    theMoves.add("left");
                    break;
                case "left":
                    moveRight();
                    theMoves.add("right");
                    break;
            }
            theUndos.remove(theUndos.size() - 1);
        }
    }


    private static int i = 0;
    @Override
    public void replay() {
        Timeline timer = new Timeline(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        if (theMoves.get(i).equals("right") || theMoves.get(i).equals("rightCaisse")) {
                            moveRight();
                        } else if (theMoves.get(i).equals("up") || theMoves.get(i).equals("upCaisse")) {
                            moveUp();
                        } else if (theMoves.get(i).equals("down") || theMoves.get(i).equals("downCaisse")) {
                            moveDown();
                        } else if (theMoves.get(i).equals("left") || theMoves.get(i).equals("leftCaisse")) {
                            moveLeft();
                        }
                        i++;
                    }
                })
        );
        timer.setCycleCount(theMoves.size());
        if (theMoves.size() > 0) {
            timer.play();
            timer.stop();
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void reset() {
        plateau = initialisePlateau();
        theMoves.clear();
        theUndos.clear();
    }

    @Override
    public char[][] getPlateau() {
        return plateau;
    }

    @Override
    public ArrayList<String> getMoves() {
        return theMoves;
    }

}
