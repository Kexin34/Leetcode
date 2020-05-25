class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexForNum = new HashMap<>();
        // <array_value, index>

        for(int i = 0; i < nums.length; i++){
            //判断hashmap中是否存在target-num[i]，有的话，返回这个index和i就为答案
            if(indexForNum.containsKey(target - nums[i])){
                return new int[] {indexForNum.get(target - nums[i]), i};
            }else{
                indexForNum.put(nums[i], i);
            }
        }
        return null;
    }
}