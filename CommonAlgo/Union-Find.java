class UF {
	
	private int count;             // 记录连通分量
	private int[] parent;          // 节点 x 的根节点是 parent[x]
    private int[] size;            // 新增一个数组记录树的“重量”, 小一些的树接到大一些的树下面

	/* 构造函数，n 为图的节点总数 */
	public UF(int n){
		this.count = n;           // 一开始互不连通
		parent = new int[n];      // 父节点指针初始指向自己         
		size = new int[n];        // 最初每棵树只有一个节点, size重量应该初始化 1

		for(int i = 0; i < n; i++){
			parent[i] = i;
			size[i] = 1;
		}
	}

    /* union函数: 将 p 和 q 连接 */
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

    /* find函数: 返回某个节点 x 的根节点, 路径压缩过的写法 */
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

    /* connected函数：判断 p 和 q 是否连通 */
    public boolean connected(int p, int q){
    	int rootP = find(p);
    	int rootQ = find(q);
    	return rootP == rootQ;
    }
}

// find就能以 O(1) 的时间找到某一节点的根节点，
// 相应的，connected和union复杂度都下降为 O(1)。




