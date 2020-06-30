class Solution {
    // 记录反转操作序列
    LinkedList<Integer> res = new LinkedList<>();
    
    public List<Integer> pancakeSort(int[] A) {
        sort(A, A.length);
        return res;
    }

    // 递归函数
    void sort(int[] cakes, int n){
        if(n == 1) return;      //base case
        
        // 寻找最大饼的index
        int maxCake = 0;
        int maxCakeIndex = 0;
        for(int i = 0; i < n; i++){
            if(cakes[i] > maxCake){
                maxCakeIndex = i;
                maxCake = cakes[i];
            }
        }
        // 第一次翻转，将最大饼翻到最上面
        /*数组索引从 0 开始，而我们要返回的结果是从 1 开始算的。*/
        reverse(cakes, 0, maxCakeIndex);
        res.add(maxCakeIndex + 1);//把翻转k值存入返回列表
        
        // 第二次翻转，将最大饼翻到最下面
        reverse(cakes, 0, n - 1);
        res.add(n);
        // 递归调用
        sort(cakes, n - 1);
    }
    
    // 翻转列表，index范围是i到j
   void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }
}

//faster than 100.00% of Java
