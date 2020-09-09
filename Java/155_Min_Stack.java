// 解法：两个stack，用queue模拟stack
// 思路：用两个栈实现，一个最小栈始终保证最小值在顶部
class MinStack {
    private Deque<Integer> stack;       // 数据栈
    private Deque<Integer> minStack;    // 辅助栈
    private int min;
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        // 数据栈和辅助栈一定会添加元素, min更新，辅助栈添加新min
        stack.offerFirst(x);
        min = Math.min(min, x);
        minStack.offerFirst(min);
    }
    
    public void pop() {
        // 两个栈都要pop，更新pop后min值
        stack.pollFirst();
        minStack.pollFirst();
        min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peekFirst();
    }
    
    public int top() {
        // 常规检查stack peek元素
        return stack.peekFirst();
    }
    
    public int getMin() {
        return minStack.peekFirst();
    }
}
// faster than 69.50% of Java 
