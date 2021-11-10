package com.mycompany.supertrunfodareciclagem.main;

import com.mycompany.supertrunfodareciclagem.util.Baralho;
import com.mycompany.supertrunfodareciclagem.util.Carta;
import com.mycompany.supertrunfodareciclagem.util.Jogador;
import com.mycompany.supertrunfodareciclagem.util.Status;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nicoletti.edu
 */
public class SuperTrunfoDaReciclagem {

    private final Scanner sc = new Scanner(System.in);
    private Jogador[] jogadores;
    private ArrayList<Carta> mesa;
    private int turno;
    private int proxJogador;
    private boolean jogadoresEmpatados;

//    public static void main(String[] args) {
//        int criterio;
//        boolean fimDoJogo;
//        SuperTrunfoDaReciclagem jogo = new SuperTrunfoDaReciclagem();
//        while (!jogo.isFim()) {
//            jogo.novaRodada();
//            jogo.faseDeCompra();
//            criterio = jogo.escolhaDeCriterio();
//            jogo.fasePrincipal(criterio);
//            jogo.faseRevelacao();
//            jogo.desempate();
//            jogo.fimDoTurno();
//
//        }
//    }

    public SuperTrunfoDaReciclagem() {
        turno = 0;
        proxJogador = 0;
        mesa = new ArrayList<>();
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
     * Inicia o jogo, põe os jogadores em ordem de quem vai começar no
     * primerio turno no vetor dividi o baralho entre eles e instancia as cartas
     * que estarão na mesa entre eles
     *
     */
    public void inicia() {
        int nroJogador;
        Random gerador = new Random();
        int primeiroJogador = gerador.nextInt();
        Baralho baralho = new Baralho();
        System.out.println("Quantos jogadores participarão? ");
        nroJogador = sc.nextInt();
        primeiroJogador = gerador.nextInt(nroJogador);//decide aleatoriamente que vai ser o primeiro jogador
        this.jogadores = new Jogador[nroJogador];

        //Inicializa os jogadores
        for (int i = 0; i < nroJogador; i++) {
            String nome;
            System.out.println("Insira o nome do jogador " + (i + 1));
            nome = sc.next();
            if (primeiroJogador < nroJogador) {
                jogadores[primeiroJogador] = new Jogador(nome);
            } else {
                primeiroJogador = 0;
                jogadores[primeiroJogador] = new Jogador(nome);
            }
            primeiroJogador++;
        }
        this.turno = 0;

        //Partilha o baralho entre os jogadores
        for (int i = 0; i < nroJogador; i++) {
            for (int j = 0; (32 / nroJogador) > j; j++) {
                this.jogadores[i].adicionaCarta(baralho.selecionaCarta());
            }
        }
    }

    //O primeiro jogador puxa a carta e olha os atributos
    public void novaRodada() {
        this.turno++;
        System.out.println("**** Início do turno: " + this.turno + " ****");
        this.mesa.add(proxJogador, jogadores[proxJogador].sacarCarta());
        System.out.println("Carta do jogador " + (proxJogador + 1) + " : \n");
        System.out.println(mesa.get(proxJogador).toString());

    }

    public int escolhaDeCriterio() {
        int escolha;
        System.out.println("Que criteiro você quer escolher? \n");
        System.out.println("1.Cor\n");
        System.out.println("2.Decomposição.\n");
        System.out.println("3.Reciclavel.\n");
        System.out.println("4.Ataque.\n");
        return escolha = sc.nextInt();
    }

    //Outros jogadores compram a carta e "põe na mesa virado para baixo
    public void faseDeCompra() {
        for (int i = 0; i < jogadores.length; i++) {
            if (i != this.proxJogador) {
                mesa.add(jogadores[i].sacarCarta());
            }
        }
    }

    private boolean isFim() {
        boolean ret = false;
        for (int i = 0; i < this.jogadores.length; i++) {
            ret = ret ^ this.jogadores[i].temCartas(); //xor entre jogadores, se houver mais de um que tenha o baralho retornará false
        }
        return ret;
    }

    //Fase que faz comparação entre as cartas
    private void fasePrincipal(int criterio) {
        Status resultado = null;
        int i = jogadores.length - 2;
        int indiceVencedor = 0;
        switch (criterio) {
            case 1:
                resultado = mesa.get(i).critCor(mesa.get(i + 1));
                if (resultado == Status.PERDE) {
                    indiceVencedor = 1;
                } else if (resultado == Status.GANHA) {
                    indiceVencedor = 0;
                } else {
                    this.jogadoresEmpatados = true;
                }

                break;
            case 2:
                resultado = mesa.get(i).critDecoposicao(mesa.get(i + 1));
                if (resultado == Status.PERDE) {
                    indiceVencedor = 1;
                } else if (resultado == Status.GANHA) {
                    indiceVencedor = 0;
                } else {
                    this.jogadoresEmpatados = true;
                }

                break;
            case 3:
                resultado = mesa.get(i).critReciclavel(mesa.get(i + 1));
                if (resultado == Status.PERDE) {
                    indiceVencedor = 1;
                } else if (resultado == Status.GANHA) {
                    indiceVencedor = 0;
                } else {
                    this.jogadoresEmpatados = true;
                }

                break;
            case 4:
                resultado = mesa.get(i).critAtaque(mesa.get(i + 1));
                if (resultado == Status.PERDE) {
                    indiceVencedor = 1;
                } else if (resultado == Status.GANHA) {
                    indiceVencedor = 0;
                } else {
                    this.jogadoresEmpatados = true;
                }
                break;
        }
        if (!this.jogadoresEmpatados) {
            proxJogador = indiceVencedor;
        }
        if (resultado != null) {
            System.out.println(resultado.toString());
        }
    }

    private void faseRevelacao() {
        int i = jogadores.length - 1;
        Carta c = mesa.get(i);
        System.out.println("Cartas do Jogador 2: ");
        System.out.println(c.toString());
    }

    private void desempate() {
        while (this.jogadoresEmpatados) {
            System.out.println("**** DESEMPATE ****");
            this.mesa.add(proxJogador, jogadores[proxJogador].sacarCarta());
            System.out.println("Carta do jogador " + (proxJogador + 1) + " : \n");
            System.out.println(mesa.get(proxJogador).toString());
            this.faseDeCompra();
            this.faseRevelacao();
            this.fasePrincipal(this.escolhaDeCriterio());
        }
    }

    private void fimDoTurno() {
        Jogador j = jogadores[proxJogador];
        for (Carta c : mesa) {
            j.adicionaCarta(c);
            mesa.remove(c);
        }
    }
}
