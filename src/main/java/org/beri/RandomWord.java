
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;


public class RandomWord {
    public static void main(String[] args) {

        String champion = "";

        int i = 0;

        while (!StdIn.isEmpty()) {

            String s = StdIn.readString();
            i++;
            if (StdRandom.bernoulli(1 / (double) i)) {
                champion = s;
            }

        }

        System.out.println(champion);
    }
}
