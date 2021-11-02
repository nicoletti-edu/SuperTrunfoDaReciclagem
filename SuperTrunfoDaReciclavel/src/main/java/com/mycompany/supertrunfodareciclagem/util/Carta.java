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
    private Double decomposicao;
    private Integer ataque;

    public abstract boolean isReciclavel();

    public Carta(String[] dados) {
        this.cod = dados[0];
        this.nome = dados[1];
        this.descricao = dados[2];
        this.tipo = dados[3];
        this.cor = Cor.stringToCor(dados[4]);
        this.decomposicao = Double.parseDouble(dados[5]);
        this.ataque = Integer.parseInt(dados[6]);
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Double getDecomposicao() {
        return decomposicao;
    }

    public void setDecomposicao(Double decomposicao) {
        this.decomposicao = decomposicao;
    }

    public Integer getAtaque() {
        return ataque;
    }

    public void setAtaque(Integer ataque) {
        this.ataque = ataque;
    }

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
