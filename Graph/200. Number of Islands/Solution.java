class Solution {
    public int numIslands(char[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int numberIslands = 0;

        int[] queue = new int[height * width];

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (grid[row][column] != '1')
                    continue;

                numberIslands++;

                int front = 0;
                int back = 0;

                queue[back++] = row * width + column;
                grid[row][column] = '0';

                while (front < back) {
                    int position = queue[front++];

                    int currentRow = position / width;
                    int currentColumn = position % width;

                    if (currentRow > 0 &&
                        grid[currentRow - 1][currentColumn] == '1') {

                        grid[currentRow - 1][currentColumn] = '0';
                        queue[back++] =
                            (currentRow - 1) * width + currentColumn;
                    }

                    if (currentRow < height - 1 &&
                        grid[currentRow + 1][currentColumn] == '1') {

                        grid[currentRow + 1][currentColumn] = '0';
                        queue[back++] =
                            (currentRow + 1) * width + currentColumn;
                    }

                    if (currentColumn > 0 &&
                        grid[currentRow][currentColumn - 1] == '1') {

                        grid[currentRow][currentColumn - 1] = '0';
                        queue[back++] =
                            currentRow * width + currentColumn - 1;
                    }

                    if (currentColumn < width - 1 &&
                        grid[currentRow][currentColumn + 1] == '1') {

                        grid[currentRow][currentColumn + 1] = '0';
                        queue[back++] =
                            currentRow * width + currentColumn + 1;
                    }
                }
            }
        }

        return numberIslands;
    }
}
