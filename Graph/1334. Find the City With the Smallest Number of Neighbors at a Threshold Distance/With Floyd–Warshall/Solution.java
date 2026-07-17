import java.util.Arrays;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        final int INF = 1_000_000_000;
        int[][] distances = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(distances[i], INF);
            distances[i][i] = 0;
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            // Handles the possibility of multiple edges between two cities.
            if (weight < distances[from][to]) {
                distances[from][to] = weight;
                distances[to][from] = weight;
            }
        }

        // Floyd–Warshall
        for (int k = 0; k < n; k++) {
            int[] distancesFromK = distances[k];

            for (int i = 0; i < n; i++) {
                int[] distancesFromI = distances[i];
                int distanceIK = distancesFromI[k];

                if (distanceIK == INF) {
                    continue;
                }

                for (int j = 0; j < n; j++) {
                    if (distancesFromK[j] == INF) {
                        continue;
                    }

                    int newDistance = distanceIK + distancesFromK[j];

                    if (newDistance < distancesFromI[j]) {
                        distancesFromI[j] = newDistance;
                    }
                }
            }
        }

        int minimumNeighbors = Integer.MAX_VALUE;
        int selectedCity = -1;

        // Descending order automatically preserves the greatest city on ties.
        for (int i = n - 1; i >= 0; i--) {
            int neighbors = 0;

            for (int j = 0; j < n; j++) {
                if (i != j && distances[i][j] <= distanceThreshold) {
                    neighbors++;

                    // This city cannot beat the current one.
                    if (neighbors >= minimumNeighbors) {
                        break;
                    }
                }
            }

            if (neighbors < minimumNeighbors) {
                minimumNeighbors = neighbors;
                selectedCity = i;
            }
        }

        return selectedCity;
    }
}
