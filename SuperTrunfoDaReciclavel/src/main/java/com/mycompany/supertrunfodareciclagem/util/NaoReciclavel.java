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

    @Override
    public String toString() {
        String dados = "Id: " + super.getCod()
                + "\nNome: " + super.getNome()
                + "\nDescrição: " + super.getDescricao()
                + "\nTipo: " + super.getTipo()
                + "\nCor: " + super.getCor().toString()
                + "\nDecomposição: " + super.getDecomposicao()
                + "\nAtaque: " + super.getAtaque()
                + "\nReciclavel: Nao";
        return dados;
    }

}
