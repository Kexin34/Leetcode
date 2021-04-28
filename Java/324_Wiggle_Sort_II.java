// sorting
// O(nlogn) time and O(n) space
// https://leetcode.com/problems/wiggle-sort-ii/discuss/77684/Summary-of-the-various-solutions-to-Wiggle-Sort-for-your-reference





// median partition
// O(n) time and O(n) space
class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int m = (n + 1) >> 1;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++)
            temp[i] = nums[i];
        
        // partition拆分成作弊那小于中位数，右边大于中位数
        int median = partition(temp, 0, n - 1, n / 2);  // k 为中位数
        
        int[] copy = Arrays.copyOf(nums, n);
        
        for (int i = 0, j = 0, k = n - 1; j <= k;) {
            if (copy[j] < median) {
                swap(copy, i++, j++);
            } else if (copy[j] > median) {
                swap(copy, j, k--);
            } else {
                j++;
            }
        }

        for (int i = m - 1, j = 0; i >= 0; i--, j += 2) nums[j] = copy[i];
        for (int i = n - 1, j = 1; i >= m; i--, j += 2) nums[j] = copy[i];
    }
    
    public int partition(int[] nums, int l, int r, int rank) {
        int left = l, right = r;
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot)
                right--;
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot)
                left++;
            nums[right] = nums[left];
        }
        if (left - l == rank)
            return pivot;
        else if (left - l < rank)
            return partition(nums, left + 1, r, rank - (left - l + 1));
        else
            return partition(nums, l, right - 1, rank);
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}