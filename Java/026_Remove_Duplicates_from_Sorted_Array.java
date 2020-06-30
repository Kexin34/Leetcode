// 双指针解法
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        int slow = 0, fast = 1;
        while(fast < n){
            if(nums[fast] != nums[slow]){//当fast遇到新元素
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 长度为索引 + 1
        return slow + 1;
    }
}

// faster than 100.00% of Java