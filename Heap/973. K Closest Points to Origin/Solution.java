import java.util.PriorityQueue;

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap =
            new PriorityQueue<>(k, (a, b) -> Integer.compare(b[2], a[2]));

        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            int distance = x * x + y * y;

            if (maxHeap.size() < k) {
                maxHeap.offer(new int[]{x, y, distance});
            } else if (distance < maxHeap.peek()[2]) {
                maxHeap.poll();
                maxHeap.offer(new int[]{x, y, distance});
            }
        }

        int[][] result = new int[k][2];

        for (int i = 0; i < k; i++) {
            int[] point = maxHeap.poll();
            result[i][0] = point[0];
            result[i][1] = point[1];
        }

        return result;
    }
}
