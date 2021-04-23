// 考trie树

class WordDictionary {
    class TrieNode {
        private boolean isEnd;
        TrieNode[] next;
        
        public TrieNode(){
            isEnd = false;
            next = new TrieNode[26];
        }
    }

    private TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()){
            if (cur.next[ch - 'a'] == null)  // 如果不存在子节点就创建
                cur.next[ch - 'a'] = new TrieNode();
            cur = cur.next[ch - 'a'];  // 进到下一层
        }
        cur.isEnd = true;
    }
    
    public boolean search(String word) {
        return find(word, 0, root);
    }
    
    // 递归搜索
    private boolean find(String word, int index, TrieNode now) {
        // 结束条件
        if (index == word.length())
            return now.isEnd;
        
        Character ch = word.charAt(index);
        // 如果当前是'.'  需要遍历当前节点已经实体化的所有next节点，递归搜索
        if (ch == '.'){
            for (int i = 0; i < 26; i++){
                if (now.next[i] != null){
                    if (find(word, index + 1, now.next[i]))
                        return true;
                }
            }
        }else if (now.next[ch - 'a'] != null){  // 如果当前char存在，进下一层搜索
            return find(word, index + 1, now.next[ch - 'a']);
        }
        return false;
    }
}

// Runtime: 34 ms, faster than 99.63% of Java