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
//
//    @Override
//    public String toString() {
//        String dados = "Id: " + super.getCod()
//                + "\nNome: " + super.getNome()
//                + "\nDescrição: " + super.getDescricao()
//                + "\nTipo: " + super.getTipo()
//                + "\nCor: " + super.getCor().toString()
//                + "\nDecomposição: " + super.getDecomposicao()
//                + "\nAtaque: " + super.getAtaque()
//                + "\nReciclavel: Sim";
//        return dados;
//    }

}
