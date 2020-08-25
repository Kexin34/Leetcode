class Solution {
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        char [] c = s.toCharArray();
        int str_len = c.length;
        
        // 建立大小为numRows的字符串数组，为的就是把之字形的数组整个存进去，
        // 然后再把每一行的字符拼接起来，就是想要的结果了
        StringBuffer[] sb = new StringBuffer[numRows];
        // sb初始化 = [sub_sb_0, sub_sb_1, sub_sb_2...sub_sb_numRows]
        for (int i = 0; i < numRows; i++)
            sb[i] = new StringBuffer();
        
        int i = 0;
        while (i < str_len){    // 按列进行遍历
            // 首先前 numRows 个字符就是按顺序存在每行的第一个位置
            //vertically assigning origianal element down to each of sb row
            for (int idx = 0; idx < numRows && i < str_len; idx++)
                sb[idx].append(c[i++]);
            
            //‘之’ 字形的连接位置了，可以发现其实都是在行数区间 [1, numRows-2] 内，
            // 只要按顺序去取字符就可以
            // middle one append up
            for (int idx = numRows - 2; idx >= 1 && i < str_len; idx--)
                sb[idx].append(c[i++]);
        }
        // 最后把每行都拼接起来即为所求
        // append next row to the beginning row-> horizontally present
        for (int r = 1; r < sb.length; r++)
            sb[0].append(sb[r]);
        return sb[0].toString();
    }
}
// faster than 81.46% of Java
// Time complexity: O(n)
// Space complexity: O(n)
