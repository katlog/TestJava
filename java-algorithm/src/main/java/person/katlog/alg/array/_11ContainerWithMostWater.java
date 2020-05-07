package person.katlog.alg.array;

public class _11ContainerWithMostWater {

    /**  暴力法：（注意  maxArea初始值 要设置为0） */
    class Solution {
        public int maxArea(int[] height) {
            int maxArea = 0;
            for(int i = 0; i<height.length -1 ; i++){
                for(int j = i+1 ; j<height.length; j++){
                    int area = (j-i)*Math.min(height[j],height[i]);
                    maxArea = maxArea>area?maxArea:area;
                }
            }
            return maxArea;
        }
    }

    class Solution1{
        public int maxArea(int[] height) {
            int maxArea = (height.length-1)*Math.min(height[0],height[height.length-1]);
            int i = 0,j = height.length -1;
            while( i < j){
                if(height[i]<height[j]){
                    i++;
                }else{
                    j--;
                }
                if (i < j && (i > 0 && height[i] > height[i - 1]) || (j < height.length - 1 && height[j] > height[j + 1])) {
                    maxArea = Math.max(maxArea, (j - i) * Math.min(height[j], height[i]));
                }

            }

            return maxArea;
        }
    }
}
