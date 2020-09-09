class MyQueue {
    private Deque<Integer> in;
    private Deque<Integer> out;
    
    /** Initialize your data structure here. */
    public MyQueue() {
        in = new ArrayDeque<>();
        out = new ArrayDeque<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        in.addFirst(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        in2out();
        return out.removeFirst();
    }
    
    /** Get the front element. */
    public int peek() {
        in2out();
        return out.peekFirst();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
    
    /* 重点：把in_stack元素转移到out_stack中
    /* if out stack is not empty, pop from the out stack
    /* if out stack is empty, transmit in to out, then pop out
    */
    private void in2out() {
        if (out.isEmpty()){
            while(!in.isEmpty())
                out.addFirst(in.removeFirst());
        }
    }
}
// faster than 100.00% of Java