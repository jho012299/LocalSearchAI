import static java.lang.Math.abs;

public class Board implements Comparable<Board>{
    public int[] state;
    public final int SIZE = 25;

    public Board(int[] state){
        this.state = state;
    }

    public int compareTo(Board b){
        return fitnessFunc(state) - fitnessFunc(b.state);
    }

    public int fitnessFunc(int board[]){
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

