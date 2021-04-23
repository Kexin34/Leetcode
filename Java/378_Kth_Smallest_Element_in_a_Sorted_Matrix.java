// 窗口类指针移动模板 + 最小堆， 解答在typora node

class Solution {
    
    class Pair {
        public int x, y, val;
        public Pair(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        int[][] dirs = {{1, 0}, {0, 1}}; // 向右，向下(col increase)
        int n = matrix.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(k, (a,b) -> a.val - b.val);//最小堆
        pq.add(new Pair(0, 0, matrix[0][0]));
        
        // 循环k - 1次
        for (int i = 0; i < k - 1; i++){
            Pair cur = pq.poll();
            for (int[] dir : dirs){
                int next_x = cur.x + dir[0];
                int next_y = cur.y + dir[1];
                if (next_x < n && next_y < n && !visited[next_x][next_y]){
                    visited[next_x][next_y] = true;
                    Pair next_Pair = new Pair(next_x, next_y, matrix[next_x][next_y]);
                    pq.add(next_Pair);
                }
            }
        }
        return pq.peek().val;
    }
}
// Runtime: 15 ms, faster than 45.00% of Java
// Time: O(klogk), k个logk操作（堆里k个元素）