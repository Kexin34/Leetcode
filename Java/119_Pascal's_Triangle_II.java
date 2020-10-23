class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> prev = new ArrayList<>();
        prev.add(1);
        if (rowIndex == 0) return prev;
        
        for (int i = 0; i <= rowIndex; i++){
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 1; j < i; j++){
                if (j >= 1)
                    cur.add(prev.get(j - 1) + prev.get(j));
                else
                    cur.add(prev.get(j));
            }
            cur.add(1);
            prev = cur;
        }
        return prev;
    }
}