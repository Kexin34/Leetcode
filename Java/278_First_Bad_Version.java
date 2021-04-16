// 解法：二分搜索（first of.....找左边界-模板）
// 时间复杂度O(logn):二分的时间复杂度
// 空间复杂度O(1)：无需额外空间
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid) == false)
                start = mid;
            else
                end = mid;
        }
        if (isBadVersion(start))
            return start;
        else
            return end;
        
    }
}
// Runtime: 12 ms, faster than 97.77% of Java



// 模板二
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 0, right = n;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid))
                right = mid;
            else
                left = mid + 1;
        }
        return right;
            
    }
}
// faster than 99.37% of Java 