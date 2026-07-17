import java.util.Arrays;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int[] minHeap = Arrays.copyOf(nums, k);

        for (int i = k / 2 - 1; i >= 0; i--) {
            siftDown(minHeap, i, k);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap[0]) {
                minHeap[0] = nums[i];
                siftDown(minHeap, 0, k);
            }
        }

        return minHeap[0];
    }

    private void siftDown(int[] heap, int index, int size) {
        while (true) {
            int left = 2 * index + 1;
            int right = left + 1;
            int smallest = index;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == index) {
                return;
            }

            int temporary = heap[index];
            heap[index] = heap[smallest];
            heap[smallest] = temporary;

            index = smallest;
        }
    }
}
