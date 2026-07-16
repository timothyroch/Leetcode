import java.util.Arrays;

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int numPoints = points.length;

        boolean[] visited = new boolean[numPoints];
        int[] minimumDistance = new int[numPoints];

        Arrays.fill(minimumDistance, Integer.MAX_VALUE);
        minimumDistance[0] = 0;

        int totalDistance = 0;

        for (int count = 0; count < numPoints; count++) {
            int nextPoint = -1;

            for (int i = 0; i < numPoints; i++) {
                if (!visited[i] &&
                    (nextPoint == -1 ||
                     minimumDistance[i] < minimumDistance[nextPoint])) {
                    nextPoint = i;
                }
            }

            visited[nextPoint] = true;
            totalDistance += minimumDistance[nextPoint];

            for (int i = 0; i < numPoints; i++) {
                if (!visited[i]) {
                    int distance =
                        Math.abs(points[nextPoint][0] - points[i][0])
                        + Math.abs(points[nextPoint][1] - points[i][1]);

                    minimumDistance[i] =
                        Math.min(minimumDistance[i], distance);
                }
            }
        }

        return totalDistance;
    }
}
