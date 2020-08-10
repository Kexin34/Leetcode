// DFS找到一个小岛，再用BFS一层层找到另一个小岛（最短距离）
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int m, n;
    
    public int shortestBridge(int[][] A) {
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;
        m = A.length;
        n = A[0].length;
        // DFS 找到其中一个小岛，把这个连通分量所有cell都设为2，且压入队列
        for (int i = 0; i < m && !found; i++){
            for (int j = 0; j < n && !found; j++){
                if (A[i][j] == 1){
                    dfs(A, i, j, queue);
                    found = true;
                }
            }
        }
        // BFS 一层层找另一个小岛from any nodes with color 2（2是指已发现的小岛）
        int steps = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){ // 处理本层所有cell，每个cell都向各方向走一步
                int[] cur = queue.poll();
                for (int[] dir : dirs){
                    int nextR = cur[0] + dir[0];
                    int nextC = cur[1] + dir[1];
                    if (nextR < 0 || nextC < 0 || nextR >= m || nextC >= n 
                        || A[nextR][nextC] == 2)
                        continue;
                    if (A[nextR][nextC] == 1)  return steps; //找到另一个小岛！
                    A[nextR][nextC] = 2;        // 现在设为2代表visited
                    queue.offer(new int[]{nextR, nextC});
                }
            }
            steps++;        // 层数增加
        }
        return -1;
    }
    
    public void dfs(int[][] A, int i, int j, Queue<int[]> queue){
        if (i < 0 || j < 0 || i >= m || j >= n || A[i][j] != 1) return;
        A[i][j] = 2;
        queue.offer(new int[]{i, j});
        for (int[] dir : dirs)
            dfs(A, i + dir[0], j + dir[1], queue);
    }
}
// faster than 97.26% of Java
// Time complexity: O(mn)
// Space complexity: O(mn)
