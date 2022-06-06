package com.company;

public class Krawedz implements Comparable<Krawedz> {
    private int v; //pierwszy wierzchołek
    private int w; // drugi wierzchołek
    private double waga; //waga krawędzi

    public Krawedz(int v, int w, double waga) {
        this.v = v;
        this.w = w;
        this.waga = waga;
    }

    public double waga() {return waga;}
    public int pierwszy() {return v;}

    public int drugi(int nrWierzchołka){
        if(nrWierzchołka==v) return w;
        else if (nrWierzchołka==w) return v;
        else return -1;
    }
    @Override
    public int compareTo(Krawedz innaKrawędź) {
        if(this.waga()<innaKrawędź.waga()) return -1;
        else if (this.waga()> innaKrawędź.waga) return 1;
        else return 0;
    }
    public String toString(){
        return String.valueOf(v)+"-"+String.valueOf(w)+String.format(" %4.3f ", waga);
    }
}
