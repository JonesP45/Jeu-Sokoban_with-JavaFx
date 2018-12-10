package Projet;

import java.util.ArrayList;

public interface Modele {
    boolean[] move(String direction);
    void undo();
    void redo();
    void reset();
    char[][] getPlateau();
    ArrayList<String> getMoves();
}
