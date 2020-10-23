// 暴力解，每次遇到0都把后面元素往后移一位
class Solution {
    public void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == 0){
                for (int j = arr.length - 2; j > i - 1 ; j--){
                    arr[j + 1] = arr[j];
                }
                i++;
            }
        }
    }
}
// faster than 5.28% of Java



// 解法（优化）
// First, go left to right and count how many shifts (sh) we can fit in our array.
// Then, go right to left and move items; if it's zero - duplicate it and decrement the shift.
class Solution {
    public void duplicateZeros(int[] arr) {
        int shift = 0;
        int i = 0;
        // 从左向右，数出总共需要shift的位数
        for (i = 0; shift + i < arr.length; i++){
            if (arr[i] == 0)
                shift++;
        }
        System.out.println(i);
        // 从右向左移动元素,i回到需要移动的那一位
        for (i = i - 1; shift > 0; --i){
            // i + sh can exceed the array size.  
            if (i + shift < arr.length)
                arr[i + shift] = arr[i];
            // 如果是0 - duplicate it and decrement the shift.
            if (arr[i] == 0){
                shift--;
                arr[i + shift] = arr[i];
            }
        }
    }
}
// faster than 94.12% of Java


