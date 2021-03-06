// 原始解法：前缀和
// Time complexity: O(n^2)
// Space complexity: O(n)
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
// Time complexity: O(n)
// Space complexity: O(n)
class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        int count = 0;
        int cur_sum = 0;
        preSum.put(0, 1); // sum为0的preFix有一个
        
        for (int num : nums){
            // cur_sum[0]...[i]的总和
            cur_sum += num;
            
            // check how many arrays nums[0:j] (j < i) that has sum (cur_sum – k),
            // then there are the same #arrays nums[j+1: i目前] that have sum k.
            count += preSum.getOrDefault(cur_sum - k, 0);
            preSum.put(cur_sum, preSum.getOrDefault(cur_sum, 0) + 1);
        }
        return count;
    }
}
// Runtime: 17 ms, faster than 96.12% of Java

//比较啰嗦的解法
class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // map：前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer> preSum = new HashMap<>();
        
        int ans = 0;
        int sum0_i = 0;
        for(int i = 0; i < n; i++){
            //计算最新前缀和 nums[0..i] 
            sum0_i += nums[i];
            
            // 如果当前前缀和等于目标，立刻更新count
            if (sum0_i == k)
                ans++;
            
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
//  faster than 73.46% of Java


