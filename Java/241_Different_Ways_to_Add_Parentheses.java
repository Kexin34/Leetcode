// 记忆化递归
// 算法：枚举每一个操作符，把当前表达式分成两个子表达式，递归求解子表达式所有的值得可能性，
// 再通过笛卡尔积(cartition product)把两个解的集合扩充，产生新的数组（新的解的集合）


class Solution {
    // 用来做记忆化递归，保存某个string对应计算出的一系列答案
    private Map<String, List<Integer>> memo = new HashMap<>();
    
    public List<Integer> diffWaysToCompute(String expression) {
        int len = expression.length();
        // 检查memo
        List<Integer> result = memo.get(expression);
        if (result != null) return result;
        result = new ArrayList<>();
        
        // base case, 当前str只有一个数字，直接放到result中，memo记录，返回
        if (isDigit(expression)) {
            result.add(Integer.parseInt(expression));
            memo.put(expression, result);
            return result;
        }
        
        // 递归, 遍历当前str每一个字符，如果是operator，递归两边求解，然后做笛卡尔积
        for (int i = 0; i < len; i++) {
            char c = expression.charAt(i);
            if (c == '*' || c == '+' || c == '-') {  //左右递归求解
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1, len));
                
                // 做笛卡尔积
                for (Integer il : left) {
                    for (Integer ir : right) {
                        switch (c) {
                            case '+': result.add(il + ir); break;
                            case '-': esult.add(il - ir); break;
                            case '*': result.add(il * ir); break;
                        }
                    }
                }
            }
        }
        // 此时笛卡尔积结果已经放入result，放入memo在返回
        memo.put(expression, result);
        return result;
    }
    
    private boolean isDigit(String s) {
        for (Character c : s.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
// Runtime: 1 ms, faster than 98.97% of Java