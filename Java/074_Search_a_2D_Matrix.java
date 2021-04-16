// 解法一（基础）两次Binary Search
// 两次BS的模板是不同的,作用不同
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0)
            return false;
        if (matrix[0] == null || matrix[0].length == 0)
            return false;
        
        int row = matrix.length;
        int cols = matrix[0].length;
        
        // 第一轮二分，find the row index, the last number <= target
        int start = 0, end = row - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target)
                return true;
            else if (matrix[mid][0] < target)
                start = mid;
            else 
                end = mid;
        }
        if (matrix[end][0] <= target)
            row = end;
        else if (matrix[start][0] <= target)
            row = start;
        else
            return false;
        
        // 第二轮二分，目前已定位是哪行
        // find the column index, the number equal to target
        start = 0;
        end = cols - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] == target)
                return true;
            else if (matrix[row][mid] < target)
                start = mid;
            else
                end = mid;
        }
        if (matrix[row][start] == target)
            return true;
        else if (matrix[row][end] == target)
            return true;
        else
            return false;
    }
}
// faster than 100.00% of Java




// 解法二： 一次Binary Search
// 思路：即将二维数组看作一个n*m大小的一维数组，二分查找值。
// m*n matrix convert to an array => matrix[x][y] => a[x*n+y] // n是列数
// An array convert to m*n matrix => a[x] => matrix[x/n][x%n]

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0)
            return false;
        if (matrix[0] == null || matrix[0].length == 0)
            return false;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int start = 0, end = rows * cols - 1;
        int mid;
        
        while (start <= end){ // 这里不能是start + 1 < end，有可能只有一个
            mid = start + (end - start) / 2;
            int i = mid / cols;
            int j = mid % cols;
            if (matrix[i][j] == target) 
                return true;
            else if (matrix[i][j] < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return false;
    }
}
// faster than 100.00% of Java
//Time complexity : O(log(mn)) since it's a standard binary search.
// Space complexity : O(1).
