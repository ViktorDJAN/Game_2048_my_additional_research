import java.util.ArrayList;
import java.util.Random;

public class Board {
    public ArrayList<ArrayList<Integer>> bordInitialization(int size){

        ArrayList<ArrayList<Integer>> resultingBoard = new ArrayList<>();
        for(int i = 0 ;i < size; i++ ){
            ArrayList<Integer> arrayList = new ArrayList<>();
            for(int k= 0 ;k < size; k++ ){
                arrayList.add(0);
            }
            resultingBoard.add(arrayList);
        }return resultingBoard;
    }

    public Integer[][] convertingToIntArray2D (ArrayList<ArrayList<Integer>> input2DArray) {
        Integer[][] intArray = input2DArray.stream().map(u -> u.toArray(new Integer[0])).toArray(Integer[][]::new);
        return intArray;
    }

    public Integer[][] setValuesInArray(Integer[][] sourceArray){
        Random random = new Random();
        Integer randomNum1 = random.nextInt(sourceArray.length);
        Integer randomNum2 = random.nextInt(sourceArray.length);

        for(int i = 0;i<sourceArray.length;i++){
            for(int j = 0 ; j<sourceArray.length;j++){
                if(sourceArray[randomNum1][randomNum2] ==0 && (freeCells(sourceArray))){
                    sourceArray[randomNum1][randomNum2] = 2;
                }
            }
        }return sourceArray;
    }

    public boolean freeCells(Integer[][] sourceArray) {
        int freeCellsCounter = 0;
        for (int i = 0; i < sourceArray.length; i++) {
            for (int j = 0; j < sourceArray.length; j++) {
                if (sourceArray[i][j] == 0) {
                    freeCellsCounter++;
                }
            }
        }
        if (freeCellsCounter != 0) return true;
        else {
            return false;
        }
    }

    public void print(ArrayList<ArrayList<Integer>> twoDimArray){
        for(ArrayList<Integer> singleArray : twoDimArray){
            for(Integer item: singleArray){
                System.out.print(item+ " ");
            }
            System.out.println();
        }
    }
}
