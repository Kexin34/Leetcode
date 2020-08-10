// graph + DFS 解法
public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        
        /* Build graph. */
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] result = new double[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            result[i] = getPathWeight(queries[i][0], queries[i][1], new HashSet<>(), graph);
        }  
        
        return result;
    }
    
    private double getPathWeight(String start, String end, Set<String> visited, 
        Map<String, Map<String, Double>> graph) {
        
        /* Rejection case. */
        if (!graph.containsKey(start)) 
            return -1.0;
        
        /* Accepting case. */
        if (graph.get(start).containsKey(end))
            return graph.get(start).get(end);
        
        visited.add(start);
        for (Map.Entry<String, Double> neighbour : graph.get(start).entrySet()) {
            // 还未访问过
            if (!visited.contains(neighbour.getKey())) {
                double productWeight = getPathWeight(neighbour.getKey(), end, visited, graph);
                if (productWeight != -1.0)//如果是有效返回值
                    return neighbour.getValue() * productWeight;
            }
        }
        
        return -1.0;
    }
    
    private Map<String, Map<String, Double>> buildGraph(String[][] equations, double[] values) {
        //出发node, 它所有目的到达nodes名字和对应value
        Map<String, Map<String, Double>> graph = new HashMap<>();
        String u, v;
        
        for (int i = 0; i < equations.length; i++) {
            u = equations[i][0];
            v = equations[i][1];
            graph.putIfAbsent(u, new HashMap<>());
            graph.get(u).put(v, values[i]);
            // 可以写成graph.computeIfAbsent(u, l -> new HashMap<String, Double>()).put(v, values[i]);
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(v).put(u, 1 / values[i]);
        }
        
        return graph;
    }

/*************************************************************************************************************/

// Union Find 解法（我copy过来的）

class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, String> parent = new HashMap<>();  //<node, parent of the node>
        Map<String, Double> ratio = new HashMap<>();   //<node, node / parent>
        // 建立union关系
        for (int i = 0; i < equations.length; i++) {
            union(parent, ratio, equations[i][0], equations[i][1], values[i]);
        }
        
        double[] res = new double[queries.length];

        // 处理queries
        for (int i = 0; i < queries.length; i++) {
            String s1 = queries[i][0];
            String s2 = queries[i][1];
            if (!parent.containsKey(s1) || !parent.containsKey(s2)
                || !find(parent, ratio, s1).equals(find(parent, ratio, s2))) {
                res[i] = -1.0;  // 如果其中一个不在图里or两者没有关系 -> 无效值
            } else {
                res[i] = ratio.get(s1) / ratio.get(s2);
            }
        }
        return res;
    }
    
    private void union(Map<String, String> parent, Map<String, Double> ratio, String s1, String s2, double val) {
            if (!parent.containsKey(s1)) {
                parent.put(s1, s1);
                ratio.put(s1, 1.0);
            }
            if (!parent.containsKey(s2)) {
                parent.put(s2, s2);
                ratio.put(s2, 1.0);
            }
            String p1 = find(parent, ratio, s1);
            String p2 = find(parent, ratio, s2);
            parent.put(p1, p2);
            ratio.put(p1, val * ratio.get(s2) / ratio.get(s1));
    }
    
    private String find(Map<String, String> parent, Map<String, Double> ratio, String s) {
        if (s.equals(parent.get(s))) {
            return s;
        }
        String father = parent.get(s);
        String grandpa = find(parent, ratio, father);
        parent.put(s, grandpa);
        ratio.put(s, ratio.get(s) * ratio.get(father));
        return grandpa;
    }
}