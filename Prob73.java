public class Prob73 {
    public static void setZeroes(int[][] matrix) {
        // borrowed from most voted discussion
        boolean isFirstColZero = false;
        int rows = matrix.length, cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            isFirstColZero = matrix[i][0] == 0;
            for (int j = 1; j < cols; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (isFirstColZero) matrix[i][0] = 0;
        }
    }
}
