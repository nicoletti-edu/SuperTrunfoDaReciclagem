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
        String dados = linhaTopo()
                + centralizaAtributos(super.getCod())
                + centralizaAtributos(super.getNome())
                + centralizaDescricao(super.getDescricao())
                + centralizaAtributos(super.getTipo())
                + centralizaAtributos(super.getCor().toString())
                + centralizaAtributos("Decomposição: " + super.getDecomposicao().toString())
                + centralizaAtributos("Ataque: " + super.getAtaque().toString())
                + centralizaAtributos("Reciclavel: Não")
                + linhaRodapé();
        return dados;
    }

}
