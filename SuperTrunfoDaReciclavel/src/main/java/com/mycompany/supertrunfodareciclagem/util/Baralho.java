package com.mycompany.supertrunfodareciclagem.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iyiSakuma
 */
public class Baralho {

    private ArrayList<Carta> baralho;

    public Baralho() {
        if (!lerDoArquivo()) {
            this.baralho = null;
        }
    }

    public Baralho(String arquivo) {
        if (!lerDoArquivo(arquivo)) {
            this.baralho = null;
        }
    }

    public ArrayList<Carta> getBaralho() {
        return baralho;
    }

    /**
     *
     * @return Retorna uma carta e remove ela do baralho, caso estiver vazio
     * retorna null.
     */
    public Carta selecionaCarta() {
        Random gerador = new Random();
        if (this.baralho.size() > 0) {
            int valor = gerador.nextInt(this.baralho.size());
            Carta carta = this.baralho.get(valor);
            this.baralho.remove(valor);
            return carta;
        }
        return null;
    }

    private boolean lerDoArquivo(){
        ArrayList<Carta> cartas = new ArrayList<>();

        try ( BufferedReader leitor = new BufferedReader(new FileReader(new File(getClass().getResource("/com/mycompany/supertrunfodareciclagem/cartas.csv").getFile())))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                String reciclavel = dados[dados.length];
                Carta carta;
                if (reciclavel.equals("sim")) {
                    carta = new Reciclavel(dados);

                } else {
                    carta = new NaoReciclavel(dados);
                }
                cartas.add(carta);
            }
            this.baralho = cartas;
            return true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Baralho.class.getName()).log(Level.SEVERE, "Erro ao carregar arquivo de cartas!", ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Baralho.class.getName()).log(Level.SEVERE, "Erro ao ler arquivo de cartas!", ex);
            return false;
        }
    }

    private boolean lerDoArquivo(String local) {
        ArrayList<Carta> cartas = new ArrayList<>();
        try {
            try ( BufferedReader leitor = new BufferedReader(new FileReader(new File(local)))) {
                String linha;
                while ((linha = leitor.readLine()) != null) {
                    String[] dados = linha.split(";");
                    String reciclavel = dados[dados.length];
                    Carta carta;
                    if (reciclavel.equals("sim")) {
                        carta = new Reciclavel(dados);

                    } else {
                        carta = new NaoReciclavel(dados);
                    }
                    cartas.add(carta);
                }
            }
            this.baralho = cartas;
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Baralho.class.getName()).log(Level.SEVERE, "Erro ao carregar arquivo de cartas!", ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Baralho.class.getName()).log(Level.SEVERE, "Erro ao ler arquivo de cartas!", ex);
            return false;
        }
    }
}
