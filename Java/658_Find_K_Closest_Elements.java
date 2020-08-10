// DP + 滑窗
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0; 
        int right = arr.length - k;
        while (left < right){
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x)
                left = mid + 1; // need to move window go left again
            else
                right = mid;
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++)
            result.add(arr[i]);
        
        return result;
    }
}
// faster than 60.54% of Java
// Time O(log(N - K)) to binary research and find result
// Space O(K) to create the returned list.



// Double pointers
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - 1;
        while (hi - lo >= k){       //左右相隔k个，这个range就是k个closet元素（答案）
            if (Math.abs(arr[lo] - x) > Math.abs(arr[hi] - x))
                lo++;
            else 
                hi--;
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = lo; i <= hi; i++)
            result.add(arr[i]);
        
        return result;
    }
}
// faster than 98.33% of Java