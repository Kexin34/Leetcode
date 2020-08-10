// Binary Search
// 把二维matrix当成一个sotred list
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
