// 解法一：标准BFS模板
class Solution {
    public int openLock(String[] deadends, String target) {
        String start = "0000";
        // 先把deadends转化为hashset，方便查找
        Set<String> dead = new HashSet<>();
        for (String d : deadends) 
            dead.add(d);
        if (dead.contains(start)) return -1;
        
        // BFS 模板
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        int steps = 0;
        
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s < size; s++){
                // 先检查本层的node
                String node = queue.poll();
                if(dead.contains(node)) {
                    size --;
                    continue;
                }
                if (node.equals(target)) return steps; // 到达！
                
                /* 四个转轮，每一个可以上下传动一格 */
                for (int i = 0; i < 4; i++){    //  每个转轮
                    for (int j = -1; j <= 1; j += 2){       // j = -1， +1
                        // 开始处理（不同组合），先把string转成array操作
                        char[] chars = node.toCharArray();
                        // 要修改的一位，先-‘0’成为int，再+（-1、+1）+10成为新的滚动后数字
                        // 再mode 10后 + ‘0’ 变回ascii 字符
                        chars[i] = (char)(((chars[i] - '0') + j + 10) % 10 + '0');
                       
                        //  目前新改造后的string,检查是否valid
                        String next = new String(chars);
                        if (dead.contains(next) || visited.contains(next)) continue;
                        
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            // 本层所有node都遍历完了，step++去遍历下一层
            steps++;
        }
        return -1;
    }
}
// faster than 72.28% of Java




// 优化解法二 ：Bidirectional BFS
class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> dead = new HashSet<>();
        // 先把deadends转化为hashset，方便查找
        for (String d : deadends)  dead.add(d);
        
        if (dead.contains("0000")) return -1;
        
        begin.add("0000");
        end.add(target);
        int steps = 0;
        
        // begin类似queue，每次把所有（这一层）node都处理完
        // 通过把访问过的都扔到dead去，省略了visited set
        while (!begin.isEmpty() && !end.isEmpty()){
            Set<String> temp = new HashSet<>();
            for(String s : begin) {
                if (end.contains(s)) return steps;  // 首尾重合，完成！
                if (dead.contains(s)) continue;     //本s是dead/已visited过，跳过
                dead.add(s);                        // 代表已visited过
                
                for (int i = 0; i < 4; i++){                //  每个转轮
                    for (int j = -1; j <= 1; j += 2){       // j = -1， +1
                        // 开始处理（不同组合），先把string转成array操作
                        char[] chars = s.toCharArray();
                        // 要修改的一位，先-‘0’成为int，再+（-1、+1）+10成为新的滚动后数字
                        // 再mode 10后 + ‘0’ 变回ascii 字符
                        chars[i] = (char)(((chars[i] - '0') + j + 10) % 10 + '0');
                       
                        //  目前新改造后的string,检查是否valid
                        String next = new String(chars);
                        if (!dead.contains(next))
                            temp.add(next);
                    }
                }
            }
            // 这一层处理完毕，begin处理完了，下一轮去遍历end
            // temp此时存有所有下一层需要遍历的node
            steps ++;
            begin = end;
            end = temp;
        }
        return -1;
    }
}
// faster than 98.53% of Java







