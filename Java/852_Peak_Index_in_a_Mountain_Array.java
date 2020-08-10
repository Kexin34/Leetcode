// same as #162, bineary search find lower bound
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int left = 0;
        int right = A.length - 1;
        
        while (left < right){
            int mid = left + (right - left) / 2;
            if (A[mid] > A[mid + 1])        //下坡，说明peak在左边
                right = mid;
            else                            //上坡，说明peak在右边
                left = mid + 1;
        }
        return left;
    }
}
// faster than 100.00% of Java 
