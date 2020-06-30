/* 需要解决的问题就是怎么添加当前节点的 neighbors，因为遍历当前节点的时候，
 * 它的邻居节点可能还没有生成。
*/

//解法一 简单粗暴版BFS
//首先对图进行一个 BFS，把所有节点 new 出来，不处理 neighbors ，并且把所有的节点存到 map 中。
//然后再对图做一个 BFS，因为此时所有的节点已经创建了，只需要更新所有节点的 neighbors。
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        
        /* 第一次BFS, 把所有节点new出来，不处理neighbors,并把所有的节点存到map中 */
        Queue<Node> queue = new LinkedList<>();
        Map<Integer, Node> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        
        queue.offer(node);
        visited.add(node.val);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            //生成每一个节点
            Node n = new Node();
            n.val = cur.val;
            n.neighbors = new ArrayList<Node>();
            map.put(n.val, n);  //节点存到map中

            /* BFS会把现节点邻居全部压入queue中,用于之后dequeue遍历 */
            for(Node temp : cur.neighbors){      
                if(visited.contains(temp.val))
                    continue;
                queue.offer(temp);
                visited.add(temp.val);
            }
        }
        
        /* 第二次 BFS,更新所有节点的 neightbors */
        queue = new LinkedList<>();
        queue.offer(node);
        visited = new HashSet<>();
        visited.add(node.val);
        
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            /* 更新新节点的neightbors */
            for(Node temp : cur.neighbors)
                map.get(cur.val).neighbors.add(map.get(temp.val));

            /* 把原节点邻居全部压入queue中之后遍历 */
            for(Node temp : cur.neighbors){
                if(visited.contains(temp.val))
                    continue;
                queue.offer(temp);
                visited.add(temp.val);
            }
        }
        return map.get(node.val);
    }
}
//faster than 95.00% of Java

// 优化BFS，只需要一次遍历
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        
        Queue<Node> queue = new LinkedList<>();
        Map<Integer, Node> map = new HashMap<>();
        queue.offer(node);
        //先生成第一个节点
        Node n = new Node();
        n.val = node.val;
        map.put(n.val, n);
        
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            for(Node temp : cur.neighbors){
                //若没有生成的节点，就生成并放入map
                if(!map.containsKey(temp.val)){
                    n = new Node(temp.val);
                    n.neighbors = new ArrayList<Node>();
                    map.put(n.val, n);
                    queue.offer(temp);
                }
                //更新目前新node的邻居(邻居也是新链的node)
                map.get(cur.val).neighbors.add(map.get(temp.val));
            }
        }
        return map.get(node.val);
    }
}

// DFS 解法，好理解
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        Map<Integer, Node> map = new HashMap<>();
        return cloneGraphHelper(node, map);
    }
    
    private Node cloneGraphHelper(Node node, Map<Integer, Node> map){
        if(map.containsKey(node.val))
            return map.get(node.val);
        //生成当前节点
        Node n = new Node(node.val);
        n.neighbors = new ArrayList<Node>();
        map.put(node.val, n);
        //添加它的所有邻居节点
        for(Node temp : node.neighbors){
            n.neighbors.add(cloneGraphHelper(temp, map));
        }
        return n;
    }
}
//faster than 8.11% of Java 

