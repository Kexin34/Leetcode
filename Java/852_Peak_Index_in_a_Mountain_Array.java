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


// 套用模板写的：
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int start = 1, end = arr.length - 2;
        while (start + 1 <  end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < arr[mid - 1]) {
                end = mid;
            } else if (arr[mid] < arr[mid + 1]) {
                start = mid;
            } else {
                return mid;
            }
        }
        if (arr[start] < arr[end]) {
            return end;
        } else { 
            return start;
        }
    }
}
// faster than 100.00% of Java 