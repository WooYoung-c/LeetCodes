import java.util.PriorityQueue;

public class prob287 {
    public int findDuplicate(int[] nums) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int j : nums) {
            priorityQueue.offer(j);
        }

        int res = -1;

        while (!priorityQueue.isEmpty()) {
            int num = priorityQueue.poll();
            if (res == num) {
                break;
            } else {
                res = num;
            }
        }
        return res;
    }

    public int findDuplicate2(int[] nums) {
        boolean[] check = new boolean[nums.length];
        for (int i : nums) {
            if (check[i]) {
                return i;
            }
            check[i] = true;
        }
        return -1;
    }
}
