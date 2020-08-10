// 基础解法： DP
// dp[i]表示到数字nums[i]位置最大可整除的子集合的长度
// 一维数组parent[]，来保存上一个能整除的数字的位置
// mx和mx_idx分别表示最大子集合的长度和起始数字的位置
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>(){};
        int n = nums.length;
        Arrays.sort(nums);
        int[] dp = new int[n];
        int[] parent = new int[n];
        int mx = 0, mx_idx = 0;
        
        // 从后往前遍历数组，对于某个数字再遍历到末尾
        for (int i = n - 1; i >= 0; --i){
            for (int j = i; j < n; ++j) {
                if (nums[j] % nums[i] == 0 && dp[i] < dp[j] + 1) {//nums[j]能整除nums[i]
                    // 更新dp[i]和parent[i]
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                    // 更新 max
                    if (mx < dp[i]){
                        mx = dp[i];
                        mx_idx = i;
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < mx; i++){
            // 根据parent数组来找到每一个数字
            res.add(nums[mx_idx]);
            mx_idx = parent[mx_idx];
        }
        return res;
    }
}
// faster than 87.00% of Java
