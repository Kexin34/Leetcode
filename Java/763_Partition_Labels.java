class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        // step1: 建立字母和其最后出现位置之间的映射
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++){
            map.put(S.charAt(i), i);
        }
        
        // Step2: 遍历字符串
        int start = 0;              // 当前子串的起始位置
        int last = 0;               // 当前子串的结束位置
        for (int i = 0; i < S.length(); i++){
            last = Math.max(last, map.get(S.charAt(i)));//提取出其最后一个位置
            // 当前子串包含了所有已出现过的字母的最后一个位置，找出之后的断开的位置
            if (i == last){
                res.add(i - start + 1);
                start = i + 1;      // start重设为新partition开头
            }
        }
        return res;
    }
}
// faster than 56.23% of Java
//  O(1) space
// O(n) time 