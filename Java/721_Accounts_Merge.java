// Solution: Union Find
// Use two hash map with union find class to solve the problem
// 1. mailToIndex: one to one mapping: mail string to its parent index mapping
// 2. disjointSet: one to many mapping: parent index to all emails that belong to same group mapping
//    来建立用户和其所有的邮箱之间的映射，也就是合并后的结果。

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        if (accounts.size() == 0) return result;
        Map<String, Integer> mailToIndex = new HashMap<>();
        Map<Integer, Set<String>> disjointSet = new HashMap<>();
        
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);
        
        // Step 1: traverse all emails except names, if we have not seen an email before
        // put it with its index into map.   Otherwise, union the email to its parent index.
        // 首先遍历每个账户和其中的所有邮箱，先将每个邮箱的 root/parent 映射为其自身
        for (int i = 0; i < n; i++){
            for (int j = 1; j < accounts.get(i).size(); j++){
                String curMail = accounts.get(i).get(j);
                if (mailToIndex.containsKey(curMail)){  
                    //若之前存在过，进行union它们的parent index
                    int preIndex = mailToIndex.get(curMail);
                    uf.union(preIndex, i);
                }else       // 若没见过，扔到mailToIndex哈希表，把当前人员index作为parent index
                    mailToIndex.put(curMail, i);   
            }
        }
        
        // Step 2: traverse every email list, find the parent of current list
        // index and put all emails into the set list that belongs to key of its parent index
        // 遍历每一个账号，首先对帐号的第一个邮箱调用 find 函数，得到其父串p，然后遍历之后的邮箱.
        // 将相同账号内的所有邮箱都链接起来了。
        for (int i = 0; i < n; i++){
            // find parent index of current list index in parent array
            int parentIndex = uf.find(i);   //  不同的i可能返回同样的parent index
            disjointSet.putIfAbsent(parentIndex, new HashSet<>());
            // 这个根用户名（parent）底下的所有邮箱
            Set<String> curSet = disjointSet.get(parentIndex);
            // 遍历原本账号i，把它所有email都扔到根用户名底下
            for (int j = 1; j < accounts.get(i).size(); j++)       //邮箱从[1]开始
                curSet.add(accounts.get(i).get(j));
            disjointSet.put(parentIndex, curSet);
        }
        
        // step 3: traverse keyset of disjoint set group, retrieve all emails from each parent 
        // index, and then SORT them, as well as adding the name at index 0 of every sublist
        for (int index : disjointSet.keySet()){
            List<String> curList = new ArrayList<>();
            if (disjointSet.containsKey(index))
                curList.addAll(disjointSet.get(index));
            Collections.sort(curList);
            //在开头添加用户名
            curList.add(0, accounts.get(index).get(0));
            result.add(curList);
        }
        return result;
    }
    
    class UnionFind {
        int size;
        int[] parent;
        public UnionFind(int size) {
            this.size = size;
            this.parent = new int[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public void union(int a, int b) {
            parent[find(a)] = parent[find(b)];
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
}
//  faster than 97.75% of Java

       