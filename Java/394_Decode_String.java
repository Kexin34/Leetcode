//k = k * 10 + ch - '0'; 
// If you have a case like 23[a] (as in with 2 or more numbers before the square bracket, 
// you may k = 2 in the first iteration and in the next iteration you make k = 2*10 + 3 
// so k will be 23 and so on)

// 3[a]2[bc]，当遇到第一个[，压入3，a加到cur里，遇到第一个]后，循环3次来把tmp压入到cur作为目前组装好的
// 当遇到第二个[, 压入2，压入现有cur到strStack，cur现在是空，append "bc"到cur，遇到第二个]后，cur = 之前组装好的
// 循环两次把tmp内容append到现有cur中

class Solution {
    public String decodeString(String s) {
        StringBuilder cur = new StringBuilder();
        Stack<Integer> intStack = new Stack();
        Stack<StringBuilder> strStack = new Stack();
        int k = 0;
        
        for (char ch : s.toCharArray()){
            if (Character.isDigit(ch))
                // ch是数字，k是之后[]里面要重复次数
                k = k * 10 + ch - '0';      // -'0' converting int from character.
            else if (ch == '['){
                intStack.push(k);
                // 把之前组装好的压入
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0; // reset
            }
            else if (ch == ']'){
                // 新的[]里面string放入tmp, cur变回原来建好的string，一个个往后面append
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k)
                    cur.append(tmp);
            }
            else
                cur.append(ch);
        }
        return cur.toString();
    }
}
// faster than 69.09% of Java
