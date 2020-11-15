/* 常考排序(手写）: 
	归并排序
	快速排序
	堆排序

较慢，很少人用：
	选择排序 
	插入排序
	Bubble Sort

	https://leetcode.com/problems/sort-an-array/discuss/492042/7-Sorting-Algorithms-(quick-sort-top-downbottom-up-merge-sort-heap-sort-etc.)
*/


// 归并排序
// 平均时间复杂度: O(nlogn), 最坏时间复杂度: O(nlogn)
class Solution {
    private int[] aux;
    
    public int[] sortArray(int[] nums) {
        aux = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    public void mergeSort(int[] nums, int lo, int hi){
        if (lo >= hi) return;
        // 分治法：divide 分为两段
        int mid = lo + (hi - lo) / 2;
        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);
        // 合并两段数据
        merge(nums, lo, mid, hi);
    }
    
    private void merge(int[] a, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        // 先把原array复制一遍，之后修改原来array
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        
        // 开始merge，前后子数组的指针就是i，j
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];               // 当左边到底了，取右
            else if (j > hi) a[k] = aux[i++];           // 当右边到底了，取左
            else if (aux[i] < aux[j]) a[k] = aux[i++];  // 右边现有元素较小，取右
            else a[k] = aux[j++];                       // 左边现有元素较小，取左
        }
    }
}




// 快速排序
/* 
  快速排序从小到大排序：
  在数组中随机选一个数（默认数组首个元素），数组中小于等于此数的放在左边部分，
  大于此数的放在右边部分，这个操作确保了这个数是处于正确位置的，再对左边部分数组和右边部分数组
  递归调用快速排序，重复这个过程。
*/
// 平均时间复杂度: O(nlogn), 最坏时间复杂度: O(n²)
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        // 思路：把一个数组分为左右两段，左段小于右段
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    // 原地交换，所以传入交换索引
    private void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;
        // 分治法：divide
        int mid = partition(nums, l, r);
        quickSort(nums, l, mid);
        quickSort(nums, mid + 1, r);
    }
    
    // 分区
    private int partition(int[] nums, int l, int r) {
        int pivot = nums[l];// 选取首个元素作为基准pivot
        int pivot_idx = l;  // 保留这个pivot index，之后和LeftGreaterThanPivot交换
        while (l < r) {
            // 从右到左找到首个RightSmallerThanPivot元素
            while (l < r && nums[r] >= pivot) r--;
            // 从左到右找到首个LeftGreaterThanPivot元素
            while (l < r && nums[l] <= pivot) l++;
            swap(nums, l, r);
        }
        // 把基准值和LeftGreaterThanPivot交换
        nums[pivot_idx] = nums[l];
        nums[l] = pivot;
        
        return l;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}






// 堆排序
// https://www.youtube.com/watch?v=2DmK_H7IdTo
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        heapSort(nums);
        return nums;
    }
   
    private void heapSort(int[] nums) {
        int len = nums.length;
        // 1、将无序数组a构建为一个大根堆
        // 第一个非叶子结点在arr.length/2-1，当前堆末尾元素在len - 1
        for (int i = len / 2 - 1; i >= 0; i--)
            heapify(nums, i, len - 1);
        
        // 这一步已经建立好从大到小排列的堆（array从大到小）
        // 每次提取出最大的堆顶，插入到已经sort好的开头，然后heapify保持最大堆结构
        
        // 2、交换a[0]和a[len(a)-1], a[0]是当前推顶最大元素
        // 3、然后把前面这段数组继续下沉保持堆结构，如此循环即可
        for (int i = len - 1; i >= 1; i--){
            swap(nums, 0, i);           // 从后往前填充值
            heapify(nums, 0, i - 1);    // 前面的长度也减一
        }
    }
    
    private void heapify(int[] nums, int i, int end) {
        while (i <= end){
            int l = 2 * i + 1;      // 左节点索引(从0开始，所以左节点为i*2+1)
            int r = 2 * i + 2;      // 右节点索引
            int maxIndex = i;       // idx保存根、左、右三者之间较大值的索引
            // 存在左节点，左节点值较大，则取左节点
            if (l <= end && nums[l] > nums[maxIndex]) maxIndex = l;
            // 存在右节点，且值较大，取右节点
            if (r <= end && nums[r] > nums[maxIndex]) maxIndex = r;
            // 如果根节点较大，则不用下沉
            if (maxIndex == i) break;
            // 如果根节点较小，则跟最大index交换值，并继续下沉
            swap(nums, i, maxIndex);
            // 继续下沉idx节点
            i = maxIndex;
        
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}
// 平均时间复杂度均为O(nlogn)





// 选择排序
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        selectionSort(nums);
        return nums;
    }
    
    public void selectionSort(int[] nums){
        for (int i = 0; i < nums.length; i++){
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++)
                if (nums[j] < nums[minIndex]) 
                    minIndex = j;
            if (minIndex != i)
                swap(nums, i, minIndex);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}



// 插入排序
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        insertionSort(nums);
        return nums;
    }
    
    public void insertionSort(int[] nums){
        for (int i = 0; i < nums.length; i++){
            for (int j = i; j > 0; j--){
                if (nums[j] >= nums[j - 1]) break;
                swap(nums, j, j - 1);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}
// faster than 9.04% of Java


// Bubble Sort
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        bubbleSort(nums);
        return nums;
    }
    
    private void bubbleSort(int[] nums) {
        for (int k = nums.length - 1; k >= 1; k--) {
            for (int i = 0; i < k; i++) {
                if (nums[i] > nums[i + 1]) 
                    swap(nums, i, i + 1);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}





