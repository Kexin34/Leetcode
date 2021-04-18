// twoSum变形，还是用双指针
// 虽然题目是四数之和，但是我们可以将他转换为三数之和，
// 再进一步就是二数之和，先进行稳定排序，然后我们准备用四个指针

// 先框定两个指针，再在这个基础上，用双指针解决问题
// 时间复杂度O(n^3)
// 空间复杂度 O(n^2)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 3; i++){
            if (i != 0 && nums[i] == nums[i - 1]) 
                continue;
            
            for (int j = i + 1; j < nums.length - 2; j++){
                if (j != i + 1 && nums[j] == nums[j - 1]) 
                    continue;

                int left = j + 1;
                int right = n - 1;
                
                while (left < right){
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target){
                        right--;
                    }else if (sum < target)
                        left++;
                    else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        res.add(list);
                        right--;
                        left++;
                        // 跳过重复元素
                        while (left < right && nums[left] == nums[left - 1])
                            left++;
                        while (left < right && nums[right] == nums[right + 1])
                            right--;
                    }
                }
            }
        }
        return res;
    }
}
// Runtime: 13 ms, faster than 75.20% of Java