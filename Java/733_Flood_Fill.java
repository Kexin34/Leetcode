// 解法：DFS
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (newColor == image[sr][sc]) return image;
        helper(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    public void helper(int[][] image, int i, int j, int originColor, int newColor){
        int m = image.length;
        int n = image[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || image[i][j] != originColor) return;
        image[i][j] = newColor;
        for (int[] dir : dirs)
            helper(image, i + dir[0], j + dir[1], originColor, newColor);
    }
}
// faster than 100.00% of Java



// 解法：BFS （重点在于怎么存这个int[i][j]到队列里面）
// 放到queue的同时就把颜色给换了
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = image.length;
        int n = image[0].length;
        int originColor = image[sr][sc];
        
        image[sr][sc] = newColor;       // 连续两次漏了最关键一行！
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int[] dir : dirs){
                int newR = cur[0] + dir[0];
                int newC = cur[1] + dir[1];
                if (newR < 0 || newR >= m || newC < 0 || newC >= n 
                    || image[newR][newC] != originColor)
                    continue;
                image[newR][newC] = newColor;
                queue.offer(new int[] {newR, newC});
            }
        }
        return image;
    }
} 
// faster than 69.98% of Java







// 解法：（没有太大必要用backtracking）
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int origColor = image[sr][sc];
        fill(image, sr, sc, origColor, newColor);
        return image;
    }
    
    void fill(int[][] image, int x, int y, int origColor, int newColor) {
        // 出界：超出数组边界
        if(!inArea(image, x, y)) return;
        // 碰壁：遇到其他颜色，超出 origColor 区域
        if(image[x][y] != origColor) return;
        // 已探索过的 origColor 区域
        if(image[x][y] == -1) return;
        
        // choose：打标记，以免重复
        image[x][y] = -1;
        fill(image, x, y + 1, origColor, newColor);
        fill(image, x, y - 1, origColor, newColor);
        fill(image, x - 1, y, origColor, newColor);
        fill(image, x + 1, y, origColor, newColor);
        // unchoose：将标记替换为 newColor
        image[x][y] = newColor;
    }
    
    boolean inArea(int[][] image, int x, int y) {
        return x >= 0 && x < image.length
            && y >= 0 && y < image[0].length;
    }
}

//faster than 100.00% of Java