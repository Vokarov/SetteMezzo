package service;

import enums.Semi;
import modelli.Card;
import modelli.CardNapoletane;

import java.util.*;


public class DeckServiceNapoletaneImpl implements DeckService {
    private  static List<Card> cards = new LinkedList<>();
    private final int nCarte=CardNapoletane.getnCarte();
    private final int nSeme=CardNapoletane.getnSeme();
    public List<Card> deckGenerator() {
        for (int i = 1; i <= nCarte/nSeme; i++) {
            cards.add(new CardNapoletane(i, Semi.getSeme(1)));
            cards.add(new CardNapoletane(i, Semi.getSeme(2)));
            cards.add(new CardNapoletane(i, Semi.getSeme(3)));
            cards.add(new CardNapoletane(i, Semi.getSeme(4)));
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

    @Override
    public int getnCarte() {
        return CardNapoletane.getnCarte();
    }

    @Override
    public int getnSeme() {
        return CardNapoletane.getnSeme();
    }


}
