/* We record the timestamp that we visit each node. 
 * For each node, we check every neighbor except its parent and return a smallest timestamp 
 * in all its neighbors. If this timestamp is strictly less than the node's timestamp, 
 * we know that this node is somehow in a cycle. 
 * Otherwise, this edge from the parent to this node is a critical connection
*/
class Solution {    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)//graph中每个元素node都是一个list,存放和这node相连的node
            graph[i] = new ArrayList<>();
        // 建表
        for (List<Integer> oneConnection : connections){
            graph[oneConnection.get(0)].add(oneConnection.get(1));
            graph[oneConnection.get(1)].add(oneConnection.get(0));
        }
        
        int timer = 0;
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[n];
        //就是每个node的lowId
        int [] timeStampAtThatNode = new int[n];
        DFS(graph, -1, 0, timer, visited, results, timeStampAtThatNode);
        return results;
    }
    
    public void DFS(List<Integer>[] graph, int parent, int node, int timer, boolean[]
                                       visited, List<List<Integer>> results, int[] timeStampAtThatNode){
        // 访问本node
        visited[node] = true;
        timeStampAtThatNode[node] = timer++;
        int currentTimeStamp = timeStampAtThatNode[node];
        
        // 对于本node的每个邻接点
        for (int neighbour : graph[node]){
            if (neighbour == parent) continue; //忽略掉parent
            if (!visited[neighbour])
                DFS(graph, node, neighbour, timer, visited, results, timeStampAtThatNode);
            // DFS callback结束，开始算本node的min
            timeStampAtThatNode[node] = Math.min(timeStampAtThatNode[node], timeStampAtThatNode[neighbour]);
            // 检查有无cycle
            if (currentTimeStamp < timeStampAtThatNode[neighbour])
                // 说明不是cycle是critical connection，加入答案
                results.add(Arrays.asList(node, neighbour));
            
        }
    }
}

// faster than 26.59% of Java

