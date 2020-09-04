// 解法一：DFS + backtracking (模板解，可以作为初始暴力解，之后优化)
class Solution {
    int m, n; 
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board.length == 0 || board[0].length == 0 || words.length == 0)
            return res;
        Set<String> set = new HashSet<>();
        for (String word : words) set.add(word);
        
        for (String word : set)
            if (exist(board, word)) res.add(word);
        
        return res;
    }
    // 子函数：在board是否存在某个word
    private boolean exist(char[][] board, String word){
        m = board.length; 
        n = board[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (search(board, word, 0, i, j))
                    return true;
            }
        }
        return false;
    }
    // 子函数：DFS四个方向搜索单词搜索
    private boolean search(char[][] board, String word, int idx, int r, int c){
        if (r < 0 || r >= m || c < 0 || c >= n || word.charAt(idx) != board[r][c])
            return false;
        // Found the last char of the word,搜索结束
        if (idx == word.length() - 1) return true;
        char cur = board[r][c];         // 用于之后resume
        board[r][c] = '#';              // 等于本次visited
        boolean found = search(board, word, idx + 1, r + 1, c)
              || search(board, word, idx + 1, r - 1, c)
              || search(board, word, idx + 1, r, c + 1)
              || search(board, word, idx + 1, r, c - 1);
        
        board[r][c] = cur;      // backtrack后resume
        return found;
    }
}
// faster than 18.26% of Java
// Time complexity: O(sum(m*n*4^l))
// Space complexity: O(l)





// 解法二（优化）：Trie + DFS
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;
        
        // 通过words建立trie
        TrieNode root = buildTrie(words);
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dfs(board, i, j, root, res);
        
        return res;
    }
    
    private void dfs(char[][] board, int r, int c, TrieNode p, List<String> res){
        int m = board.length;
        int n = board[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n) return;
        
        char cur = board[r][c];
        //如果不存在添加了本cell之后组成的prefix(无cur对应的next node），无效，返回
        if (cur == '#' || p.next[cur - 'a'] == null) return;
        p = p.next[cur - 'a'];  // trie向下走一步
        
        if (p.word != null) {   //目前node拥有trie的合法单词，添加到result
            res.add(p.word);
            p.word = null;     // de-duplicate，之后不会再重复搜索到同一个word
        }
       
        board[r][c] = '#';          // 等于本次visited
        dfs(board, r + 1, c, p, res);
        dfs(board, r - 1, c, p, res);
        dfs(board, r, c + 1, p, res);
        dfs(board, r, c - 1, p, res);
        board[r][c] = cur;          // backtrack后resume
    }
    
   public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
           }
           p.word = w;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
}
// faster than 61.96% of Java 