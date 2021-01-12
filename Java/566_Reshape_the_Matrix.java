// 解法：用一维数组来表示二维

class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length;
        int n = nums[0].length;
        if (m * n != r * c) return nums;
        int idx = 0;                        //指向原本matrix的指针
        int[][] res = new int[r][c];
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // idx以一位数组的形式表示： nums[n * i + j]
                res[i][j] = nums[idx / n][idx % n];
                idx++;
            }
        }
        return res;
    }
}
//  faster than 100.00% of Java
// Time: O(N), space: O(N)