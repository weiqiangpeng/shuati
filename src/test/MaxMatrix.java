package test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
public class MaxMatrix {
	public static int maxMatrix(int[] height) {
		//height表示直方图高度，求最大矩形面积
		ArrayList<Integer> heights = new ArrayList<>();
		for(int i=0;i<height.length;i++) {
			heights.add(height[i]);
		}
		Stack<Integer> st = new Stack<>();
		heights.add(0);
		int res = 0;
		for(int i=0;i<heights.size();i++) {
			while(!st.isEmpty() && heights.get(st.peek()) >= heights.get(i)) {
				int cur = st.pop();
				res = Math.max(res, heights.get(cur)*(st.isEmpty()?i:(i-st.peek()-1)));
			}
			st.push(i);
		}
		return res;
	}
	
	public static int maxArea(int[][] matrix) {
		/**
		 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
		示例:
		输入:
		[
  		["1","0","1","0","0"],
  		["1","0","1","1","1"],
  		["1","1","1","1","1"],
  		["1","0","0","1","0"]
		]
		输出: 6
		 * 思路：将其转为直方图的求解，先从第一层开始，等于是求直方图[1,0,1,0,0]的最大面积
		 * 然后加入第二层，等于是求直方图[2,0,2,1,1]的最大面积，这个数组怎么得到的呢？遍历第二行每一个元素，如果为0，那么直接设为0，如果为1，则在之前的基础上加1
		 * 
		 * */
		if(matrix.length == 0)
			return 0;
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[] height = new int[cols];
		Arrays.fill(height, 0);
		int res = 0;
		for(int row=0;row<rows;row++) {
			for(int col=0;col<cols;col++) {
				height[col] = matrix[row][col]==0?0:height[col]+1;
			}
			res = Math.max(res, maxMatrix(height));
		}
		return res;
	}
	public static void main(String[] args) {
		int[] m = {1,2,3,1,1,1,1};
		System.out.println(maxMatrix(m));
		int[][] matrix = {{1,0,1,1,0},
						  {1,0,1,1,1},
						  {1,1,1,1,0},
						  {1,0,1,1,1}};
		System.out.println(maxArea(matrix));
	}
}
