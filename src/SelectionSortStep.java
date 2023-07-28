import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SelectionSortStep implements Sortable {
    private final Algorithm algorithmSav;

    public SelectionSortStep(Algorithm algorithm) {
        this.algorithmSav = algorithm;
    }

    @Override
    public Algorithm sort( JFrame frame, Display displaySav) {

        List<Integer> sortedList = new ArrayList<>(algorithmSav.getListNumber());

        for (int i = 0; i < sortedList.size() - 1; i++) {
            int min_idx = i;

            for (int j = i + 1; j < sortedList.size(); j++) {
                if ((algorithmSav.lForR ? sortedList.get(j) < sortedList.get(min_idx) : sortedList.get(j) > sortedList.get(min_idx))) {
                    min_idx = j;
                }
            }

            int temp = sortedList.get(min_idx);
            sortedList.set(min_idx, sortedList.get(i));
            sortedList.set(i, temp);

            displaySav.setColumnStepRed(min_idx);


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
