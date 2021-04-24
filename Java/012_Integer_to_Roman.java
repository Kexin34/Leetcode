// Greedy
// 建立一个数表，每次通过查表找出当前最大的数，减去再继续查表
class Solution {
    static private String[]roman = {"M", "CM", "D", "CD", "C", "XC", "L", 
                                    "XL", "X", "IX", "V", "IV", "I"};
    static private int[] val = {1000, 900, 500, 400, 100, 90, 50, 40,
                                10, 9, 5, 4, 1};
    
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        
        // 每次通过查表找出当前最大的数，减去再继续查表
        for (int i = 0; i < val.length; i++){
            while (num >= val[i]){
                num -= val[i];
                res.append(roman[i]);
            }
        }
        return res.toString();
    }
}
// faster than 100.00% of Java
