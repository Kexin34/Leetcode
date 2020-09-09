// 解法一：（基础）32位逐位颠倒
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
// faster than 99.87% of Java 




// 解法二：（优化）按照bytes来颠倒
public class Solution {
    Map<Byte, Integer> cache = new HashMap<>();
    
    public int reverseBits(int n) {
        int ret = 0;
        for (int i = 0; i < 4; i++){   
            ret <<= 8;          
            ret = ret | reverseByte((byte)(n & 0b11111111)); 
            n >>= 8;             
        }
        return ret;
    }
    
    // 子函数：reverse一个byte内的8个bits
    private int reverseByte(byte b){
        if (cache.containsKey(b)) return cache.get(b);
        int ret = 0;
        byte t = b;
        for (int i = 0; i < 8; i++){
            ret <<= 1;
            ret = ret | (t & 1);
            t >>= 1;
        }
        // 缓存 byte 对应的比特位翻转
        cache.put(b, ret);
        return ret;
    }
}
// faster than 99.87% of Java