class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 2;
        int i = k;

        for (int j = k; j < nums.length; j++){
            //检查元素at current index - k is the same as new arriving element
            if (nums[j] != nums[i - k])
                nums[i++] = nums[j];
        }
        return i;
    }
}

// 0 ms, faster than 100.00% of Java 