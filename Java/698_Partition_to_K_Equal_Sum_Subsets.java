// 基础思路：dfs + backtracking
// DFS: find a subset of nums[] which sum equals to sum/k
// visited[]: record which element in nums[] is used.

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (k <= 0 || sum % k != 0) return false; // sum必须是K整数倍
        boolean[] visited = new boolean[nums.length];
        return canPartition(nums, visited, k, 0, 0, sum/k);
    }
    
    // DFS + backtracking
    public boolean canPartition(int[] nums, boolean[] visited, int k, 
                                int startIndex, int curSum, int target){
        if (k == 1) return true;    //一直成功的话，k一直减到1，说明正确
        if (curSum == target) return canPartition(nums, visited, k - 1, 0, 0, target);
        
        for (int i = startIndex; i < nums.length; i++){
            if (visited[i]) continue;
            visited[i] = true;
            if (canPartition(nums, visited, k, i + 1, curSum + nums[i], target))
                return true;
            visited[i] = false;     // finish backtracking, resume back
                
        }
        return false;
    }
}
//  faster than 43.75% of Java 



// 上面的优化解法（剪枝, 只是添加了sort和dfs里面对比curSum和target）
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (k <= 0 || sum % k != 0) return false; // sum必须是K整数倍
        
        //优化：先从大到小sort
        Arrays.sort(nums);
        for(int i = 0; i < nums.length / 2; i++){
            int temp = nums[i];
            nums[i] = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = temp;
        }

        boolean[] visited = new boolean[nums.length];
        return canPartition(nums, visited, k, 0, 0, sum/k);
    }
    
    // DFS + backtracking
    public boolean canPartition(int[] nums, boolean[] visited, int k, 
                                int startIndex, int curSum, int target){
        if (k == 1) return true;    //一直成功的话，k一直减到1，说明正确
        if (curSum > target) return false;  // 优化：剪枝
        if (curSum == target) return canPartition(nums, visited, k - 1, 0, 0, target);
        
        for (int i = startIndex; i < nums.length; i++){
            if (visited[i]) continue;
            visited[i] = true;
            if (canPartition(nums, visited, k, i + 1, curSum + nums[i], target))
                return true;
            visited[i] = false;     // finish backtracking, resume back
                
        }
        return false;
    }
}
//  faster than 97.45% of Java
// Time complexity: O(n!)
// Space complexity: O(n)

