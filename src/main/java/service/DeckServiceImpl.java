package service;

import enums.Semi;
import modelli.Card;

import java.util.*;

public class DeckServiceImpl implements DeckService{
    private  static List<Card> cards = new LinkedList<>();
    private  final int nCarte=40;
    private final int nSeme=4;

    public int getnCarte() {
        return nCarte;
    }

    public int getnSeme() {
        return nSeme;
    }

    public List<Card> deckGenerator() {
        for (int i = 1; i <= nCarte/nSeme; i++) {
            cards.add(new Card(i, Semi.bastoni));
            cards.add(new Card(i, Semi.coppe));
            cards.add(new Card(i, Semi.denari));
            cards.add(new Card(i, Semi.spade));
        }
        System.out.println("Mazzo creato");
        return cards;
    }

    @Override
    public List<Card> cardsShuffle() {
        List<Card> deck = deckGenerator();
        Card[] cardsTemp= (Card[]) deck.toArray(new Card[deck.size()-1]);
        Card temp;
        Random rand= new Random();
        int r1;
        int r2;
        for(int i=0; i<10000000; i++) {
            r1=rand.nextInt(0, nCarte-1);
            r2=rand.nextInt(0, nCarte-1);
            temp=cardsTemp[r1];
            cardsTemp[r1]=cardsTemp[r2];
            cardsTemp[r2]=temp;
        }

        System.out.println("Mazzo mescolato");

        return new LinkedList<>(Arrays.asList(cardsTemp));
    }


}
