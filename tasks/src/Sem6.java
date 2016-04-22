import java.util.*;

public class Sem6 {
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

        /* Integer[] numbers = new Integer[]{7, 4, 1, 15, 27, 103};
        for (Integer num : numbers) {
            int i = num;
            if (isPrime(i))
                setPrime.add(i);
            else
                setComposite.add(i);
        }
        int[] sorted = new int[numbers.length];
        */

        ArrayList<Integer> prime = new ArrayList<>(setPrime);
        ArrayList<Integer> composite = new ArrayList<>(setComposite);

        for (int i = 0; i < setPrime.size(); i++) {
            sorted [i] = prime.get(i);
        }
        for (int i = prime.size(); i < sorted.length; i++) {
            sorted [i] = composite.get(i - prime.size());
        }
        for (int i : sorted) {
            System.out.println(i);
        }
    }

    public static boolean isPrime(int j){
        for (int i = 2; i <= Math.sqrt(j); i++) {
            if(j % i == 0)
                return false;
        }
        return true;
    }
    //==================================================================================================================

    public static int trans (String digital){
        int r = Integer.parseInt(digital, 16);
        return r;
    }

    public static void trans2 (){
        String[] str = {"a", "b", "c", "12a", "1a", "cba"};
        TreeSet<String> set = new TreeSet<>(
                new Comparator<String>() {
                    public int  compare(String s1, String s2) {
                        StringBuilder str1 = new StringBuilder(s1);
                        StringBuilder str2 = new StringBuilder(s2);
                        return str1.reverse().toString().compareTo(str2.reverse().toString());
                    }
                });
        Collections.addAll(set, str);
        System.out.println(set);
    }
}


