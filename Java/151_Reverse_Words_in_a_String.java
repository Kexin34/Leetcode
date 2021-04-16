// 三段翻转法，只需要两步翻转: 先整个字符串整体翻转一次，然后再分别翻转每一个单词
class Solution {
    public String reverseWords(String s) {
        int storeIndex = 0;   // 表示当前存储到的位置
        int n = s.length();
        
        // 1. 先整个字符串整体翻转一次
        StringBuilder sb = new StringBuilder(s).reverse();
        
        // 2. 再分别翻转每一个单词
        for (int i = 0; i < n; i++){
            // 遇到一个新单词，要在内部翻转，然后i直接快进到本单词末尾
            if (sb.charAt(i) != ' '){
                if (storeIndex != 0) sb.setCharAt(storeIndex++, ' '); // 单词开头放个空格
                int j = i;
                while (j < n && sb.charAt(j) != ' ')
                    sb.setCharAt(storeIndex++, sb.charAt(j++));
                // 这时候已经把正序单词放入sb了,接下来要翻转(单词开头-末尾),这里拿到当前单词的翻转
                String t = new StringBuilder(sb.substring(storeIndex - (j - i), storeIndex)).reverse().toString();
                sb.replace(storeIndex - (j - i), storeIndex, t);
                i = j;
            }
        }
        sb.setLength(storeIndex);
        return sb.toString();
    }
}
// 5 ms, faster than 65.73% of Java 



// 用split来做
class Solution {
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        String[] words = s.trim().split("\\s+");
        
        for (int i = words.length - 1; i > 0; i--)
            res.append(words[i] + " ");
        
        res.append(words[0]);
        return res.toString();
    }
}
// Runtime: 8 ms, faster than 43.14% of Java