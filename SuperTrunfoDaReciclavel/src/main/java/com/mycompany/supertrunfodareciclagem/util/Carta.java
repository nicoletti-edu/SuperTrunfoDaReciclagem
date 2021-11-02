
package com.mycompany.supertrunfodareciclagem.util;

public abstract class Carta {

    private String cod;
    private String nome;
    private String descricao;
    private String tipo;
    private Cor cor;
    private Double decomposicao;
    private Integer ataque;

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

    public double getDecomposicao() {
        return decomposicao;
    }

    public void setDecomposicao(double decomposicao) {
        this.decomposicao = decomposicao;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
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
        }else if(this.cod.equals("H4")){
            //Ganha se a carta c for uma não reciclavel
            if(!c.isReciclavel()){
                return Status.GANHA;
            //Perde em outros casos
            }else{
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
                switch(c.getCor()){
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
                switch(c.getCor()){
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
                switch(c.getCor()){
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
                switch(c.getCor()){
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
                switch(c.getCor()){
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
                switch(c.getCor()){
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
                switch(c.getCor()){
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
                switch(c.getCor()){
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
                switch(c.getCor()){
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

    public Status criReciclavel(Carta c) {
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

    public Status critSAtaque(Carta c) {
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
        String dados = "Nome: " + this.nome
                + "Descrição: " + this.descricao
                + "Tipo: " + this.tipo
                + "Cor: " + cor.toString()
                + "Decomposição: " + this.decomposicao
                + "Ataque: " + this.ataque;
        return dados;
    }

}