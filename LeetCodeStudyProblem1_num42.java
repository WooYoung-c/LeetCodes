import java.util.Stack;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class LeetCodeStudyProblem1_num42 {
    int trapDynamicProgramming(int[] height) {
        if (height == null) {
            return 0;
        }

        int ans = 0;
        int size = height.length;

        int[] left_max = new int[size];
        int[] right_max = new int[size];

        left_max[0] = height[0];

        for (int i = 1; i < size; i++) {
            left_max[i] = max(height[i], left_max[i - 1]);
        }

        right_max[size - 1] = height[size - 1];

        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = max(height[i], right_max[i + 1]);
        }

        for (int i = 1; i < size - 1; i++) {
            ans += min(left_max[i], right_max[i]) - height[i];
        }

        return ans;
    }

    static int trapStack(int[] height) {
        int ans = 0, current = 0;
        Stack<Integer> stack = new Stack<>();

        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.peek();

                stack.pop();

                if (stack.isEmpty()) {
                    break;
                }

                int distance = current - stack.peek() - 1;
                int bounded_height = min(height[current], height[stack.peek()]) - height[top];

                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trapStack(height) + "");
    }
}
