package game;


import modelli.Card;
import service.DeckService;
import service.SetteMezzoService;
import service.SetteMezzoServiceImpl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SetteMezzoApp implements SetteMezzoAppInterface {
    SetteMezzoService setteMezzo;

    public SetteMezzoApp(SetteMezzoService setteMezzoService) {
        this.setteMezzo = setteMezzoService;
    }

    public void game() {
        //    private List<Integer> vincitori= new ArrayList<>();

        int numberOFPlayer = playerNumber();

        boolean isPalazzo = false;
        boolean hasBancAdd = false;
        setteMezzo.startGame();
        System.out.println();
        for (int i = 0; i < numberOFPlayer; i++) {
            setteMezzo.countHand(setteMezzo.getHandPlayer());
            System.out.println("Hai " + setteMezzo.countHand(setteMezzo.getHandPlayer()));
            System.out.println("=====================");


            while (setteMezzo.wantCard()) {
                System.out.println("=====================");
                System.out.println("Player:\n");

                setteMezzo.addCardPlayer(setteMezzo.getHandPlayer());
                System.out.println(setteMezzo.getHandPlayer());
                System.out.println("Hai " + setteMezzo.countHand(setteMezzo.getHandPlayer()));
                System.out.println("=====================");

                if (setteMezzo.countHand(setteMezzo.getHandPlayer()) == -1) {
                    System.out.println("Palazzo");
                    isPalazzo = true;
                    break;
                }
            }
            System.out.println("=====================");


            while (!setteMezzo.willBancWinner() && !isPalazzo) {
                hasBancAdd = true;
                System.out.println("=====================");
                System.out.println("Banco:\n");
                System.out.println(setteMezzo.getHandBanc());
                System.out.println();
                System.out.println("Il banco ha " + setteMezzo.countHand(setteMezzo.getHandBanc()));

                System.out.println();
                System.out.println("Il banco pesca");
                System.out.println(setteMezzo.addCardBanc(setteMezzo.getHandBanc()));
                System.out.println("Il banco ha " + setteMezzo.countHand(setteMezzo.getHandBanc()));
                System.out.println("=====================");
                if (setteMezzo.countHand(setteMezzo.getHandBanc()) == -1) {
                    System.out.println("Il banco ha fatto Palazzo");
                    System.out.println("Hai vinto");
                    isPalazzo = true;

                    break;
                }
            }

            if (!hasBancAdd && !isPalazzo) {
                System.out.println(setteMezzo.getHandBanc());
                System.out.println();
                System.out.println("Il banco ha " + setteMezzo.countHand(setteMezzo.getHandBanc()));


            }

            if (!isPalazzo) {
                if (setteMezzo.countHand(setteMezzo.getHandPlayer()) > setteMezzo.countHand(setteMezzo.getHandBanc())) {
                    System.out.println("Hai vinto");
//                    vincitori.add(i+1);

                } else {
                    System.out.println("Hai perso");
                }
            }
            isPalazzo = false;
            hasBancAdd = false;
            setteMezzo.getHandPlayer().clear();
            setteMezzo.getHandBanc().clear();
            if(i+1<numberOFPlayer){prossimoPlayer();};

        }
//        System.out.println("I vincitori:");
//        for (int i = 0; i < vincitori.size(); i++) {
//            System.out.println("Player "+vincitori.get(i).toString());
//        }

    }

    @Override
    public int playerNumber() {
        boolean isRispostaCorrect = true;
        int numberOfPlayer = 1;
        Scanner scanner = new Scanner(System.in);
        int risposta = 0;
        do {

            try {
                isRispostaCorrect = true;
                System.out.println("Quanti player siete contro il banco?");
                risposta = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.err.println(e);
                isRispostaCorrect = false;
                scanner.next();
            }

        } while (isRispostaCorrect == false);
        return risposta;

    }

    @Override
    public void prossimoPlayer() {
    System.out.println("PROSSIMO PLAYER!");
        System.out.println("=======================================\n");



        System.out.println("=======================================");
        if (setteMezzo.getDeck().size() < setteMezzo.getNumberCards() / 2) {
            setteMezzo.startGame();
        } else {
            setteMezzo.giveCard(setteMezzo.getHandPlayer());
            setteMezzo.giveCard(setteMezzo.getHandBanc());
        }
    }


}
