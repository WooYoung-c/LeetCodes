public class LeetCodeProb5_84 {
    /*
    brutal force
    time limit exeeded..
     */
    public static int largestRectangleArea(int[] heights) {
        int size = heights.length;
        int max = -1;

        for (int i = 0; i < size; i++) {
            int width = 1;

            for (int j = i - 1; j >= 0; j--) {
                if (heights[i] <= heights[j]) {
                    width++;
                } else {
                    break;
                }
            }

            for (int k = i + 1; k < size; k++) {
                if (heights[i] <= heights[k]) {
                    width++;
                } else {
                    break;
                }
            }

            int area = heights[i] * width;
            if (max < area) {
                max = area;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] height1 = {2, 1, 5, 6, 2, 3};
        int[] height2 = {2, 4};
        int[] height3 = {2, 1, 2};

        System.out.println("height1 : " + largestRectangleArea(height1));
        System.out.println("height2 : " + largestRectangleArea(height2));
        System.out.println("height3 : " + largestRectangleArea(height3));
    }
}
