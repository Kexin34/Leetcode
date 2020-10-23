// Single LinkedList
public class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
}

class MyLinkedList {
    int size;
    ListNode head;      // sentinel node
    
    /** Initialize your data structure here. */
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        
        ListNode cur = head;
        for (int i = 0; i < index + 1; i++)//因为是从head结点所以+1
            cur = cur.next;
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode toAdd = new ListNode(val);
        toAdd.next = head.next;
        head.next = toAdd;
        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;// 如果是负数则插在头部
        
        size++;
        ListNode toAdd = new ListNode(val);
        ListNode pred = head;
        for (int i = 0; i < index; i++)
            pred = pred.next;
        toAdd.next = pred.next;
        pred.next = toAdd;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        
        size--;
        ListNode pred = head;
        for (int i = 0; i < index; i++)
            pred = pred.next;
        pred.next = pred.next.next;
    }
}
//  faster than 72.47% of Java
// Time: O(1) for addAtHead. 
// O(k) for get, addAtIndex, and deleteAtIndex, where k is an index of the element to get, add or delete. 
// O(N) for addAtTail.
// Space: O(1) for all operations.







// 双向链表
// 利用bidirectional search
public class ListNode{
    int val;
    ListNode next;
    ListNode prev;
    ListNode(int x) {val = x;}
}

class MyLinkedList {
    int size;
    ListNode head;      // sentinel node
    ListNode tail;
    
    /** Initialize your data structure here. */
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        
        //Use bidirectional search
        ListNode cur = head;
        if (index + 1 < size - index){// 距离head更加，从head开始遍历
            for (int i = 0; i < index + 1; i++)//因为是从head结点所以+1
                cur = cur.next;
        }else{                          // 距离tail更近，从tail开始反着遍历
            cur = tail;
            for (int i = 0; i < size - index; i++)
                cur = cur.prev;
        }
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode pred = head, succ = head.next;
        size++;
        ListNode toAdd = new ListNode(val);
        toAdd.next = succ;
        toAdd.prev = pred;
        succ.prev = toAdd;
        pred.next = toAdd;
        
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode pred = tail.prev, succ = tail;
        size++;
        ListNode toAdd = new ListNode(val);
        toAdd.next = succ;
        toAdd.prev = pred;
        succ.prev = toAdd;
        pred.next = toAdd;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;// 如果是负数则插在头部
        
        ListNode toAdd = new ListNode(val);
        
        //Use bidirectional search
        ListNode pred, succ;
        if (index < size - index){// 距离head更加，从head开始遍历
            pred = head;
            for (int i = 0; i < index; i++)
                pred = pred.next;
            succ = pred.next;
        }else{                  // 距离tail更近，从tail开始反着遍历
            succ = tail;
            for (int i = 0; i < size - index; i++)
                succ = succ.prev;
            pred = succ.prev;
        }
        size++;
        toAdd.next = succ;
        toAdd.prev = pred;
        pred.next = toAdd;
        succ.prev = toAdd;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        
        ListNode pred, succ;
        // Use bidirectional search
        if (index < size - index){// 距离head更加，从head开始遍历
            pred = head;
            for (int i = 0; i < index; i++)
                pred = pred.next;
            succ = pred.next.next;
        }else{
            succ = tail;
            for (int i = 0; i < size - index - 1; i++)
                succ = succ.prev;
            pred = succ.prev.prev;
        }
        size--;
        pred.next = succ;
        succ.prev = pred;
    }
}
// faster than 100.00% of Java
// Time : O(1) for addAtHead and addAtTail. 
// O(min(k,N−k)) for get, addAtIndex, and deleteAtIndex, where k is an index of the element to get, add or delete.
// Space : O(1) for all operations.









