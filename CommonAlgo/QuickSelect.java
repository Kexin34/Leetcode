class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        if (k <= 0) return 0;
        return quickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
    }
    
    public int quickSelect(int[] nums, int l, int r, int k) {
        if (l  == r) return nums[l];
        
        int position = partition(nums, l, r);
        if (position + 1 == k)
            return nums[position];
        else if (position + 1 < k)  // k在分区右边
            return quickSelect(nums, position + 1, r, k);
        else 
            return quickSelect(nums, l, position - 1, k);
    }
    
    
    public int partition(int[] nums, int l, int r) {
        // 初始化左右指针和pivot
        int left = l, right = r;
        int pivot = nums[left];
        
        // 运行partition
        while (left < right) {
            while (left < right && nums[right] >= pivot)
                right--;
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot)
                left++;
            nums[right] = nums[left];
        }
        // 返还pivot点到数组里面
        nums[left] = pivot;
        return left;
    }
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