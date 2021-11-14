package com.mycompany.supertrunfodareciclagem.main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Scanner;

/**
 *
 * @author nicoletti.edu
 */
public class Menu {

    private int escolha;
    private final Scanner sc;

    public Menu() {
        this.sc = new Scanner(System.in);
    }

    public int mostraMenu() {
        imprimeArte("Super Trunfo da Reciclagem");
        System.out.println("1 - Jogar\n2 - Simulação\n3 - Sair");
        do {
            escolha = sc.nextInt();
            if (escolha < 1 || escolha > 4) {
                System.out.println("Opção Inválida!");
            }
        } while (escolha < 1 || escolha > 4);
        //Limpa terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();
        return escolha;
    }

    //Fonte: https://mkyong.com/java/ascii-art-java-example/
    private void imprimeArte(String texto) {
        int width = 200;
        int height = 30;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 12));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(texto, 10, 20);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }
    }

}
