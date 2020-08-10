class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        HashMap<Integer, Integer> map= new HashMap<>();

        // cur - prev = diff
        // prev = cur - diff -> We need to find the previous element in hashmap
        for (int curr : arr){
            int prev = curr - difference;
            // dp[x] = dp[x - d] + 1) -> dp[curr] = dp[prev] + 1 
            map.put(curr, map.getOrDefault(prev, 0) + 1);
            ans = Math.max(ans, map.get(curr));
        }
        return ans;
    }
}
// faster than 74.75% of Java
// Time: O(N)
// Space: O(N)