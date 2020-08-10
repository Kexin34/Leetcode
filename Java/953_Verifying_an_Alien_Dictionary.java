// Build a transform mapping from order,
// Find all alien words with letters in normal order.

class Solution {
    int[] mapping = new int[26];
    
    public boolean isAlienSorted(String[] words, String order) {
        // Build a transform mapping from order,
        // maaping每个order里的字母到1234.....26，index是字母，value是顺序i
        for (int i = 0; i < order.length(); i++)
            mapping[order.charAt(i) - 'a'] = i;
        
        // Find all alien words with letters in normal order.
        for (int i = 1; i < words.length; i++)
            if (bigger(words[i - 1], words[i]))   //如果前面一个大，说明乱序，false
                return false;
        
        return true;
    }
    
    // helper函数：比较两个string大小 （lexicographical order）
    //  '∅' is blank character which is less than any other character 
    public boolean bigger(String s1, String s2){
        int len1 = s1.length(), len2 = s2.length();
        // 每个字母一一比较, 当不同时，若map[s1[i]]>map[s2[i]]返回t，说明bigger，反之false
        for (int i= 0; i < len1 && i < len2; i++){
            if (s1.charAt(i) != s2.charAt(i))
                return mapping[s1.charAt(i) - 'a'] > mapping[s2.charAt(i) - 'a'];
        }
        // 此时有一方结束，prefix都一样。eg. "apple","app"
        // 先结束的比较小 (比较两个string长度即可)
        return len1 > len2;
    }
}

// faster than 100.00% of Java
// Time O(NS)
// Space O(1)

// N: length of words since we go through the words list to compare each string 
//    with its previous one.
// S: max length of word in the words list since in the helper function, 
//    the worst case is encounter the max string.
