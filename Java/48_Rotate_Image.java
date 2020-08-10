class Solution {
    public void rotate(int[][] matrix) {
        //旋转几层: n = 4转两层，thus layer = n / 2
        for (int layer = 0; layer < matrix.length / 2; ++layer){
        	rotateOneLayer(matrix, layer, matrix.length - layer * 2);
        }
    }

    private void rotateOneLayer(int[][] matrix, int offset, int size){
    	// one position in layer need rotate four time
    	for (int pos = 0; pos < size - 1; ++pos){
    		int temp = matrix[offset][offset + pos];
			matrix[offset][offset + pos] = matrix[offset + size - 1 - pos][offset];
    		matrix[offset + size - 1 - pos][offset] = 
    			matrix[offset + size - 1][offset + size - 1 - pos];
    		matrix[offset + size - 1][offset + size - 1 - pos] = 
    			matrix[offset + pos][offset + size - 1];
    		matrix[offset + pos][offset + size - 1] = temp;
    	}
    }
}
// 100%