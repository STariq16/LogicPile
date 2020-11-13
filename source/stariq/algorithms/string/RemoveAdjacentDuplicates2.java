package stariq.algorithms.string;

import java.util.Stack;

// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
public class RemoveAdjacentDuplicates2 {

    public static void main(String[] args) {
    //    System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(removeDuplicates("iiiixxxxxiiccccczzffffflllllllllfffffllyyyyyuuuuuz", 5));
    }

    public static String removeDuplicates(String str, int k) {
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        for(char c : str.toCharArray()) {
            if(charStack.isEmpty() || charStack.peek() != c) {
                charStack.push(c);
                countStack.push(1);
            } else {
                countStack.push(countStack.pop() + 1);
            }
            if(countStack.peek() == k) {
                countStack.pop();
                charStack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!charStack.isEmpty()) {
            char c = charStack.pop();
            int n = countStack.pop();
            for(int i = 0; i < n; i++) {
                sb.append(c);
            }
        }
        return sb.reverse().toString();
    }

    public static String removeDuplicatesCustom(String str, int k) {
        class CharFreq {
            char c;
            int n;

            CharFreq(char c, int n) {
                this.c = c;
                this.n = n;
            }
        }

        Stack<CharFreq> stack = new Stack<>();
        for(char c : str.toCharArray()) {
            if(stack.isEmpty() || stack.peek().c != c) {
                stack.push(new CharFreq(c, 1));
            } else {
                stack.push(new CharFreq(c, stack.pop().n + 1));
            }
            if(stack.peek().n == k) {
                stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            CharFreq cf = stack.pop();
            for(int i = 0; i < cf.n; i++) {
                sb.append(cf.c);
            }
        }
        return sb.reverse().toString();
    }


}
