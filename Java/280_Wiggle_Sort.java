// Partition做法

class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++)
            temp[i] = nums[i];
        
        // partition拆分成作弊那小于中位数，右边大于中位数
        int mid = partition(temp, 0, n - 1, n / 2);  // k 为中位数
        
        int[] ans = new int[n];
        
        // 先把ans数组初始化为中位数index
        for (int i = 0; i < n; i++)
            ans[i] = mid;
        
        int l, r;
        if (n % 2 == 0){  // 数组为偶数，左右长度相等，mid左边是l
            // l指针一直从后向前走，r从前向后走
            l = n - 2;   // 因为[n-1]放着mid
            r = 1;
            for (int i = 0; i < n; i++){
                if (nums[i] < mid){
                    ans[l] = nums[i];
                    l = l - 2;
                }else if (nums[i] > mid){
                    ans[r] = nums[i];
                    r = r + 2;
                }
            }
        }else {     // 数组为奇数,mid左边是r
            l = 0;   // l指针从前向后走
            r = n - 2;
            for (int i = 0; i < n; i++){
                if (nums[i] < mid){
                    ans[l] = nums[i];
                    l = l + 2;
                }else if (nums[i] > mid) {
                    ans[r] = nums[i];
                    r = r - 2;
                }
            }
        }
        for (int i = 0; i < n; i++)
            nums[i] = ans[i];
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
}
// Runtime: 1 ms, faster than 60.16% of Java