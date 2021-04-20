// 一位一位相加，然后算和算进位，根据进位情况看需不需要补一个高位
class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;  // 指向最低位index（最右边）
        int j = num2.length() - 1;
        int x, y;
        int carry = 0;
        StringBuilder str = new StringBuilder();
        
        while(carry == 1 || i >= 0 || j >= 0){
            //x和y一位一位相加
            if (i < 0) x = 0;   // 为了保证两者在同一位有数可以操作
            else {
                x = num1.charAt(i) - '0';   //char -> int
                i--;
            }
            if (j < 0) y = 0;
            else {
                y = num2.charAt(j) - '0';
                j--;
            }
            //算和算进位
            str.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        return str.reverse().toString();
    }
}
// faster than 97.21% of Java
// Time: O(m+n)





class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();

        int carry = 0;
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        while (p1 >= 0 || p2 >= 0) {
            int x1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int x2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
            int value = (x1 + x2 + carry) % 10;
            carry = (x1 + x2 + carry) / 10;
            res.append(value);
            p1--;
            p2--;    
        }
        
        if (carry != 0)
            res.append(carry);
        
        return res.reverse().toString();
    }
}