import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int minimumEffortPath(int[][] heights) {
        int numRows = heights.length;
        int numCols = heights[0].length;

        int[][] minimumEffort = new int[numRows][numCols];

        for (int[] row : minimumEffort) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        minimumEffort[0][0] = 0;

        PriorityQueue<int[]> queue =
            new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        queue.add(new int[]{0, 0, 0});

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        while (!queue.isEmpty()) {
            int[] current = queue.remove();

            int effort = current[0];
            int row = current[1];
            int col = current[2];

            if (effort != minimumEffort[row][col]) {
                continue;
            }

            if (row == numRows - 1 && col == numCols - 1) {
                return effort;
            }

            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];

                if (
                    nextRow < 0 || nextRow >= numRows ||
                    nextCol < 0 || nextCol >= numCols
                ) {
                    continue;
                }

                int edgeEffort = Math.abs(
                    heights[row][col] - heights[nextRow][nextCol]
                );

                int newEffort = Math.max(effort, edgeEffort);

                if (newEffort < minimumEffort[nextRow][nextCol]) {
                    minimumEffort[nextRow][nextCol] = newEffort;
                    queue.add(new int[]{newEffort, nextRow, nextCol});
                }
            }
        }

        return 0;
    }
}
