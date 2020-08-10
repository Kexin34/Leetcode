class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int res = 0;
        int max_len = 0;
        int[] LIS = new int[n];
        int[] cnt = new int[n];
        
        
        for (int i = 0; i < n; i++){
            LIS[i] = cnt[i] = 1;
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]){
                    if (LIS[i] == LIS[j] + 1) cnt[i] += cnt[j];
                    if (LIS[i] < LIS[j] + 1){
                        LIS[i] = LIS[j] + 1;
                        cnt [i] = cnt[j];
                    }
                }
            }
            if (max_len == LIS[i]) res += cnt[i];
            if (max_len < LIS[i]){
                max_len = LIS[i];
                res = cnt[i];
            }
        }
        return res;
    }
}

// faster than 32.31% of Java 

