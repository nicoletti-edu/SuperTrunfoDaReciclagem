package com.mycompany.supertrunfodareciclagem.util;

/**
 *
 * @author iyiSakuma
 */
public enum Status {
    GANHA("ganha"), PERDE("perde"), EMPATA("empata"), INDEFINIDA("indefinida");
    
    private final String status;
    Status(String strStatus){
        this.status = strStatus;
    }
    public static Status toStringComparacao(String strComp) {
        if(strComp.equals(GANHA.toString())){
            return GANHA;
        }
        if(strComp.equals(PERDE.toString())){
            return PERDE;
        }
        if(strComp.equals(EMPATA.toString())){
            return EMPATA;
        }
        return INDEFINIDA;
    }
}
