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

    public char[][] initialisePlateau() {
        int hauteur = 9;
        int largeur = 7;
        char[][] plateau = new char[hauteur][largeur];

        for (int i = 0; i < plateau[0].length; i++) {
            plateau[0][i] = mur;
            plateau[8][i] = mur;
        }
        for (int i = 0; i < plateau.length; i++) {
            plateau[i][0] = mur;
            plateau[i][6] = mur;
        }
        for (int i = 1; i < plateau.length - 1; i++) {
            for (int j = 1; j < plateau[0].length - 1; j++) {
                plateau[i][j] = sol;
            }
        }
        x_soko = 1;
        y_soko = 1;
        plateau[x_soko][y_soko] = soko;
        plateau[4][2] = caisse;
        plateau[6][4] = but;
        return plateau;
    }

    @Override
    public void move(String direction) {
        System.out.println("move");
        switch (direction) {
            case "up":
                if (plateau[x_soko - 1][y_soko] == sol) {
                    plateau[x_soko][y_soko] = sol;
                    x_soko--;
                    plateau[x_soko][y_soko] = soko;
                }
                else if (plateau[x_soko - 1][y_soko] == but) {
                    plateau[x_soko][y_soko] = sol;
                    x_soko--;
                    plateau[x_soko][y_soko] = sokoSurBut;
                }
                else if (plateau[x_soko - 1][y_soko] == caisse || plateau[x_soko - 1][y_soko] == caisseSurBut) {
                    if (plateau[x_soko - 2][y_soko] == sol) {
                        plateau[x_soko][y_soko] = sol;
                        x_soko--;
                        plateau[x_soko][y_soko] = soko;
                        x_soko--;
                        plateau[x_soko][y_soko] = caisse;
                    }
                    else if (plateau[x_soko][y_soko] == caisse && plateau[x_soko - 2][y_soko] == but) {
                        plateau[x_soko][y_soko] = sol;
                        x_soko--;
                        plateau[x_soko][y_soko] = soko;
                        x_soko--;
                        plateau[x_soko][y_soko] = caisseSurBut;
                    }
                    else if (plateau[x_soko][y_soko] == caisseSurBut && plateau[x_soko - 2][y_soko] == but) {
                        plateau[x_soko][y_soko] = sol;
                        x_soko--;
                        plateau[x_soko][y_soko] = sokoSurBut;
                        x_soko--;
                        plateau[x_soko][y_soko] = caisseSurBut;
                    }
                }
                break;
            case "down" :
                if (plateau[x_soko + 1][y_soko] == sol) {
                    plateau[x_soko][y_soko] = sol;
                    x_soko++;
                    plateau[x_soko][y_soko] = soko;
                }
                else if (plateau[x_soko + 1][y_soko] == but) {
                    plateau[x_soko][y_soko] = sol;
                    x_soko++;
                    plateau[x_soko][y_soko] = sokoSurBut;
                }
                else if (plateau[x_soko + 1][y_soko] == caisse || plateau[x_soko + 1][y_soko] == caisseSurBut) {
                    if (plateau[x_soko + 2][y_soko] == sol) {
                        plateau[x_soko][y_soko] = sol;
                        x_soko++;
                        plateau[x_soko][y_soko] = soko;
                        x_soko++;
                        plateau[x_soko][y_soko] = caisse;
                    }
                    else if (plateau[x_soko][y_soko] == caisse && plateau[x_soko + 2][y_soko] == but) {
                        plateau[x_soko][y_soko] = sol;
                        x_soko++;
                        plateau[x_soko][y_soko] = soko;
                        x_soko++;
                        plateau[x_soko][y_soko] = caisseSurBut;
                    }
                    else if (plateau[x_soko][y_soko] == caisseSurBut && plateau[x_soko + 2][y_soko] == but) {
                        plateau[x_soko][y_soko] = sol;
                        x_soko++;
                        plateau[x_soko][y_soko] = sokoSurBut;
                        x_soko++;
                        plateau[x_soko][y_soko] = caisseSurBut;
                    }
                }
                break;
            case "left" :
                if (plateau[x_soko][y_soko - 1] == sol) {
                    plateau[x_soko][y_soko] = sol;
                    y_soko--;
                    plateau[x_soko][y_soko] = soko;
                }
                else if (plateau[x_soko][y_soko - 1] == but) {
                    plateau[x_soko][y_soko] = sol;
                    y_soko--;
                    plateau[x_soko][y_soko] = sokoSurBut;
                }
                else if (plateau[x_soko][y_soko - 1] == caisse || plateau[x_soko][y_soko - 1] == caisseSurBut) {
                    if (plateau[x_soko][y_soko - 2] == sol) {
                        plateau[x_soko][y_soko] = sol;
                        y_soko--;
                        plateau[x_soko][y_soko] = soko;
                        y_soko--;
                        plateau[x_soko][y_soko] = caisse;
                    }
                    else if (plateau[x_soko][y_soko] == caisse && plateau[x_soko][y_soko - 2] == but) {
                        plateau[x_soko][y_soko] = sol;
                        y_soko--;
                        plateau[x_soko][y_soko] = soko;
                        y_soko--;
                        plateau[x_soko][y_soko] = caisseSurBut;
                    }
                    else if (plateau[x_soko][y_soko] == caisseSurBut && plateau[x_soko][y_soko - 2] == but) {
                        plateau[x_soko][y_soko] = sol;
                        y_soko--;
                        plateau[x_soko][y_soko] = sokoSurBut;
                        y_soko--;
                        plateau[x_soko][y_soko] = caisseSurBut;
                    }
                }
                break;
            case "right" :
                if (plateau[x_soko][y_soko + 1] == sol) {
                    plateau[x_soko][y_soko] = sol;
                    y_soko++;
                    plateau[x_soko][y_soko] = soko;
                }
                else if (plateau[x_soko][y_soko + 1] == but) {
                    plateau[x_soko][y_soko] = sol;
                    y_soko++;
                    plateau[x_soko][y_soko] = sokoSurBut;
                }
                else if (plateau[x_soko][y_soko + 1] == caisse || plateau[x_soko][y_soko + 1] == caisseSurBut) {
                    if (plateau[x_soko][y_soko + 2] == sol) {
                        plateau[x_soko][y_soko] = sol;
                        y_soko++;
                        plateau[x_soko][y_soko] = soko;
                        y_soko++;
                        plateau[x_soko][y_soko] = caisse;
                    }
                    else if (plateau[x_soko][y_soko] == caisse && plateau[x_soko][y_soko + 2] == but) {
                        plateau[x_soko][y_soko] = sol;
                        y_soko++;
                        plateau[x_soko][y_soko] = soko;
                        y_soko++;
                        plateau[x_soko][y_soko] = caisseSurBut;
                    }
                    else if (plateau[x_soko][y_soko] == caisseSurBut && plateau[x_soko][y_soko + 2] == but) {
                        plateau[x_soko][y_soko] = sol;
                        y_soko++;
                        plateau[x_soko][y_soko] = sokoSurBut;
                        y_soko++;
                        plateau[x_soko][y_soko] = caisseSurBut;
                    }
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
