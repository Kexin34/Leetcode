// 解法一：（原始）采用2D DP array
// 从三角的顶部向下计算 （buttom - up method）
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        // 1、状态定义：f[i][j] 表示从0,0出发，到达i,j的最短路径
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // 2、初始化
        for (int i = 0; i < n; i++){
            for (int j = 0; j < triangle.get(i).size(); j++){
                dp[i][j] = triangle.get(i).get(j);
            }
        }
        // 递推求解
        for (int i = 1; i < n; i++){
            for (int j = 0; j < triangle.get(i).size(); j++){
                // 这里分为两种情况：
                // 1、上一层没有左边值
                // 2、上一层没有右边值
                if (j - 1 < 0)                                //最左边的cell，上一层没有左边值
                    dp[i][j] = dp[i - 1][j] + dp[i][j];
                else if (j == triangle.get(i).size() - 1)     // 最右边cell，上一层没有右边值
                    dp[i][j] = dp[i-1][j - 1] + dp[i][j];
                else
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + dp[i][j];
            }
        }
        //检查最后一行 -> 找出最小dp元素
        int result = dp[n - 1][0];
        for (int i = 1; i < n; i++) 
            result = Math.min(result, dp[n-1][i]);
    
        return result;
    }
}
// faster than 43.70% of Java


// 解法二：上面的优化，采用滚动数组
// 从三角的顶部向下计算 （buttom - up method）
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // corner case
        if(triangle == null || triangle.size() == 0) return 0;
        
        // dp[i] 代表着min total from buttom到当前col pisition
        int m = triangle.size();
        int n = triangle.get(m - 1).size();// last row size
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        
        // 自上而下 induction rule
        // 下面一个dp值 = 上面两个(左、中）中较小的一个的dp值 + 本层这个自身value
        // dp[j] = min(dp[j-1], dp[j]) + curVal
        for (int i = 1; i < m; i++){                  //从第二行开始
            List<Integer> cur = triangle.get(i);

            for (int j = cur.size() - 1; j >= 0; j--){//从右向左
                if (j == 0)                     // 最左边数字, 只对应正上面一个值
                    dp[0] = dp[0] + cur.get(j);
                else if (j == cur.size() - 1)   // 最右边数字，只对应一个左上面的值
                    dp[j] = dp[j - 1] + cur.get(j);
                else
                    dp[j] = Math.min(dp[j - 1], dp[j]) + cur.get(j); 
            }
        }
        int min = Integer.MAX_VALUE;
        //检查最后一行 -> 找出最小dp元素
        for (int i = 0; i < n; i++)
            min = Math.min(min, dp[i]);
        return min;
    }
}
//98.99% of Java 



// 解法三：DP，从最底层往上面计算
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        // dp[i]代表着min total from bottom to current position
        int m = triangle.size();
        int n = triangle.get(m - 1).size(); //最后一行大小 = 列
        int[] dp = new int[n];

        // 把triangle最后一行复制到dp
        for (int i = 0; i < n; i++)
            dp[i] = triangle.get(m - 1).get(i);
        
        // 从下到上，induction rule (n-2为倒数第二行)
        // (上面一个dp值 = 下面两个中较小的一个的dp值+上面自身value)
        // dp[i] = min(dp[i], dp[i + 1]) + currVal
        for (int i = n - 2; i >= 0; i--){
            List<Integer> cur = triangle.get(i);
            for (int j = 0; j < cur.size(); j++)
                dp[j] = Math.min(dp[j], dp[j + 1]) + cur.get(j);
        }
        
        return dp[0];
    }
}

// faster than 98.99% of Java



