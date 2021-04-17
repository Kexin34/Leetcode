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
        int[] height = new int[n];
        for(int i = 0; i < n; i++)
            height[i] = envelopes[i][1];
        
        return lengthOfLIS(height);
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



// 用DP来解：
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // 先把第一维排个序（从小到大）
        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] n1, int[] n2){
                return Integer.compare(n1[0], n2[0]);
            }
        });
        
        //再用第二维做LIS
        int n = envelopes.length;
        int[]dp = new int[n];
        int ans = 0;
        
        // 遍历每个信封
        for (int i = 0; i < n; i++){
            dp[i] = 1;   // 随便先赋一个值
            for (int j = 0; j < i; j++){
                // 如果当前i的宽高都比j大，考虑一下j，更新dp[i]
                if (envelopes[i][0] > envelopes[j][0]
                   && envelopes[i][1] > envelopes[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            // 当前i所能获得的最大套娃数是dp[i]，有可能是最大的
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
// Runtime: 243 ms, faster than 20.85% of Java
