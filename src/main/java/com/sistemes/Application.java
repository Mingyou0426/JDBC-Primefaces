package com.sistemes;

public class Application {

    public static ClassConnexio connexio;

    public Application(){
        connexio = null;
    }

    public static ClassConnexio getConnexio() {
        return connexio;
    }

    public static void setConnexio(ClassConnexio connexio) {
        Application.connexio = connexio;
    }
}
