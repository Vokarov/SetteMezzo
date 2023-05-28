package game;


import service.DeckService;
import service.SetteMezzoService;
import service.SetteMezzoServiceImpl;

public class SetteMezzoApp {
    SetteMezzoService setteMezzo;

    public SetteMezzoApp(SetteMezzoService setteMezzoService) {
        this.setteMezzo = setteMezzoService;
    }

    public void game()

    {
        boolean isPalazzo = false;
        boolean hasBancAdd = false;
        setteMezzo.startGame();
        System.out.println();

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
                System.err.println("Palazzo");
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
                System.err.println("Il banco ha fatto Palazzo");
                System.err.println("Hai vinto");
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
                System.err.println("Hai vinto");
            } else {
                System.err.println("Hai perso");
            }
        }


    }

}
