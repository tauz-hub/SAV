import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<Integer> elementos = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            elementos.add(i);
        }

        Collections.shuffle(elementos);
        List<Integer> listNumber = new ArrayList<>(elementos);


        // Encontrando a altura máxima na lista
        int alturaMaxima = listNumber.stream().max(Integer::compareTo).orElse(0);

        // Construindo a matriz binária
        int tamanho = listNumber.size();
        int[][] matrizInvertida = new int[alturaMaxima][tamanho];

        for (int i = 0; i < tamanho; i++) {
            int alturaColuna = listNumber.get(i);
            for (int j = 0; j < alturaColuna; j++) {
                matrizInvertida[j][i] = 1;
            }
        }

        int[][] matriz = new int[alturaMaxima][tamanho];
        for (int i = 0; i < alturaMaxima; i++) {
            for (int j = 0; j < tamanho; j++) {
                matriz[i][j] = matrizInvertida[alturaMaxima - 1 - i][j];
            }
        }

        // Exibindo a matriz invertida
        for (int i = 0; i < alturaMaxima; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }

        // Exibindo a lista original
        System.out.println("------------------");


        JFrame frame = new JFrame("SAV por Taua Ferreira");
        boolean infinite = false;
//        if (generations == 0) {
//            infinite = true;
//
//            frame.addKeyListener(new KeyListener() {
//                @Override
//                public void keyTyped(KeyEvent e) {
//                }
//
//                @Override
//                public void keyPressed(KeyEvent e) {
//                    System.exit(0);
//                }
//
//                @Override
//                public void keyReleased(KeyEvent e) {
//                }
//            });
//        }


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxSize = Math.min(screenSize.width, screenSize.height) - 100;
        int squareSize = Math.min(maxSize / matriz.length, maxSize / matriz[0].length);
        int panelWidth = squareSize * matriz[0].length;
        int panelHeight = squareSize *  matriz.length;

        JPanel panel = new JPanel(new GridLayout( matriz.length,  matriz[0].length, 0, 0));
        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(squareSize, squareSize));

                if (matriz[i][j] == 1) {
                    cell.setBackground(Color.decode("#00FFFF"));
                } else {
                    cell.setBackground(Color.decode("#002147"));
                }

                panel.add(cell);
            }
        }

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        frame.setTitle("SAV por Taua Ferreira | Matriz ");

    }

}