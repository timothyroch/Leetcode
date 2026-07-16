import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<int[]>[] adjacencyList = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++)
            adjacencyList[i] = new ArrayList<>();

        for (int[] edge : times)
            adjacencyList[edge[0]].add(new int[]{edge[1], edge[2]});

        int[] timePerNode = new int[n + 1];
        Arrays.fill(timePerNode, Integer.MAX_VALUE);
        timePerNode[k] = 0;

        PriorityQueue<int[]> queue =
            new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        queue.add(new int[]{k, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            int currentNode = current[0];
            int currentTime = current[1];

            if (currentTime != timePerNode[currentNode])
                continue;

            for (int[] neighbor : adjacencyList[currentNode]) {
                int neighborNode = neighbor[0];
                int edgeTime = neighbor[1];
                int newTime = currentTime + edgeTime;

                if (newTime < timePerNode[neighborNode]) {
                    timePerNode[neighborNode] = newTime;
                    queue.add(new int[]{neighborNode, newTime});
                }
            }
        }

        int maximumTime = 0;

        for (int node = 1; node <= n; node++) {
            if (timePerNode[node] == Integer.MAX_VALUE)
                return -1;

            maximumTime = Math.max(maximumTime, timePerNode[node]);
        }

        return maximumTime;
    }
}
