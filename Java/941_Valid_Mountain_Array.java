// 解法：one pass
// If we walk along the mountain from left to right,
// we have to move strictly up, then strictly down.
class Solution {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) return false;
        int i = 0; 
        // walk up
        while (i + 1 < A.length && A[i + 1] > A[i])
                i++;
        
        // peak can't be first or last
        if (i == 0 || i == A.length - 1)
            return false;
        
        // walk down
        while (i + 1< A.length && A[i] > A[i + 1])
            i++;
        
        // If we reach the end, the array is valid, otherwise its not. 
        return i == A.length - 1;
    }
}
// faster than 100.00% of Java
// Time : O(N), where N is the length of A.
// Space : O(1).