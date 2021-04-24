// 九章强化 - heap
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        // min heap
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>((a, b) -> (a.h - b.h));
        int[][] visited = new int[m][n];
        
        // 把矩阵四周的cell全部都放入pa
        for (int i = 0; i < m; i++){
            pq.offer(new Cell(i, 0, heightMap[i][0]));  //第一列
            pq.offer(new Cell(i, n - 1, heightMap[i][n - 1])); //最后一列
            visited[i][0] = 1;
            visited[i][n - 1] = 1;
        }
        for (int j = 0; j < n; j++){
            pq.offer(new Cell(0, j, heightMap[0][j]));  //第一行
            pq.offer(new Cell(m - 1, j, heightMap[m - 1][j])); //最后一行
            visited[0][j] = 1;
            visited[m - 1][j] = 1;
        }
        int res = 0;
        
        while (!pq.isEmpty()){
            // 因为从最低点灌水，所以poll出pq最小的值
            Cell cur = pq.poll();
            for (int[] dir: dirs){
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny] == 1)
                    continue;
                // 如果合法，把当前位置放入pq，高度选取max(cur, h[nx][ny])
                visited[nx][ny] = 1;
                pq.offer(new Cell(nx, ny, Math.max(cur.h, heightMap[nx][ny])));
                res += Math.max(0, cur.h - heightMap[nx][ny]);
            }
        }
        return res;
    }
    
    class Cell{
        public int x, y, h;
        Cell(){}
        Cell(int _x, int _y, int _h){
            this.x = _x;
            this.y = _y; 
            this.h = _h;
        }
    }
}
// Runtime: 17 ms, faster than 28.78% of Java
// Time: O(mn*logmn), 之前遍历行列是mn，之后堆里面操作是logmn