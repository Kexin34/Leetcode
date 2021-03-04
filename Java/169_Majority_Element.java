// 解法1：哈希表
// Hash table O(n) / O(n)
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        
        for (int num : map.keySet()){
            if (map.get(num) > nums.length / 2)
                return num;
        }
        return -1;
    }
}
// Runtime: 8 ms, faster than 40.87% of Java

// 解法2：BST
// BST O(nlogk) / O(n)
// 和上面唯一区别是先sort array再计算
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        
        for (int num : map.keySet()){
            if (map.get(num) > nums.length / 2)
                return num;
        }
        return -1;
    }
}


// 解法：Moore Voting 
// O(n) / O(1)
class Solution {
    public int majorityElement(int[] nums) {
        int majority = nums[0];
        int count = 0;
        for (int num: nums){
            // 当前和major一直，增加计数
            if (num == majority) count++;
            // 不一致并且(count-- == 0)说明上个数cnt=1，reset majority & count
            else if (count-- == 0){
                count = 1;
                majority = num;
            }
        }
        return majority;
    }
}
// Runtime: 1 ms, faster than 99.89% of Java



// 解法：Divide and conquer 
// O(nlogn) / O(logn)
class Solution {
    public int majorityElement(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    public int helper(int[] nums, int lo, int hi){
        // base case: size == 1
        if (lo == hi) return nums[lo];
        int m = lo + (hi - lo) / 2;
        // 递归找出左右halves的答案（最常见元素）
        int left = helper(nums, lo, m);
        int right = helper(nums, m + 1, hi);
        
        //  If left & right halves agree on the majority element
        if (left == right) return left;
        
        // otherwise，count the occurrences of the left and right majority elements
        int leftCount = count(nums, left, lo, hi);
        int rightCount = count(nums, right, lo, hi);
        // 对比左右半答案的最大次数，determine which subslice's answer is globally correct
        return leftCount > rightCount ? left : right;
        
    }
    // 给出一段range of array，在这里计算出num出现次数
    private int count(int[] nums, int num, int lo, int hi){
        int count = 0;
        for (int i = lo; i <= hi; i++)
            if (nums[i] == num) count++;
        return count;
    }
}
// Runtime: 1 ms, faster than 99.89% of Java




// 解法：Full sorting 
// O(nlogn) / O(1)
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
// Runtime: 1 ms, faster than 99.89% of Java 