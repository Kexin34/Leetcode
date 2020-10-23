// 解法：sort
class Solution {
    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++)
            A[i] = A[i] * A[i];
        Arrays.sort(A);
        return A;
    }
}
// faster than 73.47% of Java
// Time : O(NlogN), where NN is the length of A.
// Space : O(N).



// 解法：双指针（优化）
// 思路：在负数中，最右的负数绝对值最小。变成两个递增数组
class Solution {
    public int[] sortedSquares(int[] A) {
        if (A == null) return null;
        int n = A.length;
        int j = 0;  // 作为正数起始点
        while (j < n && A[j] < 0)
            j++;
        int i = j - 1;  //作为负数最小起始点，之后向左移动
        
        int[] res = new int[n];
        int k = 0;
        // reading two increasing arrays 
        while (i >= 0 && j < n){
            if (A[i] * A[i] < A[j] * A[j]){
                res[k++] = A[i] * A[i];
                i--;
            }else{
                res[k++] = A[j] * A[j];
                j++;
            }
        }
        // 处理单方剩余
        while (i >= 0){
            res[k++] = A[i] * A[i];
            i--;
        }
        while (j < n){
            res[k++] = A[j] * A[j];
            j++;
        }
        return res;
    }
}
// faster than 73.47% of Java
// Time : O(N), where NN is the length of A.
// Space : O(N).
