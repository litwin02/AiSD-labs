package com.company;

import java.util.Iterator;

public class Graf {
    private int V;
    private int E;
    private int s = 0;
    boolean[] oznakowane;
    int[] krawedzDo;
    public Worek<Integer>[] sasiedzi;


    public Graf(int V) {
        this.V = V;
        this.E = 0;
        oznakowane = new boolean[V];
        krawedzDo=new int[V];
        sasiedzi = (Worek<Integer>[]) new Worek[V];
        for (int v = 0; v < V; v++)
            sasiedzi[v] = new Worek<Integer>();
    }

    public void dodajKrawedz(int v, int w) {
        sasiedzi[v].dodaj(w);
        sasiedzi[w].dodaj(v);
        E++;
    }

    private void dfs(Graf g, int v) {
        oznakowane[v] = true;
        for (int w : g.sasiedzi[v]) {
            if (!oznakowane[w]) {
                System.out.println("badanie dsf " + w);
                krawedzDo[w] = v;
                dfs(g, w);
            }
        }
    }
    public boolean jestSciezkaDo(int v) {return oznakowane[v];}

    public Worek<Integer> sciezkaDo(int v){
        if(!jestSciezkaDo(v)) return null;
        Worek<Integer> ścieżka = new Worek<>();
        for (int x=v;x!=s;x=krawedzDo[x]) {
            ścieżka.dodaj(x);
            ścieżka.dodaj(s);
        }
        return ścieżka;
    }
    private void bfs(Graf g, int v){
        Kolejka<Integer> kolejka = new Kolejka<Integer>();
        oznakowane[v]=true;
        kolejka.dodajDoKolejki(v);
        while (!kolejka.jestPusta()){
            int vv=kolejka.pobierzZKolejki();
            for (int w:g.sasiedzi[vv]){
                if(!oznakowane[w]){
                    System.out.println("badanie bfs "+w);
                    krawedzDo[w]=vv;
                    oznakowane[w]=true;
                    kolejka.dodajDoKolejki(w);
                }
            }
        }
    }
    public static void main(String[] args) {
        /*
        Graf graf = new Graf(6);
        graf.dodajKrawedz(0, 5);
        graf.dodajKrawedz(2, 4);
        graf.dodajKrawedz(2, 3);
        graf.dodajKrawedz(1, 2);
        graf.dodajKrawedz(0, 1);
        graf.dodajKrawedz(3, 4);
        graf.dodajKrawedz(3, 5);
        graf.dodajKrawedz(0, 2);
        */
        /*
        Graf graf = new Graf(10);
        graf.dodajKrawedz(0, 1);
        graf.dodajKrawedz(1, 2);
        graf.dodajKrawedz(2, 5);
        graf.dodajKrawedz(3, 0);
        graf.dodajKrawedz(4, 0);
        graf.dodajKrawedz(8, 4);
        graf.dodajKrawedz(9, 8);
        graf.dodajKrawedz(7, 4);
        graf.dodajKrawedz(6, 7);
        */
        /*
        Iterator<Integer> it;
        for (int i = 0; i < graf.sasiedzi.length; i++) {
            it = graf.sasiedzi[i].iterator();
            System.out.print("Następnik wierzchołka " + i + "     ");
            while (it.hasNext())
                System.out.print(it.next() + "  ");
            System.out.println();
        }
        */

        Graf graf = new Graf(10);
        graf.dodajKrawedz(1, 0);
        graf.dodajKrawedz(3, 0);
        graf.dodajKrawedz(2, 3);
        graf.dodajKrawedz(5, 2);
        graf.dodajKrawedz(4, 3);
        graf.dodajKrawedz(4, 0);
        graf.dodajKrawedz(8, 4);
        graf.dodajKrawedz(9, 8);
        graf.dodajKrawedz(7, 4);
        graf.dodajKrawedz(6, 7);

//        graf.dfs(graf, 5);
        Iterator<Integer> it;
        for (int i = 0; i < graf.sasiedzi.length; i++) {
            it = graf.sasiedzi[i].iterator();
            System.out.print("Następnik wierzchołka " + i + "     ");
            while (it.hasNext())
                System.out.print(it.next() + "  ");
            System.out.println();
        }

        if(graf.jestSciezkaDo(5)==true) {
            for (Integer x : graf.sciezkaDo(5))
                System.out.print(x + " ");
        }
        graf.bfs(graf, 0);

        for(int i=0; i<graf.V; i++){
            System.out.println(i+") "+ graf.oznakowane[i]+" "+ graf.krawedzDo[i]);
        }

        int ścieżkaDo=6;
        for(int k=ścieżkaDo; k!= graf.s;k=graf.krawedzDo[k])
            System.out.print("  ->"+k);
        System.out.print("  ->"+graf.s);


    }
}
