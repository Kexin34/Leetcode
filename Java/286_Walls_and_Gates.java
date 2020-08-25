// 解法一：DFS
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int m, n;
    
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        m = rooms.length;
        n = rooms[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (rooms[i][j] == 0)
                    dfs(rooms, i, j, 0);
            }
        }
    }
    
    public void dfs(int[][] rooms, int i, int j, int deepth){
        // 先判定是否合法, 如果遇到的cell值小于当前深度要跳过 (不碰-1 cell)
        if (i < 0 || j < 0 || i >= m|| j >= n || rooms[i][j] < deepth)
            return;
        // 如果遇到的值大于当前深度值，将位置值赋为当前深度值，并对当前点的四个相邻点开始DFS遍历
        rooms[i][j] = deepth;
        for (int[] dir : dirs)
            dfs(rooms, i + dir[0], j + dir[1], deepth + 1);
    }
}

//  faster than 93.54% of Java



// BFS
// Push all gates into queue first. Then for each gate update its 
//      neighbor cells and push them to the queue.
//Repeating above steps until there is nothing left in the queue.

class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int m, n;
    
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        m = rooms.length;
        n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // Push all gates into queue first. 
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (rooms[i][j] == 0)
                    queue.add(new int[] {i, j});
            }
        }
        while (!queue.isEmpty()){
            int[] cur = queue.remove();
            int i = cur[0], j = cur[1];
            for (int[] dir : dirs){
                int nextR = i + dir[0];
                int nextC = j + dir[1];
                if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n 
                    || rooms[nextR][nextC] < rooms[i][j] + 1)
                    // 这里改成rooms[nextR][nextC] != Integer.MAX_VALUE 也可以
                    continue;
                rooms[nextR][nextC] = rooms[i][j] + 1;
                queue.add(new int[] {nextR, nextC});
            }
        }
    }
}
// faster than 66.26% of Java
// O(mn) Time