package com.company;

public class Lista{

    ObiektListy pierwszy = null;

    void drukuj(){
        ObiektListy bieżący = pierwszy;
        while (bieżący.następny!=null){
            System.out.println(bieżący.element);
            bieżący=bieżący.następny;
        }
        System.out.println(bieżący.element);
    }
    void zamien(String nowe, int pozycja){
        ObiektListy bieżący = pierwszy;
        for(int i=0; i<=pozycja; i++){
            if(bieżący.następny==null)
                break;
            else if(i==pozycja){
                bieżący.element.nazwisko = nowe;
            }
            bieżący=bieżący.następny;
        }
    }

    private static class ObiektListy{
        Osoba element;
        ObiektListy następny = null;
    }

    public static void main(String[] args) {
        String[][] osoby = {{"Litwin" , "Jakub", "2002-09-30"},
                {"Stanowski", "Krzysztof", "1988-08-12"},
                {"Odnowiciel", "Kazimierz", "2000-02-14"},
                {"Borek", "Mateusz", "1970-07-27"}};
        Lista obiekt = new Lista();
        obiekt.pierwszy = new ObiektListy();
        obiekt.pierwszy.element=new Osoba(osoby[0][0], osoby[0][1], osoby[0][2]);
        ObiektListy kolejny = obiekt.pierwszy;

        for(int i=1; i< 4; i++){
            kolejny.następny = new ObiektListy();
            kolejny.następny.element=new Osoba(osoby[i][0],osoby[i][1], osoby[i][2]);
            kolejny = kolejny.następny;
        }

        obiekt.pierwszy.następny.element.imię = "Onufry";
        obiekt.pierwszy.następny.następny.element.dataUr = "1410-07-14";
        obiekt.zamien("Wielki", 200);
        System.out.println("NOWA LISTA PO ZAMIANIE: ");
        obiekt.drukuj();
    }
}
