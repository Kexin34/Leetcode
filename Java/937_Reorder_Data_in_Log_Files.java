// digit-logs: original order
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (s1, s2) -> {
            // produce two substrings, [1] is dig#, [2] is first word
            String[] split1 = s1.split(" ", 2);
            String[] split2 = s2.split(" ", 2);
            
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            
            if (!isDigit1 && !isDigit2){
                // both letter-logs. 
                // compare the first letter of letter-word
                int comp = split1[1].compareTo(split2[1]);
                // compare identifier if ties
                if (comp == 0) 
                    return split1[0].compareTo(split2[0]);
                else return comp;
            }
            else if (isDigit1 && isDigit2)
                // both digit-logs. So keep them in original order
                return 0; 
            else if (isDigit1 && !isDigit2)
                // first is digit, second is letter. bring letter to forward.
                return 1;
            else
                //first is letter, second is digit. keep them in this order.
                return -1; 
        });
        
        return logs;
    }
}
// faster than 77.71% of Java