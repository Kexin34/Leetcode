//直接改写Q95,返回数字， 暴力
class Solution {
    public int numTrees(int n) {
        if(n == 0) return 0;
        return getAns(1, n);
    }
    private int getAns(int start, int end) { 
        int ans = 0;
        /* 此时没有数字，只有一个数字,返回 1 */
        if (start >= end) 
            return 1;
        
        /* 尝试每个数字作为根节点 */
        for (int i = start; i <= end; i++) {
            //得到所有可能的左子树
            int leftTreesNum = getAns(start, i - 1);
            //得到所有可能的右子树
            int rightTreesNum  = getAns(i + 1, end);
            //左子树右子树两两组合
            ans += leftTreesNum * rightTreesNum;
        }
        return ans;
    }
}
//faster than 5.02% of Java


// 优化上面(只关心数量，所以不需要具体的范围)
// 同时使用 memoization，把递归过程中求出的解保存起来，第二次需要的时候直接拿即可
class Solution {
    public int numTrees(int n) {
        if(n == 0) return 0;
        HashMap<Integer,Integer> memoization = new HashMap<>();
        return getAns(n, memoization);
    }
    
    private int getAns(int n, HashMap<Integer,Integer> memoization) { 
        if(memoization.containsKey(n))
            return memoization.get(n);
        int ans = 0;
        /* 此时没有数字或者只有一个数字,返回 1 */
        if (n == 0 || n ==1) 
            return 1;
        
        /* 尝试每个数字作为根节点 */
        for (int i = 1; i <= n; i++) {
            //得到所有可能的左子树
            int leftTreesNum = getAns(i - 1, memoization);
            //得到所有可能的右子树
            int rightTreesNum  = getAns(n - i, memoization);
            //左子树右子树两两组合
            ans += leftTreesNum * rightTreesNum;
        }
        memoization.put(n, ans);
        return ans;
    }
}
// faster than 100.00% of Java





// 解法：DP，利用Catalan Numbe
// ## Basic Ideas: dynamic programming
// ##
// ##    dp(i): count with i nodes
// ##
// ##     There must be one root node
// ##
// ##        dp(j)*dp(i-1-j)
// ##           j = 0...i-1

// Complexity: Time O(n*n), Space O(1)

//       Pitfalls: try to compare the values. This direction will make things very complicated
//
//       How to get f(n) from previous values?
//           1. We will have a root, so sum(left sub-tree nodes) + sum(right sub-tree nodes) = n-1
//           2. If root is i, left sub-tree will have i-1 nodes, right sub-tree will have n-k nodes.
//                  How many different types? f(i-1)*f(n-i)
//           3. Loop k from 1 to n. Then collect the total number
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            for (int j = 0; j < i; j++){
                dp[i] += dp[j] * dp[i - j - 1]; 
            }
        }
        return dp[n];
    }
}
// faster than 100.00% of Java

