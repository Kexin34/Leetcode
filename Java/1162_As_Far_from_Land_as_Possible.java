// BFS
// Put all land cells into a queue as source nodes and BFS for water cells, 
// the last expanded one will be the farthest.
// Time compleixty: O(n^2)
// Space complexity: O(n^2)

class Solution {
    public int maxDistance(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = grid.length;
        int n = grid[0].length;
        int ans = -1;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1)        //Put all land cells into a queue as source nodes
                    queue.offer(i * n + j);   
            }
        }
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int cur = queue.poll();
                int x = cur / n;
                int y = cur % n;
                if (grid[x][y] == 2)        // mean visited
                    ans = Math.max(ans, steps);
                for (int[] dir : dirs){
                    int tx = x + dir[0];
                    int ty = y + dir[1];
                    if (tx < 0 || tx >= m || ty < 0 || ty >= n || grid[tx][ty] != 0)//BFS for water cells, 
                        continue;
                    grid[tx][ty] = 2;
                    queue.offer(tx * n + ty);
                }
            }
            steps++;
        }
        return ans;
    }
}
// Runtime: 12 ms, faster than 80.55% of Java 

