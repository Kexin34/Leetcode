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