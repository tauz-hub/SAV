import java.util.*;

import javax.swing.*;
import java.util.List;

public class SAV {
    public static void main(String[] args) {

        String t = "n";
        String in = "u";
        String algorithm = "b";
        int r = 10;
        Algorithm algorithmSav = new Algorithm();

        List<Integer> listNumberUser = new ArrayList<>();

        if (args.length > 0) {

            for (String arg : args) {

                String[] parts = arg.split("=");
                String key = parts[0];
                String value = parts[1];

                switch (key) {
                    case "t":
                        t = value.toLowerCase();
                        break;
                    case "v":
                        String[] listStringLetters = value.split("[,\\s]+");

                        List<String> numericValues = new ArrayList<>();
                        List<String> nonNumericValues = new ArrayList<>();

                        for (String item : listStringLetters) {
                            try {
                                String uppercaseItem = item.toUpperCase();

                                AlphabeticalDictionary.valueOf(uppercaseItem);

                                nonNumericValues.add(item);
                            } catch (IllegalArgumentException e) {
                                try {
                                    int newTest = Integer.parseInt(item);

                                    numericValues.add(item);
                                } catch (IllegalArgumentException er) {
                                    System.out.println("Valores de entrada invalidos");
                                    return;
                                }
                            }
                        }
                        if (nonNumericValues.size() > 0 && numericValues.size() > 0) {
                            System.out.println("Valores de entrada invalidos");
                            return;
                        }

                        if (nonNumericValues.size() > 0) {
                            algorithmSav.setIsNumericList(false);
                            for (String character : nonNumericValues) {

                                try {
                                    AlphabeticalDictionary dictionaryValue = AlphabeticalDictionary.valueOf(character);
                                    int integerValue = dictionaryValue.getValue();

                                    listNumberUser.add(integerValue);
                                    algorithmSav.addItemForListStringOriginal(character);

                                } catch (IllegalArgumentException e) {
                                    System.err.println("Caractere nao encontrado no dicionario: " + character);
                                }
                            }
                        } else {
                            for (String item : numericValues) {
                                int nmb = Integer.parseInt(item);
                                if(nmb < -1000 || nmb > 1000){
                                    System.out.println("Os valores devem estar entre -1000 e 1000");
                                    return;
                                }
                                listNumberUser.add(Integer.parseInt(item));
                            }
                        }
                        break;

                    case "in":
                        in = value.toLowerCase();
                        break;
                    case "r":
                        r = Integer.parseInt(value);
                        break;
                    case "s":
                        try {
                            algorithmSav.setSleepTime( Integer.parseInt(value));

                        } catch (Exception e) {
                            algorithmSav.setSleepTime(-1);
                        }
                        break;
                    case "a":
                        algorithm = value.toLowerCase();
                        break;
                    case "o":
                        algorithmSav.setlForR( value.equalsIgnoreCase("az") || !value.equalsIgnoreCase("za"));
                        if (!value.equalsIgnoreCase("az") && !value.equalsIgnoreCase("za")) {
                            System.out.println("Entrada invalida para ordenacao");
                            return;
                        }
                        break;

                    default:
                        System.out.println("Parametro invalido: " + arg);
                        return;
                }
            }
        }


        if (r > 40 || r < 0) {
            System.out.print("Valor de r deve ser entre 0 e 40");
            return;
        }

        if (algorithmSav.getSleepTime() > 1000 || algorithmSav.getSleepTime() < 100) {
            System.out.println("Entrada de velocidade invalida, digite entre 100 e 1000");
            return;
        }

        if (in.equals("r")) {
            listNumberUser.clear();
            int max = 10;
            int min = -10;
            for (int i = 1; i <= r; i++) {
                int randomNumber = new Random().nextInt(max - min + 1) + min;
                listNumberUser.add(randomNumber);
            }

            algorithmSav.setIsNumericList(true);
        } else if (in.equals("m")) {
            if (t.equals("c") &&  algorithmSav.isNumericList || t.equals("n") && ! algorithmSav.isNumericList) {
                System.out.println("Lista nao condiz com o tipo apresentado");
                return;
            }

        } else {
            System.out.println("Entre com um valor valido em in");
            return;
        }


        String[] list = {"b", "s", "i"};
        List<String> listAlgorithm = Arrays.asList(list);

        if (!listAlgorithm.contains(algorithm)) {
            System.out.println("Algoritmo nao existe");
            return;
        }


        for (Integer item : listNumberUser) {
            algorithmSav.setMinValueOfListNumber(Math.abs(Collections.min(listNumberUser)));

            System.out.println(item);
            int numberForList = item + algorithmSav.getMinValueOfListNumber() + 1;
            algorithmSav.addItemForListNumber(numberForList);

        }

        algorithmSav.shuffleListNumber();

        int[][] matriz = Display.buildMatrizForDisplay(algorithmSav.getListNumber());

        JFrame frame = new JFrame("SAV por Taua Ferreira");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Display displaySav = new Display(matriz);

        displaySav.updateFrame(frame, matriz, algorithmSav);

        while (!algorithmSav.algorithmResolved) {
            if (algorithm.equals("b")) {

                BubbleSortStep stepBubble = new BubbleSortStep(algorithmSav);
                algorithmSav = stepBubble.sort(frame,displaySav);


            } else if (algorithm.equals("i")) {
                InsertSortStep stepInsert = new InsertSortStep(algorithmSav);
                algorithmSav = stepInsert.sort(frame,displaySav);
            } else if (algorithm.equals("s")) {
                SelectionSortStep stepSelection = new SelectionSortStep(algorithmSav);
                algorithmSav = stepSelection.sort(frame,displaySav);
            }
        }
    }
}