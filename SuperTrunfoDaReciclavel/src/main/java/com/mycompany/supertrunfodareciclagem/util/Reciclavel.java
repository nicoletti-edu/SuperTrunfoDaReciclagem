package com.mycompany.supertrunfodareciclagem.util;

/**
 *
 * @author iyiSakuma
 */
public class Reciclavel extends Carta {

    public Reciclavel(String[] dados) {
        super(dados);
    }

    @Override
    public boolean isReciclavel() {
        return true;
    }

}
