import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int start = 0; start < graph.length; start++) {
            if (color[start] != 0)
                continue;

            color[start] = 1;
            queue.add(start);

            while (!queue.isEmpty()) {
                int current = queue.remove();

                for (int neighbor : graph[current]) {
                    if (color[neighbor] == 0) {
                        color[neighbor] = -color[current];
                        queue.add(neighbor);
                    } else if (color[neighbor] == color[current]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
