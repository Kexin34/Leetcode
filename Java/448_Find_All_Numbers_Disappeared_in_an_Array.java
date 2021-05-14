// set解法
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


// Follow-up: in-place & in O(n) runtime
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int idx = nums[i] - 1;
            // swap 直到nums[i]是在nums[i] - 1的位置上（1在1-1=0的位置上）
            while (idx != nums[i]){
                swap(nums, idx, i);
                idx = nums[i] - 1;
                // if we are going to swap same number: break
                if (nums[idx] == nums[i])
                    break;
              }
         }
        // In this point, the array looks like this: [1,2,3,4,3,2,7,8]
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            if (i != nums[i] - 1)
                res.add(i + 1);
        }
        return res;
    }
    private void swap(int[] nums, int idx1, int idx2){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}
// Runtime: 5 ms, faster than 76.56% of Java