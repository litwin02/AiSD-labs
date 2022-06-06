package com.company;

import java.util.Random;

public class Stos <Element>{
    public ObiektStosu pierwszy = null;
    private int N;

    public class ObiektStosu{
        Element element;
        ObiektStosu elPoniżej = null;
    }

    public boolean czyPusty(){return N==0;}
    public int rozmiar(){return N;}

    public void połóżNaStosie(Element el){
        ObiektStosu staryPierwszy = pierwszy;
        pierwszy = new ObiektStosu();
        pierwszy.element = el;
        pierwszy.elPoniżej=staryPierwszy;
        N++;
    }
    public Element zdejmijZeStosu()
    {
        Element pobrany = pierwszy.element;
        pierwszy=pierwszy.elPoniżej;
        N--;
        return pobrany;
    }

    public static void main(String[] args) {
        /*
        Stos<Integer> stos = new Stos<Integer>();
        Random rand = new Random();
        int ile = 10;
        int x;
        for(int i=0; i<ile; i++) {
            stos.połóżNaStosie(rand.nextInt(100));
        }
        for(int i=0; i<ile; i++){
            x = stos.zdejmijZeStosu();
            System.out.println(x + " ");
        }
        */

        Stos<Integer> stosWart = new Stos<>();
        String ONP = "2 7 + 3 / 14 3 - 4 * + 2 /";
        String[] podzielonyONP = ONP.split(" ");
        int k=0;
        int a, b;

        while (k < podzielonyONP.length) {
            String c = podzielonyONP[k];
            if (c.matches("-[0-9]*") || c.matches("[0-9]*")) {stosWart.połóżNaStosie(Integer.valueOf(c));}
            else {
                a = stosWart.zdejmijZeStosu();
                b = stosWart.zdejmijZeStosu();
                switch (c) {
                    case "+":
                        stosWart.połóżNaStosie(b + a);
                        break;
                    case "-":
                        stosWart.połóżNaStosie(b - a);
                        break;
                    case "*":
                        stosWart.połóżNaStosie(b * a);
                        break;
                    case "/":
                        stosWart.połóżNaStosie(b / a);
                        break;
                }
            }
            k++;
        }
        System.out.println("wynik: " + stosWart.zdejmijZeStosu());
    }
}
