// 一样解法，没有手写双向链表数据结构
class LRUCache {
    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    Map<Integer, Node> map = new HashMap();
    int capacity;

    public LRUCache(int _capacity) {
        capacity = _capacity;
        head.next = tail;
        tail.prev = head;
    }  
    
    public int get(int key) {
        // 如果在map中存在，返回，然后更新其在链表的位置(删除再插入头部)
        if (map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.value;
        }else
            return -1;
    }
    
    public void put(int key, int value) {
        // 如果当前key已存在，更新（先移除，再添加）
        if (map.containsKey(key))
            remove(map.get(key));
        // 如果cache满了，把移除链表最后一个node（再添加新node
        if(map.size() == capacity) {
          remove(tail.prev);
        }
        
        insert(new Node(key, value));
    }
  
    // 辅助函数：用于链表 & 哈希表的删除节点
    private void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
      }
    
    // 辅助函数：用于链表 & 哈希表的插入节点
    private void insert(Node node){
        map.put(node.key, node);
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    class Node {
        Node prev, next;
        int key, value;
        Node(int _key, int _value){
            key = _key;
            value = _value;
        }
    }
}
//  faster than 18.00% of Java







// Sourse: https://leetcode.com/problems/lru-cache/

class LRUCache {
    // 自己设定这个class有些什么元素（构建要用this指代）
    // 哈希表cache，双向链表list，容量capacity
    private Map<Integer, Node> cache;// 映射到 Node(key#, Node(key,val))
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
        cache.put(key, n);
    }     
}

// 双向链表的Node类, 比起普通node多一个prev指针
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

/* 双向链表class
 * 构造函数，API：在头部插入节点，删除尾部节点，删除第n个节点
*/
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
    // 要返回key值是因为cache需要key来删除所映射的node
    public int removeTail(){
        Node n = tail.prev;
        int key = n.key;
        remove(n);
        return key;
    }
} 

// Result: faster than 18.99%











