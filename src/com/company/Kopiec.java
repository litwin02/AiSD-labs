package com.company;

import java.util.ArrayList;

public class Kopiec<T extends Comparable<T>> {

    public boolean czyJestKopcem() {
        int dlugosc = lista.size();
        boolean jestKopcem = true;
        for (int k = 1; k < lista.size(); k++) {
            if (2 * k >= dlugosc) return true;
            jestKopcem = lista.get(k).compareTo(lista.get(2 * k)) >= 0;
            if (2 * k + 1 >= dlugosc) return jestKopcem;
            jestKopcem = jestKopcem && lista.get(k).compareTo(lista.get(2 * k + 1)) >= 0;
            if (!jestKopcem) return false;
        }
        return true;
    }

    public void wynurzanie(int k) {
        T pom;
        while (k > 1 && lista.get(k / 2).compareTo(lista.get(k)) < 0) {
            pom = lista.get(k);
            lista.set(k, lista.get(k / 2));
            lista.set(k / 2, pom);
            k = k / 2;
        }
    }

    public void zanurzanie(int k, int N) {
        int j;
        while (2 * k <= N) {
            j = 2 * k;
            if (j < N && lista.get(j).compareTo(lista.get(j + 1)) < 0) j++;
            if (!(lista.get(k).compareTo(lista.get(j)) == -1)) break;

            T pom = lista.get(k);
            lista.set(k, lista.get(j));
            lista.set(j, pom);
            k = j;
        }
    }

    public void zanurzanie(int k) {
        int N = lista.size() - 1;
        int j;
        while (2 * k <= N) {
            j = 2 * k;
            if (j < N && lista.get(j).compareTo(lista.get(j + 1)) == -1) j++;
            if (!(lista.get(k).compareTo(lista.get(j)) == -1)) break;

            T pom = lista.get(k);
            lista.set(k, lista.get(j));
            lista.set(j, pom);
            k = j;
        }
    }

    public void drukuj() {
        for (int i = 0; i < lista.size(); i++)
            System.out.print(lista.get(i) + " ");
        System.out.println();
    }

    public Object[] dopisz(Object[] lista, Object dopisywany) {
        Object[] listaW = new Object[lista.length + 1];
        for (int i = 0; i < lista.length; i++) listaW[i] = lista[i];
        listaW[lista.length] = dopisywany;
        return listaW;
    }

    public void dopisz(T dopisywany) {
        lista.add(dopisywany);
        wynurzanie(lista.size() - 1);
    }

    public T pobierzPierwszego(){
        T usunięty = lista.get(1);
        lista.set(1, lista.get(lista.size()-1));
        lista.remove(lista.size()-1);
        zanurzanie(1);
        return usunięty;
    }

    ArrayList<T> lista = new ArrayList<>();

    public static void main(String[] args) {
        /*
        System.out.println("\nKopiec imion:");
        Kopiec<String> kopiec1 = new Kopiec<String>();
        String listaA[] = {"", "Zbigniew", "Adam", "Bronisław", "Daniel", "Zdzisław", "Wojciech"};
        for (String s : listaA) kopiec1.dopisz(s);
        System.out.println(kopiec1.czyJestKopcem());
        kopiec1.drukuj();

        System.out.println("\nKopiec liczb:");
        Kopiec<Double> kopiec2 = new Kopiec<Double>();
        Double listaB[] = {0.0, 18.45, 2.68, 3.91, 5.70, 19.33};
        for (Double s : listaB) kopiec2.dopisz(s);
        System.out.println(kopiec2.czyJestKopcem());
        kopiec2.drukuj();
        */
        Kopiec<Osoba> osoby = new Kopiec<Osoba>();
        osoby.dopisz(new Osoba());
        osoby.dopisz(new Osoba());
        osoby.dopisz(new Osoba("Litwin", "Jakub", "2002-09-30"));
        osoby.dopisz(new Osoba("Składowski", "Radek", "2003-04-15"));
        osoby.dopisz(new Osoba("Mazurkiewicz", "Mikołaj", "2002-07-01"));
        osoby.dopisz(new Osoba("Piosik", "Bartosz", "2002-07-17"));
        osoby.dopisz(new Osoba("Żabiński", "Cezary", "1999-09-24"));
        osoby.drukuj();
        System.out.println();

        Osoba usuniety = osoby.pobierzPierwszego();
        System.out.println(usuniety.nazwisko + " " + usuniety.imię + "\n");
        osoby.drukuj();
    }
}
