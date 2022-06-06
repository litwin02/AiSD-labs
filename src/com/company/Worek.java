package com.company;

import java.util.Iterator;

public class Worek<Item> implements Iterable<Item> {
    private Wezel pierwszy;//pierwszy węzeł na liście
    private class Wezel {
        Item item;
        Wezel następny;
    }

    public void dodaj(Item a) {
        Wezel popPierwszy = pierwszy;
        pierwszy = new Wezel();
        pierwszy.item = a;
        pierwszy.następny = popPierwszy;
    }

    public Iterator<Item> iterator() {
        return new LIterator();
    }
    public class LIterator implements Iterator<Item> {
        private Wezel biezacy = pierwszy;
        public boolean hasNext() {
            return biezacy != null;
        }
        public Item next() {
            Item item = biezacy.item;
            biezacy = biezacy.następny;
            return item;
        }
    }
}

