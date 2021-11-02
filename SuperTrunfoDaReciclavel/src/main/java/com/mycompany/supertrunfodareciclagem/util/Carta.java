/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supertrunfodareciclagem.util;

/**
 *
 * @author iyiSakuma
 */
public abstract class Carta {

    private String cod;
    private String nome;
    private String descricao;
    private String tipo;
    private Cor cor;
    private double decomposicao;
    private int ataque;

    public abstract boolean isReciclavel();

    public String toString() {
        String dados = "Nome: " + this.nome
                + "Descrição: " + this.descricao
                + "Tipo: " + this.tipo
                + "Cor: " + cor.toString()
                + "Decomposição: " + this.decomposicao
                + "Ataque: " + this.ataque;
        return dados;
    }

}
