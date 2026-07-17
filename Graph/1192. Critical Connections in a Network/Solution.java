import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int time;

    public List<List<Integer>> criticalConnections(
        int n,
        List<List<Integer>> connections
    ) {
        List<Integer>[] adjacencyList = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (List<Integer> connection : connections) {
            int u = connection.get(0);
            int v = connection.get(1);

            adjacencyList[u].add(v);
            adjacencyList[v].add(u);
        }

        int[] discovery = new int[n];
        int[] lowLink = new int[n];
        List<List<Integer>> criticalEdges = new ArrayList<>();

        time = 1;

        for (int node = 0; node < n; node++) {
            if (discovery[node] == 0) {
                dfs(
                    node,
                    -1,
                    adjacencyList,
                    discovery,
                    lowLink,
                    criticalEdges
                );
            }
        }

        return criticalEdges;
    }

    private void dfs(
        int current,
        int parent,
        List<Integer>[] adjacencyList,
        int[] discovery,
        int[] lowLink,
        List<List<Integer>> criticalEdges
    ) {
        discovery[current] = time;
        lowLink[current] = time;
        time++;

        for (int neighbor : adjacencyList[current]) {
            if (neighbor == parent) {
                continue;
            }

            if (discovery[neighbor] == 0) {
                dfs(
                    neighbor,
                    current,
                    adjacencyList,
                    discovery,
                    lowLink,
                    criticalEdges
                );

                lowLink[current] = Math.min(
                    lowLink[current],
                    lowLink[neighbor]
                );

                if (lowLink[neighbor] > discovery[current]) {
                    criticalEdges.add(
                        Arrays.asList(current, neighbor)
                    );
                }
            } else {
                lowLink[current] = Math.min(
                    lowLink[current],
                    discovery[neighbor]
                );
            }
        }
    }
}
