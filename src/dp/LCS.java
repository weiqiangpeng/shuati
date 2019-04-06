package dp;

public class LCS {
	public static StringBuilder sb = new StringBuilder();
	//最长公共子序列问题：给定两个序列X与Y，求XY的最长公共子序列。不需要相邻
	//例如：X={ABCBDAB},Y={BDCABA},那么XY的最长公共子序列为：BCBA或者BDAB
	//自底向上求解，根据递推公式：
	//c[i][j]表示长度为i的子序列x与长度为j的子序列y最长子序列的长度。
	//         _
	//        |  0              i=0,j=0      
	//c[i][j]=|  c[i-1,j-1]+1   i,j>0 && X[i]=Y[j]
	//        |_ max(c[i-1][j], c[i][j-1])   i,j>0 && X[i]!=Y[j]
	//     
	//返回最长公共子序列的长度
	public static int LCS(String x, String y){
		if(x.length() == 0 || y.length()==0)
			return 0;
		String[][] b = new String[x.length()+1][y.length()+1];
		int[][] c = new int[x.length()+1][y.length()+1];
		for (int i = 0; i < x.length()+1; i++) {
			c[i][0] = 0;
		}
		for (int i = 0; i < y.length()+1; i++) {
			c[0][i] = 0;
 		}
		//j与m表示长度，则j-1,m-1表示下标
		for(int j=1;j<x.length()+1;j++){
			for(int m=1;m<y.length()+1;m++){
				if(x.charAt(j-1) == y.charAt(m-1)){
					c[j][m] = 1+c[j-1][m-1];
					b[j][m] = "A";     //等于A说明此时是从左对角线过来的
				}
				else{
					if(c[j-1][m] > c[j][m-1]){
						c[j][m] = c[j-1][m];
						b[j][m] = "L"; //L表示是从左侧过来的
					}
					else{
						c[j][m] = c[j][m-1];
						b[j][m] = "U";    //U表示是从上面过来的
					}
				}
					
			}
		}
		return c[x.length()][y.length()];
	}
	//返回最长公共子序列的串，可以另外使用一个数组b来记录路径。
	public static String[][]  LCSII(String x, String y){
		String[][] b = new String[x.length()+1][y.length()+1];
		if(x.length() == 0 || y.length()==0)
			return b;
		int[][] c = new int[x.length()+1][y.length()+1];
		for (int i = 0; i < x.length()+1; i++) {
			c[i][0] = 0;
		}
		for (int i = 0; i < y.length()+1; i++) {
			c[0][i] = 0;
 		}
		//j与m表示长度，则j-1,m-1表示下标
		for(int j=1;j<x.length()+1;j++){
			for(int m=1;m<y.length()+1;m++){
				if(x.charAt(j-1) == y.charAt(m-1)){
					c[j][m] = 1+c[j-1][m-1];
					b[j][m] = "A";     //等于A说明此时是从左对角线过来的
				}
				else{
					if(c[j-1][m] > c[j][m-1]){
						c[j][m] = c[j-1][m];
						b[j][m] = "L"; //L表示是从左侧过来的
					}
					else{
						c[j][m] = c[j][m-1];
						b[j][m] = "U";    //U表示是从上面过来的
					}
				}
					
			}
		}
		return b;
	}
	public static String printLCS(String x, int i, int j, String[][] b){
		if(i == 0 || j == 0)
			return sb.toString();
		if(b[i][j] == "A"){
			sb.append(x.charAt(i-1));
			printLCS(x, i-1,j-1,b);
		}
		else if(b[i][j] == "U")
			printLCS(x, i-1,j,b);
		else
			printLCS(x, i,j-1,b);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String x = "ABCBDABC";
		String y = "BDCABAC";
		System.out.println(LCS(x,y));
		String[][] b = LCSII(x, y);
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(printLCS(x, x.length(), y.length(), b));
	}
}
