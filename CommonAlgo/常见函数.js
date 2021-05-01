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


// Sort(1)
// 把String[] words按照里面字符串长度，从小到大排序
Arrays.sort(words, new StringByLengthComparator());

private static class StringByLengthComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }
}
// Sort(2)
// 若把comparator写成一行（比较两个int[])
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));


// 遍历哈希表
Map<String, Object> map = ...;
for (String key : map.keySet()) {
    // ...
}
for (Object value : map.values()) {
    // ...
}
for (Map.Entry<String, Object> entry : map.entrySet()) {
    String key = entry.getKey();
    Object value = entry.getValue();
    // ...
}


// 把list<int[]>变成int[][] : 用list的toArray函数
return res.toArray(new int[res.size()][2]);
