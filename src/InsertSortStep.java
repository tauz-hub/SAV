import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InsertSortStep implements Sortable {
    private final Algorithm algorithmSav;

    public InsertSortStep(Algorithm algorithm) {
        this.algorithmSav = algorithm;
    }

    @Override
    public Algorithm sort(JFrame frame, Display displaySav) {

        List<Integer> sortedList = new ArrayList<>(algorithmSav.getListNumber());

        for (int i = 1; i < sortedList.size(); i++) {
            int key = sortedList.get(i);
            int j = i - 1;

            while (j >= 0 && ((algorithmSav.lForR ? sortedList.get(j) > key : sortedList.get(j) < key))) {
                sortedList.set(j + 1, sortedList.get(j));
                j = j - 1;
            }

            displaySav.setColumnStepRed(key);


            sortedList.set(j + 1, key);

            int[][] newMatriz = Display.buildMatrizForDisplay(sortedList);
            displaySav.updateFrame(frame, newMatriz,algorithmSav);

            try {
                Thread.sleep(algorithmSav.getSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (algorithmSav.getListNumber().equals(sortedList)) {
            algorithmSav.setAlgorithmResolved(true);

        }
        algorithmSav.setListNumber(sortedList);
        return algorithmSav;

    }
}
