// Combination + DFS
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) return res;
        
        char[][] map = {
            {},
            {},
            {'a','b','c'},
            {'d','e','f'},
            {'g','h','i'},
            {'j','k','l'},
            {'m','n','o'},
            {'p','q','r','s'},
            {'t','u','v'},
            {'w','x','y','z'}
        };
        StringBuilder sb = new StringBuilder();
        dfs(digits, map, 0, sb, res);
        return res;
    }
    
    private void dfs(String digits, char[][] map, int cur_idx, StringBuilder sb, List<String> res) {
        if (cur_idx == digits.length()){
            res.add(sb.toString());
            return;
        }
        // 遍历当前idx代表的所有字母
        int num = digits.charAt(cur_idx) - '0';
        for (char c: map[num]){
            sb.append(c);
            dfs(digits, map, cur_idx + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);  // backtracking
        }
    }
}
// Runtime: 0 ms, faster than 100.00% of Java
// time: O(4^n),4是因为一个输入数字最多打出4种字母，一共n个输入数字
// space: O(4 ^ n + n)




// BFS
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) return res;
        
        char[][] map = {
            {},
            {},
            {'a','b','c'},
            {'d','e','f'},
            {'g','h','i'},
            {'j','k','l'},
            {'m','n','o'},
            {'p','q','r','s'},
            {'t','u','v'},
            {'w','x','y','z'}
        };
        res.add("");
        
        for (char digit: digits.toCharArray()){
            List<String> tmp = new ArrayList<>();// 作为之后要替换现有答案的
            // 把当前生成好的每一个string都拿出来，把当前数字对应的字母都追加到后面作为temp对应string
            for (String str: res){
                char[] charsToAppend = map[digit - '0'];// 当前数字对应的所有字母
                for (int i = 0; i < charsToAppend.length; i++)// 遍历所有字母，每个追加到string都都放入
                    tmp.add(str + charsToAppend[i]);
            }
            res = tmp;
        }
        return res;
    }
}
// Runtime: 4 ms, faster than 40.61% of Java 
// time: O(4^n),4是因为一个输入数字最多打出4种字母，一共n个输入数字
// space: O(4 ^ n * 3)

