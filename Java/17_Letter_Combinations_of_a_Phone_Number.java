// DFS + backtracking
class Solution {
    private static final String[] key = new String[]{
            "", // 0 & 1 don't have char
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) return combinations;
        dfs(digits, new StringBuilder(), combinations);
        return combinations;
    }
    
    public void dfs(String digits, StringBuilder prefix, List<String> combinations){
        // prefix: 本次path暂时储存的当前递归链
        if ( prefix.length() == digits.length()) {
            combinations.add(prefix.toString()); // 本轮dfs结束，把这条path添加到ans中
            return;
        }
        // 找到现在要检查的数字（prefix长度就是现在要检查的index）
        int curDigit = digits.charAt(prefix.length()) - '0';// ascii -> digit
        
        // 通过 digits 中的数字到 dict 中取出所对应的字符串
        String letters = key[curDigit];                 

        //遍历这个取出的字符串，将每个字符都加到当前的组合后面，并调用递归函数
        for (char c : letters.toCharArray()){           
            prefix.append(c);                           //向在测试递归链添加字母
            dfs(digits, prefix, combinations);
            // 重点：
            // 在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时访问一个元素
            // 可以访问已经访问过但是不在当前递归链中的元素
            prefix.deleteCharAt(prefix.length() - 1);         //递归返回时，在递归链中删除最末字母
        }
    }
}
// faster than 85.55% of Java



// BFS (无需回溯)
class Solution {
    public List<String> letterCombinations(String digits) {
        String[] key = new String[]{
            "", // 0 & 1 don't have char
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"};
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return ans;
        ans.add("");
        
        // BFS, 这里ans是第n-1位的解，temp是第n位的解集合
        for (char digit : digits.toCharArray()){
            List<String> temp = new ArrayList<>();
            for (String t : ans){       //t是n-1位解的每个str
                // 新数字对应字母们(newLetter)都和 n-1位解的每个str t合并
                String newLetter = key[Character.getNumericValue(digit)]; 
                for (int i = 0; i < newLetter.length(); i++)
                    temp.add(t + newLetter.charAt(i));
            }
            ans = temp;
        }
        return ans;
    }
}
