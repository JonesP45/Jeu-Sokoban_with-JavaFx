package Projet;

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
        _plateau[4][6] = but;

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
        afficherPlateau(plateau);
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
        afficherPlateau(plateau);
        System.out.println();
    }


    private void moveUp() {
        if (plateau[x_soko][y_soko] == soko) {
            moveX(-1, -2, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            moveX(-1, -2, but);
        }
    }

    private void moveDown() {
        if (plateau[x_soko][y_soko] == soko) {
            moveX(1, 2, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            moveX(1, 2, but);
        }
    }

    private void moveLeft() {
        if (plateau[x_soko][y_soko] == soko) {
            moveY(-1, -2, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            moveY(-1, -2, but);
        }
    }

    private void moveRight() {
        if (plateau[x_soko][y_soko] == soko) {
            moveY(1, 2, sol);
        }
        else if (plateau[x_soko][y_soko] == sokoSurBut) {
            moveY(1, 2, but);
        }
    }


    private void moveX(int move1, int move2, char change) {
        switch (plateau[x_soko + move1][y_soko]) {
            case sol:
                plateau[x_soko][y_soko] = change;
                x_soko += move1;
                plateau[x_soko][y_soko] = soko;
                break;
            case but:
                plateau[x_soko][y_soko] = change;
                x_soko += move1;
                plateau[x_soko][y_soko] = sokoSurBut;
                break;
            case caisse:
                if (plateau[x_soko + move2][y_soko] == sol) {
                    plateau[x_soko][y_soko] = change;
                    x_soko += move1;
                    plateau[x_soko][y_soko] = soko;
                    plateau[x_soko + move1][y_soko] = caisse;
                }
                else if (plateau[x_soko + move2][y_soko] == but) {
                    plateau[x_soko][y_soko] = change;
                    x_soko += move1;
                    plateau[x_soko][y_soko] = soko;
                    plateau[x_soko + move1][y_soko] = caisseSurBut;
                }
                break;
            case caisseSurBut:
                if (plateau[x_soko + move2][y_soko] == sol) {
                    plateau[x_soko][y_soko] = change;
                    x_soko += move1;
                    plateau[x_soko][y_soko] = sokoSurBut;
                    plateau[x_soko + move1][y_soko] = caisse;
                }
                else if (plateau[x_soko + move2][y_soko] == but) {
                    plateau[x_soko][y_soko] = change;
                    x_soko += move1;
                    plateau[x_soko][y_soko] = sokoSurBut;
                    plateau[x_soko + move1][y_soko] = caisseSurBut;
                }
                break;
        }
    }

    private void moveY(int move1, int move2, char change) {
        switch (plateau[x_soko][y_soko + move1]) {
            case sol:
                plateau[x_soko][y_soko] = change;
                y_soko += move1;
                plateau[x_soko][y_soko] = soko;
                break;
            case but:
                plateau[x_soko][y_soko] = change;
                y_soko += move1;
                plateau[x_soko][y_soko] = sokoSurBut;
                break;
            case caisse:
                if (plateau[x_soko][y_soko + move2] == sol) {
                    plateau[x_soko][y_soko] = change;
                    y_soko += move1;
                    plateau[x_soko][y_soko] = soko;
                    plateau[x_soko][y_soko + move1] = caisse;
                }
                else if (plateau[x_soko][y_soko + move2] == but) {
                    plateau[x_soko][y_soko] = change;
                    y_soko += move1;
                    plateau[x_soko][y_soko] = soko;
                    plateau[x_soko][y_soko + move1] = caisseSurBut;
                }
                break;
            case caisseSurBut:
                if (plateau[x_soko][y_soko + move2] == sol) {
                    plateau[x_soko][y_soko] = change;
                    y_soko += move1;
                    plateau[x_soko][y_soko] = sokoSurBut;
                    plateau[x_soko][y_soko + move1] = caisse;
                }
                else if (plateau[x_soko][y_soko + move2] == but) {
                    plateau[x_soko][y_soko] = change;
                    y_soko += move1;
                    plateau[x_soko][y_soko] = sokoSurBut;
                    plateau[x_soko][y_soko + move1] = caisseSurBut;
                }
                break;
        }
    }


    @Override
    public void reset() {
        plateau = initialisePlateau();
    }

    public char[][] getPlateau() {
        return plateau;
    }

}
