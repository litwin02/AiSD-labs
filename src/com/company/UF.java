package com.company;

public class UF {
    static int wielkosc = 10;
    static int skladowe = 10;
    static int[] reprezentanci = new int[wielkosc];
    static int[] reprezentanciA = new int[wielkosc];
    static int[] wielkosci = new int[wielkosc];

    static int wyszukaj(int x) {
        return reprezentanci[x];
    }
    static boolean połącz(int x, int y){
        int sX = wyszukaj(x);
        int sY = wyszukaj(y);
        if(sX==sY)
            return false;
        else {
            for (int i=0; i<wielkosc; i++)
            {
                if(reprezentanci[i] == sX)
                    reprezentanci[i]=sY;
            }
            skladowe--;
            return true;
        }
    }
    public static boolean połączA(int x, int y){
        int sX = wyszukaj(x);//tu ma być jakieś inne wyszukaj
        int sY = wyszukaj(y);
        if(sX==sY)
            return false;
        if (wielkosci[sX]<wielkosci[sY]){
            reprezentanciA[sX]=sY;
            wielkosci[sY]+=wielkosci[sX];
        }
        else {
            reprezentanciA[sY]=sX;
            wielkosci[sX]+=wielkosci[sY];
        }
        skladowe--;
        return true;
    }

    public static void main(String[] args) {
        połącz(4,3);
        połącz(3,8);
        połącz(6,5);
        połącz(2,1);
        połącz(5,0);
        połącz(7,2);
        połącz(5,9);
        int rep1 = wyszukaj(0);
        int rep2 = wyszukaj(6);
        System.out.println("skladowych "+skladowe);
        if(rep1==rep2)
            System.out.println("Istnieje ścieżka");
        else
            System.out.println("Nie istnieje ścieżka");
    }
}
