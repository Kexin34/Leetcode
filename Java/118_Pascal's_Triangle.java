class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        // base case
        if (numRows == 0) return res; 
        // first row
        res.add(new ArrayList<>());
        res.get(0).add(1);
        
        for (int i = 1; i < numRows; i++){
            ArrayList<Integer> curRow = new ArrayList<>();
            List<Integer> prev = res.get(i-1);
            
            for (int j = 0; j < i; j++){
                if (j >= 1)
                    curRow.add(prev.get(j - 1) + prev.get(j));
                else 
                    curRow.add(prev.get(j));        // first column
            }
            curRow.add(1);                          // last column
            res.add(curRow);
        }
        return res;
    }
}