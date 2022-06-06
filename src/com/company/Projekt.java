package com.company;

import java.util.ArrayList;

public class Projekt {
    public Krawedz[] krawedzDo; //Najkrótsza krawędź z wierzchołka drzewa
    public double[] odlegloscDo; //Tablica która będzie przechowywała wagi
    public boolean[] oznaczone;  //Tablica wyrażeń logicznych,
    // TRUE pojawia się, gdy wierzchołek znajduje się już w drzewie
    public double minWag;
    public int pozycjaMin;
    boolean czyPusta;
    GrafW graf;
    ArrayList<Double> pq = new ArrayList<Double>();

    public Projekt(int v) {
        graf = new GrafW(v);
        krawedzDo = new Krawedz[v];
        odlegloscDo = new double[v];
        oznaczone = new boolean[v];
        for (int k = 0; k < v; k++)
            odlegloscDo[k] = Double.POSITIVE_INFINITY;
        pozycjaMin = -1;
    }

    public void badajMinimum() {
        pozycjaMin = -1;
        int i;
        for (i = 0; i < graf.V; i++) {
            if (pq.get(i) != null) {
                pozycjaMin = i;
                break;
            }
        }
        czyPusta = pozycjaMin == -1;
        if (czyPusta) return;
        minWag = pq.get(i);
        for (int ii = i + 1; ii < graf.V; ii++) {
            if (pq.get(ii) != null && minWag > pq.get(ii)) {
                minWag = pq.get(ii);
                pozycjaMin = ii;
            }
        }
        pq.set(pozycjaMin, null);
    }

    public void tworzenieMST(Projekt obiektST, int ileWierz){
        for (int i = 0; i < ileWierz; i++)
            obiektST.pq.add(null);
        int v = 0;
        obiektST.odlegloscDo[0] = 0.0;
        obiektST.pq.add(0, 0.0);
        while (true) {
            obiektST.badajMinimum();
            if (obiektST.pozycjaMin == -1) break;
            v = obiektST.pozycjaMin;
            obiektST.oznaczone[v] = true;
            for (Krawedz e : obiektST.graf.sasiedzi[v]) {
                int w = e.drugi(v);
                if (obiektST.oznaczone[w]) {
                    continue;
                }
                if (e.waga() < obiektST.odlegloscDo[w]) {
                    obiektST.krawedzDo[w] = e;
                    obiektST.odlegloscDo[w] = e.waga();
                    obiektST.pq.set(w, obiektST.odlegloscDo[w]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int ileWierz = 5;
        Projekt minST = new Projekt(ileWierz);
        minST.graf.dodajKrawedz(new Krawedz(0, 1, 1));
        minST.graf.dodajKrawedz(new Krawedz(1, 2, 1));
        minST.graf.dodajKrawedz(new Krawedz(2, 3, 5));
        minST.graf.dodajKrawedz(new Krawedz(1, 3, 2));
        minST.graf.dodajKrawedz(new Krawedz(4, 1, 3));
        minST.graf.dodajKrawedz(new Krawedz(3, 4, 2));
        minST.tworzenieMST(minST, ileWierz);
        for (int i = 1; i < ileWierz; i++)
            System.out.println(minST.krawedzDo[i]);
    }
}
