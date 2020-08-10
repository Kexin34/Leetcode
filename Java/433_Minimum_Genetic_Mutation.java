// BFS Finn shortest path

class Solution {
    public int minMutation(String start, String end, String[] bank) {
        boolean[] visited = new boolean[bank.length];
        Queue<String> queue = new ArrayDeque<>();
        //Queue<String> queue = new LinkedList<>();
        int numMutations = 0;
        
        queue.offer(start);
        
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                String cur = queue.poll();
                if (cur.equals(end)) return numMutations;
                // 匹配本string和bank中所有只差一位的gene
                for (int j = 0; j < bank.length; j++){
                    if (visited[j] || !validMutation(cur, bank[j])) continue;
                    visited[j] = true;
                    queue.offer(bank[j]);
                
                }
            }
            numMutations++;
        }
        return -1;
    }
    // 查看两个string是否只相差一个位
    public boolean validMutation(String s1, String s2){
        boolean diff = false;
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) != s2.charAt(i)){
                if (diff) return false;
                else diff = true;
            }
        }
        return true;
    }
}
// faster than 100.00% of Java 
// Time complexity: O(n^2)
// Space complexity: O(n)




// DFS Solution. 我自己的版本
//What I do here is just compare each word in bank with start, and if the difference is just 1, 
//then I'll add that word to set, and move to the next set of words, making that word as start.
class Solution {
    int numMutation = Integer.MAX_VALUE;
        
    public int minMutation(String start, String end, String[] bank) {
        dfs(start, end, bank, 0, new HashSet<String>());
        return numMutation == Integer.MAX_VALUE? -1 : numMutation;
    }
    
    public void dfs(String start, String end, String[] bank, int path, Set<String> visited){
        if (start.equals(end)) 
            numMutation = Math.min(numMutation, path);
        
        for (String gene : bank){
            if (!validMutation(start, gene) || visited.contains(gene)) continue;
            visited.add(gene);
            dfs(gene, end, bank, path + 1, visited);
            visited.remove(gene);
        }
    }
    
    public boolean validMutation(String s1, String s2){
        boolean diff = false;
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) != s2.charAt(i)){
                if (diff) return false;
                else diff = true;
            }
        }
        return true;
    }
}
// faster than 100.00%