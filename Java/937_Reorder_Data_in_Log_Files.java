// digit-logs: original order
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (s1, s2) -> {
            // 我们需要的信息是分开的,先split：第一个dig#，和后面的words而已
            String[] split1 = s1.split(" ", 2);
            String[] split2 = s2.split(" ", 2);
            
            // 先检查是否是digit log：第一个word的开头(split[1]的第一个char）
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            
            // 比较1：如果都是letter log，比较words lexicographically(split[1])
            if (!isDigit1 && !isDigit2){
                int compare = split1[1].compareTo(split2[1]);
                // 如果tie，则比较identifier大小
                if (compare == 0)
                    return split1[0].compareTo(split2[0]);
                else 
                    return compare;
            }
            // 比较2：如果都是digit log，不改变顺序（原题要求）
            else if (isDigit1 && isDigit2)
                return 0; // return 0 表示sort不改变
            
            // 比较3 ： 1是let，2是dig，把1放在前面(1小，返回-1)
            else if (!isDigit1 && isDigit2)
                return -1;
            // 比较3 ：1是dig，2是letter，把2放在前面（1大，返回1）
            else
                return 1;
        });
        return logs;
    }
}
// faster than 77.71% of Java