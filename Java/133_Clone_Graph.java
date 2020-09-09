/* 需要解决的问题就是怎么添加当前节点的 neighbors，因为遍历当前节点的时候，
 * 它的邻居节点可能还没有生成。
*/

//解法一 简单粗暴版BFS
//首先对图进行一个 BFS，把所有节点 new 出来，不处理 neighbors ，并且把所有的节点存到 map 中。
//然后再对图做一个 BFS，因为此时所有的节点已经创建了，只需要更新所有节点的 neighbors。
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        Queue<Node> queue = new LinkedList<Node>();
        Map<Integer, Node> map = new HashMap<>();  // <原node's value, 对应新node>
        Set<Integer> visited = new HashSet<>();

        // First BFS
        queue.offer(node);
        visited.add(node.val);

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            //生成每一个copy的节点(deep copy)
            Node copied = new Node(cur.val);
            copied.neighbors = new ArrayList<>();
            map.put(cur.val, copied);
            
            /* BFS会把现节点(未访问过的）邻居全部压入queue中,用于之后dequeue遍历 */
            for (Node neighbor : cur.neighbors){
                if (visited.contains(neighbor.val)) continue;
                queue.offer(neighbor);
                visited.add(neighbor.val);
            }
        }
        
        // Second BFS
        queue = new LinkedList<Node>();
        visited = new HashSet<>();
        queue.offer(node);
        visited.add(node.val);
        
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            /* 更新新节点的neightbors */
            for (Node neighbor : cur.neighbors){
                map.get(cur.val).neighbors.add(map.get(neighbor.val));

                /* 把原节点邻居全部压入queue中之后遍历 */
                if (visited.contains(neighbor.val)) continue;
                queue.offer(neighbor);
                visited.add(neighbor.val);
            }
        }
        return map.get(node.val);
    }
}
//faster than 95.00% of Java


// 优化BFS，只需要一次遍历
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        Queue<Node> queue = new LinkedList<>();
        Map<Integer, Node> map = new HashMap<>();  // <node's value, node>
        
        queue.offer(node);
        //先copy生成第一个节点,并放入map
        Node n = new Node();
        n.val = node.val;
        map.put(n.val, n);

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for (Node temp : cur.neighbors){
                //若没有生成的节点，就生成并放入map
                if (!map.containsKey(temp.val)){
                    n = new Node(temp.val);
                    n.neighbors = new ArrayList<>();
                    map.put(n.val, n);
                    queue.offer(temp);  //注意，压入的是原链的邻居节点
                }
                //更新目前新链node的邻居(邻居也是新链的node)
                map.get(cur.val).neighbors.add(map.get(temp.val));
            }
        }
        return map.get(node.val);
    }
}
// faster than 96.80% of Java


// DFS解法，好理解
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        Map<Integer, Node> map = new HashMap<>();
        return cloneGraphHelper(node, map);
    }
    
    private Node cloneGraphHelper(Node node, Map<Integer, Node> map){
        // 如果visited存在node的克隆结点则返回克隆结点
        if(map.containsKey(node.val)) return map.get(node.val);

        //生成克隆节点
        Node cloneNode = new Node(node.val, new ArrayList());
        cloneNode.neighbors = new ArrayList<>();
        map.put(cloneNode.val, cloneNode);

        // 设置好cloneNode结点的克隆结点
        for (Node neighbor : node.neighbors){
            // 递归node邻居结点的克隆结点
            cloneNode.neighbors.add(clone(neighbor, map));
        }
        return cloneNode;
    }
}
//  faster than 96.51% of Java onl

