package com.company;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
public class BSTtablice<K1, K2> {
    private Wierzchołek korzeń;

    class Wierzchołek implements Comparable<Wierzchołek>{
        K1 klucz;
        K2 value;
        int ile=0;
        Wierzchołek lewyPotomek, prawyPotomek;
        int rozmiarP=0;

        public Wierzchołek(K1 a, K2 b, int N){
            this.klucz = a;
            this.value = b;
            this.ile = N;
            this.rozmiarP = N;
        }
        public String toString()
        {
            String wynik = String.format("%-11s %-11s %02d %03d", klucz, value, rozmiarP, ile);
//            String wynik = String.format("%-11s  %03d", klucz, ile);
            return wynik;
        }
        @Override
        public int compareTo(Wierzchołek x){
            return compS.compare(this.klucz, x.klucz);
        }
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
        if (kierunek.equals("trafiony")) wP.value = wartość;
        return wP;}

    Collator c = Collator.getInstance(new Locale("pl","PL"));
    Comparator<K1> compS= new Comparator<K1>() {
                @Override
                public int compare(K1 s1, K1 s2){
                    if (s1.getClass().getName().endsWith("Integer"))
                        return (int)Math.signum((Integer)s1-(Integer)s2);
                    if(s1.getClass().getName().endsWith("Character"))
                        return c.compare(String.valueOf(s1), String.valueOf(s2));
                    return c.compare(s1,s2);
                }};


    public Wierzchołek znajdz(Wierzchołek x, K1 klucz){
        if(x==null) return null;
        int porównanie = compS.compare(klucz, x.klucz);
        if (porównanie<0) return znajdz(x.lewyPotomek, klucz);
        else if (porównanie>0) return znajdz(x.prawyPotomek, klucz);
        else return x;
    }
    public int rozmiar(Wierzchołek x){
        if(x==null) return 0;
        else return x.rozmiarP;
    }
    public void dopisz(K1 key, K2 value){
        korzeń = dopisz(korzeń, key, value);
    }
    public Wierzchołek dopisz(Wierzchołek x, K1 key, K2 value){
        if(x==null) return new Wierzchołek(key, value, 1);
        int porównanie= compS.compare(key, x.klucz);
        if(porównanie<0) x.lewyPotomek=dopisz(x.lewyPotomek, key, value);
        else if (porównanie>0) x.prawyPotomek=dopisz(x.prawyPotomek, key, value);
        else {x.value=value;x.ile++;}
        x.rozmiarP=rozmiar(x.prawyPotomek)+rozmiar(x.lewyPotomek)+1;
        return x;
    }
    ArrayList<Wierzchołek> kolejka = new ArrayList<>();
    public void przeszukajZakres(Wierzchołek x, K1 dolny, K1 górny){
        if(x==null) return;
        int dZDanym = compS.compare(dolny, x.klucz);
        int gZDanym = compS.compare(górny, x.klucz);
        if (dZDanym < 0) przeszukajZakres(x.lewyPotomek, dolny, górny);
        if (dZDanym <=0 && gZDanym >=0) kolejka.add(x);
        if (gZDanym>0) przeszukajZakres(x.prawyPotomek, dolny, górny);
    }

    public Wierzchołek czyWystępuje(K1 klucz){
        Wierzchołek w = korzeń;
        while (w!=null){
            int porównanie = compS.compare(klucz, w.klucz);
            if(porównanie<0) w=w.lewyPotomek;
            else if (porównanie>0) w=w.prawyPotomek;
            else return w;
        }
        return null;
    }

    public static void main(String[] args) {
        String tekst = "Przypomnijmy: Warszawa wydała zgodę na" +
                " organizację imprezy masowej, jaką będzie finał Pucharu Polski," +
                " jednak zostawiła bardzo ważne ale (przynajmniej dla kibiców).";
        char x;
        BSTtablice<Character, Integer> drzewoS = new BSTtablice<>();
        for(int i=0; i<tekst.length(); i++){
            x=tekst.charAt(i);
            drzewoS.dopisz(x, 1);
        }
        System.out.println("zrobione");
//        BSTtablice.Wierzchołek adresZ = drzewoS.znajdz(drzewoS.korzeń, 'a');
//        System.out.println("pod adresem :" + adresZ);
//        BSTtablice.Wierzchołek[] lista = new BSTtablice.Wierzchołek[drzewoS.kolejka.size()];

        drzewoS.przeszukajZakres(drzewoS.korzeń, ' ', 'Ż');
        for(int i=0; i<drzewoS.kolejka.size(); i++){
            System.out.println((i+1)+ ") " + drzewoS.kolejka.get(i));
        }
    }
}
