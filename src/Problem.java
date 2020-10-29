import java.util.Random;

import static java.lang.Math.abs;

public class Problem {
    private final int SIZE = 25;
    Random rand = new Random();

    public int[] initialState(){
        int board[] = new int[SIZE];
        for(int i = 0; i < SIZE; i++){
           board[i] = rand.nextInt(SIZE);
        }
        return board;
    }

    public int action(){
        int num = rand.nextInt(SIZE);
        return num;
    }

    public int goalTest(int[] board){
        int counter = 0;
        int delIndex = 0;
        int delQueen = 0;
        for(int i = 0; i < SIZE - 1; i++){
            for(int j = i + 1; j < SIZE; j++){
                delIndex = j - i;
                delQueen = abs(board[i] - board[j]);
                if(board[i] == board[j] || delIndex == delQueen){
                    counter++;
                }
            }
        }
        return counter;
    }
}
