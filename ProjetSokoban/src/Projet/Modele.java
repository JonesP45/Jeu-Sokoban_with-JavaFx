package Projet;

import java.util.ArrayList;

public interface Modele {
    public void move(String direction);
    public void undo();
    public void redo();
    public void reset();
    public char[][] getPlateau();
    public Object getMoves();
}
