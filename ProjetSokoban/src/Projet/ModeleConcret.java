package Projet;

import java.util.ArrayList;

public class ModeleConcret implements Modele {

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final char mur = '#';
    private final char soko = '@';
    private final char caisse = '$';
    private final char but = '.';
    private final char sol = ' ';
    private final char sokoSurBut = '+';
    private final char caisseSurBut = '*';

    private char[][] plateau = {};
    private int x_soko;
    private int y_soko;

    private boolean etat = false;

    private ArrayList<String> theMoves = new ArrayList<>();
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


    @Override
    public void charger(char[][] plateau) {
        this.plateau = plateau;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                if (plateau[i][j] == soko || plateau[i][j] == sokoSurBut) {
                    x_soko = i;
                    y_soko = j;
                }
            }
        }
    }

    @Override
    public boolean[] move(String direction) {
        theUndos.clear();
        switch (direction) {
            case "up":
                return moveUp();
            case "down" :
                return moveDown();
            case "left" :
                return moveLeft();
            case "right" :
                return moveRight();
            default:
                return null;
        }
    }


    private boolean[] moveUp() {
        if (plateau[x_soko][y_soko] == soko) {
            return moveIt(-1, -2, 0, 0, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            return moveIt(-1, -2, 0, 0, but);
        }
        return null;
    }

    private boolean[] moveDown() {
        if (plateau[x_soko][y_soko] == soko) {
            return moveIt(1, 2, 0, 0, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            return moveIt(1, 2, 0, 0, but);
        }
        return null;
    }

    private boolean[] moveLeft() {
        if (plateau[x_soko][y_soko] == soko) {
            return moveIt(0, 0, -1, -2, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            return moveIt(0, 0, -1, -2, but);
        }
        return null;
    }

    private boolean[] moveRight() {
        if (plateau[x_soko][y_soko] == soko) {
            return moveIt(0, 0, 1, 2, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            return moveIt(0, 0, 1, 2, but);
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
    private boolean[] moveIt(int moveX1, int moveX2, int moveY1, int moveY2, char change) {
        boolean coupPoussee[] = {true, false};
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
                    coupPoussee[1] = true;
                }
                else if (plateau[x_soko + moveX2][y_soko + moveY2] == but) {
                    plateau[x_soko][y_soko] = change;
                    x_soko += moveX1;
                    y_soko += moveY1;
                    plateau[x_soko][y_soko] = soko;
                    plateau[x_soko + moveX1][y_soko + moveY1] = caisseSurBut;
                    ajoutMove(moveX1, moveX2, moveY1, moveY2);
                    coupPoussee[1] = true;
                    etat = setEtat();
                } else {
                    coupPoussee[0] = false;
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
                    coupPoussee[1] = true;
                }
                else if (plateau[x_soko + moveX2][y_soko + moveY2] == but) {
                    plateau[x_soko][y_soko] = change;
                    x_soko += moveX1;
                    y_soko += moveY1;
                    plateau[x_soko][y_soko] = sokoSurBut;
                    plateau[x_soko + moveX1][y_soko + moveY1] = caisseSurBut;
                    ajoutMove(moveX1, moveX2, moveY1, moveY2);
                    coupPoussee[1] = true;
                    etat = setEtat();
                } else {
                    coupPoussee[0] = false;
                }
                break;
            default:
                coupPoussee[0] = false;
        }
        return coupPoussee;
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
                    etat = setEtat();
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
                    etat = setEtat();
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
                    etat = setEtat();
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
                    etat = setEtat();
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
                    break;
                case "down":
                    moveUp();
                    break;
                case "right":
                    moveLeft();
                    break;
                case "left":
                    moveRight();
                    break;
            }
            theUndos.remove(theUndos.size() - 1);
        }
    }

    @Override
    public void reset() {
        theMoves.clear();
        theUndos.clear();
    }

    @Override
    public void clear() {
        plateau = null;
        reset();
        etat = false;
    }

    @Override
    public char[][] getPlateau() {
        return plateau;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<String> getMoves() {
        return (ArrayList<String>) theMoves.clone();
    }

    private boolean setEtat() {
        for (char[] tmpTab:plateau) {
            for (char tmp:tmpTab) {
                if (tmp == caisse) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean getEtat() {
        return etat;
    }

}
