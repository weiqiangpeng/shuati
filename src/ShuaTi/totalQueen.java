package ShuaTi;

public class totalQueen {
    static int count = 0;
    public static int totalNQueens(int n) {
        if(n==0)
            return 0;
        
        int[] rc = new int[n];
        dfs(rc, n, 0);
        return count;
    }
    
    public static void dfs(int[] rc, int n, int row){
        if(row == n){
            count+=1;
            return;
        }
        for(int i=0;i<n;i++){
            if(!isValid(rc, row, i))
                continue;
            rc[row] = i;
            dfs(rc, n, row+1);
        }
    }
    
    public static boolean isValid(int[] rc, int row, int c){
        for(int i=0;i<row;i++){
            if(rc[i]==c || i-row==rc[i]-c || i-row==c-rc[i])
                return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
    	for(int i=1;i<10;i++) {
    		System.out.println(totalNQueens(i));
    		count = 0;
    	}
	}
}
