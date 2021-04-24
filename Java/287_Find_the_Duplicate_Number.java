// 二分模板, 找到第一个满足情况的：
// 满足情况：第一个 m that len(nums <= m) <= m
// 运用抽屉原理

class Solution {
    public int findDuplicate(int[] nums) {
        int l = 1, r = nums.length - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            int count = 0;  // len(nums <= m),计算<=m的元素个数
            for (int num: nums){
                if (num <= mid)
                    count++;
            }
            if (count <= mid)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return l;
    }
}
// Runtime: 2 ms, faster than 53.01% of Java 
// Time complexity: O(nlogn)
// Space complexity: O(1)



// Solution: 快慢指针寻找环的入口
// Convert the problem to find the entry point of the cycle in a linked list.
// Take the number in the array as the index of next node.
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (true){
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) 
                break;
        }
        fast = 0;
        while (fast != slow){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
// Runtime: 0 ms, faster than 100.00% of Java
// Time complexity: O(n)
// Space complexity: O(1)


