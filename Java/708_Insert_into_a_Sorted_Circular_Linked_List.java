// 单次循环，不需要双指针
// 重点在于找出潜在符合条件
class Solution {
    public Node insert(Node head, int insertVal) {
        Node toAdd = new Node(insertVal);
        toAdd.next = toAdd;
        if (head == null) return toAdd;

        Node cur = head;
        while (cur.next != head){
            // 以下都是潜在符合条件
            if (insertVal == cur.val
                || (cur.val < insertVal && insertVal <= cur.next.val) // 之间
                || (cur.next.val < cur.val && cur.val < insertVal)  //大过list里所有数
                || (insertVal < cur.next.val && cur.next.val < cur.val))// 小于list里所有数
                break;
            cur = cur.next;
        }
        toAdd.next = cur.next;
        cur.next = toAdd;

        return head;
    }
}
// faster than 100.00% of Java 