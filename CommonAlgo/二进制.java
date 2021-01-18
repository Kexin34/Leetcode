/*
常见二进制操作:
	基本操作
		a=0^a=a^0
		0=a^a
		由上面两个推导出：a=a^b^b

	交换两个数
		a=a^b
		b=a^b
		a=a^b

	移除最后一个 1
		a = n & (n-1)

	获取最后一个 1
		diff = (n & (n-1)) ^n
*/ 



// 136. Single Number
// 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums)
            res = res ^ num;
        return res;
    }
}



// 137. Single Number II
// 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        // 统计每位的‘1’的个数
        for (int i = 0; i < 32; i++){
            int sum = 0;    //sum记录了所有nums数字在i位共有多少个‘1’
            // 遍历nums中每一个数
            for (int num : nums)
                sum += (num >> i) & 1;      // 在i位统计1的个数
            
            // 还原位00^10=10 或者用| 也可以
            res = res | (sum % 3) << i;
        }
        return res;
    }
}



// 260. Single Number III
// 给定一个整数数组  nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
// 两个掩码: 关键点怎么把a^b分成两部分,方案：可以通过diff最后一个1区分
class Solution {
    public int[] singleNumber(int[] nums) {
        int bitmask = 0;
        for (int n : nums)
            bitmask ^= n;
        
        // 用这个bitmask as a marker to separate x and y.
        int diff = bitmask & (-bitmask); // isolate the rightmost 1-bit
        int[] res = new int[2];
        
        for (int num : nums){
            if ((num & diff) == 0)  //如果最右侧位1的和当前数字不同
                res[0] ^= num;
            else
                res[1] ^= num;
        }
        return res;
    }
}



// 191. Number of 1 Bits
// 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’  的个数（也被称为汉明重量）。
// 位运算：移除最后一个 1 : a = n & (n-1)
public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {// isolate the rightmost 1-bit
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}



// 338. Counting Bits
// 给定一个非负整数num。对于  0 ≤ i ≤ num范围中的每个数字i，计算其二进制数中的1的数目并将它们作为数组返回。
// 移除最后一个 1 ： a = n & (n-1)
class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];   // 包括0
        for (int i = 0; i <= num; i++)
            res[i] = count(i);
        return res;
    }
    
    private int count(int num){
        int count = 0;
        while (num != 0){
            num = num & (num - 1);
            count++;
        }
        return count;
    }
}

// 190. Reverse Bits
// 颠倒给定的 32 位无符号整数的二进制位。
// 思路：依次颠倒即可
public class Solution {
    public int reverseBits(int n) {
        // 对于索引i处的位，reverse之后，其位置应该在(32-i)处
        // 检索整数最右边的位，可以用(n & 1)
        int ret = 0;
        for (int i = 0; i < 32; i++){   // i是power
            ret <<= 1;          //左移,这样可以给next right most bit from "n"留出位子
            ret = ret | (n & 1); // 把最后一位取出来，左移之后累加到结果中
            n >>= 1;             // 从左到右遍历n的位字符串 (即n = n>>>1)
        }
        return ret;
    }
}




// 201. Bitwise AND of Numbers Range
// 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        // 直接平移m和n，每次向右移一位，直到m和n相等，记录下所有平移的次数count，
        while (m != n){
            m >>= 1;
            n >>= 1;
            count++;
        }
        // 然后再把m左移count位即为最终结果
        return m << count;
    }
}







