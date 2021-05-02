import java.util.Arrays;
import java.util.PriorityQueue;

public class LeetCodeProb9_1353 {
    // refers to dicuss's most voted solution
    // use Priority Queue!
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]); // sort events increasing by start time
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int ans = 0, i = 0, n = events.length;
        for (int d = 1; d <= 100000; d++) {
            while (i < n && events[i][0] == d) { // Add new events that can attend on day `d`
                minHeap.add(events[i++][1]);
            }
            while (!minHeap.isEmpty() && minHeap.peek() < d) { // Remove events that are already closed
                minHeap.poll();
            }
            if (!minHeap.isEmpty()) { // Use day `d` to attend to the event that closes earlier
                ans++;
                minHeap.poll();
            }
        }
        return ans;
    }
}
