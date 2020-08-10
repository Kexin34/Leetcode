// DP 字符串匹配, 向前查找
// dp[i] : max length of chain of (A[0] ~A[i - 1])
// dp[i] = max{dp[j] + 1}, when A[j] is predecessor of A[i], 1 <= j < i

class Solution {
    public int longestStrChain(String[] words) {
        // 因为是在list of words里面选择，所以要先按照长度从小到大sort
        Arrays.sort(words, new StringByLengthComparator());
        
        int n = words.length;
        int[]dp = new int[n];   
        Arrays.fill(dp, 1);     // 每一个chain最起码都包括自己，长度最起码1
        int maxLen = 0;
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < i; j++){        //找前任 -> 往前比较
                if (isPredecessor(words[j], words[i]))
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
            
    // 判断S1是否是S2的predecessor
    public boolean isPredecessor(String s1, String s2){
        if (s1.length() + 1 != s2.length()) return false;
        int diff = 0;
        for (int i = 0, j = 0; i < s1.length() && j < s2.length();){
            if (s1.charAt(i) == s2.charAt(j)){
                i++; 
                j++;
            }else{
                diff++;
                if (diff > 1) return false;
                j++;        // 因为s2是比较长的那一个，拥有一个多余字符
            }  
        }
        return true; 
    }
    
    private static class StringByLengthComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return Integer.compare(s1.length(), s2.length());
        }
    }
}

// Time complexity: O(n^2*l)
// Space complexity: O(n)

