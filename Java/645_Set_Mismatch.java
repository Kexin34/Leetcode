class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        //交换数组元素，使得数组上的元素在正确的位置上
        for(int i = 0; i < n; i++){
            while(nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]){
                swap(nums, i, nums[i] - 1);
            }
        }
        //找到重复那个数字，同时返回原本应该是下一个的数字(i+1)
        for(int i = 0; i < n; i++){
            if(nums[i] != i + 1){
                return new int[]{nums[i], i + 1};
            }
        }
        return null;
    }
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
//faster than 85.93% of Java

// Method 2: 
class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int dup = -1;
        for(int i = 0; i < n; i++){
            // 现在的元素是从 1 开始的
            int index = Math.abs(nums[i]) - 1;
            // nums[index] 小于 0 则说明重复访问
            if(nums[index] < 0)
                dup = Math.abs(nums[i]);
            else
                nums[index] *= -1;
        }
        
        int missing = -1;
        for(int i = 0; i < n; i++){
            if(nums[i] > 0)
                // nums[i] 大于 0 则说明没有访问
                // 将索引转换成元素
                missing = i + 1;
        }
        return new int[]{dup, missing};
    }
}
//faster than 85.93% of Java
