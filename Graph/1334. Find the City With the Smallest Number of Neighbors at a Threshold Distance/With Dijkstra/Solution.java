import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<List<int[]>> listeVoisins = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            listeVoisins.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            listeVoisins.get(from).add(new int[]{to, weight});
            listeVoisins.get(to).add(new int[]{from, weight});
        }

        int minVoisins = n;
        int city = -1;

        for (int start = n - 1; start >= 0; start--) {
            int numVoisins = dijkstra(
                n,
                start,
                listeVoisins,
                distanceThreshold,
                minVoisins
            );

            if (numVoisins < minVoisins) {
                minVoisins = numVoisins;
                city = start;
            }
        }

        return city;
    }

    private int dijkstra(
        int n,
        int start,
        List<List<int[]>> listeVoisins,
        int distanceThreshold,
        int currentMinimum
    ) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<int[]> queue =
            new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        queue.add(new int[]{start, 0});

        int numVoisins = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            int from = current[0];
            int currentDistance = current[1];

            // Ignore an obsolete queue entry.
            if (currentDistance != distances[from]) {
                continue;
            }

            if (currentDistance > distanceThreshold) {
                break;
            }

            if (from != start) {
                numVoisins++;

                // This city can no longer improve the current answer.
                if (numVoisins >= currentMinimum) {
                    return numVoisins;
                }
            }

            for (int[] voisin : listeVoisins.get(from)) {
                int to = voisin[0];
                int weight = voisin[1];
                int newDistance = currentDistance + weight;

                // Distances above the threshold are irrelevant.
                if (
                    newDistance <= distanceThreshold &&
                    newDistance < distances[to]
                ) {
                    distances[to] = newDistance;
                    queue.add(new int[]{to, newDistance});
                }
            }
        }

        return numVoisins;
    }
}
