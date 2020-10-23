// 解法： Two Pass
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int[] res = new int[A.length];
        int j = 0;
        
        for (int i = 0; i < A.length; i++){
            if (A[i] % 2 == 0)
                res[j++] = A[i];
        }
        for (int i = 0; i < A.length; i++){
            if (A[i] % 2 == 1)
                res[j++] = A[i];
        }
        return res;
    }
}
// faster than 99.58% of Java 
// Time : O(N), where N is the length of A.
// Space : O(N), the space used by the answer.




// 解法：quick sort方法，in-place解（空间最优），一定要会
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j){
            if (A[i] % 2 > A[j] % 2)
                swap(A, i, j);


            if (A[i] % 2 == 0) i++;
            if (A[j] % 2 == 1) j--;
        }
        return A;
    }
    
    private void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
// faster than 99.58% of Java 
//Time: O(N), where N is the length of A. 
// Each step of the while loop makes j-i decrease by at least one. 
// (Note that while quicksort is O(NlogN) normally, this is O(N) because we only need one pass to sort the elements.)
// Space : O(1) in additional space complexity.











