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
/*
小结
	常见处理方式是给 0 位置占位，这样处理问题时一视同仁，初始化则在原来基础上 length+1，返回结果 f[n]
		状态可以为前 i 个
		初始化 length+1
		取值 index = i-1
		返回值：f[n]或者 f[m][n]
*/

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



// 132. Palindrome Partitioning II
// 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 返回符合要求的最少分割次数。
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isP = new boolean[n][n];
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++){
            dp[i] = i;
            for (int j = 0; j <= i; j++){   //用 j 遍历区间 [0, j]
                // 验证的是区间 [j, i] 内的子串是否为回文串
                if(s.charAt(i) == s.charAt(j) && (i - j < 2 || isP[j + 1][i - 1])){
                    isP[j][i] = true;
                    dp[i] = (j == 0) ? 0 : Math.min(dp[i], dp[j - 1] + 1);
                }
                    
            }
        }
        return dp[n - 1];
    }
}

// 注意点:
// 判断回文字符串时，可以提前用动态规划算好(isP)，减少时间复杂度


// 300. Longest Increasing Subsequence
// 给定一个无序的整数数组，找到其中最长上升子序列的长度。
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0 || nums == null) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int result = dp[0];
        for (int i = 1; i < n; i++) 
            result = Math.max(result, dp[i]);
        return result;
    }
}


// 139. Word Break
// 给定一个非空字符串s和一个包含非空单词列表的字典wordDict，判定s是否可以被空格拆分为一个或多个在字典中出现的单词。
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
      
        for (int i = 1; i <= n; i++){   // 对背包的迭代应放在外层
            for (String word : wordDict){   // 对物品的迭代应放在里层
                int len = word.length();
                if (len <= i && word.equals(s.substring(i - len, i)))
                    // 如果当前word在区间[i-len,i)里面,也要保证当前区间其他部分[0,i-len)也符合
                    dp[i] = dp[i] || dp[i - len];
            }
        }
        return dp[n];
    }
}





///////////////////////Two Sequences DP（40%）//////////////////////////////////


// 1143. Longest Common Subsequence
// 给定两个字符串text1和text2，返回这两个字符串的最长公共子序列。
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
         // dp[i][j] a前i个和b前j个字符最长公共子序列
        int[][] dp = new int[n1 + 1][n2 + 1];
        
        for (int i = 1; i <= n1; i++){
            for (int j = 1; j <= n2; j++){
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i -1][j], dp[i][j - 1]);
            }
        }
        return dp[n1][n2];
    }
}

// 72. Edit Distance
// 相等则不需要操作，否则取删除、插入、替换最小操作次数的值+1
class Solution {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        // 如果其中一个是空串
        if (n1 * n2 == 0) return n1 + n2; 
        int[][] dp = new int[n1 + 1][n2 + 1];
        
        // 边界初始化
        for (int i = 1; i <= n1; i++)
            dp[i][0] = i;   // 空串与word1前i个的距离
        for (int j = 1; j <= n2; j++)
            dp[0][j] = j;   // 空串与word2前j个的距离
    
        for (int i = 1; i <= n1; i++){
            for (int j = 1; j <= n2; j++){
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    // 本来就相等，不需要任何操作
                    dp[i][j] = dp[i - 1][j - 1];
                else// 否则取删除、插入、替换最小操作次数的值+1
                    dp[i][j] = 1 + Math.min(
                                dp[i - 1][j - 1],       // 替换
                                Math.min(dp[i - 1][j],  // 删除 
                                         dp[i][j - 1]));// 插入
            }
        }
        return dp[n1][n2];
    }
}






////////////////////////////////零钱和背包（10%）////////////////////////
// 322. Coin Change
// 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
// 如果没有任何一种硬币组合能组成总金额，返回  -1。
	// 状态 dp[i]表示金额为i时，组成的最小硬币个数
    // 推导 dp[i]  = min(dp[i-1], dp[i-2], dp[i-5])+1, 前提 i-coins[j] > 0
    // 初始化为最大值 dp[i]=amount+1
    // 返回值 dp[n] or dp[n]>amount =>-1
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[]dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);        // 初始化为最大值
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++){
            for (int coin : coins){
                if (i - coin >= 0)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        if (dp[amount] > amount) return -1;
        return dp[amount]; 
    }
}


// backpack
// 在 n 个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为 m，每个物品的大小为 A[i]
	// f[i][j] 前i个物品，是否能装j
    // f[i][j] =f[i-1][j] f[i-1][j-a[i] j>a[i]
    // f[0][0]=true f[...][0]=true
    // f[n][X]
class Solution {
	public backpack(int m, int[] A){
		int n = A.length;
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[0][0] = true;
		for (int i = 1; i <= n; i++){	//外圈是物品
			for (int j = 1; j <= m; j++){
				dp[i][j] = d[i][j - 1];
				if (j - A[i] >= 0 && dp[i - 1][j - A[i - 1]])
					dp[i][j] = true;
			}
		}
		for (int i = m; i >= 0; i--){
			if (dp[n][i]) return i;
		}
		return 0;
	}
}



// backpack-ii
// 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值. 
// 问最多能装入背包的总价值是多大?
//思路：f[i][j] 前 i 个物品，装入 j 背包 最大价值
class Solution {
	public backpack(int m, int[] A, int[] V){
	// f[i][j] 前i个物品，装入j背包 最大价值
    // f[i][j] =max(f[i-1][j] ,f[i-1][j-A[i]]+V[i]) 是否加入A[i]物品
    // f[0][0]=0 f[0][...]=0 f[...][0]=0
		int n = A.length;
		int[][]dp = new int[n + 1][m + 1];
		dp[0][0] = 0;
		for (int i = 1; i <= n; i++){
			for (int j = 0; j <= n; j++){
				dp[i][j] = d[i - 1][j];
				if (j - A[i - 1] >= 0)			//可以放得下这个物品(i-1)
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
			}
		}
		return dp[n][m];
	}
}










