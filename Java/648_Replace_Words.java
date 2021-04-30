// Trie
class Solution {
    private TrieNode root;
    
    public String replaceWords(List<String> dictionary, String sentence) {
        root = new TrieNode();
        
        for (String word : dictionary) 
            root.insert(word);
        
        String[] words = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            sb.append(root.startsWith(words[i]));
            if (i != words.length - 1)   // 单词中间添加空格
                sb.append(" ");
        }
        return sb.toString();
    }
    
    class TrieNode {
        boolean isEnd;
        TrieNode[] next;
        String str;
        
        TrieNode() {
            this.isEnd = false;
            next = new TrieNode[26];
            this.str = "";
        }
        
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null)
                    node.next[c - 'a'] = new TrieNode();
                node = node.next[c - 'a'];
            }
            node.isEnd = true;
            node.str = word;
        }
        
        public String startsWith(String prefix) {
            TrieNode node = root;
            for (char ch : prefix.toCharArray()) {
                node = node.next[ch - 'a'];
                if (node == null) return prefix;  // 不匹配，返回原本单词
                if (node.isEnd) return node.str;  // 找到匹配的完整prefix，返回前缀
            }
            return prefix;
        }
    }
}
// Runtime: 17 ms, faster than 53.43% of Java


