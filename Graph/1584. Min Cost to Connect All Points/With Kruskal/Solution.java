import java.util.Arrays;

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int numberOfEdges = n * (n - 1) / 2;

        int[][] edges = new int[numberOfEdges][3];
        int edgeIndex = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int distance =
                    Math.abs(points[i][0] - points[j][0])
                    + Math.abs(points[i][1] - points[j][1]);

                edges[edgeIndex++] = new int[]{i, j, distance};
            }
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        DisjointSet disjointSet = new DisjointSet(n);

        int totalDistance = 0;
        int edgesUsed = 0;

        for (int[] edge : edges) {
            int point1 = edge[0];
            int point2 = edge[1];

            if (disjointSet.union(point1, point2)) {
                totalDistance += edge[2];
                edgesUsed++;

                if (edgesUsed == n - 1)
                    break;
            }
        }

        return totalDistance;
    }

    private static class DisjointSet {
        private final int[] parent;
        private final int[] rank;

        public DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];

            for (int i = 0; i < size; i++)
                parent[i] = i;
        }

        private int find(int node) {
            if (parent[node] != node)
                parent[node] = find(parent[node]);

            return parent[node];
        }

        public boolean union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);

            if (root1 == root2)
                return false;

            if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }

            return true;
        }
    }
}
