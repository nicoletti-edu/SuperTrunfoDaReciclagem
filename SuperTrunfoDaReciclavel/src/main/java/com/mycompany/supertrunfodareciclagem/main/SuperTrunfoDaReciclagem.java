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
    private int turno, proxJogador, atualJogador;
    private boolean jogadoresEmpatados;
    private Random gerador;

    public static void main(String[] args) {
        int criterio;
        Status status;

        SuperTrunfoDaReciclagem jogo = new SuperTrunfoDaReciclagem();
        while (!jogo.isFim()) {
            jogo.novaRodada();
            criterio = jogo.escolhaDeCriterio();
            status = jogo.fasePrincipal(criterio, false);
            jogo.faseRevelacao(status);
            jogo.desempate();
            jogo.fimDoTurno();
        }
        for (Jogador j : jogo.getJogadores()) {
            System.out.println("Nro da carta do jogador " + j.getNome() + " : " + j.numeroDeCartas());
        }
        System.out.println("----> Fim do Jogo ");
        System.out.println("----> Turno: " + jogo.getTurno());
        System.out.println("----> Ganhador: " + jogo.ganhador().getNome());
    }

    public SuperTrunfoDaReciclagem() {
        gerador = new Random();
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
     * Inicia o jogo, põe os jogadores em ordem de quem vai começar no primerio
     * turno no vetor dividi o baralho entre eles e instancia as cartas que
     * estarão na mesa entre eles
     *
     */
    public void inicia() {
        int nroJogador;
        int primeiroJogador;
        System.out.println("------- Inicio do Jogo -------");

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
            for (int j = 0; Math.floor(32 / nroJogador) > j; j++) {
                this.jogadores[i].adicionaCarta(baralho.selecionaCarta());
            }
        }
    }

    //O primeiro jogador puxa a carta e olha os atributos
    public void novaRodada() {
        this.turno++;
        System.out.println("---------- Turno " + this.turno + " ----------");
        System.out.println("++++ Cartas dos jogadores: ****");
        for (Jogador j : jogadores) {
            System.out.println(j.getNome() + " : " + j.numeroDeCartas());
        }
        this.faseDeCompra();
        System.out.println("Carta do jogador " + jogadores[proxJogador].getNome() + " : \n");
        System.out.println(mesa.get(proxJogador).toString());

    }

    public int escolhaDeCriterio() {
        int escolha;
        System.out.println("Que criteiro você deseja escolher? \n");
        System.out.println("1.Cor |");
        System.out.println("2.Decomposição. |");
        System.out.println("3.Reciclavel. |");
        System.out.println("4.Ataque.\n");
        escolha = gerador.nextInt(3) + 1;
        System.out.println("Criterio escolhido: " + escolha);
        return escolha;
    }

    //Outros jogadores compram a carta e "põe na mesa virado para baixo
    public void faseDeCompra() {
        for (int i = 0; i < jogadores.length; i++) {
            mesa.add(jogadores[i].sacarCarta());
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
    private Status fasePrincipal(int criterio, boolean empate) {
        if (!empate) {
            System.out.println("-- Início da fase principal --");
        } else {
            System.out.println("--------- Desempate --------");
        }
        Status resultado = null;
        int i = mesa.size() - this.jogadores.length;
        int indiceVencedor = -1;
        switch (criterio) {
            case 1:
                resultado = mesa.get(i).critCor(mesa.get(i + 1));
                if (resultado == Status.EMPATA) {
                    this.jogadoresEmpatados = true;
                } else {
                    switch (resultado) {
                        case PERDE:
                            indiceVencedor = 1;
                            break;
                        case GANHA:
                            indiceVencedor = 0;
                            break;
                    }
                }

                break;

            case 2:
                resultado = mesa.get(i).critDecoposicao(mesa.get(i + 1));
                if (resultado == Status.EMPATA) {
                    this.jogadoresEmpatados = true;
                } else {
                    switch (resultado) {
                        case PERDE:
                            indiceVencedor = 1;
                            break;
                        case GANHA:
                            indiceVencedor = 0;
                            break;
                    }
                }
                break;

            case 3:
                resultado = mesa.get(i).critReciclavel(mesa.get(i + 1));
                if (resultado == Status.EMPATA) {
                    this.jogadoresEmpatados = true;
                } else {
                    switch (resultado) {
                        case PERDE:
                            indiceVencedor = 1;
                            break;
                        case GANHA:
                            indiceVencedor = 0;
                            break;
                    }
                }
                break;

            case 4:
                resultado = mesa.get(i).critAtaque(mesa.get(i + 1));
                if (resultado == Status.EMPATA) {
                    this.jogadoresEmpatados = true;
                } else {
                    switch (resultado) {
                        case PERDE:
                            indiceVencedor = 1;
                            break;
                        case GANHA:
                            indiceVencedor = 0;
                            break;
                    }
                }
                break;

        }
        if (indiceVencedor != -1) {
            atualJogador = proxJogador;
            proxJogador = indiceVencedor;
            this.jogadoresEmpatados = false;
        }
        return resultado;
    }

    private void faseRevelacao(Status status) {
        System.out.println("------- Fase revelação -------");

        int j = mesa.size() - jogadores.length; // pega as ultimas cartas do monte
        for (int i = 0; i < jogadores.length; i++) {
            if (i != atualJogador) {
                System.out.println("Carta do jogador " + jogadores[i].getNome() + " : \n" + mesa.get(j).toString());
            }
            j++;
        }
        if (status != null) {
            if (status == Status.EMPATA) {
                System.out.println("----------- Turno Empatado -----------");
            } else {
                System.out.println("----------- Vencedor da Rodada:" + jogadores[proxJogador].getNome() + " -----------");
            }
        }
    }

    private void desempate() {
        while (this.jogadoresEmpatados) {
            Status status;
            int i;
            this.faseDeCompra();

            for (i = 0; i < mesa.size(); i++) {
                Carta c;
                c = mesa.get(i);
                if (c == null) {
                    this.proxJogador = (i + 1) % jogadores.length;
                    this.jogadoresEmpatados = false;
                    break;
                }
            }
            if (this.jogadoresEmpatados) {
                int indice = mesa.size() - jogadores.length + proxJogador;
                System.out.println("--------- Desempate ---------");
                System.out.println("Carta do jogador " + jogadores[proxJogador].getNome() + " : \n");
                System.out.println(mesa.get(indice).toString());
                status = this.fasePrincipal(this.escolhaDeCriterio(), true);
                this.faseRevelacao(status);
            }
        }
    }

    private void fimDoTurno() {
        Jogador j = jogadores[proxJogador];
        for (Carta c : mesa) {
            if (c != null) {
                j.adicionaCarta(c);
            }
        }
        mesa.removeAll(mesa);
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    private Jogador ganhador() {
        Jogador vencedor = null;
        for (Jogador j : this.jogadores) {
            if (j.temCartas()) {
                vencedor = j;
            }
        }
        return vencedor;
    }

    public Jogador[] getJogadores() {
        return jogadores;
    }

    public void setJogadores(Jogador[] jogadores) {
        this.jogadores = jogadores;
    }
}
