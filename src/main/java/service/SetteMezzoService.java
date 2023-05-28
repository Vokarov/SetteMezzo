package service;

import modelli.Card;

import java.util.LinkedList;
import java.util.List;

public interface SetteMezzoService {
    void startGame();

    List<Card> addCardPlayer(List<Card> hand);

    List<Card> addCardBanc(List<Card> hand);

    double countHand(List<Card> hand);

    boolean wantCard();

    void giveCard(List<Card> hand);

    boolean wantContinue();

    boolean isWin();

    boolean willBancWinner();

    boolean getWin();

    List<Card> getHandBanc();

    List<Card> getHandPlayer();

    LinkedList<Card> getDeck();
}
