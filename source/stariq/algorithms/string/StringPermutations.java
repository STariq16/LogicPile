package stariq.algorithms.string;

import java.util.ArrayList;
import java.util.List;

// Find all the permutations of a given string (all unique arrangements of characters in a string).
// Check if the second string contains permutation of the first string.
public class StringPermutations {

    public static void main(String[] args) {
        permutation("ABC", 0, 2);

        System.out.println();

        for(String s : permutation2("ABC", 0, 3)) {
            System.out.print(s + " ");
        }

        System.out.println();

        permutationIteratively("ABC");

        System.out.println(checkPermutation("abd", "eidebaoo"));
    }

    // Simpler method. The end parameter can be removed as it does not change.
    // Better to leave end parameter if only permutations of substring needs to be found.
    // Return type is void - just prints out all the permutations.
    public static void permutation(String str, int start, int end) {
        if(start == end) {
            System.out.print(str + " ");
        } else {
            for(int i = start; i <= end; i++) {
                String swapped = swap(str, start, i);
                permutation(swapped, start + 1, end);
            }
        }
    }

    // Return type is a list - adds them to a static list.
    // The list cannot be local as recursion will keep resetting it to null.
    static List<String> permutations2 = new ArrayList<>();
    public static List<String> permutation2(String str, int start, int len) {
        if(start == len - 1) {
            permutations2.add(str);
        } else {
            for(int i = start; i < len; i++) {
                str = swap(str, start, i);
                permutation2(str, start + 1, len);
                str = swap(str, start , i);
            }
        }
        return permutations2;
    }

    // Prints permutation iteratively
    public static void permutationIteratively(String str) {
        // Stores all partial permutations - initialise with first character.
        List<String> partial = new ArrayList<>();
        partial.add(String.valueOf(str.charAt(0)));

        // Loop for every character in string.
        for(int i = 1; i < str.length(); i++) {
            // Loop backwards through the partial permutation.
           for(int j = partial.size() - 1; j >= 0; j--) {
               String rem = partial.remove(j);
               // Loop to insert character in all possible positions of current partial permutation.
               for(int k = 0; k <= rem.length(); k++) {
                   String initial = rem.substring(0,k);
                   char current = str.charAt(i);
                   String last = rem.substring(k);
                   partial.add(initial + current + last);
               }
           }
        }
        System.out.println(partial);
    }

    // Swap method used in all implementations of permutations.
    private static String swap(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char c = chars[i];
        chars[i] = chars[j];
        chars[j] = c;
        return String.valueOf(chars);
    }

    public static boolean checkPermutation(String s1, String s2) {
        if(s1.length() > s2.length()) {
            return false;
        }
        int[] count = new int[26];
        for(char c : s1.toCharArray()) {
            count[c - 'a']++;
        }
        int counter = s1.length();
        int j = 0;
        while(j < s1.length()) {
            char c = s2.charAt(j);
            if(count[c - 'a']-- > 0) {
                counter--;
            }
            j++;
        }
        int i = 0;
        while(j < s2.length() && counter != 0) {
            char start = s2.charAt(i);
            char end = s2.charAt(j);
            if(count[start - 'a']++ >= 0) {
                counter++;
            }
            if(count[end - 'a']-- > 0) {
                counter--;
            }
            i++;
            j++;
        }
        return counter == 0;
    }
}