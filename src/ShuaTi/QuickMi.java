package ShuaTi;

public class QuickMi {
	public static double poww(int base, int exp) {
		long ex = (long) Math.abs(exp);
		double res = 1.0;
		while(ex != 0) {
			//ex是经过绝对值之后的指数，对于的二进制位上如果是1的话就计算res，如果是0的话就不计算res,直接跳到计算base的累乘中
			if((ex & 1) != 0)
				res *= base;
			base *= base;
			ex >>= 1;
		}
		return exp >0?res:1/res;
	}
	
	public static void main(String[] args) {
		System.out.println(poww(3,-4));
	}
}
