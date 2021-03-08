// 解法一：利用set去除重复，需要优化
// 
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();     // 用set去除duplicates
        if (nums.length == 0) return new ArrayList<>(res);
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) 
                	res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                else if (sum > 0) right--;
                else if (sum < 0) left++;
            }
        }
        return new ArrayList<>(res);
    }
}
// Runtime: 465 ms, faster than 20.60% of Java
// Time complexity: O(n^2)
// Explanation: Sorting takes O(nlogn) & 'for' loop and 'while' loop takes O(n^2) together. But since O(n^2) > O(nlogn). Therefore, O(n^2).
// Space complexity: O(n)
// Explanation: As the total elements are n and the space complexity will be some factor of n. Therefore, after removing constant, we are left with O(n).


// 需要优化：We're building many tuples (ArrayLists) that the alternative solution doesn't need to build at all.



// 优化：同样思路，没有用set，而是在while判断中去除重复

public List<List<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    List<List<Integer>> res = new LinkedList<>(); 
    for (int i = 0; i < num.length-2; i++) {
        if (i == 0 || (i > 0 && num[i] != num[i-1])) {
            int lo = i+1, hi = num.length-1, sum = 0 - num[i];
            while (lo < hi) {
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    while (lo < hi && num[lo] == num[lo+1]) lo++;
                    while (lo < hi && num[hi] == num[hi-1]) hi--;
                    lo++; hi--;
                } else if (num[lo] + num[hi] < sum) {
                    // improve: skip duplicates
                    while (lo < hi && num[lo] == num[lo+1]) lo++;
                    lo++;
                } else {
                    // improve: skip duplicates
                    while (lo < hi && num[hi] == num[hi-1]) hi--;
                    hi--;
                }
            }
        }
    }
    return res;
}
// Runtime: 23 ms, faster than 58.91% of Java 


