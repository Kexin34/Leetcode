// Method 1: 使用 Integer.bitcount() 来统计 1 个的个数。
class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}


// Method 2: 对两个数进行异或^操作，位级表示不同的那一位为 1，统计有多少个 1 即可。
class Solution {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int cnt = 0;
        while(z != 0){
            if((z & 1) == 1)
                cnt++;
            z = z >> 1;
        }
        return cnt;
    }
}


// Method 3: 使用 z&(z-1) 去除 z 位级表示最低的那一位。
class Solution {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int cnt = 0;
        while(z != 0){
            z = z & (z - 1);//去除 z 位级表示最低的那一位
            cnt++;
        }
        return cnt;
    }
}