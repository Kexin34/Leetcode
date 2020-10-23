
// 递归

class Solution {
    public int kthGrammar(int N, int K) {
        if (K == 1) return 0;       //最左元素永远是0
        if (K % 2 == 0)             // 当K是偶数时，其是右结点
            //右子结点和其父节点值相反
            return (kthGrammar(N - 1, K / 2) == 0) ? 1 : 0;
        else
            //左子结点和其父节点的值相同
            return (kthGrammar(N - 1, (K + 1) / 2) == 0) ? 0 : 1;
    }
}
