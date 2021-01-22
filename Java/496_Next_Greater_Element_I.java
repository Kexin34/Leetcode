// 解法：单调栈

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();//<x, x's next greater element> 
        
        for (int num2 : nums2){
            while (!stack.isEmpty() && num2 > stack.peek()){
            	// 栈顶元素对应的next greater就是num2，关系存入map
                int smallerNum = stack.pop();
                map.put(smallerNum, num2);
            }
            stack.push(num2);
        }
        //重点：map存放的是已有的关系，找不到的就直接返回-1.
        for (int i = 0; i < nums1.length; i++)
            res[i] = map.getOrDefault(nums1[i], -1);
        return res;
    }
}
// faster than 81.61% of Java