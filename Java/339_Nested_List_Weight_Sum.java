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

//私人答案
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        for (NestedInteger n : nestedList)
            sum += getSum(n, 1);
        return sum;
    }
    
    
    public int getSum(NestedInteger ni, int level){
        int sum = 0;
        if (ni.isInteger())
            return level * ni.getInteger();
        for (NestedInteger a : ni.getList())
            sum += getSum(a, level + 1);
        return sum;
    }
}
// faster than 100.00% of Java


// 官方答案
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }
    
    public int depthSum(List<NestedInteger> nestedList, int depth){
        int sum = 0;
        for (NestedInteger n : nestedList){
            //If a nested integer is an integer n, calculate its sum as n×d.
            if (n.isInteger())
                sum += n.getInteger() * depth;
            else
                // If the nested integer is a list, calculate the sum of this list 
                // recursively using the same process but with depth d+1.
                sum += depthSum(n.getList(), depth + 1);
        }
        return sum;
    }
}
//faster than 38.36% of Java