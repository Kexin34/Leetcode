class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        if (cells == null || cells.length == 0 || N <= 0) return cells;
        boolean hasCycle = false;
        int StepInCycle = 0;
        HashSet<String> set = new HashSet<>();//用来检查有无重复cell state
        
        //开始simulation
        for (int i = 0 ; i < N; i++){
            int[] next = nextDay(cells);
            String key = Arrays.toString(next);
            if (!set.contains(key)){      //若是新的state，store cell state
                set.add(key);
                StepInCycle++;
            }else{                      //hit a cycle
                hasCycle = true;
                break;
            }
            cells = next;
        }
        
        // 如果有环，
        if (hasCycle){ 
            N = N % StepInCycle;
            for (int i = 0; i < N; i++)
                cells = nextDay(cells);
        }
        return cells;
    }
    
    public int[] nextDay(int[] cells){
        int[] tmp = new int[cells.length];
        // i要修改的range不包括首尾element，tmp的首尾ele默认已经是0了
        for (int i = 1; i < cells.length - 1; i++){
            tmp[i] = (cells[i-1] == cells[i+1]) ? 1 : 0;
        }
        return tmp;
    }
}

// Time: O(1) 