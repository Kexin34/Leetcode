//Solution: Recursion
class Solution {
    private final String[] belowTwenty = { "", "One", "Two", "Three", "Four", "Five", 
    "Six", "Seven", "Eight", "Nine", "Ten", "Eleven","Twelve", 
    "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };

    private final String[] belowHundred = { "", "Ten", "Twenty", "Thirty", "Forty",
     "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    
    final String[] unit = {"Billion", "Million", "Thousand", ""};
    final int[] radix = {1000000000, 1000000, 1000, 1};
    
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < radix.length; i++){
            // Group the number by thousands (3 digits). 
            if (num / radix[i] == 0) continue; //把大unit先排除掉
            // 找到目前要处理的三位
            int currentProcess = num / radix[i];
            sb.append(helper(currentProcess)).append(unit[i]).append(' ');
            num %= radix[i];    // 摆脱掉处理过后的
        }
        return sb.toString().trim();
    }
    
    private String helper(int num){
        if (num == 0) return "";
        if (num < 20) return belowTwenty[num] + " ";
        if (num < 100) return belowHundred[num / 10] + " " + helper(num % 10);
        return belowTwenty[num / 100] + " Hundred " + helper(num % 100);
    }
}
// Time complexity: O(logn)
// Space complexity: O(logn)
