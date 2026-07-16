import java.util.ArrayList;
import java.util.ArrayDeque;

class Solution {
    public boolean validPath(
        int n,
        int[][] edges,
        int source,
        int destination
    ) {
        if (source == destination)
            return true;

        ArrayList<Integer>[] adjacencyList = new ArrayList[n];

        for (int i = 0; i < n; i++)
            adjacencyList[i] = new ArrayList<>();

        for (int[] edge : edges) {
            adjacencyList[edge[0]].add(edge[1]);
            adjacencyList[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.push(source);
        visited[source] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int neighbor : adjacencyList[current]) {
                if (neighbor == destination)
                    return true;

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }

        return false;
    }
}
