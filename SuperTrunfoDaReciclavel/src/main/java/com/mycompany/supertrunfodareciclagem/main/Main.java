package com.mycompany.supertrunfodareciclagem.main;

import com.mycompany.supertrunfodareciclagem.util.Jogador;

/**
 *
 * @author nicoletti.edu
 */
public class Main {
    public static void main(String[] args) {
        int criterio;

        SuperTrunfoDaReciclagem jogo = new SuperTrunfoDaReciclagem();
        jogo.inicia();
        while (!jogo.isFim()) {
            jogo.novaRodada();
            criterio = jogo.escolhaDeCriterio();
            jogo.fasePrincipal(criterio, false);
            jogo.faseRevelacao(false);
            jogo.desempate();
            jogo.fimDoTurno();
            jogo.ValidaJogadores();
        }
        for (Jogador j : jogo.getJogadores()) {
            System.out.println("Nro da carta do jogador " + j.getNome() + " : " + j.numeroDeCartas());
        }
        System.out.println("----> Fim do Jogo ");
        System.out.println("----> Turno: " + jogo.getTurno());
        System.out.println("----> Ganhador: " + jogo.ganhador().getNome());
    }
}
