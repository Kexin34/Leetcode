// Monotonic Stack
// Time complexity: O(n)
// Space complexity: O(n)
// Use a monotonic stack to compute left[i] and right[i] 类似 901. Online Stock Span

class Solution {
    public int sumSubarrayMins(int[] arr) {
        int kMod = 1_000_000_007;
        int n = arr.length;
        Stack<int[]> stack = new Stack<int[]>();// [nums, it's corresponding lens]
        int[] left = new int[n];
        int[] right = new int[n];
        int ans = 0;
        
        // A[i], L numbers that are greater than A[i] in range A[0] ~ A[i – 1], 
        for (int i = 0; i < n; i++) {
            int len = 1;
            while (!stack.isEmpty() && stack.peek()[0] > arr[i])
                len += stack.pop()[1];
            stack.push(new int[]{arr[i], len});
            left[i] = len;
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            int len = 1;
            while (!stack.isEmpty() && stack.peek()[0] >= arr[i])
                len += stack.pop()[1];
            stack.push(new int[]{arr[i], len});
            right[i] = len;
        }
        for(int i = 0; i < n; i++)
            ans = (int)(ans + (long)arr[i] * left[i] * right[i]) % kMod;
        return ans;
    }
}

