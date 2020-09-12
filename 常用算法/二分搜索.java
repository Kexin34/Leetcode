/*
二分搜索模板
	给一个有序数组和目标值，找第一次/最后一次/任何一次出现的索引，如果没有出现返回-1

模板四点要素:
	1、初始化：start = 0、end = len-1
	2、循环退出条件：start + 1 < end
	3、比较中点和目标值：A[mid] ==、 <、> target
	4、判断最后两个元素是否符合：A[start]、A[end] ? target

时间复杂度 O(logn)，使用场景一般是有序数组的查找

https://www.cnblogs.com/grandyang/p/6854825.html

*/

// 704. Binary Search
// 最简单的二分搜索，不需要找第一个、最后一个位置、或者是没有重复元素，可以使用模板#1，代码更简洁
class Solution {
    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int mid; 
        while (start <= end){
            mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                end = mid - 1;
            else
                start = mid + 1;
        }
        return -1;
    }
}

// 34. Find First and Last Position of Element in Sorted Array
// 给定一个包含n个整数的排序数组，找出给定目标值target的起始和结束位置。如果目标值不在数组中，则返回[-1, -1]
// 思路：核心点就是找第一个target的索引，和最后一个 target 的索引，所以用两次二分搜索分别找第一次和最后一次的位置
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0) return result;
        
        // 第一轮二分搜索：搜索左边的索引
        int start = 0, end = nums.length-  1;
        int mid;
        while (start < end){
            mid = start + (end - start) / 2;
            if (target > nums[mid])
                start = mid + 1;
            else // 继续向左找，就能找到第一个目标值的位置
                end = mid;
        }
        // 此时left/right都应该是左边target index
        if (nums[start] != target) return result;
        result[0] = start;
        
        // 第二轮二分搜索：搜索右边的索引
        end = nums.length - 1;      // start目前是左边target index，不要reset
        while (start < end){
            mid = (start + (end - start) / 2)  + 1;// Make mid biased to the right
            if (target < nums[mid])
                end = mid - 1;
            else // 继续向右找，就能找到最后一个目标值的位置
                start = mid;
        }
        result[1] = end;
        return result;
    }
}


// 35. Search Insert Position
// 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
// 思路：找到第一个 >= target 的元素位置
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        // <= 理由：如果target比最右元素都大，left在等于right后继续加一再终止，返回正确index
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (target == nums[mid])
                return mid;
            else if (target < nums[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
        
    }
}


// 74. Search a 2D Matrix
// 编写一个高效的算法来判断  m x n  矩阵中，是否存在一个目标值。该矩阵具有如下特性：
// 每行中的整数从左到右按升序排列。
// 每行的第一个整数大于前一行的最后一个整数。
// 思路：将2纬数组转为1维数组 进行二分搜索
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        
        while (left <= right){
            int mid = left + (right - left) / 2;
            // An array convert to m*n matrix => a[x] => matrix[x/n][x%n]
            int midVal = matrix[mid / n][mid % n];
            if (midVal == target)
                return true;
            else if (midVal > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }
}


// 278. First Bad Version
// 找出导致之后所有版本出错的第一个错误的版本。
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 0, right = n;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid))
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }
}

// 153. Find Minimum in Rotated Sorted Array
// 假设按照升序排序的数组在预先未知的某个点上进行了旋转( 例如，数组  [0,1,2,4,5,6,7] 可能变为
// [4,5,6,7,0,1,2] )。 请找出其中最小的元素。
// 思路： 
class Solution {
    public int findMin(int[] nums) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) //数组没有旋转或者旋转点在左半段
                right = mid;            	// 去左半边继续搜索
            else    
                left = mid + 1;         	// 去右半段查找
        }
        return nums[right];
    }
}


// 154. Find Minimum in Rotated Sorted Array II
// 假设按照升序排序的数组在预先未知的某个点上进行了旋转( 例如，数组  [0,1,2,4,5,6,7] 可能变为
// [4,5,6,7,0,1,2] )。 请找出其中最小的元素。(包含重复元素)
class Solution {
    public int findMin(int[] nums) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right])    	//数组没有旋转或者旋转点在左半段
                right = mid;                	// 去左半边继续搜索
            else if (nums[mid] > nums[right])
                left = mid + 1;             	// 去右半段查找
            else              //如果两者重复，右指针左移一位，略过一个相同数字
                right--;
        }
        return nums[right];
    }
}



// 33. Search in Rotated Sorted Array
// 如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的
// 然后检查区间
// 我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > nums[right]){  // 说明左边有序 
                // 检查左边区间首尾，看target是否在左边区间首尾内,是的话改右边界
                if (nums[left] <= target && nums[mid] > target)
                    right = mid - 1;
                else
                    left = mid + 1;
            }else{                               // 说明右边有序 
                // 检查右边区间首尾，看target是否在左边区间首尾内,是的话改右边界
                if (nums[mid] < target && nums[right] >= target)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}
//注意点: 面试时，可以直接画图进行辅助说明，空讲很容易让大家都比较蒙圈











