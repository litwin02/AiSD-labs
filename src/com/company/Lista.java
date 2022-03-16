package com.company;

public class Lista{
    private static class ObiektListy{
        Osoba element;
        ObiektListy następny = null;
    }

    public static void main(String[] args) {
        ObiektListy pierwszy = new ObiektListy();
        ObiektListy kolejny = pierwszy;
        for(int i=1; i< 4; i++){
            kolejny.następny = new ObiektListy();
            kolejny=kolejny.następny;
        }
    }

}
