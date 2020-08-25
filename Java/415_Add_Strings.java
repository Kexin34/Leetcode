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