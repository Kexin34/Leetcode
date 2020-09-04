/* We record the timestamp that we visit each node. 
 * For each node, we check every neighbor except its parent and return a smallest timestamp 
 * in all its neighbors. If this timestamp is strictly less than the node's timestamp, 
 * we know that this node is somehow in a cycle. 
 * Otherwise, this edge from the parent to this node is a critical connection
*/
class Solution {    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) //graph中每个元素node都是一个list,存放和这node相连的node
            graph[i] = new ArrayList<>();
        // 建表
        for (List<Integer> oneConnection : connections){
            graph[oneConnection.get(0)].add(oneConnection.get(1));
            graph[oneConnection.get(1)].add(oneConnection.get(0));
        }
        
        int timer = 0;
        boolean[] visited = new boolean[n];
        //就是每个node的lowId
        int [] timeStamp = new int[n];
        DFS(graph, -1, 0, timer, visited, results, timeStamp);
        return results;
    }
    
    public void DFS(List<Integer>[] graph, int parent, int node, int timer, boolean[]
                                       visited, List<List<Integer>> results, int[] timeStamp){
        // 访问本node
        visited[node] = true;
        timeStamp[node] = timer++;
        int currentTimeStamp = timeStamp[node];
        
        // 遍历本node的每个邻接点进行dfs
        for (int neighbour : graph[node]){
            if (neighbour == parent) continue; //忽略掉parent
            if (!visited[neighbour])
                DFS(graph, node, neighbour, timer, visited, results, timeStamp);

            // DFS callback结束，开始算本node的最小时间戳
            timeStamp[node] = Math.min(timeStamp[node], timeStamp[neighbour]);

            // 如果更新的邻接点时间戳大于原本时间戳，说明是从本node过去的，没有cycle
            if (currentTimeStamp < timeStamp[neighbour])
                // 说明不是cycle是critical connection，加入答案
                results.add(Arrays.asList(node, neighbour));
            
        }
    }
}

// faster than 82.02% of Java 

