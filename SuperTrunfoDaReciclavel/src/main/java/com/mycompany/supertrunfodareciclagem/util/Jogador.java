/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supertrunfodareciclagem.util;

import java.util.ArrayList;

/**
 *
 * @author iyiSakuma
 */
public class Jogador {
    private String nome;
    private ArrayList<Carta> cartas = new ArrayList<Carta>();
    
    public String getNome(){
        return this.nome;
    }
    public int getNumeroDeCartas(){
       return cartas.size();
    }
    public void setCarta(Carta carta){
        this.cartas.add(carta);
    }
    public Carta excluir(){
        Carta c = null;
        if(this.temCartas()){
            c = cartas.get(0);
        }
        return c;
    }
    public boolean temCartas(){
        if(cartas.isEmpty())
            return false;
        return true;
    }
}
