package com.mycompany.supertrunfodareciclagem.util;

/**
 *
 * @author iyiSakuma
 */
public class NaoReciclavel extends Carta {

    public NaoReciclavel(String[] dados) {
        super(dados);
    }

    @Override
    public boolean isReciclavel() {
        return false;
    }

}
