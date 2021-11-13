package com.mycompany.supertrunfodareciclagem.util;

import java.util.ArrayList;

/**
 *
 * @author iyiSakuma
 */
public class Jogador {

    private String nome;
    private ArrayList<Carta> cartas = new ArrayList<>();

    public Jogador() {

    }

    public Jogador(String nome) {
        this.nome = nome;
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

    public int numeroDeCartas() {
        return cartas.size();
    }

    public void adicionaCarta(Carta carta) {
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
        if(cartas.isEmpty()){
            temcarta = false;
        }else{
            temcarta = true;
        }
        return temcarta;
    }
}
