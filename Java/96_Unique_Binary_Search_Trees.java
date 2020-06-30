//直接改写Q95,返回数字
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