// 基础解
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int i = 0; i < strs[0].length(); i++){
            for (int j = 1; j < strs.length; j++)
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i))
                    return strs[0].substring(0, i);
        }
        return strs[0];
    }
}
// faster than 71.40% of Java 


// 优化：只对比两个字符串
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs);      //  按字母顺序排序
        int i = 0;
        //len是排序后首尾字母串最短长度
        int len = Math.min(strs[0].length(), strs[strs.length - 1].length());
        while (i < len && strs[0].charAt(i) == strs[strs.length - 1].charAt(i))
            i++;
        return strs[0].substring(0, i);
    }
}
// faster than 71.40% of Java