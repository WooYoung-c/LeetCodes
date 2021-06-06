import java.util.Stack;

public class prob55 {
    public boolean canJump(int[] nums) {
        // discussion에서 가져옴
        // 가장 첫번째 인덱스의 값을 이동할 수 있는 가장 최대의 수로 지정
        int curMax = nums[0];
        // 각 인덱스에 접근해서 그 인덱스에 이동할 수 있는지 검사
        for (int i = 1; i < nums.length; i++) {
            // 현재 이동할 수 있는 최대의 수로 i 번째 인덱스로 점프할 수 있는지 검사
            if (curMax < i) return false;
            // 현재 최대로 이동할 수 있는 수와 i번째 인덱스에서 점프할 수 있는 수를 비교, 더 큰 값을 유지한다.
            curMax = Math.max(curMax, i + nums[i]);
        }
        return true;
    }
}
