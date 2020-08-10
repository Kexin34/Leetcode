// 简化版递归 Hierholzer's algorith
// 这个版本效率最高，最好理解，推荐
class Solution {
    // 哈希表：origin -> list of destinations
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> route = new LinkedList();
    
    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1). build the graph first
        for (List<String> ticket : tickets)
            map.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        
        // Step 2). post-order DFS
        visit("JFK");
        return route;
    }
    
    public void visit(String origin){
        while(map.containsKey(origin) && !map.get(origin).isEmpty())
            // while we visit the edge, we trim it off from graph.
            visit(map.get(origin).poll());
        // add the airport to the head of the itinerary
        route.add(0, origin);
    }
}
//  faster than 75.86% of Java


// 上面的遍历版本
// 需要借助栈来实现，来实现回溯功能
// 哈希表：一个src机场映射一个优先队列（可以按照字母顺序从小到大存放dst机场）
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new LinkedList<>();
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        Stack<String> stack = new Stack<>();
        
        // 把所有机票按照src放入哈希表, 首次映射需要建立优先序列再添加dst
        for (List<String> ticket : tickets)
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        
        stack.push("JFK");
        while (!stack.isEmpty()){
            // 如果当前节点映射的优先序列里有节点，
            while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty()){
                //取出这个dst节点，将其在pq里删掉，然后继续遍历(压入stack）这个节点
                String cur = targets.get(stack.peek()).poll();
                stack.push(cur);
            }
            //最后结果中存的顺序和我们需要的相反，最后再翻转一下即可
            res.add(0, stack.pop());//把src加入答案
        }
        return res;
    }
}



// 官方：Hierholzer's algorith (上面的是优化版，这个很啰嗦)
class Solution {
    // 哈希表：origin -> list of destinations
    HashMap<String, LinkedList<String>> route = new HashMap<>();
    LinkedList<String> res = new LinkedList<String>();
    
    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1). build the graph first
        for (List<String> ticket : tickets){
            String origin = ticket.get(0);
            String dest = ticket.get(1);
            if (route.containsKey(origin))
                route.get(origin).add(dest);
            else{
                LinkedList<String> destList = new LinkedList<String>();
                destList.add(dest);
                route.put(origin, destList);
            }
        }
        
        // Step 2). order the destinations
        route.forEach((key, value) -> Collections.sort(value));
        
        // Step 3). post-order DFS
        DFS("JFK");
        return res;
    }
    
    public void DFS(String origin){
        // Visit all the outgoing edges first.
        if (route.containsKey(origin)){
            LinkedList<String> destList = route.get(origin);
            while (!destList.isEmpty()){
                // while we visit the edge, we trim it off from graph.
                String dest = destList.pollFirst();
                DFS(dest);
            }
        }
        // add the airport to the head of the itinerary
        res.offerFirst(origin);
    }
}