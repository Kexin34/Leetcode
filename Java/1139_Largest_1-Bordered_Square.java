// 1. Count the number of consecutive 1s on the top and on the left.
// 2. From length of edge l = min(m,n) to l = 1, check if the 1-bordered square exist.
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int [][] left = new int[m][n];
        int [][] top = new int[m][n];
        
        //Count the number of consecutive 1s on the top and on the left.
        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (grid[i][j] > 0){
                    //遍历每个cell，如果某cell为1，看是否为最左column，是的话left[i][j]设为1
                    // 不是最左col的话，left[i][j]设为left[i][j - 1] + 1 (DP) ，
                    left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
                    top[i][j] = i > 0  ? top[i - 1][j] + 1 : 1;  // 同理top[i][j]
                }
            }
        }
        // new we get the number of continuous 1s for each row and col and store them in 
        // top[][] and left[][] respectively.
        
        // The number of continuous 1s is actually the length of the square we are looking for.
        // we need to know how many 1s there and if they can form a square. 
        // For each row, top[i+r-1][j],top[i+r-1][j+r-1],left[i][j+r-1],left[i+r-1][j+r-1] 
        // are the places to confirm if the 1s are continuous. If their values >= the value of the row, 
        // we know the acreage will be the square of the number of that row.
        
        // From length of edge l = min(m,n) to l = 1（从最长边界长到1）
        // 遍历每个cell检查看the 1-bordered square是否存在，如果有符合，最后返回 len^2，没有返回0
        for (int len = Math.min(m, n); len > 0; --len){
            for (int i = 0; i < m - len + 1; ++i)
                for (int j = 0; j < n - len + 1; ++j)
                    //上下左右加起来和row对比，看有无连续的1s
                    if (top[i + len - 1][j] >= len          
                       && top[i + len - 1][j + len - 1] >= len
                       && left[i][j + len - 1] >= len
                       && left[i + len - 1][j + len - 1] >= len)
                        return len * len;
        }
        return 0;
    }
}
// faster than 84.98% of Java
// Time O(N^3)
// Space O(N^2)

