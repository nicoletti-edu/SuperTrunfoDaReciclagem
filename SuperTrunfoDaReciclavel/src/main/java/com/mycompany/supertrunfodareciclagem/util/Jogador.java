package com.mycompany.supertrunfodareciclagem.util;

import java.util.ArrayList;

/**
 *
 * @author iyiSakuma
 */
public class Jogador {

    private String nome;
    private ArrayList<Carta> cartas = new ArrayList<Carta>();
    
    public Jogador(){
        
    }
    public Jogador(String nome){
        this.nome = nome;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    public String getNome() {
        return this.nome;
    }

    public int getNumeroDeCartas() {
        return cartas.size();
    }

    public void setCarta(Carta carta) {
        this.cartas.add(carta);
    }

    public Carta excluir() {
        Carta c = null;
        if (this.temCartas()) {
            c = cartas.get(0);
        }
        return c;
    }

    public boolean temCartas() {
        if (cartas.isEmpty()) {
            return false;
        }
        return true;
    }
}
