class MyHashMap {
    ListNode[] nodes = new ListNode[10000];
    class ListNode {
        int key, val;
        ListNode next;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int i = idx(key);
        // 如果当前bucket为空，新建list的dummy头结点
        if (nodes[i] == null)
            nodes[i] = new ListNode(-1, -1);
        // 在这个bucket里面遍历，看有没有存这个key的node
        ListNode prev = find(nodes[i], key);
        // 如果当前buckey没有这个key，新建这个node并放到list末尾
        if (prev.next == null)
            prev.next = new ListNode(key, value);
        // 如果当前bucket已经有这个key，更新key的value值
        else
            prev.next.val = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int i = idx(key);
        if (nodes[i] == null) return -1;
        ListNode node = find(nodes[i], key);
        return node.next == null ? -1 : node.next.val;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int i = idx(key);
        if (nodes[i] == null) return;
        ListNode prev = find(nodes[i], key);
        if (prev.next == null) return;
        prev.next = prev.next.next;
    }
    
    // Helper functions
    // 找出这个key对应的index（通过module找出该存放在哪一个list中）
    private int idx(int key){
        return Integer.hashCode(key) % nodes.length;
    }
    
    // 进入这个index的linked list，一个个遍历node看是否存在这个value
    // 返回的是目标节点的前一个节点（为了日后可以remove节点）
    ListNode find(ListNode bucket, int key){
        ListNode node = bucket;
        ListNode prev = null;
        while (node != null && node.key != key){
            prev = node;
            node = node.next;
        }
        return prev;
    }
}
// Runtime: 12 ms, faster than 88.26% of Java
