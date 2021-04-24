// 基础暴力的字符串匹配
class Solution {
    public int strStr(String haystack, String needle) {
        // empty needle appears everywhere, first appears at 0 index
        if (needle.length() == 0) return 0;
        if (haystack.length() == 0) return -1;
        int len_pat = needle.length();
        int len_txt = haystack.length();
        
        for (int i = 0; i < len_txt; i++){
            // no enough places for needle after i
            if (i + len_pat > len_txt) break;
            for (int j = 0; j < len_pat; j++){
                if (needle.charAt(j) != haystack.charAt(i + j))
                    break;
                if (j == len_pat - 1) //j reach到pattern末端index，说明找到了
                    return i;
            }
        }
        return -1;
    }
}
// faster than 68.76% of Java
// 时间复杂度 O(MN)，空间复杂度O(1)



class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (haystack.length() == 0) return -1;
        int M = needle.length();
        int N = haystack.length();
        
        /* 构造KMP 构建状态转移图 */
        String pat = needle;
        // dp[状态][字符] = 下个状态
        int[][] dp = new int[M][256];
        // base case : 只有遇到 pat[0] 这个字符才能使状态从 0 转移到 1
        dp[0][pat.charAt(0)] = 1;
        // 影子状态 X 初始为 0
        int X = 0;
        // 构建状态转移图（稍改的更紧凑了）
        for (int j = 1; j < M; j++){
            for (int c = 0; c < 256; c++)
                dp[j][c] = dp[X][c];
            dp[j][pat.charAt(j)] = j + 1; //符合，状态推进
            // 更新影子状态
            X = dp[X][pat.charAt(j)];
        }
        
        // 开始search
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++){
            // 计算 pat 的下一个状态
            j = dp[j][haystack.charAt(i)];
            // 到达终止态，返回结果
            if (j == M) return i - M + 1;
        }
        
        // 没到达终止态，匹配失败
        return -1;
    }
}
// 时间复杂度 O(N), txt一次扫描
// 空间复杂度 O(256M) = O(M)。
