public class prob322 {
    // 솔루션 참조
    // brute force - backtracking을 위한 재귀
    // 시간 초과
    public int coinChange_backtracking_recursive(int[] coins, int amount) {
        return coinChange_backtracking_recursive(0, coins, amount);
    }

    private int coinChange_backtracking_recursive(int idxCoin, int[] coins, int amount) { // idxCoin : 현재 고려해야 하는 코인의 인덱스, coins : 배열, amount : 코인으로 만들어야 하는 값
        // amount가 0일 경우, 그대로 0을 리턴.
        if (amount == 0)
            return 0;
        // idxCoin의 값이 배열 길이보다 작고, amount가 0보다 클 때
        if (idxCoin < coins.length && amount > 0) {
            // maxVal : idxCoin 번째의 코인 값이 amount 안에 차지할 수 있는 가장 큰 갯수.
            int maxVal = amount / coins[idxCoin];
            // minCost : idxCoin 번째 코인의 가능한 최소 갯수
            int minCost = Integer.MAX_VALUE;
            // amount 내에 idxCoin 번째 코인이 들어갈 수 있는 갯수마다 다른 코인들이 몇개 들어갈 수 있는지 계산한다.
            for (int x = 0; x <= maxVal; x++) {
                // idxCoint 번째 코인이 x개 있을 때, 아직 amount 내의 값인지 검사 후
                if (amount >= x * coins[idxCoin]) {
                    // idxCoin번째 코인값 * x을 amount에서 빼고, 그 값 안에 idxCoin + 1번째 코인은 얼마나 들어갈 수 있는지 같은 함수를 호출해 검사한다.
                    int res = coinChange_backtracking_recursive(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    // 그렇게 해서 얻은 갯수값이 -1이 아니라면 거기에 x를 더한 값과 minCost를 비교, 둘 중 더 작은 값을 유지한다.
                    if (res != -1)
                        minCost = Math.min(minCost, res + x);
                }
            }
            // 앞의 과정을 거쳐도 minCost가 아직도 Integer.MAX_VALUE일 경우(== 조건을 만족하는 코인이 없다), -1을 반환하고 그렇지 않다면 minCost 값을 반환
            return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
        }
        // idxCoin의 값이 배열 길이보다 크고, amount가 0과 같거나 작을 경우(== 조건을 만족하는 코인이 없다)
        return -1;
    }

    // 동적 프로그래밍을 이용.
    // 반복되는 하위 문제의 값을 일일히 계산하지 말고 캐시하자.
    public int coinChange_dynamicProgramming_topDown(int[] coins, int amount) {
        // amount가 1보다 작을 경우(== 0 밖에 없다), 0을 리턴
        if (amount < 1) return 0;
        // 캐시를 위해 여러번 불릴 값을 배열을 하나 할당해준다.
        // 배열의 길이는 amount. 각각 amount가 1, 2, ..., amount - 1, amount일 때 필요한 최소의 코인 갯수를 저장한다.
        return coinChange_dynamicProgramming_topDown(coins, amount, new int[amount]);
    }

    private int coinChange_dynamicProgramming_topDown(int[] coins, int rem, int[] count) {
        // amount가 0보다 작을 경우, 답이 없는 불가능한 경우기 때문에 -1 반환
        if (rem < 0) return -1;
        // amount가 0일 경우, 0을 반환
        if (rem == 0) return 0;
        // 이미 계산된 유의미한 값이 있다면 저장된 값을 반환
        if (count[rem - 1] != 0) return count[rem - 1];

        // 필요한 하위 값이 없다면 직접 계산
        // min :
        int min = Integer.MAX_VALUE;
        // coins 안에 있는 값들을 현재 amount로 할당된 값들에 빼서 다음 하위 문제의 목표로 설정.
        for (int coin : coins) {
            // 같은 함수를 호출해 필요한 값을 계산해 저장해놓는다.
            int res = coinChange_dynamicProgramming_topDown(coins, rem - coin, count);
            // 그 값이 유의미하다면(0보다 크고 현재 min 값보다 작다면) 그 값에 1을 더한다.
            // 현재 고려한 코인 1개를 이렇게 얻은 값에 반영해야 하기 때문에 1을 더한다.
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        // 배열에 최신화한다.
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
}
