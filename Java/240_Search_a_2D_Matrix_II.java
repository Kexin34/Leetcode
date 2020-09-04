// 解法：二分查找
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 )
            return false;
        int m = matrix.length, n = matrix[0].length;
        return searchRect(matrix, target, 0, 0, m - 1, n- 1);
    }
    
    public boolean searchRect(int[][] matrix, int target, int top, int left, 
                              int bottom, int right){
        //search if the target is inside the rectangular matrix[top:bottom][left:right]
        //each time we discard 1/4 of all elements
        //time complexity O( log(mn)/log(4/3) ) = O(logm + logn)
        
        if(top > bottom || left > right)  return false;
        int x = (top + bottom) / 2;             // mid row 
        int y = (left + right) / 2;             // mid col
        int center = matrix[x][y];
        
        if(center > target){        // target在center里面
            return searchRect(matrix, target, top, left, x - 1, right) // bottom = mid row - 1
                || searchRect(matrix, target, x, left, bottom, y - 1); // right = mid col - 1
        }
        else if(center < target){   // target在center外面
            return searchRect(matrix, target, x + 1, left, bottom, right) // top = mid row + 1
                || searchRect(matrix, target, top, y+1, x, right);      // left = mid col + 1
        }
        else
            return true;
    }
}
// faster than 65.40% of Java
//将效率提高到O(log(mn)/log(4/3) ) = O(logm + logn) = log(m)+log(n)
    
    


// 解法（最优）：从左下角开始，如果大于目标，向上走，如果小于目标，向右走
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 )
            return false;
        int m = matrix.length, n = matrix[0].length;
        // 从左下角开始
        int row = m - 1;
        int col = 0;
        while (row >= 0 && col < n){
            if (matrix[row][col] > target) row--;//向上走
            else if (matrix[row][col] < target) col++;//向右走
            else return true;                   // 找到
        }
        return false;
    }
}
// faster than 100.00% of Java 
// Time: O(m + n)