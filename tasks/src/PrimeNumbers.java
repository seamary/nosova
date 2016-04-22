import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class PrimeNumbers {
    public static void main(String[] args) {

        TreeSet<Integer> setPrime = new TreeSet<>();
        TreeSet<Integer> setComposite = new TreeSet<>();

        for (String arg : args) {
            int i = Integer.parseInt(arg);
            if (isPrime(i))
                setPrime.add(i);
            else
                setComposite.add(i);
        }
        int[] sorted = new int[args.length];
        ArrayList<Integer> prime = new ArrayList<>(setPrime);
        ArrayList<Integer> composite = new ArrayList<>(setComposite);

        for (int i = 0; i < setPrime.size(); i++) {
            sorted [i] = prime.get(i);
        }
        for (int i = prime.size(); i < sorted.length; i++) {
            sorted [i] = composite.get(i - prime.size());
        }
        System.out.println(Arrays.toString(sorted));
    }

    public static boolean isPrime(int j){
        for (int i = 2; i <= Math.sqrt(j); i++) {
            if(j % i == 0)
                return false;
        }
        return true;
    }
}
