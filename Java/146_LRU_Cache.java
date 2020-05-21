// Sourse: https://leetcode.com/problems/lru-cache/

class LRUCache {
    private Map<Integer, Node> cache;// 映射到 Node(key, val)
    private DoublyLinkedList list;
    private int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.list = new DoublyLinkedList();
    }
    
    public int get(int key) {
        if(!cache.containsKey(key))
            return -1;
        int val = cache.get(key).value;
        Node n = cache.get(key);

        // Update, 利用 put 方法把该数据提前
        list.remove(n);
        list.insertHead(n);//mdouble linklist 更新顺序
        cache.put(key, n);
        return val;
    }
    
    public void put(int key, int value) {
        Node n = new Node(key, value);
        if (cache.containsKey(key))
            // 等于更新顺序,删除旧的节点，(之后)新的插到头部
            list.remove(cache.get(key));
        else if (cache.size() >= capacity){
            // 删除链表最后一个数据
            int k = list.removeTail();
            cache.remove(k);
        }
        // 直接添加到头部
        list.insertHead(n);
        cache.put(key,n);
    }     
}

//双链表的节点类
class Node {
    int value, key;
    Node prev, next;
    public Node(){};
    
    public Node(int key, int value){
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

//依靠Node类型构建一个双链表，实现几个需要的 API
class DoublyLinkedList {  
    Node head;
    Node tail;
    
    public DoublyLinkedList (){
        head = new Node();
        tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    // 在链表头部添加节点 x，时间 O(1)
    public void insertHead(Node n){
        n.prev = head;
        n.next = head.next;
        head.next.prev = n;
        head.next = n;
    }

    // 删除链表中的n节点（x 一定存在)，时间 O(1)
    public void remove(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
    
    // 删除链表中最后一个节点，并返回该节点的value，时间 O(1)
    public int removeTail(){
        Node n = tail.prev;
        int key = n.key;
        remove(n);
        return key;
    }
}    
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// Result: faster than 18.99%