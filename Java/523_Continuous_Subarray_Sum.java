
/* 暴力解：
 * 求子数组或者子矩阵之和 -> 建立累加和数组或者累加和矩阵来做
 * 我们要遍历所有的子数组，然后利用累加和来快速求和。在得到每个子数组之和时，我们先和k比较，
 * 如果相同直接返回true，否则再判断，若k不为0，且sum能整除k，同样返回true。
 * 最后遍历结束返回false 
 */
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length == 0) return false;
        int n = nums.length;
        int sum;
        
        for (int i = 0; i < n; i++){
            sum = nums[i];
            for (int j = i + 1; j < n; j++){       //向后加
                sum += nums[j];
                if (sum == k) return true;
                if (k != 0 && sum % k == 0) return true;
            }
        }
        return false;
    }
}
// faster than 16.00% of Java



// 优化解法DP：Prefix Sum Reminder做法
// 原理：若数字a和b分别除以数字c，若得到的余数相同，那么(a-b)必定能够整除c
// 如果当前的累加和除以k得到的余数在hashmap中已经存在了，那么说明之前必定有一段子数组和可以整除k。
// 需要注意的是k为0的情况，由于无法取余，我们就把当前累加和放入set中。
// 我们每次存入map中的是remainder，而不是当前的累积和. 这里我们建立余数和当前位置之间的映射，
// 有了位置信息，之前用保存的坐标和当前位置i比较判断就可以了
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();// 余数和当前位置之间的映射
        map.put(0, -1);
        int n = nums.length, sum = 0;
        
        for (int i = 0; i < n; i++){
            sum += nums[i];
            int rem = (k == 0) ? sum : (sum % k);
            if (map.containsKey(rem)){
                if (i - map.get(rem) > 1) 
                    return true;
            }
            else map.put(rem, i);
        }
        return false;
    }
}
// faster than 44.61% of Java 
// Time complexity: O(n)
// Space complexity: O(min(n, k))
