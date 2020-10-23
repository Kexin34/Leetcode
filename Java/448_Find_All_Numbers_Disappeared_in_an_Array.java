class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> res = new LinkedList<>();
        
        for (int i = 0; i < nums.length; i++)
            set.add(nums[i]);
        
        for (int i = 1; i <= nums.length; i++){
            if (!set.contains(i))
                res.add(i);
        }
        return res;
    }
}
// faster than 24.23% of Java