// 解法： stack
class StockSpanner {
    Stack<int[]> stack;     // <当前的股价,之前比其小的连续天数>
    
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && price >= stack.peek()[0])
            // If the current price is greater than stack peek.
            span += stack.pop()[1];
        
        stack.push(new int[]{price, span});
        return span;
    }
}
// faster than 79.09% of Java 
// One price will be pushed once and popped once.
// So 2 * N times stack operations and N times calls.
// Time complexity: amortized O(1)

