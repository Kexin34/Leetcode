// Dynamic Programming
class Solution {
    public int maxCoins(int[] nums) {
        int[] fullNums = new int[nums.length + 2]; // defensive coding
        fullNums[0] = 1;
        fullNums[nums.length + 1] = 1;
        for (int i = 1; i <= nums.length; ++i)
        	fullNums[i] = nums[i - 1];

        // store the potimise result as[begin][end]
        int[][] store = new int[fullNums.length][fullNums.length];
        // set defaul value as -1
        for (int i = 1; i < fullNums.length; ++i){
        	for (int j = i; j < fullNums.length; ++j)
        		store[i][j] = -1;
        }
        //*12345* length = 7 (1,5)(1, length - 2)
        return getStore(1, fullNums.length - 2, store, fullNums);
    }

    private int getStore(int begin, int end, int[][] store, int[] ful1){
    	// two coner cases
    	if (begin > end) return 0;
    	//if already visited: MEMORIZATION
        if (store[begin][end] != -1) return store[begin][end];

        for (int pos = begin; pos <= end; ++pos){
        	int leftCoin = getStore(begin, pos - 1, store, fullNums);
        	int midCoin = fullNums[begin - 1] * fullNums[pos] * fullNums[end +1];
        	int rightCoin = getStore(pos + 1, end, store, fullNums);
        	int coin = leftCoin + midCoin + rightCoin;
        	if (coin > store[begin][end])
        		store[begin][end] = coin;
        }
        return store[begin][end];
    }
}
//faster than 12.35% of Java



// 九章- O(N^3)

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];// 两边添加个1方便计算
        int[][] visited = new int[n + 2][n + 2];
        
        // 把原始数组转化为前后元素为1的数组
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 1; i <= n; i++)
            arr[i] = nums[i - 1];
        
        return search(arr, dp, visited, 1, n);
    }
    
    private int search(int[] arr, int[][] dp, int[][] visited, int left, int right){
        // memorization
        if (visited[left][right] == 1) 
            return dp[left][right];
        int res = 0;
        // dp[i][j] = max(dp[i][k-1] + dp[k+1][j] + midValue)
        for (int k = left; k <= right; ++k){
            int midValue = arr[left - 1] * arr[k] * arr[right + 1];
            int leftValue = search(arr, dp, visited, left, k - 1);
            int rightValue = search(arr, dp, visited, k + 1, right);
            res = Math.max(res, leftValue + midValue + rightValue);
        }
        visited[left][right] = 1;
        dp[left][right] = res;
        return res;
    }
}
// Runtime: 217 ms, faster than 5.32% of Java