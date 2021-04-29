// Greedy + Two Pointers
// Time complexity: O(mlogm + nlogn)   for循环是m + m
// Space complexity: O(1)

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int j = 0;  // point to s（cookie）
        int ans = 0;
        
        for (int p : g) {   // 枚举每一个小孩（sorted）
            while (j < s.length && s[j] < p) // 如果cookie指针不满足当前，继续移动
                j++;
            if (j < s.length){   // 正序移动，第一个满足当前小孩的cookie
                ans++;
                j++;
            }
        }
        return ans;
    }
}
// Runtime: 6 ms, faster than 99.08% of Java