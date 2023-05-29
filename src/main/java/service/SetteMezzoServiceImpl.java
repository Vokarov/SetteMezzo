package service;

import enums.Semi;
import modelli.Card;

import java.util.*;

public class SetteMezzoServiceImpl implements SetteMezzoService {

    DeckService deckService;
    private boolean win = false;
    private final double SOGLIA =5;
    public SetteMezzoServiceImpl(DeckService deckService) {
        this.deckService = deckService;
    }

    LinkedList<Card> deck;
    List<Card> handBanc = new ArrayList<>();
    List<Card> handPlayer = new ArrayList<>();

    @Override
    public void startGame() {
        deck = (LinkedList<Card>) deckService.cardsShuffle();
        giveCard(handPlayer);
        giveCard(handBanc);
        System.out.println(getHandPlayer());
    }

    // Aggiungo le carte alla mano. Questo medoto viene utilizzato all'interno del package e nelle sottoclassi
    public void giveCard(List<Card> hand) {
        hand.add(deck.removeFirst());
    }

    @Override // Aggiungo carta e Mostro solo le carte aggiunte (tranne la prima index=0)
    public List<Card> addCardPlayer(List<Card> hand) {
        giveCard(hand);
        List<Card> handShow = new ArrayList<>();
        for (int i = 1; i < hand.size(); i++) {
            handShow.add(hand.get(i));
        }
        if (countHand(hand) == 7.5) {
            System.out.println("Sette e Mezzo");

        }
        //        System.out.println("Hai "+ countHand(hand));
        return handShow;
    }

    @Override // Aggiungo carta e Mostro tutte le carte (quando il banco aggiunge le carte mostra anche la prima)
    public List<Card> addCardBanc(List<Card> hand) {
        giveCard(hand);
        List<Card> handShow = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            handShow.add(hand.get(i));
        }
        if (countHand(hand) == 7.5) {
            System.out.println("Sette e Mezzo");
            win = true;
        }
        return handShow;
    }

    @Override
    public double countHand(List<Card> hand) {
        double point = 0;
        double valore = 0;
        boolean hasMatta = false;
        for (Card card : hand) {
            if (card.getNumero() == 10 && card.getSeme() == Semi.denari) {
                hasMatta = true;
            }
            if (card.getNumero() == 10 || card.getNumero() == 9 || card.getNumero() == 8) {
                valore = 0.5;
            } else {
                valore = card.getNumero();
            }
            point += valore;
        }
        if (point > 7.5) {
            //            System.out.println("Palazzo");
            win = false;
            return -1;
        } else if (hasMatta && (point-0.5) == (int) point && point != 0.5) {
            point = 7;
        } else if (hasMatta && (point-0.5) != (int) point && point != 0.5) {
            point = 7.5;
            System.err.println("Sette e Mezzo");
        }
        //        System.out.println( "Hai "+point);
        return point;
    }


    @Override
    public boolean wantCard() {
        boolean isRispostaCorrect = true;
        boolean doWantCard = false;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                isRispostaCorrect = true;
                System.out.println("Vuoi una carta?");
                System.out.println("yes/no?");
                String risposta = scanner.nextLine();
                risposta = risposta.toLowerCase().trim();
                if (!risposta.equals("yes") && !risposta.equals("no")) {
                    throw new InputMismatchException("Non hai inserito la risposta esatta");
                }
                if (risposta.equals("yes")) {
                    doWantCard = true;
                }
                if (risposta.equals("no")) {
                    doWantCard = false;
                }

            } catch (InputMismatchException e) {
                System.err.println(e);
                isRispostaCorrect = false;
            }
        } while (isRispostaCorrect == false);
        return doWantCard;

    }


    @Override
    public boolean wantContinue() {
        boolean isRispostaCorrect = true;
        boolean doWantContinue = false;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                isRispostaCorrect = true;
                System.out.println("Vuoi Continuare");
                System.out.println("yes/no?");
                String risposta = scanner.nextLine();
                risposta = risposta.toLowerCase().trim();
                if (!risposta.equals("yes") && !risposta.equals("no")) {
                    throw new InputMismatchException("Non hai inserito la risposta esatta");
                }
                if (risposta.equals("yes")) {
                    doWantContinue = true;
                }
                if (risposta.equals("no")) {
                    doWantContinue = false;
                }
            } catch (InputMismatchException e) {
                System.err.println(e);
                isRispostaCorrect = false;
            }
        } while (isRispostaCorrect == false);
        return doWantContinue;
    }

    @Override
    public boolean isWin() {
        if (countHand(handPlayer) > countHand(handBanc)) {

            win=true;
            return win;
        }
        win=false;
        return win;
    }

    public boolean willBancWinner(){
        double point=0;
        Card matta = new Card(10, Semi.denari);
        boolean hasMatta = false;
        double valore=0;

        for (int i = 1; i <getHandPlayer().size() ; i++) {
            int card=getHandPlayer().get(i).getNumero();

            if (card== 10 || card == 9 || card== 8) {
                valore = 0.5 ;
            }else{
                valore=card;
            }
            point += valore;
        }
        if (handPlayer.contains(matta)) {
            point=7; // se fosse 7.5 lo direbbe il count.
        }
        double valueShown = point;

        if (countHand(handBanc)==7.5){
            return true;
        }
        if (countHand(handPlayer)==7.5 && countHand(handBanc)!=7.5){
            return false;
        }




        if (valueShown == 0){ // Se sul tavolo non ci sono carte scoperte pesco solo ho un punteggio oltre una soglia
            if (countHand(handBanc)>SOGLIA){
                System.out.println("valueShown == 0 \n countHand(handBanc)>SOGLIA");
                return true;
            }else {
                System.out.println("valueShown == 0 \n *NO* countHand(handBanc)>SOGLIA ");
                return false;
            }
        }



        if (valueShown >= countHand(handBanc)) { //Se il player ha sul tavolo un valore maggiore uguale non e' vincente -> PESCA
            System.out.println("valueShown >= countHand(handBanc");
            return false;
        } else if (valueShown > SOGLIA && countHand(handBanc)<6.5) { //Se il player ha sul tavolo un valore oltre la soglia ed il banco non ha 6.5-> PESCA
            System.out.println("*NO* valueShown >= countHand(handBanc) \n else if (valueShown > SOGLIA && countHand(handBanc)<6.5)");

            return false;
        }else if(valueShown < SOGLIA && countHand(handBanc)<5){
            System.out.println("*NO* valueShown >= countHand(handBanc) \n *NO* (valueShown > SOGLIA && countHand(handBanc)<6.5) \n else if(valueShown < SOGLIA && countHand(handBanc)<5)");

            return false;
        }else {
            System.out.println("*NO* valueShown >= countHand(handBanc) \n *NO* (valueShown > SOGLIA && countHand(handBanc)<6.5) \n *NO* (valueShown < SOGLIA && countHand(handBanc)<5) ");
            return true; //Se il player ha sul tavolo un valore minore la soglia -> Il banco potrebbe vincere
        }
    }


    public boolean getWin() {
        return win;
    }

    public List<Card> getHandBanc() {
        return handBanc;
    }

    public List<Card> getHandPlayer() {
        return handPlayer;
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }


}
