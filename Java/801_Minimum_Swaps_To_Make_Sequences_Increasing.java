// DP
class Solution {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[] keep = new int[n];
        int[] swap = new int[n];
        Arrays.fill(keep, Integer.MAX_VALUE);
        Arrays.fill(swap, Integer.MAX_VALUE);
        
        keep[0] = 0; // if only one num in each array
        swap[0] = 1;
        
        for (int i = 1; i < n; ++i) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1]){
                // Case1: no swapping for i and i-1
                keep[i] = keep[i - 1];
                
                // Case2: Swapped for both i and i-1
                // swap A[i - 1] / B[i - 1], swap A[i], B[i] as well
                swap[i] = swap[i - 1] + 1;
            }
            
            if (B[i] > A[i - 1] && A[i] > B[i - 1]) {
                // Case3: swap i, A[i - 1] / B[i - 1] weren't swapped.
                swap[i] = Math.min(swap[i], keep[i - 1] + 1);
                // Case4: Swap i-1, no swap needed for A[i] / B[i]      
                keep[i] = Math.min(keep[i], swap[i - 1]);
            }
        }
        return Math.min(keep[n-1], swap[n-1]);
    }
}
// faster than 100.00% of Java 
// Time complexity: O(n)
// Space complexity: O(n)