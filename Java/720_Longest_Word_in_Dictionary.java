// Trie模板

class Solution {
    private TrieNode root;
    
    public String longestWord(String[] words) {
        String best = "";
        root = new TrieNode();
        
        for (String word : words)
            root.insert(word);
        
        for (String word : words) { 
            // 首先要先filter一下可能选项，如果长度大于best或者相等时lexically更大，不需要去搜寻判断了
            if (word.length() < best.length() 
                || (word.length() == best.length() && word.compareTo (best) > 0)){
                continue;
            }
            
            if (root.hasAllPrefixes(word)){
                best = word;
            }
        }
        return best;
    }
    
    class TrieNode {
        private boolean isEnd;
        TrieNode[] next;
        TrieNode() {
            this.isEnd = false;
            next = new TrieNode[26];
        }
        
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null)
                    node.next[c - 'a'] = new TrieNode();
                node = node.next[c - 'a'];
            }
            node.isEnd = true;
        }
        // 模板修改：每层遍历都要检查isEnd，如果当前遍历层没有isEnd，说明不对
        public boolean hasAllPrefixes(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null) return false;
                node = node.next[c - 'a'];
                if (node.isEnd == false) return false;
            }
            return true;
        }
    }
}
// Runtime: 5 ms, faster than 99.74% of Java 
