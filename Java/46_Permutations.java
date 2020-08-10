// DFS + backtracking
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        
        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_list = new ArrayList<Integer>();
        for (int num : nums)        //把input里的数字单独分别放入num_lst中
            nums_list.add(num);
        
        int len = nums.length;
        backtrack(len, nums_list, result, 0);
        return result;
    }
    
    public void backtrack(int len, 
                         ArrayList<Integer> nums,
                         List<List<Integer>> result,
                         int first){
         //if all integers in this turn are used up, add it into output list
        if (first == len)
            result.add(new ArrayList<Integer>(nums));
        
        for (int i = first; i < len; i++){
            // place i-th integer first in the current permutation
            Collections.swap(nums, first, i);
            // use next integers to complete the permutations
            backtrack(len, nums, result, first + 1);
            //backtrack
            Collections.swap(nums, first, i);
        }
    }
}
//faster than 61.86% of Java 
//Time complexity: O(n!)
//Space complexity: O(n)