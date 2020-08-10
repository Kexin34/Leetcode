// E.g.
// input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
// subarray after step 1: [[7,0], [7,1]]
// subarray after step 2: [[7,0], [6,1], [7,1]]
// ...

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        //pick up the tallest guy first
        //when insert the next tall guy, just need to insert him into kth position
        //repeat until all people are inserted into list
        Arrays.sort(people, new Comparator<int[]>(){    
            @Override
            public int compare(int[] o1, int[]o2){
                //如果身高不等，按照height的从大到小排 -> o2[0] - o1[0]
                //如果身高相等，按照[rank]从小到大排 -> o1[1] - o2[1]
                return o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]; 
            }
        });
        // 问的是int[][]，我想把List of int[]添加完，再convert to Array
        List<int[]> res = new LinkedList<>();
        for (int[] cur : people)
            res.add(cur[1], cur); // insert him into kth position
        return res.toArray(new int[people.length][]);
    }
}
// faster than 86.31% of Java