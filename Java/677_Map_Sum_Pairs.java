// 解法： 前缀树
class MapSum {
    private class Node{
        int value;
        Node[] child = new Node[26];
    }
    private Node root = new Node();
    /** Initialize your data structure here. */
    public MapSum() {
        
    }
    
    public void insert(String key, int val) {
        insert(key, root, val);
    }

    // insert子函数：DFS递归插入
    private void insert(String key, Node node, int val) {
        if (key.length() == 0){ //递归结束条件: 字符串访问到底，在结束位置放入val
            node.value = val;
            return;
        }
        int index = indexForChar(key.charAt(0));// 拿到当前要处理的char的位置
        // 如果当前节点没有这个char的对应child，创建
        if (node.child[index] == null)
            node.child[index] = new Node();
        // 递归的去插入一下个string中的char，当前节点next数组中所有的MapSum
        insert(key.substring(1), node.child[index], val);
    }
    
    public int sum(String prefix) {
        return sum(prefix, root);
    }

    // sum子函数：递归，返回所有以该前缀开头的键的值的总和
    private int sum(String prefix, Node node) {
        if (node== null) return 0;// node递归到底
        //递归：向下，拿到当前node 所对应（char）的child node
        if (prefix.length() != 0){
            int index = indexForChar(prefix.charAt(0));
            return sum(prefix.substring(1), node.child[index]);
        }
        // 当前prefix到底，sum就是当前node值再加上它（递归）child的sum值
        int sum = node.value;
        for (Node child: node.child )
            sum += sum(prefix, child);
        return sum;
    }
    
    private int indexForChar(char ch){
        return ch -'a';
    }
}
// faster than 97.09% of Java

// Time: 每个插入或Sum操作都是O(K), K是key的长度。
// Space：和总输入大小称线性