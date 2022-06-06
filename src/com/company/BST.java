package com.company;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class BST<K1, K2>
{
    Comparator<K1> compS=
            new Comparator<K1>() {
                Collator c = Collator.getInstance(new Locale("pl","PL"));
                public int compare(K1 s1, K1 s2){
                    if (s1.getClass().getName().endsWith("Integer"))
                        return (int)Math.signum((Integer)s1-(Integer)s2);
                    return c.compare(s1,s2);
                }};

    private Wierzchołek korzeń;
    class Wierzchołek
    {
        K1 klucz;
        K2 wartość;
        Wierzchołek lewyPotomek;
        Wierzchołek prawyPotomek;
        int rozmiarP;

        public Wierzchołek(K1 a, K2 b, int N)
        {
            this.klucz=a;
            this.wartość =b;
            this.rozmiarP = N;
        }
        public String toString()
        {
            String wynik = String.format("%-11s %-11s %02d", klucz, wartość, rozmiarP);
            return wynik;
        }
    }
    public int rozmiar(Wierzchołek x){
        if(x==null) return 0;
        else return x.rozmiarP;
    }
    public Wierzchołek miejsceDla(K1 klucz, K2 wartość)
    {
        String kierunek = "";
        Wierzchołek w=korzeń, wP=null;
        while(w!=null)
        {
            wP=w;
            int porównanie = compS.compare(klucz, w.klucz);
            if(porównanie<0)
            {
                kierunek ="lewy";
                w=w.lewyPotomek;
            }
            else if(porównanie>0){
                kierunek = "prawy";
                w=w.prawyPotomek;
            }
            else{kierunek ="trafiony"; break;}
        }
        if (korzeń == null) korzeń = new Wierzchołek(klucz, wartość, 1);
        else{
            if (kierunek.equals("lewy")) wP.lewyPotomek=new Wierzchołek(klucz,wartość,1);
        }
        if (kierunek.equals("prawy")) wP.prawyPotomek=new Wierzchołek(klucz, wartość, 1);
        if (kierunek.equals("trafiony")) wP.wartość = wartość;
        return wP;}

    // metoda szukająca wierzchołka i sprawdzająca czy zachowuje on strukturę BST
    public Wierzchołek znajdz(Wierzchołek x, K1 klucz){
        if(x==null) return null;
        int porównanie = compS.compare(klucz, x.klucz);
        if (porównanie<0) return znajdz(x.lewyPotomek, klucz);
        else if (porównanie>0) return znajdz(x.prawyPotomek, klucz);
        else return x;
    }

    public void dopisz(K1 key, K2 value){
        korzeń = dopisz(korzeń, key, value);
    }
    public Wierzchołek dopisz(Wierzchołek x, K1 key, K2 value){
        if(x==null) return new Wierzchołek(key, value, 1);
        int porównanie= compS.compare(key, x.klucz);
        if(porównanie<0) x.lewyPotomek=dopisz(x.lewyPotomek, key, value);
        else if (porównanie>0) x.prawyPotomek=dopisz(x.prawyPotomek, key, value);
        else {x.wartość=value;}
        x.rozmiarP=rozmiar(x.prawyPotomek)+rozmiar(x.lewyPotomek)+1;
        return x;
    }

    public int zwróćPoziom(K1 klucz){
        Wierzchołek w = korzeń;
        int poziom = 0;
        while (w!=null){
            poziom++;
            int porównanie = compS.compare(klucz, w.klucz);
            if(porównanie<0) w=w.lewyPotomek;
            else if (porównanie>0) w=w.prawyPotomek;
            else return poziom;
        }
        return -1;
    }
    public static void main(String[] args) {
        BST<String, String> drzewo = new BST<String,String>();

        /*
        drzewo.miejsceDla("Zawada", "2011");
        drzewo.miejsceDla("Środa", "2020-12-12");
        drzewo.miejsceDla("Gródek", "1990");
        drzewo.miejsceDla("Alabama", "2020-11-20");
        drzewo.miejsceDla("Aleksandria", "2018-01-23");
        drzewo.miejsceDla("Żabińska", "2011");
        drzewo.miejsceDla("Słupsk", "2010-10-12");
        drzewo.miejsceDla("Gdańsk", "2010");
        drzewo.miejsceDla("Kraków", "1999");
        */
        /*
        drzewo.dopisz("Zawada", "2011");
        drzewo.dopisz("Środa", "2020-12-12");
        drzewo.dopisz("Gródek", "1990");
        drzewo.dopisz("Alabama", "2020-11-20");
        drzewo.dopisz("Aleksandria", "2018-01-23");
        drzewo.dopisz("Żabińska", "2011");
        drzewo.dopisz("Słupsk", "2010-10-12");
        drzewo.dopisz("Gdańsk", "2010");
        drzewo.dopisz("Kraków", "1999");
        */
        drzewo.dopisz("Alabama", "2020-11-20");
        drzewo.dopisz("Aleksandria", "2018-01-23");
        drzewo.dopisz("Gdańsk", "2010");
        drzewo.dopisz("Gródek", "1990");
        drzewo.dopisz("Kraków", "1999");
        drzewo.dopisz("Słupsk", "2010-10-12");
        drzewo.dopisz("Środa", "2020-12-12");
        drzewo.dopisz("Zawada", "2011");
        drzewo.dopisz("Żabińska", "2011");
        int poziomy = drzewo.zwróćPoziom("Żabińska");
        BST.Wierzchołek x = drzewo.znajdz(drzewo.korzeń, "Środa");
        System.out.println("korzeń: " + x);
        System.out.println(x.lewyPotomek + " <--- lewy potomek || prawy potomek----> " + x.prawyPotomek);
        System.out.println("poziom" + poziomy);
    }
}