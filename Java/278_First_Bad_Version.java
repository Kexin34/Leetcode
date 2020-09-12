// 解法：二分搜索（找左边界-模板）
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 0, right = n;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid))
                right = mid - 1;
            else
                left = mid + 1;
        }
        
        if (isBadVersion(left)) 
            return left;
        else 
            return left - 1;
    }
}
// faster than 49.19% of Java



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