import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BubbleSortStep implements Sortable {
    private final Algorithm algorithmSav;

    public BubbleSortStep(Algorithm algorithm) {
        this.algorithmSav = algorithm;
    }

    @Override
    public Algorithm sort(JFrame frame, Display displaySav) {
        List<Integer> sortedList = new ArrayList<>(algorithmSav.getListNumber());

        for (int i = 0; i < sortedList.size() - 1; i++) {
            for (int j = 0; j < sortedList.size() - i - 1; j++) {

                if ((algorithmSav.lForR ? sortedList.get(j) > sortedList.get(j + 1) : sortedList.get(j) < sortedList.get(j + 1))) {
                    int temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);

                    int[][] newMatriz = Display.buildMatrizForDisplay(sortedList);
                    displaySav.updateFrame(frame, newMatriz, algorithmSav);


                    try {
                        Thread.sleep(algorithmSav.getSleepTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                displaySav.setColumnStepRed(j + 2);

            }
        }
        if (algorithmSav.getListNumber().equals(sortedList)) {

            algorithmSav.setAlgorithmResolved(true);
        }
        algorithmSav.setListNumber(sortedList);
        return algorithmSav;
    }
}
