// 看整个图是否是全连接
// 遍历整个图，看最后结果#node是否等于原图#node

// DFS (recursive)
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>(); // 存放访问过的node
        dfs(rooms, 0, visited);
        return visited.size() == rooms.size();
    }
    
    public void dfs(List<List<Integer>> rooms, int cur, Set<Integer> visited){
        if (visited.contains(cur)) return;
        visited.add(cur);
        for (Integer key : rooms.get(cur))
            dfs(rooms, key, visited);
    }
}


// DFS (Interation)
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<Integer>();
        stack.add(0);
        visited.add(0);
        
        while (!stack.isEmpty()){
            int cur = stack.pop();
            for (int next : rooms.get(cur))
                if (!visited.contains(next)){
                    stack.push(next);
                    visited.add(next);
                }
        }
        return visited.size() == rooms.size();
    }
}

