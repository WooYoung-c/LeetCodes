import java.util.PriorityQueue;

public class prob287 {
    public int findDuplicate(int[] nums) {
        // 플로이드 토끼와 거북이 알고리즘 이용
        // 토끼, 거북이에 해당하는 인덱스로 배열을 순환하며 반복되는 지점을 찾는다
        // 배열 안의 요소는 1~n의 값으로 한정되어 이 알고리즘을 이용할 수 있다. -> 사이클이 있는 그래프로 비유될 수 있다.

        // 토끼는 거북이보다 한단계 더 나아가게 해서 최초로 만나는 곳을 찾는다.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // 토끼와 거북이가 만났다면,
        // 토끼와 거북이의 속도를 같게 하고 다시 만나는 지점을 찾는다.
        // 그 곳이 반복의 시작점이다.
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }
}
