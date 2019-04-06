package ShuaTi;

import java.util.ArrayList;
import java.util.Arrays;

public class nQueens {
	//n皇后问题：给定n，表示n个皇后，将其放置在一个n*n的棋盘上，使得任意两个皇后不能处于同一行、同一列、同一对角线上
	//打印出所有可能的结果
	public static ArrayList<ArrayList<String>> getNqueens(int n){
		ArrayList<ArrayList<String>> res = new ArrayList<>();
		if(n == 0)
			return res;
		//rc[i] = j 表示（i,j）处有一个皇后
//		int[] rc = new int[n];
		dfs(res, n, new int[n], 0);
		return res;
	}
	public static void dfs(ArrayList<ArrayList<String>> res, int cols, int[] rc, int row) {
		if(row == cols) {
			//cols==n,当行数也到了n的时候，表示可以获取这一次的结果了
			ArrayList<String> tmp = new ArrayList<>();
			//这里是遍历每一行，因为行数是和cols相等的
			for(int i=0;i<cols;i++) {
				char[] ch = new char[cols];
				Arrays.fill(ch, '*');
				//因为一行只能有一个皇后，这个皇后就是在rc[i]列，将其对应的位置置为‘Q’即可
				ch[rc[i]] = 'Q';
				tmp.add(new String(ch));
			}
			res.add(tmp);
			return;
		}
		
		//否则说明当前行还不到最后一行，还需深度搜索,遍历所有列，找到这一行（row）可以放置皇后的位置
		for(int c=0;c<cols;c++) {
			//判断当前位置（row,c）是否有效，无效的话跳出，寻找下一列
			if(!isValid(rc, row, c))
				continue;
			//有效的话就可以将皇后放置此列
			rc[row] = c;
			//接着找下一行
			dfs(res, cols, rc, row+1);
		}
		
	}
	
	public static boolean isValid(int[] rc, int row, int i) {
		//这里解释一下如何判断当前（row，i）位置上是否可以放一个皇后的思想
		//首先我们是要在第row行和第i列的位置上判断，那么我们要保证第i列上是没有皇后的，当然第row行不用考虑的，因为我们是要在该行放置，
		//之前是一定没有的，那么如何判断第i列上有没有皇后呢？只能从第0行到第row-1行(用j表示)，分别判断是不是存在，也即是判断rc[j] == i即可。
		//除此之外，还要判断主副对角线上是否存在，同理，若主对角线上存在皇后的话，rc[j] - i==j-row,若副对角线上存在皇后的话，j-row==i-rc[j]
		for(int j=0;j<row;j++) {
			if(rc[j] == i || j-row == rc[j]-i || j-row == i-rc[j])
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		ArrayList<ArrayList<String>> res = getNqueens(5);
		for(ArrayList<String> val:res) {
			for(String s:val)
				System.out.println(s);
			System.out.println();
		}
		System.out.println("共有"+res.size()+"种可能");
	}
}
