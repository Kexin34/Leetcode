// 1. Find Numbers with Even Number of Digits
// Given an array nums of integers, return how many of them contain an even number of digits.
class Solution {
    public int findNumbers(int[] nums) {
        int res = 0;
        for (int num : nums){
            int count = 0;
            while (num > 0){
                count++;
                num = num / 10;
            }
            if (count % 2 == 0)
                res++;
        }
        return res;
    }
}
// 最优解
class Solution {
    public int findNumbers(int[] nums) {
        int res = 0;
        for (int num : nums){
            if (num > 9 && num < 100 || num > 999 && num <= 9999 || num == 100000)
                res++;
        }
        return res;
    }
}




// 2. Check If N and Its Double Exist
// Given an array arr of integers, check if there exists two integers N and M 
// such that N is the double of M ( i.e. N = 2 * M). 
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();//<arr element>
            
        for (int i : arr){
            // i is half or 2 times of a number in set.
            if (set.contains(i * 2) || i % 2 == 0 && set.contains(i / 2))
                return true;
            set.add(i);
        } 
        return false;
    }
}
// Time & space: O(n), n = arr.length.



// 3. Replace Elements with Greatest Element on Right Side
// Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.
// Input: arr = [17,18,5,4,6,1]
// Output: [18,6,6,6,1,-1]
// 暴力解：
class Solution {
    public int[] replaceElements(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++){
            int max = Integer.MIN_VALUE;
            for (int j = i + 1; j < arr.length; j++)
                max = Math.max(max, arr[j]);
            
            arr[i] = max;   
        }
        arr[arr.length - 1] = -1;
        return arr;
    }
}

// 最优解：反过来遍历
class Solution {
    public int[] replaceElements(int[] arr) {
        int max = -1;       //represent the max on the right.
        int n = arr.length;
        int a;
        // 从右到左遍历一遍
        for (int i = n - 1; i >= 0; i--){
            a = arr[i];
            arr[i] = max;
            max = Math.max(max, a);
        }
        return arr;
    }
}
// Time O(N)
// Space O(1)




// 4. Height Checker
// 不是特别会
class Solution {
    public int heightChecker(int[] heights) {
        int[] bucket = new int[101];
        for(int number : heights) {
            bucket[number]++;
        }
        // 重点：check the ammount of disparities between the input array and the bucket
        int count = 0, index = 0;
        
        for (int i = 0; i <= 100; i++){
            while(bucket[i] > 0){
                if(i != heights[index++]) count++;
                bucket[i]--;
            }
        }
        return count;
    }
}



// 5. Max Consecutive Ones II
// The idea is to keep a window [l, h] that contains at most 1 zero
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, zero = 0;
        for (int l = 0, h = 0; h < nums.length; h++){
            if (nums[h] == 0)
                zero++;
            while (zero > 1){
                if (nums[l] == 0)
                    zero--;
                l++;    
            }
         max = Math.max(max, h - l + 1);
        }
        return max;
    }
}
// Time: O(n) Space: O(1)
// Follow-up
// Here q stores the index of zero within the window [l, h]
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, q = -1;
        for (int l = 0, h = 0; h < nums.length; h++){
            if (nums[h] == 0){
                l = q + 1;
                q = h;
            }  
         max = Math.max(max, h - l + 1);
        }
        return max;
    }
}










