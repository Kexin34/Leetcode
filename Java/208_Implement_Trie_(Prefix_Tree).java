class Trie {
    class TrieNode{
        private boolean isEnd;  // 该节点是否是一个字符串的结束
        TrieNode[] next;        // 字母映射表
        
        public TrieNode() {
            isEnd = false;
            next = new TrieNode[26];
        }
    }

    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (char ch: word.toCharArray()){
            if (node.next[ch - 'a'] == null)
                node.next[ch - 'a'] = new TrieNode();// 开辟新的节点
            node = node.next[ch - 'a'];
        }
        node.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch: word.toCharArray()) {
            node = node.next[ch - 'a'];
            if (node == null) return false;
        }
        return node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.next[ch - 'a'];
            if (node == null) return false;
        }
        return true;
    }
}
//  faster than 64.35% of Java


