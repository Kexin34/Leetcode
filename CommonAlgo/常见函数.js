// 把list转换层array
ans.stream().mapToInt(i -> i).toArray();


// 任意字母ch手动转换成upper case
(ch + 32 - 'a') % 32

//Java自带的判断字符Character.isLetterOrDigit，可写成
private boolean isAlphaNum(char ch){
    if (ch >= 'a' && ch <= 'z') return true;
    if (ch >='A' && ch <= 'Z') return true;
    if (ch >= '0' && ch <= '9') return true;
    return false;
}
