// BFS
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String word : wordList) 
            dict.add(word);
        if (!dict.contains(endWord)) return 0;
        
        Queue<String> queue = new ArrayDeque<>(); // 用来做BFS
        queue.offer(beginWord);
        
        int wordLen = beginWord.length();
        int steps = 0;
        
        while (!queue.isEmpty()){
            steps++;
            // 遍历本层所有node
            for (int s = queue.size(); s > 0; --s){
                String word = queue.poll();
                char[] chs = word.toCharArray();
                // 对本word的每个字母都进行26字母替换
                for (int i = 0; i < wordLen; ++i){
                    char ch = chs[i];   // 将要被替换的字母+位置
                    for (char c = 'a'; c <= 'z'; ++c){
                        if (c == ch) continue;
                        chs[i] = c;     // 替换好
                        String tmp = new String(chs);
                        // 检查新替换好的word是否是target或者在库里面
                        if (tmp.equals(endWord)) return steps + 1;//find, 结束
                        if (!dict.contains(tmp)) continue;
                        dict.remove(tmp); //为防止有环，删掉字典里访问了的word
                        queue.offer(tmp);
                    }
                    chs[i] = ch;        //resume, 为了backtrack
                }
            }
        }
        return 0;
    }
}
// faster than 68.69% of Java

// Bidirectional Breadth First Search
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String word : wordList)  dict.add(word);
        if (!dict.contains(endWord)) return 0;
        
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add(beginWord);
        q2.add(endWord);
        int woedLen = beginWord.length();
        int steps = 0;
        
        while (!q1.isEmpty() && !q2.isEmpty()){
            ++steps;
            
            if (q1.size() > q2.size()){   // 优化：只有q1>q2时才swap去处理q2（先拓展size较小的queue
                Set<String> tmp = q1;
                q1 = q2;
                q2 = tmp;
            }
            Set<String> q = new HashSet<>();
            for (String word : q1){       // 遍历本层所有node
                // 对本word的每个字母都进行26字母替换
                char[] chs = word.toCharArray();
                for (int i = 0; i < woedLen; ++i){
                    char ch = chs[i];       // 将要被替换的字母
                    for (char c = 'a'; c <= 'z'; ++c){
                        chs[i] = c;
                        String tmp = new String(chs);
                        // 检查新替换好的word是否是target或者在库里面
                        if (q2.contains(tmp)) return steps + 1;//found
                        if (!dict.contains(tmp)) continue;
                        dict.remove(tmp);
                        q.add(tmp);
                    }
                    chs[i] = ch;        //为防止有环，删掉字典里访问了的word
                }
            }
            q1 = q;     // 下一层BFS
        }
        return 0;
    }
}
// faster than 90.78% of Java 
     
