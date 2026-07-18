import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    private static final int MOD = 1_000_000_007;

    public int countPaths(int n, int[][] roads) {
        ArrayList<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int node1 = road[0];
            int node2 = road[1];
            int time = road[2];

            graph[node1].add(new int[]{node2, time});
            graph[node2].add(new int[]{node1, time});
        }

        long[] distances = new long[n];
        Arrays.fill(distances, Long.MAX_VALUE);

        int[] ways = new int[n];

        distances[0] = 0;
        ways[0] = 1;

        PriorityQueue<long[]> queue =
            new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        queue.offer(new long[]{0, 0});

        while (!queue.isEmpty()) {
            long[] current = queue.poll();

            int node = (int) current[0];
            long distance = current[1];

            if (distance != distances[node]) {
                continue;
            }

            if (node == n - 1) {
                return ways[node];
            }

            for (int[] edge : graph[node]) {
                int neighbor = edge[0];
                long newDistance = distance + edge[1];

                if (newDistance < distances[neighbor]) {
                    distances[neighbor] = newDistance;
                    ways[neighbor] = ways[node];

                    queue.offer(new long[]{neighbor, newDistance});
                } else if (newDistance == distances[neighbor]) {
                    ways[neighbor] += ways[node];

                    if (ways[neighbor] >= MOD) {
                        ways[neighbor] -= MOD;
                    }
                }
            }
        }

        return ways[n - 1];
    }
}
