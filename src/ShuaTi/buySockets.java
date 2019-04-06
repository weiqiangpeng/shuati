package ShuaTi;

public class buySockets {
	public int maxProfit(int[] prices) {
        int max = 0;
        for(int i=1;i<prices.length;i++){
        	System.out.println("left:"+helper(prices, 0, i)+" right:"+helper(prices, i-1, prices.length));
            int val = helper(prices, 0, i) + helper(prices, i, prices.length);
            max = Math.max(max, val);
            System.out.println("max is:"+max);
        }
        return max;
    }
    
    public int helper(int[] a, int start, int end){
        int res = 0;
        int min = Integer.MAX_VALUE;
        if(start >= end)
            return res;
        for(int i=start;i<end;i++){
            if(a[i] < min){
                min = a[i];
            }
            res = Math.max(res, a[i]-min);
        }
        return res;
    }
    public static void main(String[] args) {
		int[] a = {3,3,5,0,0,3,1,4};
		buySockets s = new buySockets();
		System.out.println(s.maxProfit(a));
	}
    
    
}
