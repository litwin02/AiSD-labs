package com.company;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class DrzewaKolorowe<Klucz, Wartość> {
    Comparator<Klucz> compS = new Comparator<Klucz>() {
        Collator c = Collator.getInstance(new Locale("pl", "PL"));

        @Override
        public int compare(Klucz o1, Klucz o2) {
            if (o1.getClass().getName().equals("java.lang.Integer")) {
                return (int) Math.signum((Integer) o1 - (Integer) o2);
            }
            return c.compare(o1, o2);
        }
    };
    static final boolean CZERWONY = true;
    static final boolean CZARNY = false;
    private Wierzchołek korzeń;

    class Wierzchołek {
        Klucz klucz;
        Wartość wartość;
        Wierzchołek lewyPotomek, prawyPotomek;
        int N = 0;
        boolean kolor;

        public Wierzchołek(Klucz a, Wartość b, int N, boolean kolor) {
            this.klucz = a;
            this.wartość = b;
            this.N = N;
            this.kolor = kolor;
        }

        public String toString() {
            return String.format("%12s %12s wielkość %5s", klucz.toString(), wartość.toString(), String.valueOf(N));
        }
    }

    public int rozmiar() {
        return rozmiar(korzeń);
    }

    private int rozmiar(Wierzchołek w) {
        if (w == null) return 0;
        else return w.N;
    }

    public Wierzchołek rotacjaLewa(Wierzchołek h) {
        Wierzchołek x = h.prawyPotomek;
        h.prawyPotomek = x.lewyPotomek;
        x.lewyPotomek = h;
        x.kolor = h.kolor;
        h.kolor = CZERWONY;
        x.N = h.N;
        h.N = 1 + rozmiar(h.lewyPotomek) + rozmiar(h.prawyPotomek);
        return x;
    }

    public Wierzchołek rotacjaPrawa(Wierzchołek h) {
        Wierzchołek x = h.lewyPotomek;
        h.lewyPotomek = x.prawyPotomek;
        x.prawyPotomek = h;
        x.kolor = h.kolor;
        h.kolor = CZERWONY;
        x.N = h.N;
        h.N = 1 + rozmiar(h.lewyPotomek) + rozmiar(h.prawyPotomek);
        return x;
    }

    void zamieńKolory(Wierzchołek h) {
        h.kolor = CZERWONY;
        h.lewyPotomek.kolor = CZARNY;
        h.prawyPotomek.kolor = CZARNY;
    }

    private void dodaj(Klucz klucz, Wartość wartość) {
        korzeń = dodaj(korzeń, klucz, wartość);
        korzeń.kolor = CZARNY;
    }

    private Wierzchołek dodaj(Wierzchołek wierz, Klucz klucz, Wartość wartość) {
        if (wierz == null)
            return new Wierzchołek(klucz, wartość, 1, CZERWONY);
        int porównanie = compS.compare(klucz, wierz.klucz);
        if (porównanie < 0) wierz.lewyPotomek = dodaj(wierz.lewyPotomek, klucz, wartość);
        else if (porównanie > 0) wierz.prawyPotomek = dodaj(wierz.prawyPotomek, klucz, wartość);
        else wierz.wartość = wartość;
        if (jestCzerwony(wierz.prawyPotomek) && !jestCzerwony(wierz.lewyPotomek))
            wierz = rotacjaLewa(wierz);
        if (jestCzerwony(wierz.lewyPotomek) && jestCzerwony(wierz.lewyPotomek.lewyPotomek))
            wierz = rotacjaPrawa(wierz);
        if (jestCzerwony(wierz.prawyPotomek) && jestCzerwony(wierz.lewyPotomek))
            zamieńKolory(wierz);
        wierz.N = rozmiar(wierz.prawyPotomek) + rozmiar(wierz.lewyPotomek) + 1;
        return wierz;
    }

    boolean jestCzerwony(Wierzchołek x) {
        if (x == null) return false;
        return x.kolor == CZERWONY;
    }

    public int zwróćPoziom(Klucz klucz) {
        Wierzchołek w = korzeń;
        int poziom = 0;
        while (w != null) {
            poziom++;
            int porównanie = compS.compare(klucz, w.klucz);
            if (porównanie < 0) w = w.lewyPotomek;
            else if (porównanie > 0) w = w.prawyPotomek;
            else return poziom;
        }
        return -1;
    }
    ArrayList<Wierzchołek> kolejka = new ArrayList<>();
    public void przeszukajZakres(Wierzchołek x, Klucz dolny, Klucz górny){
        if(x==null) return;
        int dZDanym = compS.compare(dolny, x.klucz);
        int gZDanym = compS.compare(górny, x.klucz);
        if (dZDanym < 0) przeszukajZakres(x.lewyPotomek, dolny, górny);
        if (dZDanym <=0 && gZDanym >=0) kolejka.add(x);
        if (gZDanym>0) przeszukajZakres(x.prawyPotomek, dolny, górny);
    }
    public int wysokośćDrzewa(){
        if(kolejka.isEmpty())
            return -1;
        int wysokość = 0;
        int poziomKlucza = 0;
        for (Wierzchołek w: kolejka){
            poziomKlucza = zwróćPoziom(w.klucz);
            if(wysokość<poziomKlucza)
                wysokość = poziomKlucza;
        }
        return wysokość;
    }

    public static void main(String[] args) {
        DrzewaKolorowe<String, String> drzewo = new DrzewaKolorowe<>();
        drzewo.dodaj("Zawada", "2011");
        drzewo.dodaj("Środa", "2020-12-12");
        drzewo.dodaj("Gródek", "1990");
        drzewo.dodaj("Alabama", "2020-11-20");
        drzewo.dodaj("Aleksandria", "2018-01-23");
        drzewo.dodaj("Żabińska", "2011");
        drzewo.dodaj("Słupsk", "2010-10-12");
        drzewo.dodaj("Gdańsk", "2010");
        drzewo.dodaj("Kraków", "1999");
        int poziom = 0;
        drzewo.przeszukajZakres(drzewo.korzeń, "Gródek", "Kraków");
        poziom = drzewo.wysokośćDrzewa();
        System.out.println("wysokość drzewa: " + poziom);
    }
}


