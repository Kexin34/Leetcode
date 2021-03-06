// 优先队列
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // init heap 'the smallest element poll first'
        // 可写成PriorityQueue<Integer> pq = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < nums.length; i++){
            pq.offer(nums[i]);
            if (pq.size() > k)
                pq.poll();
        }
        int res = pq.poll();
        return res;
    }
}
// Runtime: 4 ms, faster than 59.63% of Java 
// time: O(Nlogk)
// 在heap中添加一个元素是O(logk)，一共操作N次




// 用Quick Select来解决
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
// Runtime: 8 ms, faster than 22.99% of Java
// Time: 平摊O(n),最坏O(n^2)