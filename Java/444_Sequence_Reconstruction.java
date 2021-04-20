// 判断是否只存在一个拓扑排序的序列
// 制药保证队列中一直最多只有一个元素

// 拓扑排序的路数 
// 1. 构建两个字典，一个用来存放入度，一个用来存放邻居，用org里的元素初始化字典 
// 2. 要防止seqs里面给出不合法的节点，如果发现直接返回False 
// 3. 确保seqs里的节点个数和orgs相等

class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        // Initialize the graph and inDegree map
        for (List<Integer> list : seqs){
            for (int i = 0; i < list.size(); i++){
                inDegree.putIfAbsent(list.get(i), 0);
                map.putIfAbsent(list.get(i), new ArrayList<>());
            }
        }
        
        // Return false if the #nodes are not equal to the count in the original array
        if (inDegree.size() != org.length) return false;
        
        // Create the graph and set the indegree map at the same time
        for (List<Integer> list : seqs){
            for (int i = 0; i < list.size() - 1; i++){
                int from = list.get(i);
                int to = list.get(i + 1);
                // Check to not increment the indegree count twice for the same from-to relationship
                if(!map.get(from).contains(to)) {
                    map.get(from).add(to);
                    inDegree.put(to, inDegree.get(to) + 1);
                }
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        // 找出入度为0的点，放入queue作为起点
        for (int key : inDegree.keySet()){
            if (inDegree.get(key) == 0) 
                queue.offer(key);
        }
        
        // Actual Topological sort logic :
        // Check for sources with indegree as 0 and decrement the indegree count for the current nodes children
        // Once a nodes indegree becomes 0, add it to the queue
        int [] res = new int [org.length];
        int idx = 0;
        
        while (!queue.isEmpty()){
            if (queue.size() > 1) return false;
            int from = queue.poll();
            List<Integer> toList = map.get(from);
            res[idx++] = from;
            for (int to : toList){
                inDegree.put(to, inDegree.get(to) - 1);
                if (inDegree.get(to) == 0)
                    queue.offer(to);
            }
        }
        // Compare the result of Topological sort with the original array
        for (int i = 0; i < org.length; i++){
            if (res[i] != org[i])
                return false;
        }
        return true;
    }
}

