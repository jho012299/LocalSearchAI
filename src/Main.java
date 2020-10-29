import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean flag = false;
        boolean start = false;

        Problem prob = new Problem();
        Schedule schedule = new Schedule();
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter 1 for Simulated Annealing and 2 for Genetic Algorithm. ");
        int num = kb.nextInt();

        while(!start) {
            if (num == 1) {
                flag = true;
                start = true;
            } else if (num == 2) {
                flag = false;
                start = true;
            } else {
                System.out.println("Enter a valid value. ");
                num = kb.nextInt();
            }
        }

        if(flag) {
            SimulatedAnnealing simAnneal = new SimulatedAnnealing();
            double startTime = System.nanoTime();
            int solution[] = simAnneal.anneal(prob, schedule);
            double endTime = System.nanoTime();

            System.out.println("Simulated Annealing");
            System.out.print("Solution: ");
            for (int n = 0; n < 25; n++) {
                System.out.print(solution[n] + " ");
            }

            System.out.println("");
            for(int i = 0; i < 25; i++){
                for(int j = 0; j < 25; j++){
                    if(i == solution[j]){
                        System.out.print("Q  ");
                    }
                    else{
                        System.out.print("-  ");
                    }
                }
                System.out.println("");
            }
            System.out.println("\nPairs of Attacking Queens: " + prob.goalTest(solution));
            System.out.println("Time taken: " + ((endTime - startTime) / 1000000000) + " seconds");
        }
        else {
            GeneticAlgorithm geneticAlg = new GeneticAlgorithm();
            double startTime = System.nanoTime();
            int solution[] = geneticAlg.genetic();
            double endTime = System.nanoTime();

            System.out.println("Genetic Algorithm");
            System.out.print("Solution: ");
            for (int i = 0; i < 25; i++) {
                System.out.print(solution[i] + " ");
            }
            System.out.println("");
            for(int i = 0; i < 25; i++){
                for(int j = 0; j < 25; j++){
                    if(i == solution[j]){
                        System.out.print("Q  ");
                    }
                    else{
                        System.out.print("-  ");
                    }
                }
                System.out.println("");
            }
            Board board = new Board(solution);
            System.out.println("\nFitness: " + board.fitnessFunc(solution));
            System.out.println("Time taken: " + ((endTime - startTime) / 1000000000) + " seconds");
        }
    }
}