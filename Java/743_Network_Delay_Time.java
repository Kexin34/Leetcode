// Dijkstra's + BFS
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> weightAdj = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]> (
            (int[] a, int[] b) -> a[1] - b[1] // [1]=>按照weight从小到大排列
        );
        
        // 添加每一条边，建立weightAdj
        for (int[] edge : times){
            // 哈希表weightAdj存着key为每个node u，value为list of pair(连接的node v，距离 w）
            // 每个 node u都有几个pair(统称adj），所以是list of int[]
            List<int[]> adj = weightAdj.getOrDefault(edge[0], new ArrayList<>());
            adj.add(new int[]{edge[1], edge[2]}); // add {v,weight} to u
            weightAdj.put(edge[0], adj);
        }
        
        int res = Integer.MIN_VALUE;
        
        // 哈希表dist的Key是node#, value是到K的距离. 可作为visited set
        Map<Integer, Integer> dist = new HashMap<>();
        pq.add(new int[]{K, 0}); // push K into pq first
        
        while(!pq.isEmpty()){
            
            int[] cur = pq.poll();
            int node = cur[0];
            int dis = cur[1];
            
            if (dist.containsKey(node)) continue;
            dist.put(node, dis);
            res = Math.max(res, dis);

            if (weightAdj.containsKey(node)){   //如果本node有邻接点
                for (int[] adj : weightAdj.get(node)){//遍历所有，若未visited，压入pq
                    int adjNode = adj[0];
                    int adjDist = adj[1];
                    if (!dist.containsKey(adjNode))
                        pq.add(new int[]{adjNode, adjDist + dis});
                }
            }
        }
        return dist.size() == N ? res : -1; //看有无访问到所有节点
    }
}
// faster than 63.53% of Java
// Time: O(ElogE)，遍历edge
// space: O(N+E)



// Floyd–Warshall algorithm

class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        // Step 1：填充距离矩阵
        double[][] disTo = new double[N][N];
        
        for (int i = 0; i < N; i++)//先全部为正无穷
            Arrays.fill(disTo[i], Double.POSITIVE_INFINITY);
        
        for (int i = 0; i < N; i++) //对脚线 -> 到自己的距离为0
            disTo[i][i] = 0;
        
        for (int[] edge : times) // node序列从1开始，array需要-1
            disTo[edge[0] - 1][edge[1] - 1] = edge[2];// [u][v] = w
        
        for (int k = 0; k < N; k++){
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    if (disTo[i][j] > disTo[i][k] + disTo[k][j])
                        disTo[i][j] = disTo[i][k] + disTo[k][j];
                }
            }
        }
        double ans = Double.MIN_VALUE;
        
        // 遍历所有node，找到它和K的最短距离（存在disTo[K-1][i]），返回最大数
        for (int i = 0; i < N; i++){
            // 若有未visit的node，说明有未连通分量，无法从k点reach
            if (disTo[K - 1][i] == Double.POSITIVE_INFINITY) 
                return -1; 
            ans = Math.max(ans, disTo[K - 1][i]);
        }
        return (int) ans;
    }
}
// Time : O(n^3)
// Space: O(n^2);
//faster than 47.35% of Java


