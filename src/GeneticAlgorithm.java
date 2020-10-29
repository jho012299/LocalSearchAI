import java.util.*;

public class GeneticAlgorithm {
    private final int POP_SIZE = 100;
    private final int SIZE = 25;
    Random rand = new Random();
    public int[] genetic() {
        ArrayList<Board> oldPopulation = new ArrayList<>();

        for(int a = 0; a < POP_SIZE; a++){
            int array[] = new int[SIZE];
            for(int b = 0; b < SIZE; b++){
                array[b] = rand.nextInt(SIZE);
            }
            Board parent = new Board(array);
            oldPopulation.add(parent);
        }

        Collections.sort(oldPopulation);

        for(int n = 0; n < 10000; n++) {
            ArrayList<Board> newPopulation = new ArrayList<>();
            for (int i = 1; i < POP_SIZE; i++) {
                Board x = randomSelection(oldPopulation);
                Board y = randomSelection(oldPopulation);
                Board child = reproduce(x, y);
                if(rand.nextDouble() < 0.8 || checkDupe(x, y)){
                    mutate(child);
                }
                if(child.fitnessFunc(child.state) == 0){
                    return child.state;
                }
                newPopulation.add(child);
            }
            Collections.sort(newPopulation);
            oldPopulation = newPopulation;
        }
        return oldPopulation.get(0).state;
    }

    public Board reproduce(Board x, Board y){
        int num;
        do {
            num = rand.nextInt(SIZE);
        }
        while(num == 0 || num == SIZE - 1);

        int array[] = new int[SIZE];
        for(int i = 0; i < num; i++){
            array[i] = x.state[i];
        }
        for(int j = num; j < SIZE; j++){
            array[j] = y.state[j];
        }
        Board child = new Board(array);
        return child;
    }

    public Board randomSelection(ArrayList<Board> pop){
        return pop.get(rand.nextInt(POP_SIZE / 5));
    }

    public void mutate(Board child){
        int num = rand.nextInt(25);
        child.state[num] = rand.nextInt(25);
    }

    public boolean checkDupe(Board x, Board y){
        for(int i = 0; i < SIZE; i++){
            if(x.state[i] != y.state[i]){
                return false;
            }
        }
        return true;
    }
}

