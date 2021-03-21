public class LeetCodeProb6_174 {

    // using memoization
    // KeyPoint : it needs to traverse in reverse order
    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] minHP = new int[m][n];

        minHP[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);

        for (int row = m - 2; row >= 0; row--) {
            minHP[row][n - 1] = Math.max(minHP[row + 1][n - 1] - dungeon[row][n - 1], 1);
        }

        for (int col = n - 2; col >= 0; col--) {
            minHP[m - 1][col] = Math.max(minHP[m - 1][col + 1] - dungeon[m - 1][col], 1);
        }

        for (int row = m - 2; row >= 0; row--) {
            for (int col = n - 2; col >= 0; col--) {
                int val1 = Math.max(minHP[row+1][col] - dungeon[row][col], 1);
                int val2 = Math.max(minHP[row][col+1] - dungeon[row][col], 1);
                minHP[row][col] = Math.min(val1, val2);
            }
        }

        return minHP[0][0];
    }

    // recursive call
    // time limit exceeded
    // it need to be used with Memoization.
    public static int calculateMinimumHP_2(int[][] dungeon) {
        int curNeededHP = dungeon[0][0] < 0 ? 1 - dungeon[0][0] : 1;
        findMinHPByRecursive(dungeon, 0, 0, 0, curNeededHP);
        return minHP;
    }

    static int minHP = Integer.MAX_VALUE;

    public static void findMinHPByRecursive(int[][] dungeon, int row, int col, int usedHP, int neededHP) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        int curUsedHP = usedHP + dungeon[row][col];
        int curNeededHP = curUsedHP < 0 ? Math.max(neededHP, 1 - curUsedHP) : neededHP;

        if (row == m - 1 && col == n - 1) {
            minHP = Math.min(minHP, curNeededHP);
            return;
        }

        if (row + 1 < m) {
            findMinHPByRecursive(dungeon, row + 1, col, curUsedHP, curNeededHP);
        }
        if (col + 1 < n) {
            findMinHPByRecursive(dungeon, row, col + 1, curUsedHP, curNeededHP);
        }
    }

    public static void main(String[] args) {
        int[][] dungeon1 = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        int[][] dungeon2 = {{1, -1, 2}, {2, -2, -2}};
        int[][] dungeon3 = {{1, -3, 3}, {0, -2, 0}, {-3, -3, -3}};

        System.out.println("output1 : " + calculateMinimumHP_2(dungeon1));
        minHP = Integer.MAX_VALUE;
        System.out.println("output2 : " + calculateMinimumHP_2(dungeon2));
        minHP = Integer.MAX_VALUE;
        System.out.println("output3 : " + calculateMinimumHP_2(dungeon3));
        minHP = Integer.MAX_VALUE;

        System.out.println("");
        System.out.println("output1 : " + calculateMinimumHP(dungeon1));
        System.out.println("output2 : " + calculateMinimumHP(dungeon2));
        System.out.println("output3 : " + calculateMinimumHP(dungeon3));
    }
}
