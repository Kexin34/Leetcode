// object要进行flatten展开，这个放在hasNext里面

public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        pushListToStack(nestedList);
    }

    @Override
    public Integer next() {
        if (!hasNext()) return null;
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            //说明是个list，要faltten展开再添加回stack
            pushListToStack(stack.pop().getList());
        }
        return !stack.isEmpty();
    }
    
    // 用两个stack，来做到先进先出
    private void pushListToStack(List<NestedInteger> nestedList) {
        Stack<NestedInteger> temp = new Stack<>();
        for (NestedInteger nested : nestedList) 
            temp.push(nested);
        
        while (!temp.isEmpty()) 
            stack.push(temp.pop());
    }
}
// Runtime: 3 ms, faster than 39.01% of Java
// Time: O(N)，平摊每一步是O(1)