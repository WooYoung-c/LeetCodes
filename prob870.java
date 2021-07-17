import java.util.Arrays;
import java.util.PriorityQueue;

public class prob870 {
    public static int[] advantageCount(int[] nums1, int[] nums2) {
        // most voted solution from discussion

        // 탐욕 알고리즘(Greedy Algorithm)
        // 1. 항상 nums2의 가장 큰 값부터 nums1에서 큰 값이 있는지 체크해야한다. -> 그 값이 제일 조건을 충족하기 어렵기 때문
        // 2. 체크하는 nums2의 현재 값보다 nums1의 가장 큰 값이 작다면, 그 어떤 것도 조건을 만족시킬 수 없다.

        // nums1을 오름차순으로 정렬한다.
        Arrays.sort(nums1);
        // n = nums1의 길이
        int n = nums1.length;
        // n만큼의 결과 배열을 생성한다.
        int[] res = new int[n];
        // nums2의 인덱스와 값을 저장하는 int형 배열을 만들어, 이를 정렬할 우선순위 큐를 선언한다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // 그 우선순위 큐에 요소를 채워 넣는다.
        for (int i = 0; i < n; i++) pq.add(new int[]{nums2[i], i});
        // nums1와 nums2의 값 비교를 위해, nums1 인덱스를 조절할 변수를 선언한다.
        int lo = 0, hi = n - 1;
        // 우선순위 큐가 빌 때까지 반복.
        while (!pq.isEmpty()) {
            // 우선순위 큐 요소 하나를 뺀다.
            int[] cur = pq.poll();
            // 인덱스, 값을 분리해 각각의 변수로 넣는다.
            int idx = cur[1], val = cur[0];
            // nums1의 가장 높은 값과 우선순위 큐 안에 있던 값을 비교한다.
            // 만약 nums1의 가장 높은 값이 nums2의 값 보다 높다면,
            // res에 nums2의 값이 있던 위치와 같은 곳에 그 값을 넣는다.
            if (nums1[hi] > val) res[idx] = nums1[hi--];
            // 그렇지 않다면 nums1의 가장 낮은 값을 그 곳에 넣는다.
            else res[idx] = nums1[lo++];
        }
        // 그렇게 완성된 res를 반환한다.
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {1, 10, 4, 11};

        int[] output = advantageCount(nums1, nums2);
    }
}
