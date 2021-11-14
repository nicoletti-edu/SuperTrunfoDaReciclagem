package com.mycompany.supertrunfodareciclagem.util;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Carta {

    private String cod;
    private String nome;
    private String descricao;
    private String tipo;
    private Cor cor;
    private Double decomposicao;
    private Integer ataque;
    private int indiceJogador;
    private static final int LARGURA = 29; // TEM QUE SER IMPAR ;)

    public Carta(String[] dados) {
        this.cod = dados[0];
        this.nome = dados[1];
        this.descricao = dados[2];
        this.tipo = dados[3];
        this.cor = Cor.stringToCor(dados[4]);
        this.decomposicao = Double.parseDouble(dados[5]);
        this.ataque = Integer.parseInt(dados[6]);
    }

    public abstract boolean isReciclavel();

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

    public void setCor(String c) {
        this.cor = Cor.stringToCor(c);
    }

    public Double getDecomposicao() {
        return decomposicao;
    }

    public void setDecomposicao(double decomposicao) {
        this.decomposicao = decomposicao;
    }

    public Integer getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getIndiceJogador() {
        return indiceJogador;
    }

    public void setIndiceJogador(int indiceJogador) {
        this.indiceJogador = indiceJogador;
    }

    public Status critCor(Carta c) {
        //Se a carta for MEGA WINNER
        if (this.cod.equals("H3")) {
            //Perde se a Carta c for uma não reciclavel
            if (!c.isReciclavel()) {
                return Status.PERDE;
                //Ganha em outros casos, independente da cor
            } else {
                return Status.GANHA;
            }
            //Se a carta for o SUPER PNEU    
        } else if (this.cod.equals("H4")) {
            //Ganha se a carta c for uma não reciclavel
            if (!c.isReciclavel()) {
                return Status.GANHA;
                //Perde em outros casos
            } else {
                return Status.PERDE;
            }
        }
        switch (this.cor) {
            case VERDE:
                switch (c.getCor()) {
                    case VERDE:
                        return Status.EMPATA;
                    case MARROM:
                        return Status.GANHA;
                    case CINZA:
                        return Status.GANHA;
                    case PRETO:
                        return Status.GANHA;
                    case BRANCO:
                        return Status.GANHA;
                    case LARANJA:
                        return Status.GANHA;
                    default:
                        return Status.PERDE;
                }
            case CINZA:
                switch (c.getCor()) {
                    case CINZA:
                        return Status.EMPATA;
                    case PRETO:
                        return Status.GANHA;
                    case BRANCO:
                        return Status.GANHA;
                    case LARANJA:
                        return Status.GANHA;
                    case ROXO:
                        return Status.GANHA;
                    case AZUL:
                        return Status.GANHA;
                    default:
                        return Status.PERDE;
                }
            case LARANJA:
                switch (c.getCor()) {
                    case LARANJA:
                        return Status.EMPATA;
                    case ROXO:
                        return Status.GANHA;
                    case AZUL:
                        return Status.GANHA;
                    case VERMELHO:
                        return Status.GANHA;
                    case AMARELO:
                        return Status.GANHA;
                    case VERDE:
                        return Status.GANHA;
                    default:
                        return Status.PERDE;
                }
            case VERMELHO:
                switch (c.getCor()) {
                    case VERMELHO:
                        return Status.EMPATA;
                    case AMARELO:
                        return Status.GANHA;
                    case VERDE:
                        return Status.GANHA;
                    case MARROM:
                        return Status.GANHA;
                    case CINZA:
                        return Status.GANHA;
                    case PRETO:
                        return Status.GANHA;
                    default:
                        return Status.PERDE;
                }
            case AZUL:
                switch (c.getCor()) {
                    case AZUL:
                        return Status.EMPATA;
                    case VERMELHO:
                        return Status.GANHA;
                    case AMARELO:
                        return Status.GANHA;
                    case VERDE:
                        return Status.GANHA;
                    case MARROM:
                        return Status.GANHA;
                    case CINZA:
                        return Status.GANHA;
                    default:
                        return Status.PERDE;
                }
            case AMARELO:
                switch (c.getCor()) {
                    case AMARELO:
                        return Status.EMPATA;
                    case VERDE:
                        return Status.GANHA;
                    case CINZA:
                        return Status.GANHA;
                    case PRETO:
                        return Status.GANHA;
                    case MARROM:
                        return Status.GANHA;
                    case BRANCO:
                        return Status.GANHA;
                    default:
                        return Status.PERDE;
                }
            case MARROM:
                switch (c.getCor()) {
                    case MARROM:
                        return Status.EMPATA;
                    case CINZA:
                        return Status.GANHA;
                    case PRETO:
                        return Status.GANHA;
                    case ROXO:
                        return Status.GANHA;
                    case LARANJA:
                        return Status.GANHA;
                    case BRANCO:
                        return Status.GANHA;
                    default:
                        return Status.PERDE;
                }
            case ROXO:
                switch (c.getCor()) {
                    case ROXO:
                        return Status.EMPATA;
                    case AZUL:
                        return Status.GANHA;
                    case VERMELHO:
                        return Status.GANHA;
                    case AMARELO:
                        return Status.GANHA;
                    case MARROM:
                        return Status.GANHA;
                    case VERDE:
                        return Status.GANHA;
                    default:
                        return Status.PERDE;
                }
            case BRANCO:
                switch (c.getCor()) {
                    case BRANCO:
                        return Status.EMPATA;
                    case ROXO:
                        return Status.GANHA;
                    case LARANJA:
                        return Status.GANHA;
                    case AZUL:
                        return Status.GANHA;
                    case VERMELHO:
                        return Status.GANHA;
                    case AMARELO:
                        return Status.GANHA;
                    default:
                        return Status.PERDE;
                }
            case PRETO:
                switch (c.getCor()) {
                    case PRETO:
                        return Status.EMPATA;
                    case AZUL:
                        return Status.GANHA;
                    case VERMELHO:
                        return Status.GANHA;
                    case ROXO:
                        return Status.GANHA;
                    case LARANJA:
                        return Status.GANHA;
                    case BRANCO:
                        return Status.GANHA;
                    default:
                        return Status.PERDE;
                }
            default:
                return Status.INDEFINIDA;
        }
    }

    public Status critDecoposicao(Carta c) {
        //Se a carta for mega Winner
        if (this.cod.equals("H3")) {
            //Se a carta comparada for da serie 1
            if (c.getCod().substring(1).equals("1")) {
                return Status.GANHA;
            } else {
                return Status.PERDE;
            }
        } else {
            if (this.decomposicao < c.getDecomposicao()) {
                return Status.GANHA;
            } else if (this.decomposicao > c.getDecomposicao()) {
                return Status.PERDE;
            } else {
                return Status.EMPATA;
            }
        }
    }

    public Status critReciclavel(Carta c) {
        if (this.isReciclavel()) {
            if (c.isReciclavel()) {
                return Status.EMPATA;
            } else {
                return Status.GANHA;
            }
        } else {
            if (c.isReciclavel()) {
                return Status.PERDE;
            } else {
                return Status.EMPATA;
            }
        }
    }

    public Status critAtaque(Carta c) {
        if (this.ataque.equals(c.getAtaque())) {
            return Status.EMPATA;
        } else {
            if (this.ataque > c.getAtaque()) {
                return Status.GANHA;
            } else {
                return Status.PERDE;
            }
        }
    }

    public String toString() {
        String dados = linhaTopo()
                + centralizaAtributos(this.cod)
                + centralizaAtributos(this.nome)
                + centralizaDescricao(this.descricao)
                + centralizaAtributos(this.tipo)
                + centralizaAtributos(this.cor.toString())
                + centralizaAtributos("Decomposição: " + this.decomposicao.toString())
                + centralizaAtributos("Ataque: " + this.ataque.toString())
                + linhaRodapé();
        return dados;
    }

    protected String linhaTopo() {
        StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < LARGURA - 1; i++) {
            sb.append("_");
        }
        sb.append("\n|");
        for (int i = 0; i < LARGURA - 1; i++) {
            sb.append(" ");
        }
        sb.append("|\n");
        return sb.toString();
    }

    protected String linhaRodapé() {
        StringBuilder sb = new StringBuilder("|");
        for (int i = 0; i < LARGURA - 1; i++) {
            sb.append("_");
        }
        sb.append("|\n");
        return sb.toString();
    }

    /**
     *
     * @param conteudo Atributo da carta
     * @return Linha da carta a ser impressa
     */
    protected String centralizaAtributos(String conteudo) {
        StringBuilder sb = new StringBuilder(conteudo);
        boolean par = conteudo.length() % 2 == 0;
        Double espacos = (LARGURA - conteudo.length()) / 2.0;
        espacos = Math.floor(espacos);
        sb.insert(0, "|");
        for (int i = 0; i < (par ? espacos : espacos - 1); i++) {
            sb.insert(1, " ");
        }
        for (int i = 0; i < espacos; i++) {
            sb.append(" ");
        }
        sb.append("|\n");
        return sb.toString();
    }

    /**
     *
     * @param descricao Descricao da carta
     * @return Lista com a descricao separada em linhas para caber na carta
     */
    protected String centralizaDescricao(String descricao) {
        StringBuilder sb = new StringBuilder("");
        ArrayList<String> linhas = separaString(descricao);
        linhas.forEach(linha -> {
            sb.append(centralizaAtributos(linha));
        });
        return sb.toString();
    }

    private ArrayList<String> separaString(String texto) {
        ArrayList<String> palavras = new ArrayList<>(Arrays.asList(texto.split(" ")));
        ArrayList<String> palavrasARemover = new ArrayList<>();
        ArrayList<String> resultado = new ArrayList<>();
        String linha = "";
        while (!palavras.isEmpty()) {
            for (String palavra : palavras) {
                if ((palavra + " " + linha).length() <= LARGURA - 2) {
                    linha += palavra + " ";
                    palavrasARemover.add(palavra);
                } else {
                    resultado.add(linha);
                    linha = "";
                    break;
                }
            }
            //Para quando sobra uma palavra na linha
            if (!linha.equals("")) {
                resultado.add(linha);
            }
            palavrasARemover.forEach(palavra -> {
                palavras.remove(palavra);
            });
            palavrasARemover.clear();
        }
        return resultado;
    }

}
