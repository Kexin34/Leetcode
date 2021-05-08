// 将 equations 中的算式根据 == 和 != 分成两部分，先处理 == 算式，
// 使得他们通过相等关系各自勾结成门派；然后处理 != 算式，检查不等关系是否破坏了相等关系的连通性。
class Solution {
    public boolean equationsPossible(String[] equations) {
        // 26 个英文字母
        UF uf = new UF(26);
        // 先让相等的字母形成连通分量
        for(String eq : equations){
            if(eq.charAt(1) == '='){
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                uf.union(x - 'a', y - 'a');
            }
        }
        
        // 检查不等关系是否打破相等关系的连通性
        for(String eq : equations){
            if(eq.charAt(1) == '!'){
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                // 如果之前已经有连通关系，就是逻辑冲突
                if(uf.connected(x - 'a', y - 'a'))
                    return false;
            }
        }
        return true;
    }
}


class UF {
	// 记录连通分量
	private int count;
	// 节点 x 的节点是 parent[x]
	private int[] parent;
	// 新增一个数组记录树的“重量”, 小一些的树接到大一些的树下面
    private int[] size;

	/* 构造函数，n 为图的节点总数 */
	public UF(int n){
		// 一开始互不连通
		this.count = n;
		// 父节点指针初始指向自己
		parent = new int[n];
		// 最初每棵树只有一个节点, size重量应该初始化 1
		size = new int[n];

		for(int i = 0; i < n; i++){
			parent[i] = i;
			size[i] = 1;
		}
	}

    /* 将 p 和 q 连接 */
    public void union(int p, int q){
    	int rootP = find(p);
    	int rootQ = find(q);
    	if(rootP == rootQ)
 			return;

 		// 将两棵树合并为一棵, 小树接到大树下面，较平衡
 		if(size[rootP] > size[rootQ]){//Q的parent变成P
 			parent[rootQ] = rootP;
 			size[rootP] += size[rootQ];
 		}else{
 			parent[rootP] = rootQ;
 			size[rootQ] += size[rootP];
 		}
 		count--; // 两个分量合二为一
    }

    /* 返回某个节点 x 的根节点, 路径压缩过的写法 */
    private int find(int x){
    	// 根节点的 parent[x] == x
    	while(parent[x] != x){
    		// 进行路径压缩
        	parent[x] = parent[parent[x]];
        	x = parent[x];
    	}
    	return x;
    }

    /* 返回当前的连通分量个数 */
    public int count(){
    	return count;
    }

    /* 判断 p 和 q 是否连通 */
    public boolean connected(int p, int q){
    	int rootP = find(p);
    	int rootQ = find(q);
    	return rootP == rootQ;
    }
}
// Runtime: 2 ms, faster than 38.67% of Java