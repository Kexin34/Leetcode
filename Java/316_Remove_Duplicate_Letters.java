// 哈希表记录次数

class Solution {
    public String removeDuplicateLetters(String s) {
        // 哈希表可用数组代替
        int[] map = new int[256];
        int[] visited = new int[256];
        StringBuilder sb = new StringBuilder("0");  // 放入一个0作为之后对比
        char[] ch = s.toCharArray();
        
        // 记录每个字母出现次数
        for (char c: ch)
            map[c-'a']++;
        
        for (char c: ch){
            int index = c - 'a';
            map[index]--;
            if (visited[index] == 1) continue;// 说明该字母已经出现在结果中并且位置已经安排妥当

            // map[lastLetter]不为0说明后面还会出现lastLetter这个字母
            while (c < sb.charAt(sb.length() - 1) && map[sb.charAt(sb.length() - 1)-'a'] != 0){
                // 在结果中删去最后一个字母且将其标记为未访问
                visited[sb.charAt(sb.length() - 1)-'a'] = 0;
                sb.setLength(sb.length() - 1);  
            }
            sb.append(c);
            visited[c-'a'] = 1;
            System.out.println(sb.toString());
        }
        return sb.toString().substring(1); //去掉开头放入的0
    }
}
// Runtime: 1 ms, faster than 100.00% of Java

