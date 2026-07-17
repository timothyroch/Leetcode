import java.util.PriorityQueue;

class KthLargest {
    private final PriorityQueue<Integer> minHeap;
    private final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>(k);

        for (int num : nums) {
            insert(num);
        }
    }

    public int add(int val) {
        insert(val);
        return minHeap.peek();
    }

    private void insert(int value) {
        if (minHeap.size() < k) {
            minHeap.offer(value);
        } else if (value > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(value);
        }
    }
}
