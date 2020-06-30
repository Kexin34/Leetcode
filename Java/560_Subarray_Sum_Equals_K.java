// 原始解法：前缀和
class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构造前缀和
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for(int i = 0; i < n ; i++)
            sum[i + 1] = sum[i] + nums[i];
        
        int ans = 0;
        // 穷举所有子数组
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){
                // sum of nums[j..i-1]
                if(sum[i] - sum[j] == k)
                    ans++;
            }     
        }
        return ans;
    }
}
// faster than 5.01% of Java 


// 优化解法：直接记录下有几个 sum[j] 和 sum[i] - k 相等，直接更新结果
// 借助哈希表去除不必要的嵌套循环，在记录前缀和的同时记录该前缀和出现的次数。
class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // map：前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer> preSum = new HashMap<>();
        // base case
        preSum.put(0, 1);
        
        int ans = 0;
        int sum0_i = 0;
        for(int i = 0; i < n; i++){
            sum0_i += nums[i];
            // 这是我们想找的前缀和 nums[0..j]
            int sum0_j = sum0_i - k;
            // 如果前面有这个前缀和，则直接更新答案
            if(preSum.containsKey(sum0_j))
                ans += preSum.get(sum0_j);
            // 把前缀和 nums[0..i] 加入并记录出现次数
            preSum.put(sum0_i,
                      preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return ans;
    }
}
// faster than 52.69% of Java 


