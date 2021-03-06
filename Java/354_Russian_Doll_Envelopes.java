// DP, 变成LIS题目
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 按宽度升序排列，如果宽度一样，则按高度降序排列
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[]b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });
        
        // 对高度数组寻找 LIS
        int[] heights = new int[n];
        for (int i = 0; i < n; i++)
            heights[i] = envelopes[i][1];
        
        return lengthOfLIS(heights);
    }
    
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int max = 0;
        for (int num : dp)
            max = Math.max(max, num);
        return max;
    }
}
// Runtime: 149 ms, faster than 59.42% of Java 




// Follow-up: 时间复杂度为 O(NlogN)
class Solution {
    // envelopes = [[w, h], [w, h]...]
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 难点：排序，按宽度w升序排列，如果宽度一样，则按高度h降序排列
        Arrays.sort(envelopes, new Comparator<int[]>()
        {
            public int compare(int[] a, int[] b){
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }        
        });
        // 对高度数组寻找 LIS (在h数组上寻找最长递增子序列)
        int[] heights = new int[n];
        for (int i = 0; i < n; i++)
            heights[i] = envelopes[i][1];
        
        return lengthOfLIS(heights);
    }
    
    /* 二分查找解法, 返回 nums 中 LIS 的长度 */
    public int lengthOfLIS(int[] nums) {
        int piles = 0, n = nums.length;
        int[] top = new int[n];
        for (int i = 0; i < n; i++) {
            // 要处理的扑克牌
            int poker = nums[i];
            int left = 0, right = piles;
            // 二分查找插入位置
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] >= poker)
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶
            top[left] = poker;
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }
}
// faster than 83.15% of Java


