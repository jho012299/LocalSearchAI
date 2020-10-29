import java.util.Random;

import static java.lang.Math.abs;

public class SimulatedAnnealing {
    private Node current;
    private double temp;
    private final int SIZE = 25;
    Random rand = new Random();

    public int[] anneal(Problem prob, Schedule schedule) {
        current = new Node(prob.initialState());
        for(int i = 1; i < 999999999; i++){
            temp = schedule.decay(i);

            if(temp <= 0){
                return current.board.state;
            }

            int successorNum = prob.action(); //changes one column randomly
            int successor[] = new int[SIZE];
            for(int j = 0; j < SIZE; j++){
                successor[j] = current.board.state[j];
            }
            Node next = new Node(successor);
            next.board.state[successorNum] = rand.nextInt(SIZE);

            double delta = value(current) - value(next);
            if(delta > 0){
                current = next;
            }
            else{
                double probability = java.lang.Math.exp(delta / temp);
                if(randProbability() < probability) {
                    current = next;
                }
            }
        }
        return null;
    }

    public int value(Node node){
        int counter = 0;
        int delIndex = 0;
        int delQueen = 0;
        int board[] = node.board.state;
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

    public double randProbability(){
        double num = rand.nextDouble();
        return num;
    }

    private class Node {
        public Board board;

        private Node(int board[]) {
            this.board = new Board(board);
        }
    }
}
