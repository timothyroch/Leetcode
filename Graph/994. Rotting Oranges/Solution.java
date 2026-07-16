import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid.length == 0)
            return 0;

        int height = grid.length;
        int width = grid[0].length;
        int freshOranges = 0;
        int nbDays = 0;

        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        if (freshOranges == 0)
            return 0;

        while (!queue.isEmpty() && freshOranges > 0) {
            int lenQ = queue.size();

            for (int k = 0; k < lenQ; k++) {
                int[] current = queue.remove();
                int i = current[0];
                int j = current[1];

                if (i > 0 && grid[i - 1][j] == 1) {
                    grid[i - 1][j] = 2;
                    freshOranges--;
                    queue.add(new int[]{i - 1, j});
                }

                if (j > 0 && grid[i][j - 1] == 1) {
                    grid[i][j - 1] = 2;
                    freshOranges--;
                    queue.add(new int[]{i, j - 1});
                }

                if (i < height - 1 && grid[i + 1][j] == 1) {
                    grid[i + 1][j] = 2;
                    freshOranges--;
                    queue.add(new int[]{i + 1, j});
                }

                if (j < width - 1 && grid[i][j + 1] == 1) {
                    grid[i][j + 1] = 2;
                    freshOranges--;
                    queue.add(new int[]{i, j + 1});
                }
            }

            nbDays++;
        }

        if (freshOranges > 0)
            return -1;

        return nbDays;
    }
}
