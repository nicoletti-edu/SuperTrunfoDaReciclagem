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
    private ArrayList<Integer> indiceEmpatados;
    private int turno, proxJogador, atualJogador;
    private boolean jogadoresEmpatados;
    private Random gerador;

    public SuperTrunfoDaReciclagem() {
        gerador = new Random();
        turno = 0;
        proxJogador = 0;
        mesa = new ArrayList<>();
        indiceEmpatados = new ArrayList<>();
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Jogador[] getJogadores() {
        return jogadores;
    }

    public void setJogadores(Jogador[] jogadores) {
        this.jogadores = jogadores;
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
        do {
            System.out.println("Quantos jogadores participarão? ( 2 a 4 Jogadores ) ");
            nroJogador = sc.nextInt();
            if (nroJogador < 2 || nroJogador > 4) {
                System.out.println("Numero de jogadores Inválido!");
            }
        } while (nroJogador < 2 || nroJogador > 4);
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
        this.faseDeCompra(false);
        System.out.println("");
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
    public void faseDeCompra(Boolean isDesempate) {
        if (!isDesempate) {
            for (int i = 0; i < jogadores.length; i++) {
                mesa.add(jogadores[i].sacarCarta());
            }
        } else {
            for (int i : indiceEmpatados) {
                mesa.add(jogadores[i].sacarCarta());
            }
        }
    }

    public boolean isFim() {
        boolean ret = false;
        if (this.jogadores.length == 1) {
            ret = true;
        }
        return ret;
    }

    //Fase que faz comparação entre as cartas
    public void fasePrincipal(int criterio, boolean isEmpate) {
        if (!isEmpate) {
            System.out.println("-- Início da fase principal --");
        } else {
            System.out.println("--------- Desempate --------");
        }
        int cartaAtual = mesa.size() - this.jogadores.length, indiceVencedor;
        int proximaCarta = cartaAtual + 1;
        for (int j = 0; j < jogadores.length-1; j++) {
            cartaAtual = comparaCartas(criterio, cartaAtual, proximaCarta + j);
            if (cartaAtual == -1) {
                cartaAtual = proximaCarta;
            }
        }
        if (!this.jogadoresEmpatados) {
            indiceVencedor = cartaAtual;
            atualJogador = proxJogador;
            proxJogador = indiceVencedor;
            this.jogadoresEmpatados = false;
        }
    }

    public int comparaCartas(int criterio, int cartaUmIndice, int cartaDoisIndice) {
        Status resultado;
        int indiceVencedor = -1;
        switch (criterio) {
            case 1:
                resultado = mesa.get(cartaUmIndice).critCor(mesa.get(cartaDoisIndice));
                indiceVencedor = computaResultado(resultado, cartaUmIndice, cartaDoisIndice);
                break;
            case 2:
                resultado = mesa.get(cartaUmIndice).critDecoposicao(mesa.get(cartaDoisIndice));
                indiceVencedor = computaResultado(resultado, cartaUmIndice, cartaDoisIndice);
                break;
            case 3:
                resultado = mesa.get(cartaUmIndice).critReciclavel(mesa.get(cartaDoisIndice));
                indiceVencedor = computaResultado(resultado, cartaUmIndice, cartaDoisIndice);
                break;
            case 4:
                resultado = mesa.get(cartaUmIndice).critAtaque(mesa.get(cartaDoisIndice));
                indiceVencedor = computaResultado(resultado, cartaUmIndice, cartaDoisIndice);
                break;
        }
        return indiceVencedor;
    }

    private int computaResultado(Status resultado, int cartaUmIndice, int cartaDoisIndice) {
        int indiceVencedor = -1;
        if (resultado == Status.EMPATA) {
            indiceVencedor = -1;
            this.jogadoresEmpatados = true;
            if (!indiceEmpatados.contains(cartaUmIndice)) {
                this.indiceEmpatados.add(cartaUmIndice);
            }
            if (!indiceEmpatados.contains(cartaDoisIndice)) {
                this.indiceEmpatados.add(cartaDoisIndice);
            }
        } else {
            switch (resultado) {
                case PERDE:
                    //Se a carta empatada perder acaba o desempate 
                    if (this.indiceEmpatados.contains(cartaUmIndice)) {
                        this.jogadoresEmpatados = false;
                        this.indiceEmpatados.clear();
                    }
                    indiceVencedor = cartaDoisIndice;
                    break;
                case GANHA:
                    indiceVencedor = cartaUmIndice;
                    break;
            }
        }
        return indiceVencedor;
    }

    public void faseRevelacao() {
        System.out.println("------- Fase revelação -------");

        int j = mesa.size() - jogadores.length; // pega as ultimas cartas do monte
        for (int i = 0; i < jogadores.length; i++) {
            if (i != atualJogador) {
                System.out.println("Carta do jogador " + jogadores[i].getNome() + " : \n" + mesa.get(j).toString());
            }
            j++;
        }
        if (this.jogadoresEmpatados) {
            System.out.println("----------- Turno Empatado -----------");
        } else {
            System.out.println("----------- Vencedor da Rodada:" + jogadores[proxJogador].getNome() + " -----------");
        }

    }

    public void desempate() {
        while (this.jogadoresEmpatados) {
            int i;
            this.faseDeCompra(true);

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
                this.fasePrincipal(this.escolhaDeCriterio(), true);
                this.faseRevelacao();
            }
        }
    }

    public void fimDoTurno() {
        Jogador j = jogadores[proxJogador];
        for (Carta c : mesa) {
            if (c != null) {
                j.adicionaCarta(c);
            }
        }
        indiceEmpatados.clear();
        mesa.clear();
    }

    public Jogador ganhador() {
        Jogador vencedor = null;
        for (Jogador j : this.jogadores) {
            if (j.temCartas()) {
                vencedor = j;
            }
        }
        return vencedor;
    }

    public void ValidaJogadores() {
        int i = 0;
        ArrayList<Jogador> jogadoresValidos = new ArrayList<>();
        Jogador[] novoJogadores;
        for (Jogador j : jogadores) {
            if (j.temCartas()) {
                jogadoresValidos.add(j);
            } else {
                System.out.println("Jogador " + j.getNome() + " perdeu o jogo");
                System.out.println("Turno: " + this.turno);
            }
        }
        novoJogadores = new Jogador[jogadoresValidos.size()];
        for (Jogador j : jogadoresValidos) {
            novoJogadores[i] = j;
            i++;
        }
        this.jogadores = novoJogadores;
    }
}
