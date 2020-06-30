// 字符串乘法

class Solution {
    public String multiply(String num1, String num2) {
        int m  = num1.length(), n = num2.length();
        // 结果最多为 m + n 位数
        int[] res = new int[m + n];
        // 从个位数开始逐位相乘
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // 乘积在 res 对应的索引位置（单数相乘，结果最多两位）
                int p1 = i + j;     //十位
                int p2 = i + j + 1; //个位
                // 叠加到 res 上
                int sum = mul + res[p2];
                res[p2] = sum % 10;
                res[p1] += sum / 10;
            }
        }
        
        // 将计算结果转化成字符串
        StringBuilder sb = new StringBuilder();
        for(int p : res) 
            if(!(sb.length() == 0 && p == 0)) 
                sb.append(p);   // 结果前缀可能存的 0（未使用的位）
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

// faster than 91.03% of Java