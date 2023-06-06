package modelli;

import enums.Semi;

public class CardNapoletane extends Card{
    private static final int nCarte=40;
    private static final int nSeme=4;

    public static int getnCarte() {
        return nCarte;
    }

    public static int getnSeme() {
        return nSeme;
    }
    public CardNapoletane(int numero, Semi seme) {
        super(numero, seme);
    }
}
