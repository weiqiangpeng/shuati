package ShuaTi;

public class MatrixHasPath {
	//请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
	//每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
	//例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
	//因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
	
	public static boolean hasPath(char[] matrix, int rows, int cols, char[] ch) {
		if(matrix.length == 0 || ch.length == 0)
			return false;
		
		//从matrix中找出可能的起始点
		for(int row=0;row<rows;row++) {
			for(int col=0;col<cols;col++) {				
				if(matrix[cols*row+col] == ch[0]) {
					//默认都是false,表示未访问过
					boolean[][] isVisited = new boolean[rows][cols];
					isVisited[row][col] = true;
					if(helper(matrix, row, col, rows, cols, ch, isVisited, 1))
						return true;
				}
			}
		}
		return false;
	}
	
	public static boolean helper(char[] matrix, int row, int col, int rows, int cols, char[] ch, boolean[][] isVisited, int pos) {
		if(pos == ch.length)
			return true;
		for(int k=0;k<4;k++) {
			int newx = 0;
			int newy = 0;
			//表示四个方向
			switch (k) {
			case 0:
				newx = row+1;
				newy = col;
				break;
			case 1:
				newx = row-1;
				newy = col;
				break;
			case 2:
				newy = col+1;
				newx = row;
				break;
			case 3:
				newy = col-1;
				newx = row;
				break;
			default:
				break;
			}
			
			if(newx>=0 && newx<rows && newy>=0 && newy<cols && !isVisited[newx][newy] && matrix[newx*cols+newy]==ch[pos]) {
				System.out.println("满足点：" + newx + " " + newy);
				isVisited[newx][newy] = true;
				//四个方向只要有一个找到了出路，就直接返回true,
				if(helper(matrix, newx, newy, rows,cols,ch,isVisited,pos+1))
					return true;
				isVisited[newx][newy] = false;
			}
			System.out.println("不满足点：" + newx + " " +newy);
		}
		return false;
	}
	
	public static void main(String[] args) {
		/**
		 * matrix:            a b c e 
		 *                    s f c s
		 *                    a d e e
		 */
		char[] matrix = {'a','b','c','e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'};
		char[] ch1 = {'b','c','c','e','d'};
		char[] ch2 = {'a','b','c','d'};
		
		System.out.println(hasPath(matrix, 3, 4, ch1));
		System.out.println("----------------------------");
		System.out.println(hasPath(matrix, 3, 4, ch2));
	}
}
