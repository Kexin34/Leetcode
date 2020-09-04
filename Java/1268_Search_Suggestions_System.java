// 基础解法：Binary Search
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();// <单词，数组对应其index>
        Arrays.sort(products);
        
        // sort后再从array变成list
        List<String> productsList = Arrays.asList(products);
        // 接着放入treeMap
        for (int i = 0; i < products.length; i++)
            map.put(products[i], i);
        
        String key = "";
        for (char c : searchWord.toCharArray()){    // 开始query
            key += c;
            String l = map.ceilingKey(key);         // lower bound
            String r = map.floorKey(key + "~");     // upper bound
            if (l == null || r == null) break;      // 查找失败
            res.add(productsList.subList(map.get(l), 
                                         Math.min(map.get(l) + 3, map.get(r) + 1)));
            
        }
        
        while (res.size() < searchWord.length())//如果未完结，添加空的
            res.add(new ArrayList<>());
        
        return res;
    }
}
// 把products转化成list的原因：需要在原list里根据index来提取出subList，放入result
// 用TreeMap的原因：可以用ceilingKey、floorKey函数来找到。类似找lower_bound in C++
// faster than 53.58% of Java

//Time complexity: O(nlogn + l * logn)
//Space complexity: O(1)




// 解法： Trie
class Solution {
    class Trie{
        Trie[] sub = new Trie[26];
        LinkedList<String> suggestion = new LinkedList<>();
    }
    
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();
        // build the Trie
        for (String word : products)
            insert(word, root);
        return search(searchWord, root);
    }
    
    /* 子函数：把word放到Trie中 */
    private void insert(String word, Trie root){
        Trie t = root;
        for (char c : word.toCharArray()){  // insert current product into Trie.
            if (t.sub[c - 'a'] == null)
                t.sub[c - 'a'] = new Trie();
            t = t.sub[c - 'a'];
            t.suggestion.offer(word);// put word w/ same prefix into suggestion list.
            Collections.sort(t.suggestion);
            if (t.suggestion.size() > 3)// maintain 3 lexicographically minimum strings
                t.suggestion.pollLast();
        }
    }
    
    /* 子函数：在Trie中搜索word每个字符，返回每个prefix 对应List of product */
    private List<List<String>> search(String searchWord, Trie root){
        List<List<String>> ans = new ArrayList<>();
        for (char c : searchWord.toCharArray()){   // search product.
            // if there exist products with current prefix.
            if (root != null) 
                root = root.sub[c - 'a'];
            
            // add it if there exist products with current prefix.
            ans.add(root == null ? Arrays.asList() : root.suggestion);
        }
        return ans;
    }
}
// faster than 61.70% of Java
// Initialization: Sum(len(products[i]))
// Query: O(len(searchWord))

