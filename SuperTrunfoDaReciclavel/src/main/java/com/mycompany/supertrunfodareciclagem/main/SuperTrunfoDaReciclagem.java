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
    private ArrayList<Carta> monte; // Cartas do desempate
    private ArrayList<Integer> indiceEmpatados;
    private int turno, proxJogador, atualJogador;
    private boolean jogadoresEmpatados;
    private Random gerador;

    public SuperTrunfoDaReciclagem() {
        this.gerador = new Random();
        this.turno = 0;
        this.proxJogador = 0;
        this.mesa = new ArrayList<>();
        this.monte = new ArrayList<>();
        this.indiceEmpatados = new ArrayList<>();
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
                this.jogadores[primeiroJogador] = new Jogador(nome, primeiroJogador);
            } else {
                primeiroJogador = 0;
                this.jogadores[primeiroJogador] = new Jogador(nome, primeiroJogador);
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
                Carta carta = jogadores[i].sacarCarta();
                if (carta != null) {
                    this.mesa.add(carta);
                }
            }
        } else {
            for (int i : indiceEmpatados) {
                Carta carta = jogadores[i].sacarCarta();
                if (carta != null) {
                    this.mesa.add(carta);
                }else{
                    System.out.println("Jogador " + jogadores[i].getNome() + " sem cartas!");
                }
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
        this.indiceEmpatados.clear();
        this.jogadoresEmpatados = false;
        int cartaAtual = 0;
        int proximaCarta = 1;
        for (int j = 0; j < mesa.size() - 1; j++) {
            cartaAtual = comparaCartas(criterio, cartaAtual, proximaCarta + j);
            if (cartaAtual == -1) {
                cartaAtual = proximaCarta;
            }
        }
        this.atualJogador = proxJogador;
        this.proxJogador = mesa.get(cartaAtual).getIndiceJogador();
        if (!this.jogadoresEmpatados) {
            this.jogadoresEmpatados = false;
        }
    }

    public int comparaCartas(int criterio, int cartaUmIndice, int cartaDoisIndice) {
        Status resultado;
        int indiceVencedor = -1;
        switch (criterio) {
            case 1:
                resultado = this.mesa.get(cartaUmIndice).critCor(this.mesa.get(cartaDoisIndice));
                indiceVencedor = computaResultado(resultado, cartaUmIndice, cartaDoisIndice);
                break;
            case 2:
                resultado = this.mesa.get(cartaUmIndice).critDecoposicao(this.mesa.get(cartaDoisIndice));
                indiceVencedor = computaResultado(resultado, cartaUmIndice, cartaDoisIndice);
                break;
            case 3:
                resultado = this.mesa.get(cartaUmIndice).critReciclavel(this.mesa.get(cartaDoisIndice));
                indiceVencedor = computaResultado(resultado, cartaUmIndice, cartaDoisIndice);
                break;
            case 4:
                resultado = this.mesa.get(cartaUmIndice).critAtaque(this.mesa.get(cartaDoisIndice));
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
            if (!this.indiceEmpatados.contains(mesa.get(cartaUmIndice).getIndiceJogador())) {
                this.indiceEmpatados.add(mesa.get(cartaUmIndice).getIndiceJogador());
            }
            if (!this.indiceEmpatados.contains(mesa.get(cartaDoisIndice).getIndiceJogador())) {
                this.indiceEmpatados.add(mesa.get(cartaDoisIndice).getIndiceJogador());
            }
        } else {
            switch (resultado) {
                case PERDE:
                    //Se a carta empatada perder acaba o desempate 
                    if (this.indiceEmpatados.contains(mesa.get(cartaUmIndice).getIndiceJogador())) {
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

    public void faseRevelacao(boolean isEmpate) {
        System.out.println("------- Fase revelação -------");
        for (int i = 0; i < this.mesa.size(); i++) {
            if (mesa.get(i).getIndiceJogador() != this.atualJogador) {
                System.out.println("Carta do jogador " + this.jogadores[mesa.get(i).getIndiceJogador()].getNome() + " : \n" + mesa.get(i).toString());
            }
        }
        if (this.jogadoresEmpatados) {
            System.out.println("----------- Turno Empatado -----------");
            this.monte.addAll(this.mesa);
            this.mesa.clear();
        } else {
            System.out.println("----------- Vencedor da Rodada:" + this.jogadores[this.proxJogador].getNome() + " -----------");
        }

    }

    private Carta buscaCartaNaMesa(int indiceJogador) {
        for (Carta carta : this.mesa) {
            if (carta.getIndiceJogador() == indiceJogador) {
                return carta;
            }
        }
        return null;
    }

    public void desempate() {
        while (this.jogadoresEmpatados) {
            this.faseDeCompra(true);
            //Se o prox jogador nao conseguiu jogar cartas na mesa, busca o próximo que consegue
            while (buscaCartaNaMesa(proxJogador) == null) {
                if (this.mesa.size() <= 1) {
                    this.jogadoresEmpatados = false;
                    break;
                } else {
                    proxJogador = mesa.get(0).getIndiceJogador();
                }
            }
            if (this.jogadoresEmpatados) {
                System.out.println("--------- Desempate ---------");
                System.out.println("Carta do jogador " + this.jogadores[this.proxJogador].getNome() + " : \n");
                System.out.println(buscaCartaNaMesa(proxJogador).toString());
                this.fasePrincipal(this.escolhaDeCriterio(), true);
                this.faseRevelacao(true);
            }
        }
    }

    public void fimDoTurno() {
        Jogador j = this.jogadores[this.proxJogador];
        for (Carta c : this.mesa) {
            if (c != null) {
                j.adicionaCarta(c);
            }
        }
        for (Carta c : this.monte) {
            if (c != null) {
                j.adicionaCarta(c);
            }
        }
        this.indiceEmpatados.clear();
        this.jogadoresEmpatados = false;
        this.mesa.clear();
        this.monte.clear();
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

    public void validaJogadores() {
        int i = 0;
        ArrayList<Jogador> jogadoresValidos = new ArrayList<>();
        Jogador[] novoJogadores;
        for (Jogador j : this.jogadores) {
            if (j.temCartas()) {
                jogadoresValidos.add(j);
            } else {
                System.out.println("Jogador " + j.getNome() + " perdeu o jogo");
                System.out.println("Turno: " + this.turno);
            }
        }
        novoJogadores = new Jogador[jogadoresValidos.size()];
        for (Jogador j : jogadoresValidos) {
            if(j.getIndice() == proxJogador){
                proxJogador = i;
            }
            if(j.getIndice() == atualJogador){
                atualJogador = i;
            }
            j.setIndice(i);
            novoJogadores[i] = j;
            i++;
        }
        this.jogadores = novoJogadores;
    }
}
