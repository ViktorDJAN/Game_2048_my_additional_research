import java.util.ArrayList;

public class GameHandler {

    public static void playGame(){
        Board board = new Board();
        GameLogic gl = new GameLogic();
        gl.greeting();
        Integer[][] arr = (board.convertingToIntArray2D(board.bordInitialization(4)));
        boolean flag = true;

        while(board.freeCells(arr)==flag){
            arr= board.setValuesInArray(arr);
            arr = gl.from2DArrayTo2DIntListConverting(gl.movement(arr));
            gl.printArray(arr);
            System.out.println();
        }
    }
}
