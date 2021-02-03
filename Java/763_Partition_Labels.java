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


// 把哈希表改为数组（题目说全部都是小写字母）
class Solution {
  public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0)
            return null;
        
        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];  // record the last index of the each char

        for(int i = 0; i < S.length(); i++)
            map[S.charAt(i)-'a'] = i;
        
        // record the end index of the current sub string
        int last = 0;
        int start = 0;
        for(int i = 0; i < S.length(); i++){
            last = Math.max(last, map[S.charAt(i)-'a']);
            if(last == i){
                list.add(last - start + 1);
                start = last + 1;
            }
        }
        return list;
    }
}
// Runtime: 3 ms, faster than 85.13% of Java