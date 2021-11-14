package com.mycompany.supertrunfodareciclagem.util;

import java.util.ArrayList;

/**
 *
 * @author iyiSakuma
 */
public class Jogador {

    private String nome;
    private ArrayList<Carta> cartas = new ArrayList<>();
    private int indice;

    public Jogador() {

    }

    public Jogador(String nome, int indice) {
        this.nome = nome;
        this.indice = indice;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
        for (int i = 0; i < this.cartas.size(); i++) {
            Carta cartaNova = this.cartas.get(i);
            cartaNova.setIndiceJogador(indice);
            this.cartas.set(i, cartaNova);
        }
    }

    public int numeroDeCartas() {
        return cartas.size();
    }

    public void adicionaCarta(Carta carta) {
        carta.setIndiceJogador(this.indice);
        this.cartas.add(carta);
    }

    public Carta sacarCarta() {
        if (this.temCartas()) {
            Carta c = this.cartas.get(0);
            this.cartas.remove(c);
            return c;

        }
        return null;
    }

    public boolean temCartas() {
        boolean temcarta;
        if (cartas.isEmpty()) {
            temcarta = false;
        } else {
            temcarta = true;
        }
        return temcarta;
    }
}
