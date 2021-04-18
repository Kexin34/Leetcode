// 双指针
// two sum问题变形
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int bestResult = nums[0] + nums[1] + nums[2];
        
        for (int i = 0; i < n - 2; i++){
            int left = i + 1, right = n - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(bestResult - target))
                    bestResult = sum;
                if (sum < target)
                    left++;
                else
                    right--;
            }
        }
        return bestResult;
    }
}