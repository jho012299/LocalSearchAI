public class Schedule {
    public double decay(int t){
        double num = Math.pow(0.98, t);
        return 1 * num;
    }
}