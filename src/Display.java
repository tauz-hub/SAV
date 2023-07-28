import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Display {
    static int countStep = 1;
    public static void printConsole(int alturaMaxima, int tamanho, int[][] matriz) {
        System.out.println("-----Etapa " + countStep + " ---------");
        for (int i = 0; i < alturaMaxima; i++) {
            for (int j = 0; j < tamanho; j++) {

                System.out.print(matriz[i][j] == 0 ? " " : "*");
            }
            System.out.println();
        }

        countStep++;
    }
    static int columnStepRed = 0;

    public void setColumnStepRed(int value){
        columnStepRed = value;
    }
    public static int[][] buildMatrizForDisplay(List<Integer> listNumber) {

        int alturaMaxima = listNumber.stream().max(Integer::compareTo).orElse(0);

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

        printConsole(alturaMaxima, tamanho, matriz);


        return matriz;
    }
    static int panelWidth = 0;
    static int panelHeight = 0;
    static int squareSize = 0;

    public Display(int[][] matriz){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxSize = Math.min(screenSize.width, screenSize.height) - 100;
        squareSize = Math.min(maxSize / matriz.length, maxSize / matriz[0].length);
        panelWidth = squareSize * matriz[0].length;
        panelHeight = squareSize * matriz.length;
    }
    public void updateFrame(JFrame frame, int[][] matriz, Algorithm algorithmSav ) {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(matriz.length, matriz[0].length, 1, 0));
        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));


        int[] maxValues = new int[matriz[0].length];
        for (int j = 0; j < matriz[0].length; j++) {
            int maxValue = -1;
            for (int i = 0; i < matriz.length; i++) {
                if (matriz[i][j] == 1) {
                    maxValue = i;
                    break;
                }
            }
            maxValues[j] = maxValue;
        }


        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[0].length; j++) {
                JPanel cell = new JPanel();

                cell.setPreferredSize(new Dimension(squareSize, squareSize));
                if (i == matriz.length - 1) {
                    cell.setLayout(new BoxLayout(cell, BoxLayout.Y_AXIS));
                    cell.setLayout(new BoxLayout(cell, BoxLayout.X_AXIS));

                    JLabel labelText;
                    if (algorithmSav.isNumericList) {
                        labelText = new JLabel(String.valueOf(matriz.length - maxValues[j] - Math.abs(algorithmSav.getMinValueOfListNumber()) - 1));
                    } else {
                        labelText = new JLabel(algorithmSav.getItemForListStringOriginal(j));
                    }
                    cell.add(Box.createVerticalGlue());
                    cell.add(Box.createHorizontalGlue());

                    cell.add(labelText);

                    cell.add(Box.createVerticalGlue());
                    cell.add(Box.createHorizontalGlue());
                }
                if (i == maxValues[j]) {
                    cell.setBackground(Color.BLACK);
                } else if (matriz[i][j] == 1) {

                    if (j == columnStepRed) {

                        cell.setBackground(Color.RED);
                    } else {

                        cell.setBackground(Color.GRAY);

                    }
                } else {
                    cell.setBackground(Color.WHITE);
                }

                panel.add(cell);
            }
        }

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        frame.setTitle("SAV por Taua Ferreira | Matriz ");
        panel.revalidate();
        panel.repaint();
    }
}
