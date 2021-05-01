// hashmap 来存每个subdomain的count
// 1. 先找出count
// 2. 每个domain string会一次次substring到下一个. 所在位置，然后更新这个subdomain的count

class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (String str : cpdomains) {
            int i = str.indexOf(' ');
            int count = Integer.valueOf(str.substring(0, i));
            
            String domain = str.substring(i + 1);     
            while (true) {
                map.put(domain, map.getOrDefault(domain, 0) + count);
                int j = domain.indexOf('.');
                if (j == -1) break;   // 当已经找不到domain时结束循环
                domain = domain.substring(j + 1);
              }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            String str = entry.getValue() + " " + entry.getKey();
            res.add(str);
        }
        return res;
    }
}
// Runtime: 12 ms, faster than 91.68% of Java 

