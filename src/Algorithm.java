import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {
    boolean lForR = true;
    int sleepTime = 100;

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    boolean isNumericList = true;

   int minValueOfListNumber = 0;

    public boolean islForR() {
        return lForR;
    }

    public void setlForR(boolean lForR) {
        this.lForR = lForR;
    }

    public boolean isIsNumericList() {
        return this.isNumericList;
    }

    public void setIsNumericList(boolean isNumericList) {
        this.isNumericList = isNumericList;
    }

    public  int getMinValueOfListNumber() {
        return this.minValueOfListNumber;
    }

    public void setMinValueOfListNumber(int minValueOfListNumber) {
        this.minValueOfListNumber = minValueOfListNumber;
    }

    public  List<Integer> getListNumber() {
        return this.listNumber;
    }

    public  void setListNumber(List<Integer> listNumber) {
        this.listNumber = listNumber;
    }
    public  void addItemForListNumber(int value) {
        this.listNumber.add(value);
    }

    public void shuffleListNumber(){
        Collections.shuffle(this.listNumber);
    }
    public  List<String> getListStringOriginal() {
        return this.listStringOriginal;
    }
    public String getItemForListStringOriginal(int index) {
        return this.listStringOriginal.get(index);
    }
    public void setListStringOriginal(List<String> listStringOriginal) {
        this.listStringOriginal = listStringOriginal;
    }

    public void addItemForListStringOriginal(String str) {
        this.listStringOriginal.add(str);
    }

     List<Integer> listNumber = new ArrayList<>();
     List<String> listStringOriginal = new ArrayList<>();


    boolean algorithmResolved = false;

    public boolean isAlgorithmResolved() {
        return algorithmResolved;
    }

    public void setAlgorithmResolved(boolean algorithmResolved) {
        this.algorithmResolved = algorithmResolved;
    }



    public List<Integer> selectionSortStep(List<Integer> inputList, JFrame frame, Display displaySav) {

        List<Integer> sortedList = new ArrayList<>(inputList);

        for (int i = 0; i < sortedList.size() - 1; i++) {
            int min_idx = i;

            for (int j = i + 1; j < sortedList.size(); j++) {
                if ((this.lForR ? sortedList.get(j) < sortedList.get(min_idx) : sortedList.get(j) > sortedList.get(min_idx))) {
                    min_idx = j;
                }
            }

            int temp = sortedList.get(min_idx);
            sortedList.set(min_idx, sortedList.get(i));
            sortedList.set(i, temp);

            displaySav.setColumnStepRed(min_idx);


            int[][] newMatriz = Display.buildMatrizForDisplay(sortedList);
            displaySav.updateFrame(frame, newMatriz,this);

            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (listNumber.equals(sortedList)) {
            this.algorithmResolved = true;
        }

        return sortedList;
    }
}
