package Projet;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controleur implements Sujet {

    private static Controleur singletonControleur;

    public static Controleur getControleur() {
        if (singletonControleur == null)
            singletonControleur = new Controleur(new FacadeModele());
        return singletonControleur;
    }


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


    private FacadeModele facadeModele;
    private Animateur animateur = new Animateur(this);
    private ArrayList<Observateur> observateurs = new ArrayList<>();

    private int level = 0;
    private ArrayList<char[][]> theFiles = new ArrayList<>();

    public boolean firstMoveLevel = false;


    private Controleur(FacadeModele facadeModele) {
        this.facadeModele = facadeModele;
    }

    public void abonne(Observateur observateur) {
        observateurs.add(observateur);
    }

    @Override
    public void notifie() {
        for (Observateur observateur:observateurs)
            observateur.actualise();
    }


    public void incrementerAnimateur() {
        animateur.incrementer();
        notifie();
    }

    public void areterAnimateur() {
        animateur.areter();
        notifie();
    }

    public void reinitialiserAnimateur() {
        animateur.reinitialiser();
        notifie();
    }


    public void load(String fileName) throws Exception {
        ArrayList<ArraylistToArray> list = new ArrayList<>();
        int largeur = 0;
        int hauteur = 0;
        FileInputStream fis = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line = reader.readLine();
        while (line != null) {
            if (line.equals("")) {
                line = reader.readLine();
            }
            else if (line.charAt(0) != ';') {
                if (line.length() > largeur) {
                    largeur = line.length();
                }
                for (int i = 0; i < line.length(); i++) {
                    char tmp = line.charAt(i);
                    list.add(new ArraylistToArray(hauteur, i, tmp));
                }
                hauteur++;
                line = reader.readLine();
            }
            else {
                char[][] plateau = new char[hauteur][largeur];
                int i = 0;
                int j = 0;
                for (ArraylistToArray tmp:list) {
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
            } //finElse
            level++;
        }
        level = 0;
        notifie();
    }

    @SuppressWarnings("ManualArrayCopy")
    private char[][] cloneChar2DTab(char[][] tab) {
        char[][] res = new char[tab.length][tab[0].length];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                res[i][j] = tab[i][j];
            }
        }
        return res;
    }


    public void previousLevel() {
        if (level == 0) {
            level = theFiles.size() - 1;
        } else {
            level--;
        }
        notifie();
    }

    public void nextLevel() {
        if (level == theFiles.size() - 1) {
            level = 0;
        } else {
            level++;
        }
        notifie();
    }

    public void play() {
        facadeModele.play(cloneChar2DTab(theFiles.get(level)));
        notifie();
    }


    public boolean move(String direction) {
        boolean nextLevel = false;
        if (!firstMoveLevel) {
            firstMoveLevel = true;
            animateur.demarer();
        }
        facadeModele.move(direction);
        if (facadeModele.getEtat()) {
            animateur.areter();
            nextLevel = true;
            firstMoveLevel = false;
            if (level < theFiles.size())
                level++;
            else
                level = 0;
        }
        notifie();
        return nextLevel;
    }

    public void clear() {
        facadeModele.clear();
        notifie();
    }

    public void undo() {
        facadeModele.undo();
        notifie();
    }

    public void redo() {
        facadeModele.redo();
        notifie();
    }

    private static int indice = 0;
    private Timeline timer = new Timeline();
    public void replay() {
        timer.stop();
        ArrayList<String> theMoves = facadeModele.getMoves();
        System.out.println();
        timer = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        (ActionEvent event) -> {
                            switch (theMoves.get(indice)) {
                                case "right":
                                    facadeModele.move("right"); break;
                                case "rightCaisse":
                                    facadeModele.move("right"); break;
                                case "up":
                                    facadeModele.move("up"); break;
                                case "upCaisse":
                                    facadeModele.move("up"); break;
                                case "down":
                                    facadeModele.move("down"); break;
                                case "downCaisse":
                                    facadeModele.move("down"); break;
                                case "left":
                                    facadeModele.move("left"); break;
                                case "leftCaisse":
                                    facadeModele.move("left"); break;
                            }
                            notifie();
                            indice++;
                        })
        );
        timer.setCycleCount(theMoves.size());
        if (theMoves.size() > 0) {
            animateur.pause();
            firstMoveLevel = false;
            reset();
            timer.play();
        }
        indice = 0;
        notifie();
    }

    public void reset() {
        if (animateur.getTimer().getStatus() != Animation.Status.PAUSED) {
            animateur.areter();
            animateur.reinitialiser();
            firstMoveLevel = false;
        }
        facadeModele.reset();
        notifie();
    }

    public CommandeInt commandeChrono() {
        return () -> animateur.getTime();
    }

    public CommandeInt commandeLevel() {
        return () -> level;
    }

    public CommandeInt commandeNbCoup() { return () -> facadeModele.getNbCoup(); }

    public CommandeInt commandeNbPoussee() { return () -> facadeModele.getNbPoussee(); }

    public CommandeTab2DChar commandeGetPlateau() { return () -> facadeModele.getPlateau(); }

}
