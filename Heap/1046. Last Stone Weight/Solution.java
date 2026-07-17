class Solution {
    public int lastStoneWeight(int[] stones) {
        int size = stones.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(stones, i, size);
        }

        while (size > 1) {
            int stone1 = stones[0];

            stones[0] = stones[--size];
            siftDown(stones, 0, size);

            int stone2 = stones[0];

            stones[0] = stones[--size];
            siftDown(stones, 0, size);

            int difference = stone1 - stone2;

            if (difference > 0) {
                stones[size] = difference;
                siftUp(stones, size);
                size++;
            }
        }

        return size == 0 ? 0 : stones[0];
    }

    private void siftDown(int[] heap, int index, int size) {
        while (true) {
            int left = 2 * index + 1;
            int right = left + 1;
            int largest = index;

            if (left < size && heap[left] > heap[largest]) {
                largest = left;
            }

            if (right < size && heap[right] > heap[largest]) {
                largest = right;
            }

            if (largest == index) {
                return;
            }

            swap(heap, index, largest);
            index = largest;
        }
    }

    private void siftUp(int[] heap, int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap[parent] >= heap[index]) {
                return;
            }

            swap(heap, parent, index);
            index = parent;
        }
    }

    private void swap(int[] heap, int i, int j) {
        int temporary = heap[i];
        heap[i] = heap[j];
        heap[j] = temporary;
    }
}
