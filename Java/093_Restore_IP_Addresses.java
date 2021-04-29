// 解法：简单递归（非最快）
public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        helper(s, 0, "", res);
        return res;
    }
    public void helper(String s, int n, String out, List<String> res) {
        // 如果k = 0，则表示三个点已经加入完成，四段已经形成，若这时字符串刚好为空，则将当前分好的结果保存
        if (n == 4) {
            if (s.isEmpty()) res.add(out);
            return;
        }
        /* 若k != 0, 则对于每一段，我们分别用一位，两位，三位来尝试，分别判断其合不合法
           如果合法，则调用递归继续分剩下的字符串，最终和求出所有合法组合*/
        for (int k = 1; k < 4; ++k) {
            if (s.length() < k) break;
            // 从头到本i段，转换成整型val，再转回字符串和原字符串对比
            // 此时的长度和i值不同了，这样就可以找出不合要求的情况了
            int val = Integer.parseInt(s.substring(0, k));
            if (val > 255 || k != String.valueOf(val).length()) continue;
            helper(s.substring(k), n + 1, out + s.substring(0, k) + (n == 3 ? "" : "."), res);
        }
    }
}
// faster than 52.23% of Java 




//解法: Backtracking (DFS)
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;

        StringBuilder path = new StringBuilder();    //用作递归链
        helper(0, path, res, s);
        return res;
    }
    
    private void helper(int k, StringBuilder path, List<String> res, String s){
        if (k == 4 || s.length() == 0){
            if(k == 4 && s.length() == 0){
                // 如果k = 4，则表示三个点已经加入完成，四段已经形成，
                // 若这时字符串刚好为空，则将当前分好的结果保存
                res.add(path.toString());
            }
            return;
        }

        /* 若k != 0, 则对于每一段，我们分别用一位，两位，三位来尝试，分别判断其合不合法
            如果合法，则调用递归继续分剩下的字符串，最终和求出所有合法组合*/
        for (int i = 0; i < s.length() && i <= 2; i++){         //把从目前到末尾的subString递归
            if (i != 0 && s.charAt(0) == '0') break;

            String section = s.substring(0, i + 1);
            if (Integer.valueOf(section) > 255) continue;       //每段数字必须小于255，不然为invalid
            
            if (path.length() != 0)                             // 若递归链有东西,放入dot
                section = "." + section;              
            path.append(section);                               // 加入到递归链里面

            helper(k + 1, path, res, s.substring(i + 1)); // 递归继续处理下一个
            // 回溯
            path.delete(path.length() - section.length(), path.length());
        }
    }
}
//faster than 89.90% of Java 
