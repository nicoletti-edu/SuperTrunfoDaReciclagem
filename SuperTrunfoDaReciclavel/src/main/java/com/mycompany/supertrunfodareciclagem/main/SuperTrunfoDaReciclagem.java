package com.mycompany.supertrunfodareciclagem.main;

import com.mycompany.supertrunfodareciclagem.util.Baralho;
import com.mycompany.supertrunfodareciclagem.util.Jogador;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicoletti.edu
 */
public class SuperTrunfoDaReciclagem {

    Jogador[] jogadores;

    public static void main(String[] args) {
        System.out.println("Iniciando jogo...\n");
        System.out.println("Teste 1 - Sacando Cartas\n");
        Baralho baralho = new Baralho();
        for (int i = 0; i < 32; i++) {
            System.out.println(baralho.selecionaCarta().toString() + "\n");
            System.out.println("Pressione enter para próxima carta!\n");
            try {
                System.in.read();
            } catch (IOException ex) {
                Logger.getLogger(SuperTrunfoDaReciclagem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
