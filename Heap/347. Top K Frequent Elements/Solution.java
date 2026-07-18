import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencies = new HashMap<>();

        for (int num : nums) {
            frequencies.merge(num, 1, Integer::sum);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
            new PriorityQueue<>(
                (a, b) -> Integer.compare(a.getValue(), b.getValue())
            );

        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            minHeap.offer(entry);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }
}
