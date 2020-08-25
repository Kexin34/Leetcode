// 双指针
class Solution {
    public int compress(char[] chars) {
        int len = chars.length;
        int cur = 0;//来标记下一个可以修改的位置，那么最终cur的值就是新数组的长度
        
        for (int i = 0, j = 0; i < len; i = j) {
            char ch = chars[i];
            // 最终j指向的是第一个和i指向字符不同的地方
            while (j < len && chars[j] == ch)
                j++;
            chars[cur++] = ch;
            
            // 只有一个字符的话，后面是不用加个数的，所以直接跳过
            if (j == i + 1) continue;
            
            // 将重复个数转为字符串
            String count = Integer.toString(j - i);
            for (char digit : count.toCharArray())
                chars[cur++] = digit; 
        }
        return cur;
    }
}
// faster than 96.61% of Java