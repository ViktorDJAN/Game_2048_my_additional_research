import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {
    /**This method reacts to shifting the array depending on the entered directions */
    public ArrayList<ArrayList<Integer>> movement(Integer[][] inputArray) {
        ArrayList<ArrayList<Integer>> processedArray = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String direction = scanner.next().toLowerCase();
        switch (direction) {
            case "w":
                ArrayList<ArrayList<Integer>> upProcessed = upProcessing(inputArray);

                for (Integer[] arr : transTo90DegRight(from2DArrayTo2DIntListConverting(upProcessed))) {   // from twoDim array to ordinary array
                    ArrayList<Integer> oneDimTemporaryArray1 = new ArrayList<>(); // tempo for saving
                    for (Integer item : arr) {
                        oneDimTemporaryArray1.add(item);
                    }
                    processedArray.add(oneDimTemporaryArray1);
                }
                break;

            case "s":
                ArrayList<ArrayList<Integer>> downProcessed = downProcessing(inputArray);

                for (Integer[] arr : transTo90DegLeft(from2DArrayTo2DIntListConverting(downProcessed))) {   // from twoDim array to ordinary array
                    ArrayList<Integer> oneDimTemporaryArray1 = new ArrayList<>(); // tempo for saving
                    for (Integer item : arr) {
                        oneDimTemporaryArray1.add(item);
                    }
                    processedArray.add(oneDimTemporaryArray1);
                }
                break;

            case "a":
                processedArray = leftProcessing(inputArray);
                break;

            case "d":
                processedArray = rightProcessing(inputArray);
                break;
            case "q":
                break;
        }
        return processedArray;

    }
    /**
     * Rotating 90 degrees counterclockwise*/
    public Integer[][] transTo90DegLeft(Integer[][] inputArray) {
        Integer[][] two = new Integer[4][4];
        for (Integer i = 0; i < inputArray.length; i++) {
            for (Integer j = 0; j < inputArray[i].length; j++) {
                two[j][i] = inputArray[i][j];   // here is substituting rows by columns
            }
        }
        return two;
    }

    /**
     * Rotating 90 degrees clockwise*/

    public Integer[][] transTo90DegRight(Integer[][] inputArray) {
        Integer[][] two = new Integer[4][4];
        for (Integer i = 0; i < inputArray.length; i++) {
            for (Integer j = 0; j < inputArray[i].length; j++) {
                two[i][j] = inputArray[j][i];   // here is substituting rows by columns
            }
        }
        return two;
    }
    /**
     * Converting from two dimension ArrayList to Integer two dimension list*/

    public Integer[][] from2DArrayTo2DIntListConverting(ArrayList<ArrayList<Integer>> twoDimArrayList) {
        Integer[][] arrAdditional = new Integer[4][4];
        for (int i = 0; i < arrAdditional.length; i++) {
            for (int j = 0; j < arrAdditional[i].length; j++) {
                arrAdditional[i][j] = twoDimArrayList.get(i).get(j);
            }
        }
        return arrAdditional;
    }

    /**
     * Processing shift UP direction*/
    public ArrayList<ArrayList<Integer>> upProcessing(Integer[][] inputArray) {
        ArrayList<ArrayList<Integer>> temporaryTwoDimArrayUp = new ArrayList<>();
        for (Integer[] arr : transTo90DegLeft(inputArray)) {
            ArrayList<Integer> oneDimTemporaryArray = new ArrayList<>(); // tempo for saving
            for (Integer item : arr) {
                if (item != 0) oneDimTemporaryArray.add(item);
            }
            ArrayList<Integer> res = leftAndUpMerge(oneDimTemporaryArray,inputArray);
            temporaryTwoDimArrayUp.add((res));
        }
        return temporaryTwoDimArrayUp;
    }
    /**
     * Processing shift DOWN direction*/
    public ArrayList<ArrayList<Integer>> downProcessing(Integer[][] inputArray) {
        ArrayList<ArrayList<Integer>> temporaryTwoDimArrayDown = new ArrayList<>();
        for (Integer[] arr : transTo90DegRight(inputArray)) {
            ArrayList<Integer> oneDimTemporaryArray = new ArrayList<>(); // tempo for saving

            for (Integer item : arr) {
                if (item != 0) oneDimTemporaryArray.add(item);
            }
            ArrayList<Integer> res = rightAndDownMerge(oneDimTemporaryArray,inputArray);
            temporaryTwoDimArrayDown.add((res));
        }
        return temporaryTwoDimArrayDown;
    }

    /**
     * Processing shift LEFT direction*/
    public ArrayList<ArrayList<Integer>> leftProcessing(Integer[][] inputArray) {
        ArrayList<ArrayList<Integer>> temporaryTwoDimArrayLeft = new ArrayList<>();
        for (Integer[] arr : inputArray) {   // from twoDim array to ordinary array
            ArrayList<Integer> oneDimTemporaryArray = new ArrayList<>(); // tempo for saving

            for (Integer item : arr) {
                if (item != 0) oneDimTemporaryArray.add(item);
            }

            ArrayList<Integer> res = leftAndUpMerge(oneDimTemporaryArray,inputArray);
            temporaryTwoDimArrayLeft.add((res));
        }
        return temporaryTwoDimArrayLeft;
    }

    /**
     * Processing shift RIGHT direction*/
    public ArrayList<ArrayList<Integer>> rightProcessing(Integer[][] inputArray) {
        ArrayList<ArrayList<Integer>> temporaryTwoDimArrayRight = new ArrayList<>();
        for (Integer[] arr : inputArray) {   // from twoDim array to ordinary array
            ArrayList<Integer> oneDimTemporaryArray = new ArrayList<>();
            ArrayList<Integer> result = new ArrayList<>();// tempo for saving

            for (Integer item : arr) {
                if (item != 0) oneDimTemporaryArray.add(item);
            }
            ArrayList<Integer> res = rightAndDownMerge(oneDimTemporaryArray,inputArray);
            temporaryTwoDimArrayRight.add((res));
        }
        return temporaryTwoDimArrayRight;
    }

    /**
     * merging fulfilling and nulls adding(alignment of array)  in RIGHT and DOWN directions*/
    public ArrayList<Integer>rightAndDownMerge(ArrayList<Integer> list ,Integer[][] inputArray){
        int arSize = inputArray.length;
        for(int i = 0; i<list.size()-1;i++){
            if(list.get(i)==list.get(i+1)){
                list.set(i,(list.get(i+1)+list.get(i)));
                list.remove(i+1);
            }
        }
        while(list.size()!=arSize){
            list.add(0,0);
        }
       return list;
    }
    /**
     * merging fulfilling and nulls adding(alignment of array)  in LEFT and UP directions*/
    public ArrayList<Integer>leftAndUpMerge(ArrayList<Integer> list ,Integer[][] inputArray){
        int arSize = inputArray.length;
        for(int i = 0; i<list.size()-1;i++){
            if(list.get(i)==list.get(i+1)){
                list.set(i,(list.get(i+1)+list.get(i)));
                list.remove(i+1);
            }
        }
        while(list.size()!=arSize){
            list.add(0);
        }
        return list;
    }

    /**
     * printing Integer two dimensions array*/
    public void printArray(Integer[][] array) {
        for (Integer[] arr : array) {
            for(Integer item : arr){
                System.out.print(" " + (item < 10 ? " " + item :  item) );
            }
            System.out.println();
        }
    }
    public void greeting(){
        System.out.println("Welcome to the game 2048");
        System.out.println("To begin press represented below buttons ");
        System.out.println("Tap a button of direction: W - up, S -down, D-right, A-left");
    }

}
