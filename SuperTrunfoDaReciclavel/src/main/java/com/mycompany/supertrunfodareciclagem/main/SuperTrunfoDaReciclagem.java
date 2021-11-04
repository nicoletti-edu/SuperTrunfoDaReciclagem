package com.mycompany.supertrunfodareciclagem.main;

import com.mycompany.supertrunfodareciclagem.util.Baralho;
import com.mycompany.supertrunfodareciclagem.util.Carta;
import com.mycompany.supertrunfodareciclagem.util.Jogador;
import com.mycompany.supertrunfodareciclagem.util.Status;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicoletti.edu
 */
public class SuperTrunfoDaReciclagem {

    private Jogador[] jogadores;
    private ArrayList<Carta> mesa;
    private static int turno;
    private static int proxJogador;

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
        int criterio;
        boolean fimTurno;
        SuperTrunfoDaReciclagem jogo = new SuperTrunfoDaReciclagem();
        while (!jogo.isFim()) {
            jogo.novaRodada();
            criterio = jogo.escolhaDeCriterio();
            jogo.faseDeCompra();
            fimTurno = jogo.fimDoTurno(criterio);
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
     * @return inicia o jogo, põe os jogadores em ordem de quem vai começar no
     * primerio turno no vetor dividi o baralho entre eles e instancia as cartas
     * que estarão na mesa entre eles
     *
     */
    public void inicia() {
        int nroJogador;
        Random gerador = new Random();
        int primeiroJogador = gerador.nextInt();
        Scanner sc = new Scanner(System.in);
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
                this.jogadores[i].setCarta(baralho.selecionaCarta());
            }
        }
        sc.close();
    }

    //O primeiro jogador puxa a carta e olha os atributos
    public void novaRodada() {
        if (turno == 0) {
            this.mesa.add(0,jogadores[0].excluir());
            System.out.println("Carta do jogador 1 : \n");
            System.out.println(mesa.get(0));
        } else {
            this.mesa.add(proxJogador, jogadores[proxJogador].excluir());
            System.out.println("Carta do jogador " + (proxJogador + 1) + " : \n");
            System.out.println(mesa.get(proxJogador).toString());
        }
        turno++;
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

    //Outros jogadores compram a carta e "põe na mesa virado para baixo
    public void faseDeCompra() {
        for (int i = 0; i < this.jogadores.length - 1; i++) {
            if (proxJogador < this.jogadores.length) {
                this.mesa.add(jogadores[proxJogador].excluir());
            } else {
                proxJogador = 0;
                this.mesa.add(jogadores[proxJogador].excluir());
            }
            proxJogador++;
        }
        //Seta na posicao do primeiro Jogador
        if (proxJogador < this.jogadores.length) {
            proxJogador++;
        } else {
            proxJogador = 0;
        }
    }

    public Jogador[] getJogadores() {
        return jogadores;
    }

    public void setJogadores(Jogador[] jogadores) {
        this.jogadores = jogadores;
    }

    public static int getTurno() {
        return turno;
    }

    public static void setTurno(int turno) {
        SuperTrunfoDaReciclagem.turno = turno;
    }

    public static int getProxJogador() {
        return proxJogador;
    }

    private boolean isFim() {
        boolean ret = false;
        for (int i = 0; i < this.jogadores.length; i++) {
            ret = ret ^ this.jogadores[i].temCartas(); //xor entre jogadores, se houver mais de um que tenha o baralho retornará false
        }
        return ret;
    }

    private boolean fimDoTurno(int criterio) {
        boolean fim = true;
        Status resultado;
        int indiceVencedor = 0;
        switch (criterio) {
            case 1:
                for (int i = 0; i < this.jogadores.length - 1; i++) {
                    resultado = mesa.get(i).critCor(mesa.get(i+1));
                    if (resultado.equals(Status.PERDE)) {
                        if (resultado.equals(Status.PERDE)) {
                            if ((indiceVencedor = i + 1) >= this.jogadores.length) {
                                indiceVencedor = 0;
                            }
                        } else if (resultado.equals(Status.GANHA)) {
                            indiceVencedor = i;
                        } else {
                            fim = false;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < this.jogadores.length - 1; i++) {
                    resultado = mesa.get(i).critCor(mesa.get(i+1));
                    if (resultado.equals(Status.PERDE)) {
                        if (resultado.equals(Status.PERDE)) {
                            if ((indiceVencedor = i + 1) >= this.jogadores.length) {
                                indiceVencedor = 0;
                            }
                        } else if (resultado.equals(Status.GANHA)) {
                            indiceVencedor = i;
                        } else {
                            fim = false;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < this.jogadores.length - 1; i++) {
                    resultado = mesa.get(i).critCor(mesa.get(i+1));
                    if (resultado.equals(Status.PERDE)) {
                        if (resultado.equals(Status.PERDE)) {
                            if ((indiceVencedor = i + 1) >= this.jogadores.length) {
                                indiceVencedor = 0;
                            }
                        } else if (resultado.equals(Status.GANHA)) {
                            indiceVencedor = i;
                        } else {
                            fim = false;
                        }
                    }
                }
                break;
            case 4:
                for (int i = 0; i < this.jogadores.length - 1; i++) {
                    resultado = mesa.get(i).critCor(mesa.get(i+1));
                    if (resultado.equals(Status.PERDE)) {
                        if ((indiceVencedor = i + 1) >= this.jogadores.length) {
                            indiceVencedor = 0;
                        }
                    } else if (resultado.equals(Status.GANHA)) {
                        indiceVencedor = i;
                    } else {
                        fim = false;
                    }
                }
        }
        proxJogador = indiceVencedor;
        return fim;
    }
}
