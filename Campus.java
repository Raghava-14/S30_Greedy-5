/*
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.

Example:

Input:
workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: 6
Explanation:
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6

*/

//Time = 
//Space = O(mn)

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // Get the lengths of the input arrays
        int m = workers.length, n = bikes.length;
        
        // Initialize an array of lists to store the pairs of worker-bike distances for each worker
        List<int[]>[] dist = new List[m];
        for (int i = 0; i < m; i++) {
            dist[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Calculate the Manhattan distance between the current worker and bike and add it to the list
                int distance = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                dist[i].add(new int[]{distance, i, j});
            }
            // Sort the list in ascending order of distance
            Collections.sort(dist[i], (a, b) -> a[0] - b[0]);
        }
        
        // Initialize arrays to keep track of which workers and bikes have been assigned
        boolean[] assignedWorker = new boolean[m];
        boolean[] assignedBike = new boolean[n];
        
        // Initialize an array to store the assigned bike for each worker
        int[] ans = new int[m];
        
        // Assign bikes to workers in ascending order of distance
        int assigned = 0;
        for (int d = 0; assigned < m; d++) {
            for (int i = 0; i < m; i++) {
                if (assignedWorker[i]) continue;
                int j = dist[i].get(d)[2];
                if (assignedBike[j]) continue;
                ans[i] = j;
                assignedWorker[i] = true;
                assignedBike[j] = true;
                assigned++;
            }
        }
        
        return ans;
    }
}
