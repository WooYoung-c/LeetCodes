import java.util.HashSet;
import java.util.Set;

public class Prob128 {
    public int longestConsecutive(int[] nums) {
        // discusstion most voted
        // nums를 set으로 변환한다. -> 요소 탐색을 더 용이하게 하려고 한듯
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        // 리턴할 답을 담을 변수 선언
        int best = 0;
        // 가장 긴 연속된 수열을 찾음
        // 요소 하나씩(n) 탐색
        for (int n : set) {
            // 만약 이 요소보다 1 적은 요소가 없을 때 == 연속되는 수열의 첫번째 요소일 때 == 수열의 왼쪽 부분은 고려할 필요가 없음
            if (!set.contains(n - 1)) {  // only check for one direction
                // 지금 요소보다 1 더 큰 변수(m)를 정의
                // n보다 무조건 1이 더 커지게 되는데, m - n으로 답을 검출하기 때문이 아닐까
                int m = n + 1;
                // 그 변수가 집합에 있을 때 == 연속된 수열을 계속 만들 수 있을 때, 변수를 1씩 높여간다
                while (set.contains(m)) {
                    m++;
                }
                // 기존 답과 m - n 중 더 큰 값으로 유지
                best = Math.max(best, m - n);
            }
        }
        return best;
    }
}
