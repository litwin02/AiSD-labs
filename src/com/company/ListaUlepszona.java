package com.company;

public class ListaUlepszona {
    ObiektListy ostatni, pierwszy;
    int N;

    private static class ObiektListy{
        Osoba element;
        ObiektListy następny = null;
        ObiektListy poprzedni = null;
    }

    boolean jestPusta(){
        return  N==0;
    }

    public void dodajDoListy(Osoba os){
        ObiektListy staryOstatni = ostatni;
        ostatni = new ObiektListy();
        ostatni.element = os;
        ostatni.następny = null;
        if(jestPusta()) pierwszy=ostatni;
        else{
            staryOstatni.następny=ostatni;
//            ostatni.poprzedni=pierwszy;
        }
        N++;
    }

    public static void main(String[] args) {
        ListaUlepszona obiekt = new ListaUlepszona();
        obiekt.dodajDoListy(new Osoba("Litwin", "Jakub", "2002-09-30"));
        obiekt.dodajDoListy(new Osoba("Mazurkiewicz", "Mikołaj", "2002-07-01"));
        System.out.println(obiekt.pierwszy.element);
        System.out.println(obiekt.pierwszy.następny.element);
        System.out.println(obiekt.ostatni.poprzedni.element);
    }
}
