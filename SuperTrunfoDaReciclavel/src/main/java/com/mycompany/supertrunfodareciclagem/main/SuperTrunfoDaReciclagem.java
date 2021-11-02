package com.mycompany.supertrunfodareciclagem.main;

import com.mycompany.supertrunfodareciclagem.util.Baralho;
import com.mycompany.supertrunfodareciclagem.util.Carta;
import com.mycompany.supertrunfodareciclagem.util.Jogador;
import com.mycompany.supertrunfodareciclagem.util.Status;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicoletti.edu
 */
public class SuperTrunfoDaReciclagem {

    Jogador[] jogadores;
    static int turno;
    static int proxJogador;

    public static void main(String[] args) {
        /*System.out.println("Iniciando jogo...\n");
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
        }*/

        SuperTrunfoDaReciclagem str = new SuperTrunfoDaReciclagem();
        while (str.jogadores.length > 1) {
        }
    }

    public SuperTrunfoDaReciclagem() {
        turno = 0;
        proxJogador = 0;
        inicia();

        //printa as cartas dos jogadores
        /*for (int i = 0; i < 2; i++) {
            System.out.println("Jogador: " + jogadores[i].getNome());
            for(Carta c : jogadores[i].getCartas()){
                System.out.println(c.toString() + "\n");
            }
        }*/
    }

    /**
     *
     * @return inicia o jogo, intanciando os jogadores e dividindo o baralho
     * entre eles
     *
     */
    public void inicia() {
        int nroJogador;
        Scanner sc = new Scanner(System.in);
        Baralho baralho = new Baralho();
        System.out.println("Quantos jogadores participarão? ");
        nroJogador = sc.nextInt();
        this.jogadores = new Jogador[nroJogador];

        //Inicializa os jogadores
        for (int i = 0; i < nroJogador; i++) {
            String nome;
            System.out.println("Insira o nome do jogador " + (i + 1));
            nome = sc.next();
            jogadores[i] = new Jogador(nome);
        }
        this.turno = 0;

        //Partilha o baralho entre os jogadores
        for (int i = 0; i < nroJogador; i++) {
            for (int j = 0; (32 / nroJogador) > j; j++) {
                this.jogadores[i].setCarta(baralho.selecionaCarta());
            }
        }
        sc.close();
    }

    public Carta novaRodada() {
        if (turno == 0) {
            Carta c = jogadores[proxJogador].excluir();
            System.out.println("Carta do jogado " + (proxJogador + 1) + " : \n");
            System.out.println(c.toString());
            turno++;
            return c;
        } else {
            Carta c = jogadores[proxJogador].excluir();
            System.out.println("Carta do jogado " + (proxJogador + 1) + " : \n");
            System.out.println(c.toString());
            turno++;
            return c;
        }
    }

    public int escolhaDeCriterio() {
        int escolha;
        Scanner sc = new Scanner(System.in);
        System.out.println("Que criteiro você quer escolher? \n");
        System.out.println("1.Cor\n");
        System.out.println("2.Decomposição.\n");
        System.out.println("3.Reciclavel.\n");
        System.out.println("4.Ataque.\n");
        do {
            escolha = sc.nextInt();
        } while (0 < escolha && escolha < 5);
        sc.close();
        return escolha;
    }
}
