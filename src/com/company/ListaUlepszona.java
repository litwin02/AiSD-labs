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
        ostatni.poprzedni=staryOstatni;

        if(jestPusta()) pierwszy=ostatni;
        else{
            staryOstatni.następny=ostatni;
        }
        N++;
        wynurzanie();
    }

    void drukuj(){
        ObiektListy bieżący = pierwszy;
        while (bieżący.następny!=null){
            System.out.println(bieżący.element);
            bieżący=bieżący.następny;
        }
        System.out.println(bieżący.element);
    }
    void drukuj_wstecz(){
        ObiektListy bieżący = ostatni;
        while(bieżący.poprzedni!=null){
            System.out.println(bieżący.element);
            bieżący=bieżący.poprzedni;
        }
        System.out.println(bieżący.element);
    }

    public Osoba pobierzPierwszy(){
        if(jestPusta()) return null;
        Osoba os = pierwszy.element;
        pierwszy = pierwszy.następny;
        if(pierwszy!=null){
            pierwszy.poprzedni=null;
        }
        N--;
        if(jestPusta()) ostatni=null;
        return os;
    }
    public ObiektListy pobierzItego(int index){
        if(jestPusta()) return null;
        ObiektListy zwracany = pierwszy;
        int i;
        for(i=1; i<N; i++){
            if(i==index)
                return zwracany;
            zwracany=zwracany.następny;
        }
        if(i==N) return zwracany;
        return null;
    }
    public boolean zamien_pozycjami(int i, int j){
        if(i>N || j>N) return false;
        ObiektListy first = pobierzItego(i);
        ObiektListy second = pobierzItego(j);
        Osoba pom = first.element;
        first.element=second.element;
        second.element=pom;
        return true;
    }
    public boolean czyJestKopcem() {
        int dlugosc = N;
        boolean jestKopcem = true;
        for (int k = 1; k < dlugosc; k++) {
            if (2 * k >= dlugosc) return true;
            jestKopcem = pobierzItego(k).element.compareTo(pobierzItego(2*k).element) >= 0;
            if (2 * k + 1 >= dlugosc) return jestKopcem;
            jestKopcem = jestKopcem && pobierzItego(k).element.compareTo(pobierzItego(2*k+1).element) >= 0;
            if (!jestKopcem) return false;
        }
        return true;
    }
    public void wynurzanie() {
        int k=N;
        ObiektListy pierwszyK = pobierzItego(k/2);
        ObiektListy drugiK = pobierzItego(k);
        boolean warunek = pierwszyK.element.compareTo(drugiK.element)<0;
        while (k > 1 && warunek) {
            zamien_pozycjami(k/2, k);
            k = k / 2;
            if(k==1) break;
            pierwszyK = pobierzItego(k/2);
            drugiK = pobierzItego(k);
            warunek=pierwszyK.element.compareTo(drugiK.element)<0;
        }
    }

    public static void main(String[] args) {
        ListaUlepszona obiekt = new ListaUlepszona();
        obiekt.dodajDoListy(new Osoba("Litwin", "Jakub", "2002-09-30"));
        obiekt.dodajDoListy(new Osoba("Mazurkiewicz", "Mikołaj", "2002-07-01"));
        obiekt.dodajDoListy((new Osoba("Składowski", "Radosław", "2003-04-15")));
        obiekt.dodajDoListy(new Osoba("Staszkiewicz", "Tymoteusz", "2002-04-16"));
        obiekt.dodajDoListy(new Osoba("Dubowska", "Weronika", "2002-12-06"));
        obiekt.dodajDoListy(new Osoba("Żurawski", "Daniel", "1999-02-02"));
        obiekt.dodajDoListy(new Osoba("Zarębski", "Janusz", "1980-07-18"));
        /*System.out.println(obiekt.pierwszy.element);
        System.out.println(obiekt.pierwszy.następny.element);
        System.out.println(obiekt.ostatni.poprzedni.element);
        obiekt.drukuj();
        System.out.println();
        obiekt.drukuj_wstecz();
        System.out.println(obiekt.pobierzPierwszy().nazwisko);
        obiekt.drukuj();
        System.out.println("\nZAMIANA\n");
        obiekt.zamien_pozycjami(1, 2);
        obiekt.drukuj();
        int wynik = obiekt.pobierzItego(4).element.compareTo(obiekt.pobierzItego(3).element);
        obiekt.drukuj();*/
        int wynik = obiekt.pobierzItego(6).element.compareTo(obiekt.pobierzItego(7).element);
        System.out.println("czy jest kopcem: " + obiekt.czyJestKopcem() + wynik);
        obiekt.drukuj();

    }
}
