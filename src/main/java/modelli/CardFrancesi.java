package modelli;

import enums.Semi;

public class CardFrancesi extends  Card {

    private static final int nCarte=52;
    private static final int nSeme=4;
    private final boolean jolly;

    public static int getnCarte() {
        return nCarte;
    }

    public static int getnSeme() {
        return nSeme;
    }


    public CardFrancesi(int numero, Semi seme, boolean jolly) {
        super(numero, seme);
        this.jolly=jolly;

    }
}
