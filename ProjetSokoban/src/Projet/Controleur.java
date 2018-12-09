package Projet;

import java.util.ArrayList;

public class Controleur implements Sujet {

    private static Controleur singleton;

    public static Controleur getControleur() {
        if (singleton == null)
            singleton = new Controleur(new FacadeModele());
        return singleton;
    }


    private FacadeModele facadeModele;
    private ArrayList<Observateur> observateurs = new ArrayList<Observateur>();

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

    public void move(String direction) {
        facadeModele.move(direction);
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

    public void reset() {
        facadeModele.reset();
        notifie();
    }

    public CommandeInt commandeNbCoup() {
        return new CommandeInt() {
            @Override
            public int exec() {
                return facadeModele.getNbCoup();
            }
        };
    }

    public CommandeInt commandeNbPoussee() {
        return new CommandeInt() {
            @Override
            public int exec() {
                return 0;
            }
        };
    }

    public CommandeTab2DChar commandeGetPlateau() {
        return new CommandeTab2DChar() {
            @Override
            public char[][] exec() {
                return facadeModele.getPlateau();
            }
        };
    }

}
