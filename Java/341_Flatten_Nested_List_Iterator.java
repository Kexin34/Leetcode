
// 用列表来打平嵌套
public class NestedIterator implements Iterator<Integer> {
    private LinkedList<NestedInteger> list;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        // 不直接用nestedList的引用，是因为不能确定它的底层实现
        // 必须保证是LinkedList，否则下面的addFirst（插入头部）会很难实现
        list = new LinkedList<>(nestedList);
    }

    @Override
    public Integer next() {
        // hasNext方法保证了第一个元素一定是整数类型
        return list.remove(0).getInteger();
    }

    @Override
    public boolean hasNext() {
        // 循环拆分列表元素，直到列表第一个元素是整数类型
        while (!list.isEmpty() && !list.get(0).isInteger()){
            // 当列表开头第一个元素是列表类型是，进入循环
            List<NestedInteger> first = list.remove(0).getList();
            // 将第一个列表打平并按顺序添加到开头
            for (int i = first.size() - 1; i >= 0; i--)
                list.addFirst(first.get(i));
        }
        return !list.isEmpty();
    }
}
// Runtime: 2 ms, faster than 97.36% of Java










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