/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

// 解法一： DFS 
// (1, 2, 3, ... n) and (n, n-1, n-2 ... 1). 
// (n, n-1, n-2 ..., 1) can be rewritten as ( (n+1)-1, (n+1)-2, (n+1)-3, ... (n+1)-n ).
// we can add weight (n+1) for every node value (flatSum * (max + 1)), and substract 
// depthSum(1, 2, 3, ... n) to get the answer.
class Solution {
    int flatSum = 0;
    int max = 0;
    
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int depthSum = DFS(nestedList, 1);
        return flatSum * (max + 1) - depthSum;
        
    }
    
    public int DFS(List<NestedInteger> input, int depth){
        if (input == null || input.size() == 0) return 0;
        int sum = 0;    //   current depthSum
        for (NestedInteger n : input){
            if (n.isInteger()){
                max = Math.max(max, depth);
                sum += n.getInteger() * depth; //计算本深度integer总和
                flatSum += n.getInteger();
            }else
                sum += DFS(n.getList(), depth + 1);
                
        }
        return sum;
    }
}
//faster than 100.00% of Java


