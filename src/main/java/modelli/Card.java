package modelli;


import enums.Semi;

import java.util.Objects;

public class Card {
    private final int numero;
    private final Semi seme;

    public Card(int numero, Semi seme) {
        this.numero = numero;
        this.seme = seme;
    }

    public int getNumero() {
        return numero;
    }

    public Semi getSeme() {
        return seme;
    }

    @Override
    public String toString() {
        return "\t"+numero + " " + seme + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return numero == card.numero && seme == card.seme;
    }



    @Override
    public int hashCode() {
        return Objects.hash(numero, seme);
    }
}
