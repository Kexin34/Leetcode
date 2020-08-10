// Dijkstra's + BFS
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> weightAdj = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]> (
            (int[] a, int[] b) -> a[1] - b[1] // [1]=>按照weight从小到大排列
        );
        
        // 添加每一条边，建立weightAdj
        for (int[] edge : times){
            List<int[]> adj = weightAdj.getOrDefault(edge[0], new ArrayList<>());
            adj.add(new int[]{edge[1], edge[2]}); // add {v,weight} to u
            weightAdj.put(edge[0], adj);
        }
        
        int res = Integer.MIN_VALUE;
        
        //Key is node, value is distance from origin node K. It also acts as a visited set
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
        double[][]disTo = new double[N][N];
        
        for (int i = 0; i < N; i++)
            Arrays.fill(disTo[i], Double.POSITIVE_INFINITY);
        
        for (int i = 0; i < N; i++)
            disTo[i][i] = 0;
        
        for (int[] edge : times)
            disTo[edge[0]- 1][edge[1] - 1] = edge[2];// [u][v] = w
        
        
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (disTo[i][j] > disTo[i][k] + disTo[k][j])
                        disTo[i][j] = disTo[i][k] + disTo[k][j];
                }
            }
        }
        double ans = Double.MIN_VALUE;
        
        for (int i = 0; i < N; i++){
           //有未visit的，说明有未连通分量，无法从k点reach
           if (disTo[K - 1][i] == Double.POSITIVE_INFINITY) return -1; 
           ans = Math.max(ans, disTo[K - 1][i]);
        }
        return (int) ans;
    }
}
//faster than 47.35% of Java


