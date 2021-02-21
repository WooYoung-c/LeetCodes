public class LeetCodeProb4_123 {
    static class Solution {
        public static int maxProfit(int[] prices) {
            int len = prices.length;

            if (len < 2) {
                return 0;
            }

            int min = prices[0];

            int[] earnL = new int[len];

            for (int i = 1; i < len; i++) {
                earnL[i] = Math.max(earnL[i - 1], prices[i] - min);
                min = Math.min(min, prices[i]);
            }

            int[] earnR = new int[len];
            int max = prices[len - 1];

            for (int i = len - 2; i >= 0; i--) {
                earnR[i] = Math.max(earnR[i + 1], max - prices[i]);
                max = Math.max(max, prices[i]);
            }

            int res = earnR[0];

            for (int i = 0; i < len - 1; i++) {
                res = Math.max(res, earnL[i] + earnR[i + 1]);
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] prices1 = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices2 = {1, 2, 3, 4, 5};
        int[] prices3 = {7, 6, 4, 3, 1};
        int[] prices4 = {1};
        System.out.println("prices1 : " + Solution.maxProfit(prices1));
        System.out.println("prices2 : " + Solution.maxProfit(prices2));
        System.out.println("prices3 : " + Solution.maxProfit(prices3));
        System.out.println("prices4 : " + Solution.maxProfit(prices4));
    }
}
