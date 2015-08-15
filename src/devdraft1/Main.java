package devdraft1;
import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] height = new int[N];
        int[] right = new int[N];
        int[] left = new int[N];
        Stack<Integer> rights = new Stack<Integer>();
        Stack<Integer> lefts = new Stack<Integer>();

        int i = 0;
        height[i] = sc.nextInt();   //first entry
        while (!rights.empty() || i < N) {
            if (height[i] < 1) {
                if (!rights.empty()) {  //if the right side stack has something in it, do this
                        while (!rights.empty() && i >= (rights.peek() + height[rights.peek()]) ) {
                            right[rights.peek()] = i - rights.pop();
                        }
                }
            }
            else {
                    rights.push(new Integer(i));    // if domino exists, add it to right side stack
                    if (lefts.empty()) {
                        lefts.push(new Integer(i));     // if first domino, do this
                        left[i] = height[i];
                    }
                    else {
                        if ((i - height[i]) > lefts.peek()) {   //if the domino doesnt reach the previous domino
                            left[i] = height[i];
                        }
                        else {
                            if (i - height[i] > lefts.peek() - left[lefts.peek()]) {
                                left[i] = i - (lefts.peek() - left[lefts.peek()]);
                            }
                            else {
                                left[i] = i - (lefts.peek() - left[lefts.peek()]);
                            }
                        }
                        lefts.push(new Integer(i));     // if domino exists, add to left side stack
                    }
            }
            //this is in case dominos fall past the right edge
            if (i==N-1) {   //when no more input, clean our rights
                while (!rights.empty()) {
                    while (!rights.empty() && i >= (rights.peek() + height[rights.peek()]) ) {
                        right[rights.peek()] = i - rights.pop();
                    }
                    i++;
                }
            }
            if (i < N-1) {
                i++;
                height[i] = sc.nextInt();
            }
        }
        String rightSide = Arrays.toString(right).replace(",","").replace("[", "").replace("]", "");
        String leftSide = Arrays.toString(left).replace(",","").replace("[", "").replace("]", "");
        System.out.println(rightSide);
        System.out.println(leftSide);
    }
}