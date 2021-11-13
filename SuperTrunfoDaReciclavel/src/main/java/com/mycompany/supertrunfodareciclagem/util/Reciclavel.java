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

    @Override
    public String toString() {
        String dados = linhaTopo()
                + centralizaAtributos(super.getCod())
                + centralizaAtributos(super.getNome())
                + centralizaDescricao(super.getDescricao())
                + centralizaAtributos(super.getTipo())
                + centralizaAtributos(super.getCor().toString())
                + centralizaAtributos("Decomposição: " + super.getDecomposicao().toString())
                + centralizaAtributos("Ataque: " + super.getAtaque().toString())
                + centralizaAtributos("Reciclavel: Sim")
                + linhaRodapé();
        return dados;
    }

}
