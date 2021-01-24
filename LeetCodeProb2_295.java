import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCodeProb2_295 {

    static class MedianFinder_Sort {
        static ArrayList<Integer> store = new ArrayList<>();

        public static void addNum(int num) {
            store.add(num);
        }

        public static double findMedian() {
            store.sort(null);

            int n = store.size();
            return (n & 1) == 1 ? store.get(n / 2) : ((double) store.get(n / 2 - 1) + store.get(n / 2)) * 0.5;
        }
    }

    static class MedianFinder_InsertionSort {
        static ArrayList<Integer> store = new ArrayList<>();

        public static void addNum(int num) {
            if (store.isEmpty()) {
                store.add(num);
            } else {
                store.add(lowerBoundSearch(store, 0, store.size(), num), num);
            }
        }

        public static int lowerBoundSearch(ArrayList<Integer> store, int start, int end, int k) {
            while (start < end) {
                int middle = (start + end) / 2;
                if (store.get(middle) >= k) {
                    end = middle;
                } else {
                    start = middle + 1;
                }
            }
            return end;
        }

        public static double findMedian() {
            int n = store.size();
            return (n & 1) == 1 ? store.get(n / 2) : ((double) store.get(n / 2 - 1) + store.get(n / 2)) * 0.5;
        }
    }

    static class MedianFinder_TwoHeap {
        static PriorityQueue<Integer> lo = new PriorityQueue<>(Comparator.reverseOrder());
        static PriorityQueue<Integer> hi = new PriorityQueue<>();

        public static void addNum(int num) {
            lo.add(num);

            hi.add(lo.peek());
            lo.poll();

            if (lo.size() < hi.size()) {
                lo.add(hi.peek());
                hi.poll();
            }
        }

        public static double findMedian() {
            return lo.size() > hi.size() ? lo.peek() : ((double) lo.peek() + hi.peek()) * 0.5;
        }
    }

    // need more works to understand multiset and two pointers..

    public static void main(String[] args) {
        MedianFinder_Sort.addNum(41);
        MedianFinder_Sort.addNum(35);
        System.out.println("when use Sort : " + MedianFinder_Sort.findMedian());
        MedianFinder_Sort.addNum(62);
        System.out.println("when use Sort : " + MedianFinder_Sort.findMedian());

        MedianFinder_InsertionSort.addNum(41);
        MedianFinder_InsertionSort.addNum(35);
        System.out.println("when use Insertion Sort : " + MedianFinder_InsertionSort.findMedian());
        MedianFinder_InsertionSort.addNum(62);
        System.out.println("when use Insertion Sort : " + MedianFinder_InsertionSort.findMedian());

        MedianFinder_TwoHeap.addNum(41);
        MedianFinder_TwoHeap.addNum(35);
        System.out.println("when use Two heaps : " + MedianFinder_TwoHeap.findMedian());
        MedianFinder_TwoHeap.addNum(62);
        System.out.println("when use Two heaps : " + MedianFinder_TwoHeap.findMedian());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */