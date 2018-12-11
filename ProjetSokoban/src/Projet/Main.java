package Projet;


public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> IHMFX.getIHMFX().lance());
        thread.start();
    }

}
