class Solution {
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) return 0;
        
        // Define boundaries
        int left = 0, right  = 1;
        while (reader.get(right) < target){
            left = right;
            right *= 2;
        }
            
        // Binary Search
        int mid, midVal;
        while (left <= right){
            mid = left + (right - left) / 2;
            midVal = reader.get(mid);
            
            if (midVal == target) return mid;
            if (midVal > target) right = mid - 1;
            else 
                left = mid + 1;
        }
        // there is no target element
        return -1;
    }
}
//faster than 64.80% of Java 
// Time complexity : O(logT)