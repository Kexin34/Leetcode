// binary search

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // <=理由：如果target比最右元素都大，left在等于right后继续加一再终止，返回正确index
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (target == nums[mid])
                return mid;
            else if (target < nums[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
        
    }
}
//Time complexity : O(logN).//master theorem
// Space: O(1)