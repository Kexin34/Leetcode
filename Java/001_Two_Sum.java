class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> remain = new HashMap<>();
        // <its array_value, index>

        for(int i = 0; i < nums.length; i++){
            //判断hashmap中是否存在target-num[i]，有的话，返回这个index和i就为答案

            if(remain.containsKey(target - nums[i]))    // found!
                return new int[] {remain.get(target - nums[i]), i};
            else
                remain.put(nums[i], i);
        }
        return null;
    }
}
// Time : O(N), 每次哈希表的查询时间为 O(1)
// Space : O(N)