// 解法一（基础）两次Binary Search
// 两次BS的模板是不同的,作用不同
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;

        // 第一轮BS，搜索行, 查找第一个大于目标值的数，这样只要回退一个，就一定是 target 所在的行
        int left = 0;
        int right = matrix.length - 1;
        int mid;
        // <= 理由：如果target比最右元素都大，left在等于right后继续加一再终止，返回正确index
        while (left <= right){
            mid = left + (right - left) / 2;
            if (target == matrix[mid][0])
                return true;
            else if (target < matrix[mid][0])
                right = mid - 1;
            else
                left = mid + 1;
        }
        int row = (left > 0) ? left - 1 : left;//如果返回的是0，就不能回退了，以免越界
        
        // 第二轮BS，搜索target 所在的行数的列,查找和 target 值相同的数(模板一)
        left = 0;
        right = matrix[0].length - 1;
        
        while (left <= right){
            mid = left + (right - left) / 2;
            if (matrix[row][mid] == target)
                return true;
            else if (matrix[row][mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }
}
// faster than 21.63% of Java




// 解法二（优化）一次Binary Search
// 思路：将2纬数组转为1维数组 进行二分搜索
// m*n matrix convert to an array => matrix[x][y] => a[x*n+y] // n是列数
// An array convert to m*n matrix => a[x] => matrix[x/n][x%n]

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int lo = 0;
        int hi = m * n - 1;
        
        // Binary search
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            // An array convert to m*n matrix => a[x] => matrix[x/n][x%n]
            int midVal = matrix[mid / n][mid % n];
            if (midVal == target)
                return true;
            else if (midVal < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return false;
    }
}
// faster than 100.00% of Java
//Time complexity : O(log(mn)) since it's a standard binary search.
// Space complexity : O(1).
