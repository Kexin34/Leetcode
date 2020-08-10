// DP
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        for (int i = 0; i < len; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        // dp已经建立好，遍历找到最大的
        int ans = 0;
        for (int i = 0; i < dp.length; i++)
            ans = Math.max(ans, dp[i]);
        return ans;
    }
}
// faster than 62.23% of Java
// 时间复杂度 O(N^2)





//解法二：二分查找法（思维拓展，一般人想不到）
// 大致就是，二分查找的变体->搜索左侧边界
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        // 牌堆数初始化为 0
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            // i要处理的扑克牌
            int poker = nums[i];
            
            /***** 搜索左侧边界的二分查找 *****/
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            /*********************************/
            // 没找到合适的牌堆，新建一堆
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶
            top[left] = poker; 
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }
}