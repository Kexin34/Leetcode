/*

动态规划就是把大问题变成小问题，并解决了小问题重复计算的方法称为动态规划

动态规划和 DFS 区别:
	像二叉树 子问题是没有交集，所以大部分二叉树都用递归或者分治法，即 DFS，就可以解决
	像 triangle 这种是有重复走的情况，子问题是有交集，所以可以用动态规划来解决

递归和动规关系：
	递归是一种程序的实现方式：函数的自我调用
	动态规划：是一种解决问题的思想，大规模问题的结果，是由小规模问题的结果运算得来的。
		动态规划可用递归来实现(Memorization Search)

使用场景
	满足两个条件：
		1. 满足以下条件之一
			求最大/最小值（Maximum/Minimum ）
			求是否可行（Yes/No ）
			求可行个数（Count(*) ）
		2. 满足不能排序或者交换（Can not sort / swap ）

四点要素
	1. 状态 State
		灵感，创造力，存储小规模问题的结果
	2. 方程 Function
		状态之间的联系，怎么通过小的状态，来算大的状态
	3. 初始化 Intialization
		最极限的小状态是什么, 起点
	4. 答案 Answer
		最大的那个状态是什么，终点

常见四种类型
	Matrix DP (10%)
	Sequence (40%)
	Two Sequences DP (40%)
	Backpack (10%)

注意点：贪心算法大多题目靠背答案，所以如果能用动态规划就尽量用动规，不用贪心算法


*/

// 120. Triangle
// 1. 从三角的顶部向下计算 （buttom - up method）
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

// 从最底层往上面计算
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




/////////////////////1、矩阵类型（10%）//////////////////////////////////
// 64. Minimum Path Sum
// 给定一个包含非负整数的  m x n  网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
/* 思路：动态规划 
 * 1、state: dp[x][y]从起点走到 x,y 的最短路径 
 * 2、function: dp[x][y] = min(dp[x-1][y], dp[x][y-1]) + A[x][y] 
 * 3、intialize: dp[0][0] = A[0][0]、dp[i][0] = sum(0,0 -> i,0)、 dp[0][i] = sum(0,0 -> 0,i) 
 * 4、answer: dp[n-1][m-1]
*/
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return -1;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];      // 初始化
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i == 0 && j == 0) continue;
                if (i == 0)
                    dp[0][j] = dp[0][j - 1] + grid[i][j];
                else if (j == 0)
                    dp[i][0] = dp[i - 1][0] + grid[i][j];
                else
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}


// 62. Unique Paths
// 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
// 问总共有多少条不同的路径？
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // put number of paths equal to 1 for the first row and the first column. 
        for (int[] arr : dp)
            Arrays.fill(arr, 1);
        for (int r = 1; r < m; r++)
            for(int c = 1; c < n; c++)
                dp[r][c] = dp[r][c - 1] + dp[r - 1][c];
        return dp[m-1][n-1];
    }
}
// 2. 一维DP
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}



// 63. Unique Paths II
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; i++){
            if (obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
                break;
            }else
                dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++){
            if (obstacleGrid[0][j] == 1){
                dp[0][j] = 0;
                break;
            }else
                dp[0][j] = 1;
        }
        
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}






/////////////////////////////2、序列类型（40%）///////////////////////////
// 70. Climbing Stairs
// 假设你正在爬楼梯。需要  n  阶你才能到达楼顶。
class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i = 3; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }
}
// 2. DP 滚动数组优化
class Solution {
    public int climbStairs(int n) {
        // dp[i] 只与 dp[i - 1] 和 dp[i - 2] 
        // base case
        if (n <= 2) return n;
        int pre2 = 1;       // dp[1] = 1;
        int pre1 = 2;       // dp[2] = 2;
        int cur = 0;
        
        for (int i = 3; i <= n; i++){
            cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}


// 55. Jump Game
// 给定一个非负整数数组，你最初位于数组的第一个位置。 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
// 判断你是否能够到达最后一个位置。
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;
        int[] dp = new int[n];  // dp[i] 表示达到i位置时剩余的跳力
        
        for (int i = 1; i < n; i++){
            // 状态转移方程
            dp[i] = Math.max(dp[i - 1], nums[i - 1]) - 1;
            // 如果当某一个时刻 dp 数组的值为负了，说明无法抵达当前位置
            if (dp[i] < 0) return false;
        }
        return true;
    }
}


// 45. Jump Game II
// 给定一个非负整数数组，你最初位于数组的第一个位置。 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
// 动态规划+贪心优化
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int steps = 0;
        int i = 0;
        int cur = 0;            // 当前的能到达的最远位置
        
        while (cur < n - 1){    // 未到达末尾index时继续循环
            steps++;
            int pre = cur;      // 表示上一次循环后能到达的最远位置
            
            //当前位置i <= pre，说明还是在上一跳能到达的范围内
            for (; i <= pre; i++)
                cur = Math.max(cur, i + nums[i]);
        }
        return steps;
    }
}











